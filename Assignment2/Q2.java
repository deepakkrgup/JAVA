
abstract class Student
{
    private String name;
    private int rollNo;
    private int marks;

    Student(String name, int rollNo, int marks)
    {
        this.name = name;
        this.rollNo = rollNo;
        this.marks = marks;
    }

    public String getName() { return name; }
    public int getRollNo() { return rollNo; }
    public int getMarks() { return marks; }

    abstract double calculateGrade();

    void displayDetails()
    {
        System.out.println("Name    : " + getName());
        System.out.println("Roll No : " + getRollNo());
        System.out.println("Marks   : " + getMarks());
        System.out.println("Grade   : " + calculateGrade());
    }
}

public class Q2
{
    public static void main(String[] args)
    {
        Student s = new Student("Rahul", 101, 75)
        {
            double calculateGrade()
            {
                if (getMarks() >= 80)
                    return 10.0;
                else if (getMarks() >= 60)
                    return 8.0;
                else if (getMarks() >= 40)
                    return 6.0;
                else
                    return 0.0;
            }
        };

        s.displayDetails();
    }
}
