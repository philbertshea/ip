import java.util.Scanner;

public class Phil {
    public static void printOutput(String output) {
        System.out.println("\n-----------------------------------------\n");
        System.out.println(output);
        System.out.println("\n-----------------------------------------\n");
    }

    public static void main(String[] args) {
        String[] taskList = new String[100];
        Scanner sc = new Scanner(System.in);
        int taskCount = 0;
        printOutput("Hello. I'm Phil.\nWhat can I do for you?" +
                    "\nTip: say 'list' to get a list of actions to do, say 'bye' to end the conversation.");

        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                printOutput("Bye. Hope to see you again soon.");
                break;
            } else if (input.equals("list")) {
                StringBuilder listToPrint = new StringBuilder();
                for (int i = 0; i < taskCount; i++) {
                    listToPrint.append((i + 1)).append(". ").append(taskList[i]).append("\n");
                }
                printOutput(listToPrint.toString());

            } else {
                taskList[taskCount] = input;
                taskCount++;
                printOutput("added: " + input);
            }
        }
    }
}
