import java.util.*;
import java.text.*;

public class currencyFormatter {

    public static void main(String[] args) {
        System.out.println("Enter Payment:");
        Scanner scanner = new Scanner(System.in);
        double payment = scanner.nextDouble();
        scanner.close();
        NumberFormat fIndia = NumberFormat.getCurrencyInstance(new Locale("en", "IN"));
        NumberFormat fUs = NumberFormat.getCurrencyInstance(Locale.US);
        NumberFormat fChina = NumberFormat.getCurrencyInstance(Locale.CHINA);
        NumberFormat fFrance = NumberFormat.getCurrencyInstance(Locale.FRANCE);
        // Write your code here.
        String us = fUs.format(payment);
        String india = fIndia.format(payment);
        String china = fChina.format(payment);
        String france = fFrance.format(payment);

        System.out.println("US: " + us);
        System.out.println("India: " + india);
        System.out.println("China: " + china);
        System.out.println("France: " + france);
    }
}