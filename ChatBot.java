import java.util.Scanner;
import java.util.regex.*;

public class ChatBot {
    private static double lastResult = 0.0;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to ChatBot! How can I assist you today?");

        while (true) {
            String userInput = scanner.nextLine().toLowerCase().trim();
            String botResponse = getBotResponse(userInput);
            System.out.println(botResponse);

            if (userInput.equals("bye")) {
                System.out.println("Goodbye! Have a great day.");
                break;
            }
        }

        scanner.close();
    }

    public static String getBotResponse(String userInput) {
        if (userInput.matches(".*\\b(\\+|\\-|\\*|\\/|\\^)\\b.*")) {
            try {
                Pattern pattern = Pattern.compile("(-?\\d+(?:\\.\\d+)?)\\s*([-+*/^])\\s*(-?\\d+(?:\\.\\d+)?)");
                Matcher matcher = pattern.matcher(userInput);
                if (matcher.find()) {
                    double num1 = Double.parseDouble(matcher.group(1));
                    String operator = matcher.group(2);
                    double num2 = Double.parseDouble(matcher.group(3));
                    double result;

                    switch (operator) {
                        case "+":
                            result = num1 + num2;
                            break;
                        case "-":
                            result = num1 - num2;
                            break;
                        case "*":
                            result = num1 * num2;
                            break;
                        case "/":
                            if (num2 == 0) {
                                return "Division by zero is not allowed.";
                            }
                            result = num1 / num2;
                            break;
                        case "^":
                            result = Math.pow(num1, num2);
                            break;
                        default:
                            return "I'm sorry, I didn't understand that operation.";
                    }

                    lastResult = result; 
                    return "The result is: " + result;
                } else {
                    return "Sorry, I couldn't perform the calculation. Please provide valid inputs.";
                }
            } catch (NumberFormatException | ArithmeticException e) {
                e.printStackTrace(); 
                return "Sorry, I couldn't perform the calculation. Please provide valid inputs.";
            }
        } else if (userInput.equals("last")) {
            return "The last result was: " + lastResult;
        }

        switch (userInput) {
            case "hello":
                return "Hi there! How can I help you?";
            case "how are you":
                return "I'm just a program, so I don't have feelings, but thanks for asking!";
            case "what's your name":
                return "I'm ChatBot, your virtual assistant.";
            case "thank you":
                return "You're welcome!";
            default:
                return "I'm sorry, I didn't understand that. Can you please rephrase?";
        }
    }
}
