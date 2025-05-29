import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        Forest f = new Forest(50, 50, 0.7, 1, 0.5);
        // Forest f = new Forest(50, 50, 0.8);
        System.out.print("\033\143");
        // f.startFire();
        // f.lineFire();
        f.pulseOfAMeteor(5);

        while(!f.doneBurning()){
            System.out.println(f.toString());
            f.simulateFire();
            f.percentCurrentlyBurning();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.print("\033\143");
    
        }
        System.out.println(f.toString());
        f.information();
    }
}