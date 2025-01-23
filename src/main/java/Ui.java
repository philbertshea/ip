import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Ui {

    public Parser parser;

    public Ui(Parser parser) {
        this.parser = parser;
    }

    public void printOutput(String output) {
        System.out.println("\n-----------------------------------------\n");
        System.out.println(output);
        System.out.println("\n-----------------------------------------\n");
    }

    public void runBot() {
        Scanner sc = new Scanner(System.in);
        printOutput("Hello. I'm Phil.\nWhat can I do for you?" +
                "\nTip: say 'list' to get a list of actions to do, say 'bye' to end the conversation.");

        while (sc.hasNextLine()) {
            try {
                String input = sc.nextLine();
                printOutput(parser.processInput(input));
                if (input.equals("bye")) {
                    break;
                }
            } catch (PhilException e) {
                printOutput(e.getMessage());
            }
        }
    }
}
