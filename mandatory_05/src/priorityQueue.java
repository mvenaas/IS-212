import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    private final int KASSETID = 1000; // 1000 millisekunder PR KUNDE I KASSEN.

    List<QueueSpot> queue = new ArrayList<>();

    public Customer getNextCustomerInQueue() {
        QueueSpot nextInLine = null;
        for (QueueSpot spot : queue) {
            if (nextInLine != null) {
                if (spot.time <  nextInLine.time) {
                    nextInLine = spot;
                }
            } else {
                nextInLine = spot;
            }
        }
        queue.remove(nextInLine);
        return nextInLine.customer;
    }


    private void customerWalksIn() {
        String[] names = {"Christian", "Erlend", "martin", "Hallgeir", "Even", "Sindre", "Moen", "Pelle", "Kåre", "Peter"};

        Customer customer = new Customer(
                names[new Random().nextInt(names.length)]
        );
        Long tidIButikken = new Random().nextLong();
        queue.add(new QueueSpot(customer, tidIButikken));
        System.out.println("Kunden " + customer.name + " kom inn i butikken");
    }

    public static void main(String[] args) throws InterruptedException {
        Main main = new Main();


        Runnable cashRegister = () -> {
            while (true) {
                try {
                    while (!main.queue.isEmpty()) {
                        System.out.println("Neste i kassen er : " + main.getNextCustomerInQueue().name);
                        Thread.sleep(main.KASSETID);
                    }

                    Thread.sleep(1000); // to get the queue to stack up again..
                }catch (Exception e) {
                    System.out.println(e);
                }
            }
        };

        Runnable customerEntersStore = () -> {
            while (true) {
                try {

                    main.customerWalksIn();
                    Thread.sleep(500); // to get the queue to stack up again..

                }catch (Exception e) {
                    System.out.println("Enter store error");
                    System.out.println(e);
                }
            }
        };

        Thread thread = new Thread(customerEntersStore);
        thread.start();
        thread = new Thread(cashRegister);
        thread.start();
    }

    public static class Customer {
        String name;

        public Customer (String name) {
            this.name = name;
        }
    }

    public static class QueueSpot{
        public Customer customer;
        public Long time;

        public QueueSpot(Customer customer, Long time) {
            this.customer = customer;
            this.time = time;
        }

    }
}
