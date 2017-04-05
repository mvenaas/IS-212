import java.sql.Time;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by Erlend on 26.03.2017.
 */

public class Store extends Visual {
    private PriorityQueue<Long> line = new PriorityQueue<>();

    public void visitStore(Customer customer){
        Time ct = Time.valueOf("12:00:00");
        ct.setTime(super.time);
        System.out.println(ct + " : " + customer.name + " ankom butikken.");
        for(int i = 0; i <= customer.visitTime ; i++) {
            try { TimeUnit.MILLISECONDS.sleep(10); }
            catch(InterruptedException e) { System.out.println("Error"); }
        }

        goToQue(customer, this.time);

    }

    public void goToQue(Customer customer, long time){
        Time ct = Time.valueOf("12:00:00");
        ct.setTime(this.time);
        System.out.println(ct + " : " + customer.name + " Gikk til kassa.");
        line.add(time); // Add customer to que
        Random rnd = new Random();
        int quetime = rnd.nextInt(50);
        try { TimeUnit.MILLISECONDS.sleep(10 + quetime);
            }
        catch(InterruptedException e) { System.out.println("Error"); }
        line.remove(time);
        System.out.println("Folk i kø: " + line.size());
        leaveStore(customer);


    }
    public void leaveStore(Customer customer){
        Time ct = Time.valueOf("12:00:00");
        ct.setTime(this.time);
        System.out.println(ct + " : " + customer.name + " forlot butikken.");
        long visited = customer.arrivalTime;
        long visittime = this.time - visited;
        System.out.println(customer.name + " besøkte butikken i " + visittime / 10000 + " minutter.");
    }


}
