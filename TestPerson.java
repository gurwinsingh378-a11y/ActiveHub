public class TestPerson {
    public static void main(String[] args) {

        Person joe = new Person();

        joe.setFname("joe");
        joe.setBYear(2001);
        joe.setAddr("Bandar Sunway");

        System.out.println(joe.getFname());

        System.out.println(joe.getAge());

        System.out.println(joe.toString());

    }
}
