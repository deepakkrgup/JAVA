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
}

class EngineeringStudent extends Student
{
    EngineeringStudent(String name, int rollNo, int marks)
    {
        super(name, rollNo, marks);
    }

    double calculateGrade()
    {
        return marks / 10.0;
    }
}

class MedicalStudent extends Student
{
    MedicalStudent(String name, int rollNo, int marks)
    {
        super(name, rollNo, marks);
    }

    double calculateGrade()
    {
        return marks / 12.5;
    }
}

class AllRounderStudent extends Student
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
}

public class Q8
{
    public static void main(String[] args)
    {
        Student s;

        s = new EngineeringStudent("Amit", 101, 80);
        System.out.println("Engineering Grade: " + s.calculateGrade());

        s = new MedicalStudent("Riya", 102, 75);
        System.out.println("Medical Grade: " + s.calculateGrade());

        s = new AllRounderStudent("Rohit", 103, 78);
        System.out.println("AllRounder Grade: " + s.calculateGrade());
    }
}
