import java.util.Scanner;

public class PasswordStrengthAnalyzer {
    public static void main(String args[]) {

        Scanner input = new Scanner(System.in);

        System.out.print("Enter a password: ");
        String password = input.nextLine();

        if (password.isBlank()) {
            System.out.println("Password cannot be empty.");
            input.close();
            return;
        }

        boolean hasUpper = false;
        boolean hasLower = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;

        int length = password.length();

        // Common passwords
        String[] commonPasswords = {
            "123456",
            "admin",
            "12345678",
            "123456789",
            "12345",
            "password",
            "Aa123456",
            "1234567890",
            "Pass@123",
            "admin123",
            "1234567",
            "123123",
            "111111",
            "1234567910",
            "P@ssw0rd",
            "Aa@123456",
            "admintelecom",
            "Admin@123",
            "112233"
        };

        for (String common : commonPasswords) {
            if (password.equalsIgnoreCase(common)) {
                System.out.println("\n--- Password Security Report ---\n");

                System.out.println("[FAIL] Common password detected");

                System.out.println("\nStrength Rating: Very Weak");

                System.out.println("\nRecommendations:");
                System.out.println("- Choose a less predictable password");
                System.out.println("- Avoid commonly used passwords");
                System.out.println("- Use a combination of letters, numbers, and special characters");

                System.out.println("\nAnalysis Complete.");
                input.close();
                return;
            }
        }

        //Analyze password
        for(int i = 0; i < length; i++){
            char ch = password.charAt(i);

            if (Character.isUpperCase(ch)) {
                hasUpper = true;
            } else if (Character.isLowerCase(ch)) {
                hasLower = true;
            } else if (Character.isDigit(ch)) {
                hasDigit = true;
            } else {
                hasSpecial = true;
            }
        }
        
        // Score password
        int score = 0;

        if (length >= 8)
            score++;
        if (hasUpper)
            score++;
        if (hasLower)
            score++;
        if (hasDigit)
            score++;
        if (hasSpecial)
            score++;

        // Report
        System.out.println("\n--- Password Security Report ---\n");

        printCheck(length >= 8,
                "Length Requirement (" + length + " characters)",
                "Password must be at least 8 characters");
        
        printCheck(hasUpper,
                "Uppercase Letter",
                "Missing uppercase letter");
                
        printCheck(hasLower,
                "Lowercase Letter",
                "Missing lowercase letter");

        printCheck(hasDigit,
                "Number",
                "Missing number");

        printCheck(hasSpecial,
                "Special Character",
                "Missing special character");

        System.out.println();

        if (score == 5) {
            System.out.println("Strength Rating: Strong");
        } else if (score >= 3) {
            System.out.println("Strength Rating: Moderate");
        } else {
            System.out.println("Strength Rating: Weak");
        }

        System.out.println("\nRecommendations:");

        if (length < 8)
            System.out.println("- Increase password length");
        if (!hasUpper)
            System.out.println("- Add an uppercase letter");
        if (!hasLower)
            System.out.println("- Add a lowercase letter");
        if (!hasDigit)
            System.out.println("- Add a number");
        if (!hasSpecial)
            System.out.println("- Add a special character");

        if (score == 5) {
            System.out.println("- No improvements needed");
        }

        System.out.println("\nAnalysis Complete.");

        input.close();
    }

    public static void printCheck(boolean passed, String passMessage, String failMessage) {
        if (passed) {
            System.out.println("[PASS] " + passMessage);
        } else {
            System.out.println("[FAIL] " + failMessage);
        }
    }
}
