public class TestOrder {

    public static void main(String[] args) {

        Order order = new Order();

        order.add();
        System.out.println("Status: " + order.getStatus());

        order.modify();
        System.out.println("Status: " + order.getStatus());

        order.confirm();
        System.out.println("Status: " + order.getStatus());

        order.cancel();
        System.out.println("Status: " + order.getStatus());
    }
}