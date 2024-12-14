package ATMPROJECT;

import java.util.Scanner;

public class ATMMain {
    public static void main(String[] args) {
       
        Account account = new Account("123456", "1111", 1500.0);

        // Initialize ATM operations
        ATMOperations atmOperations = new ATMOperations();
        atmOperations.linkAccount(account);

        // Create a single scanner instance
        Scanner scanner = new Scanner(System.in);

        // Authenticate user
        System.out.print("Enter your PIN: ");
        String enteredPin = scanner.nextLine();

        if (atmOperations.authenticate(enteredPin)) {
            System.out.println("Authentication successful!");
            atmOperations.displayMenu(scanner);  // Pass scanner to ATMOperations
        } else {
            System.out.println("Authentication failed. Incorrect PIN.");
        }

        // Close the scanner at the end of the program
        scanner.close();
    }
}
