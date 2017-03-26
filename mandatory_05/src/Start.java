import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Erlend on 26.03.2017.
 */

public class Start {
    static Time ts = Time.valueOf("12:00:00"); // 32400000 - 61200000
    static long time = 32400000;
    static Store butikk = new Store();
    static boolean open = true;

    public static void timeTicks() {
        Timer t = new Timer();

        t.scheduleAtFixedRate(
                new TimerTask() {
                    public void run() {
                        time = time + 10000;
                    }
                },
                0,
                10);

        ts.setTime(time);
    }

    public static void main(String[] args) {
        Runnable t1 = new Runnable() {
            public void run() {
                timeTicks();
            }
        };
        Runnable t2 = new Runnable() {
            public void run() {
                int number = 1;
                while(open) {
                    new Customer("Man " + String.valueOf(number));
                    number++;
                    if (time > 61200000) {
                        open = false;
                        System.out.println("\n Butikken stenger!!!! \n");
                    }
                }
            }
        };

        Runnable t3 = new Runnable() {
            public void run() {
                int number = 1;
                while(open) {
                    new Customer("Woman " + String.valueOf(number));
                    number++;
                    if (time > 61200000) {
                        open = false;
                    }
                }
            }
        };

        new Thread(t1).start();
        new Thread(t2).start();
        new Thread(t3).start();

    }

}
