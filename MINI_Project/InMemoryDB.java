import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;


class InvalidCommandException extends RuntimeException {
    InvalidCommandException(String msg) { super(msg); }
}

class DatabaseStoppedException extends RuntimeException {
    DatabaseStoppedException() { super("Database is stopped"); }
}

class KeyNotFoundException extends RuntimeException {
    KeyNotFoundException(Integer key) { super("Key not found: " + key); }
}

class InvalidTTLException extends RuntimeException {
    InvalidTTLException() { super("TTL must be > 0"); }
}

enum CommandType {
    PUT, GET, DELETE, STOP, START, EXIT
}

class Command {
    CommandType type;
    Integer key;
    String rawValue;
    Long ttl;
}


class CommandParser {

    public static Command parse(String input) {
        if (input == null || input.trim().isEmpty())
            throw new InvalidCommandException("Empty command");

        String[] tokens = input.trim().split("\\s+");
        Command cmd = new Command();

        try {
            cmd.type = CommandType.valueOf(tokens[0].toUpperCase());
        } catch (Exception e) {
            throw new InvalidCommandException("Invalid command type");
        }

        switch (cmd.type) {
            case PUT:
                if (tokens.length < 3 || tokens.length > 4)
                    throw new InvalidCommandException("PUT syntax error");

                cmd.key = Integer.parseInt(tokens[1]);
                cmd.rawValue = tokens[2];

                if (tokens.length == 4) {
                    long ttl = Long.parseLong(tokens[3]);
                    if (ttl <= 0) throw new InvalidTTLException();
                    cmd.ttl = ttl;
                }
                break;

            case GET:
            case DELETE:
                if (tokens.length != 2)
                    throw new InvalidCommandException("Invalid syntax");
                cmd.key = Integer.parseInt(tokens[1]);
                break;

            case STOP:
            case START:
            case EXIT:
                break;
        }
        return cmd;
    }
}


class Entry<T> {
    T value;
    long expiryTime; // -1 = no expiry

    Entry(T value, long expiryTime) {
        this.value = value;
        this.expiryTime = expiryTime;
    }

    boolean isExpired() {
        return expiryTime != -1 && System.currentTimeMillis() > expiryTime;
    }
}

class InMemoryDatabase<T> {

    private final ConcurrentHashMap<Integer, Entry<T>> store = new ConcurrentHashMap<>();
    private volatile boolean running = true;

    public void start() {
        running = true;
        System.out.println("Database STARTED");
    }

    public void stop() {
        running = false;
        System.out.println("Database STOPPED");
    }

    private void checkRunning() {
        if (!running)
            throw new DatabaseStoppedException();
    }

    public void put(Integer key, T value, Long ttl) {
        checkRunning();
        long expiry = (ttl == null) ? -1 : System.currentTimeMillis() + ttl;
        store.put(key, new Entry<>(value, expiry));
    }

    public T get(Integer key) {
        Entry<T> entry = store.get(key);
        if (entry == null)
            throw new KeyNotFoundException(key);

        if (entry.isExpired()) {
            store.remove(key);
            throw new KeyNotFoundException(key);
        }
        return entry.value;
    }

    public void delete(Integer key) {
        checkRunning();
        if (store.remove(key) == null)
            throw new KeyNotFoundException(key);
    }

   
    public void startCleaner() {
        Thread cleaner = new Thread(() -> {
            while (true) {
                try {
                    for (Map.Entry<Integer, Entry<T>> e : store.entrySet()) {
                        if (e.getValue().isExpired()) {
                            store.remove(e.getKey());
                        }
                    }
                    Thread.sleep(1000);
                } catch (Exception ignored) {}
            }
        });
        cleaner.setDaemon(true);
        cleaner.start();
    }
}


class CommandExecutor implements Runnable {

    private final InMemoryDatabase<String> db;
    private final Scanner scanner;

    CommandExecutor(InMemoryDatabase<String> db) {
        this.db = db;
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        while (true) {
            try {
                String input = scanner.nextLine();
                Command cmd = CommandParser.parse(input);

                switch (cmd.type) {
                    case PUT:
                        db.put(cmd.key, cmd.rawValue, cmd.ttl);
                        System.out.println("OK");
                        break;

                    case GET:
                        System.out.println(db.get(cmd.key));
                        break;

                    case DELETE:
                        db.delete(cmd.key);
                        System.out.println("DELETED");
                        break;

                    case STOP:
                        db.stop();
                        break;

                    case START:
                        db.start();
                        break;

                    case EXIT:
                        System.exit(0);
                }
            } catch (Exception e) {
                System.out.println("ERROR: " + e.getMessage());
            }
        }
    }
}


class InMemoryDB {

    public static void main(String[] args) {

        InMemoryDatabase<String> db = new InMemoryDatabase<>();
        db.startCleaner();

        for (int i = 0; i < 2; i++) {
            new Thread(new CommandExecutor(db)).start();
        }
    }
}