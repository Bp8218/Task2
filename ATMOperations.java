package ATMPROJECT;

import java.util.Scanner;

public class ATMOperations {
    private Account currentAccount;
    private boolean isAuthenticated;

    public ATMOperations() {
        this.isAuthenticated = false;
    }

    public boolean authenticate(String enteredPin) {
        if (currentAccount != null && currentAccount.getPin().equals(enteredPin)) {
            isAuthenticated = true;
            return true;
        }
        return false;
    }

    public void linkAccount(Account account) {
        this.currentAccount = account;
    }

    public void displayMenu(Scanner scanner) {
        // The scanner object is now passed as a parameter
        while (isAuthenticated) {
            System.out.println("\nATM Menu:");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Change PIN");
            System.out.println("5. Logout");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    checkBalance();
                    break;
                case 2:
                    deposit(scanner);  // Pass scanner to deposit method
                    break;
                case 3:
                    withdraw(scanner); // Pass scanner to withdraw method
                    break;
                case 4:
                    changePin(scanner); // Pass scanner to changePin method
                    break;
                case 5:
                    logout();
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    private void checkBalance() {
        System.out.println("Your current balance is: $" + currentAccount.getBalance());
    }

    private void deposit(Scanner scanner) {
        System.out.print("Enter deposit amount: $");
        double amount = scanner.nextDouble();

        if (amount > 0) {
            currentAccount.deposit(amount);
            System.out.println("Deposit successful. Your new balance is: $" + currentAccount.getBalance());
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    private void withdraw(Scanner scanner) {
        System.out.print("Enter withdrawal amount: $");
        double amount = scanner.nextDouble();

        if (amount > 0) {
            if (currentAccount.withdraw(amount)) {
                System.out.println("Withdrawal successful. Your new balance is: $" + currentAccount.getBalance());
            } else {
                System.out.println("Insufficient balance or invalid amount.");
            }
        } else {
            System.out.println("Invalid withdrawal amount.");
        }
    }

    private void changePin(Scanner scanner) {
        System.out.print("Enter your new PIN: ");
        String newPin = scanner.nextLine();
        currentAccount.setPin(newPin);
        System.out.println("PIN successfully changed.");
    }

    private void logout() {
        isAuthenticated = false;
        System.out.println("You have been logged out.");
    }
}
