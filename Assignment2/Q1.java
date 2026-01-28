
class Student
{
    private int roll_num;
    private String name;
    private int sem;

    public int getRoll_Num()
    {
        return roll_num;
    }
    public String getName()
    {
        return name;
    }
    public int getSem()
    {
        return sem;
    }

    public void setRoll_Num(int r)
    {
        this.roll_num = r;
    }
    public void setName(String n)
    {
        this.name = n;
    }

    public void setSem(int s)
    {
        this.sem = s;
    }
}
public class Q1 {
    public static void main(String args[])
    {
        Student s = new Student();

        s.setRoll_Num(47);
        s.setName("Deepak Kumar");
        s.setSem(6);

        System.out.println("Roll Number : "+s.getRoll_Num());
        System.out.println("Name : "+s.getName());
        System.out.println("Semester : "+s.getSem());
    }
}
