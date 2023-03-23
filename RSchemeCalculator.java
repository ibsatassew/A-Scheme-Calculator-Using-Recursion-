
import java.util.Scanner;
import java.util.Vector;

public class RSchemeCalculator {
    private static double evaluateExpression(int i, Vector<String> myList) {
        String item = myList.get(i);
        double result;
    
        if (item.equals("(")) {
            // Evaluate the expression inside the parenthesis recursively
            result = evaluateExpression(i + 1, myList);
        } else if (item.equals("+")) {
            // Evaluate the operands recursively and sum them up
            result = evaluateExpression(i + 1, myList);
            if (myList.get(i + 2).equals(")")) {
                return result;
            } else { 
                result += evaluateExpression(i + 2, myList);
            }
        } else if (item.equals("-")) {
            // Evaluate the operands recursively and subtract them from each other
            result = evaluateExpression(i + 1, myList);
            if (myList.get(i + 2).equals(")")) {
                return result;
            } else {
                result -= evaluateExpression(i + 2, myList);
            }
        } else if (item.equals("*")) {
            // Evaluate the operands recursively and multiply them together
            result = evaluateExpression(i + 1, myList);
            if (myList.get(i + 2).equals(")")) {
                return result;
            } else {
                result *= evaluateExpression(i + 2, myList);
            }
        } else if (item.equals("/")) {
            // Evaluate the operands recursively and divide the first operand by the rest of the operands
            result = evaluateExpression(i + 1, myList);
            if (myList.get(i + 2).equals(")")) {
                return result;
            } else {
                result /= evaluateExpression(i + 2, myList);
            }
        } else {
            // item is a number, so convert it to double
            result = Double.parseDouble(item);
        }
    
        // If there are more tokens to evaluate, recurse
        if (i + 3 < myList.size()) {
            result = evaluateExpression(i + 2, myList);
        }
    
        return result;
    }
public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    while (scanner.hasNextLine()) {
        String line = scanner.nextLine().trim();
        if (line.isEmpty()) {
            continue;
        }

        Vector<String> tokens = new Vector<>();
        for (String token : line.split("\\s+")) {
            tokens.add(token);
        }

        double result = evaluateExpression(0, tokens);
        System.out.println(result);
    }
}
}
