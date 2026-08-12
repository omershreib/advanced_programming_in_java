package mamans.maman04.src.q2;

import java.security.SecureRandom;

public class RandomFlightNumber {

    // Characters allowed for airline code
    private static final String LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final SecureRandom random = new SecureRandom();

    // Generate a random airline code (2 uppercase letters)
    private static String generateAirlineCode() {
        StringBuilder code = new StringBuilder(2);
        for (int i = 0; i < 2; i++) {
            code.append(LETTERS.charAt(random.nextInt(LETTERS.length())));
        }
        return code.toString();
    }

    // Generate a random flight number (3 to 4 digits)
    private static String generateFlightNumberDigits() {
        int digits = random.nextInt(2) + 3; // 3 or 4 digits
        int min = (int) Math.pow(10, digits - 1);
        int max = (int) Math.pow(10, digits) - 1;
        return String.valueOf(random.nextInt(max - min + 1) + min);
    }

    // Generate full flight number
    public static String generateFlightNumber() {
        return generateAirlineCode() + generateFlightNumberDigits();
    }

    public static void main(String[] args) {
        // Generate and print 5 random flight numbers
        for (int i = 0; i < 5; i++) {
            System.out.println(generateFlightNumber());
        }
    }
}
