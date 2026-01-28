abstract class Student
{
    protected String name;
    protected int rollNo;

    Student(String name, int rollNo)
    {
        this.name = name;
        this.rollNo = rollNo;
    }

    abstract double calculateGrade();

    void displayDetails()
    {
        System.out.println("Name    : " + name);
        System.out.println("Roll No : " + rollNo);
        System.out.println("Grade   : " + calculateGrade());
    }
}

class EngineeringStudent extends Student
{
    private int internalMarks;
    private int externalMarks;

    EngineeringStudent(String name, int rollNo, int internalMarks, int externalMarks)
    {
        super(name, rollNo);
        this.internalMarks = internalMarks;
        this.externalMarks = externalMarks;
    }

    double calculateGrade()
    {
        int total = internalMarks + externalMarks;
        return total / 20.0;
    }
}

class MedicalStudent extends Student
{
    private int theoryMarks;
    private int practicalMarks;

    MedicalStudent(String name, int rollNo, int theoryMarks, int practicalMarks)
    {
        super(name, rollNo);
        this.theoryMarks = theoryMarks;
        this.practicalMarks = practicalMarks;
    }

    double calculateGrade()
    {
        int total = theoryMarks + practicalMarks;
        return total / 25.0;
    }
}

public class Q5
{
    public static void main(String[] args)
    {
        Student e = new EngineeringStudent("Amit", 201, 40, 50);
        Student m = new MedicalStudent("Riya", 301, 70, 60);

        e.displayDetails();
        m.displayDetails();
    }
}
