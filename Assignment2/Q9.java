
interface SportsParticipant
{
    void playSport();
}

abstract class Student
{
    protected String name;

    Student(String name)
    {
        this.name = name;
    }
}

class AllRounderStudent extends Student implements SportsParticipant
{
    AllRounderStudent(String name)
    {
        super(name);
    }

    public void playSport()
    {
        System.out.println(name + " is playing sports.");
    }
}

public class Q9
{
    public static void main(String[] args)
    {
        SportsParticipant sp;
        sp = new AllRounderStudent("Rohit");
        sp.playSport();
    }
}
