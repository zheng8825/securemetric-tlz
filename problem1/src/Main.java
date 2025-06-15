import java.math.BigInteger;

public class Main {
    public static void main(String[] args) {
        BigInteger previous = BigInteger.ZERO;
        BigInteger current = BigInteger.ZERO;

        for (int n = 1; n <= 1000; n++) {
            if (n % 2 == 1) {
                // if odd position then add the position number
                current = previous.add(BigInteger.valueOf(n));
            } else {
                // if even position then multiply by the position number
                current = previous.multiply(BigInteger.valueOf(n));
            }
            previous = current;
        }

        System.out.printf("1000th member of the series is %s%n", current);
    }
}