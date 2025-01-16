import java.util.Scanner;

public class Phil {
    public static void printOutput(String output) {
        System.out.println("\n-----------------------------------------\n");
        System.out.println(output);
        System.out.println("\n-----------------------------------------\n");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        printOutput("Hello. I'm Phil.\nWhat can I do for you?");
        int inputCount = 0;
        while (sc.hasNextLine()) {
            inputCount++;
            String input = sc.nextLine();
            if (input.equals("bye")) {
                printOutput("Bye. Hope to see you again soon.");
                break;
            } else {
                if (inputCount % 10 == 0) {
                    input += "\nTo end our conversation, say bye.";
                }
                printOutput(input);
            }
        }
    }
}
