
class Student
{
    static String universityName;

    static
    {
        universityName = "Lovely Professional University";
    }
    
    static void displayUniversityName()
    {
        System.out.println("University Name : "+universityName);
    }
}
public class Q3 {
    public static void main(String args[])
    {
        Student ob1 = new Student();
        Student ob2 = new Student();
        Student ob3 = new Student();
        Student.displayUniversityName();
        System.out.println("Object ob1 : "+ob1.universityName);
        System.out.println("Object ob2 : "+ob2.universityName);
        System.out.println("Object ob3 : "+ob3.universityName);
    }
}
