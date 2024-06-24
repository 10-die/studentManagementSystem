public class Student
{
    private String ID;
    private String Name;
    private String Mobile;
    //
    public Student()
    {

    }

    //
    public Student(String ID, String Name, String Mobile)
    {
        setID(ID);
        setName(Name);
        setMobile(Mobile);
    }

    //
    public void setID(String ID)
    {
        this.ID = ID;
    }
    public void setName(String Name)
    {
        this.Name = Name;
    }
    public void setMobile(String Mobile)
    {
        this.Mobile = Mobile;
    }

    //
    public String getID()
    {
        return this.ID;
    }
    public String getName()
    {
        return this.Name;
    }
    public String getMobile()
    {
        return this.Mobile;
    }
}