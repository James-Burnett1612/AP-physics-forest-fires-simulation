public class Main {
    public static void main(String[] args) {
        Forest f = new Forest(5, 5, 0.5, 1, 0.5);
        System.out.println(f.toString());
        f.startFire();
        System.out.println(f.toString());
        f.simulateFire();
        System.out.println(f.toString());


    }
}