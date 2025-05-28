public class Main {
    public static void main(String[] args) {
        Forest f = new Forest(5, 5, 0.5, 2, 0.5);
        f.simulateFlame();
        System.out.println(f.toString());
    }
}