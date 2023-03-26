package utility;

import java.time.LocalTime;

public class Time implements Runnable{
    private static LocalTime start;
    private static LocalTime now;
    public static int existTime;
    public static boolean addTank;
    @Override
    public void run(){
        start = LocalTime.now();
        while (true){
            try {
                Thread.sleep(1000);
                now = LocalTime.now();
                existTime = now.toSecondOfDay() - start.toSecondOfDay();
                if (existTime % 2 == 0)addTank = true;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
