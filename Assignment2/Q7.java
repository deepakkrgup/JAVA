
abstract class Student
{
    protected String name;
    protected int rollNo;
    protected int marks;

    Student(String name, int rollNo, int marks)
    {
        this.name = name;
        this.rollNo = rollNo;
        this.marks = marks;
    }

    abstract double calculateGrade();

    void displayDetails()
    {
        System.out.println("Name    : " + name);
        System.out.println("Roll No : " + rollNo);
        System.out.println("Marks   : " + marks);
        System.out.println("Grade   : " + calculateGrade());
    }
}

interface SportsParticipant
{
    void playSport();
}

interface CulturalParticipant
{
    void performActivity();
}

class AllRounderStudent extends Student implements SportsParticipant, CulturalParticipant
{
    AllRounderStudent(String name, int rollNo, int marks)
    {
        super(name, rollNo, marks);
    }

    double calculateGrade()
    {
        if (marks >= 80)
            return 10.0;
        else if (marks >= 60)
            return 8.0;
        else if (marks >= 40)
            return 6.0;
        else
            return 0.0;
    }

    public void playSport()
    {
        System.out.println("All-rounder student is playing sports.");
    }

    public void performActivity()
    {
        System.out.println("All-rounder student is performing cultural activities.");
    }
}

public class Q7
{
    public static void main(String[] args)
    {
        AllRounderStudent s = new AllRounderStudent("Rohit", 401, 78);
        s.displayDetails();
        s.playSport();
        s.performActivity();
    }
}
