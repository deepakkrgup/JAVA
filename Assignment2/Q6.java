
interface SportsParticipant
{
    void playSport();
}

interface CulturalParticipant
{
    void performActivity();
}

class Student implements SportsParticipant, CulturalParticipant
{
    public void playSport()
    {
        System.out.println("Student is playing a sport.");
    }

    public void performActivity()
    {
        System.out.println("Student is performing a cultural activity.");
    }
}

public class Q6
{
    public static void main(String[] args)
    {
        Student s = new Student();
        s.playSport();
        s.performActivity();
    }
}
