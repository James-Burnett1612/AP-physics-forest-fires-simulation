import java.util.concurrent.TimeUnit;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

//         ArrayList<Double> dimensionality = new ArrayList<Double>();
//         ArrayList<Double> burnPercent = new ArrayList<Double>();
//         ArrayList<Integer>  finalTrees = new ArrayList<Integer>();
//         // Forest f = new Forest(50, 50, 0.8);
//         System.out.print("\033\143");
//         // f.lineFire();
//         // f.pulseOfAMeteor(5);
//         for(int i = 0; i < 70; i++){
//             for(int j = 0; j < 70; j++){
//             double existPercent = (double)i / 80 + 0.1;
//             double burnPercentValue = (double)j / 80 + 0.1; 

//             Forest f = new Forest(50, 50, burnPercentValue , 1, existPercent );
//             f.startFire();
//         while(!f.doneBurning()){
// //            System.out.println(f.toString());
//             f.simulateFire();
//             f.percentCurrentlyBurning();
//             // try {
//             //     TimeUnit.SECONDS.sleep(0);
//             // } catch (InterruptedException e) {
//             //     // TODO Auto-generated catch block
//             //     e.printStackTrace();
//             // }
//             // System.out.print("\033\143");
    
//         }
//         // System.out.println(f.toString());
//         // f.information();
//             dimensionality.add(f.getDimensionality());
//             burnPercent.add(burnPercentValue);
//             finalTrees.add(f.getSurvivingTrees());
//             }
//         }

//         System.out.println(" dimensionality: " + dimensionality.toString());
//         System.out.println("a                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        ");
//         // System.out.println(" survive: " + survivalPercent.toString());
//         System.out.println("burnPercent value: " + burnPercent.toString());
//         System.out.println("a                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        ");
        
//         System.out.println(" finalTrees: " + finalTrees.toString());


        Forest f = new Forest(50, 50, 0.37, 1, 0.73);
        // Forest f = new Forest(50, 50, 0.8);
        System.out.print("\033\143");
        f.startFire();
        // f.lineFire();
        // f.pulseOfAMeteor(5);

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