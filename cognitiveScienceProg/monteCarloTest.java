import java.util.Random;
public class monteCarloTest {
    public static void main(String[] args) {
        Random random = new Random();
        int trial = 1000000;
        double counter = 0;
        for (int i = 0; i < trial; i++) {
            double x = random.nextDouble();
            double y = random.nextDouble();
            if (x * x + y * y <= 1) {
                counter++;
            }
        }
        double pi = 4 * counter / trial;
        System.out.println("円周率は，" + pi + "かもね");
    }

}
