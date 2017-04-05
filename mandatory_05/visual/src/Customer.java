import java.util.Random;

/**
 * Created by Erlend on 26.03.2017.
 */

public class Customer extends Visual {
    public String name;
    public int visitTime;
    static long arrivalTime;
    public Customer(String name){
        this.name = name;
        arrivalTime = super.time;
        Random rnd = new Random();
        this.visitTime = rnd.nextInt(100);
        super.butikk.visitStore(this);
        super.moveIcon(visitTime);

    }
}