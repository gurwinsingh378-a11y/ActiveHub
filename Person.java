import java.util.Calendar;

public class Person {

    private String fname;
    private String addr;
    private int bYear;

    public String getFname() {
        return fname;
    }

    public String getAddr() {
        return addr;
    }

    public void setFname(String f) {
        fname = f;
    }

    public void setAddr(String a) {
        addr = a;
    }

    public void setBYear(int yy) {
        bYear = yy;
    }

    public int getAge() {
        return countAge();
    }

    private int countAge() {
        Calendar now = Calendar.getInstance();
        return now.get(Calendar.YEAR) - bYear;
    }


// QUESTION (b) — ADD THIS HERE
    @Override
    public String toString() {
        return "Name: " + fname +
               "\nAddress: " + addr +
               "\nBirth Year: " + bYear +
               "\nAge: " + getAge();
    }
}

class TestPerson {
    public static void main(String[] args) {

        Person joe = new Person();

        joe.setFname("joe");
        joe.setAddr("Kuala Lumpur");
        joe.setBYear(2000);

        System.out.println(joe.getFname());
        System.out.println(joe.getAge());

        // QUESTION (b)
        System.out.println(joe.toString());
    }
}