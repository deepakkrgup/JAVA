class Student
{
    final int MAX_SEMESTER = 8;

    final void showRules()
    {
        System.out.println("Maximum semesters allowed: " + MAX_SEMESTER);
        System.out.println("Students must follow university rules.");
    }
}

class UGStudent extends Student
{
    // Error: Cannot override the final method from Student
    
    // void showRules()
    // {
    //     System.out.println("Modified rules");
    // }
    
}

public class Q4
{
    public static void main(String[] args)
    {
        Student s = new Student();
        s.showRules();
    }
}
