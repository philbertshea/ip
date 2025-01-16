import java.util.Scanner;

public class Phil {
    public static void printOutput(String output) {
        System.out.println("\n-----------------------------------------\n");
        System.out.println(output);
        System.out.println("\n-----------------------------------------\n");
    }

    public static void main(String[] args) {
        Task[] taskList = new Task[100];
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
                listToPrint.append("Here are the tasks in your list: \n");
                for (int i = 0; i < taskCount; i++) {
                    listToPrint.append((i + 1)).append(". ").append(taskList[i].toString()).append("\n");
                }
                printOutput(listToPrint.toString());

            } else if (input.startsWith("mark")) {
                String[] inputArgs = input.split(" ");
                if (inputArgs.length != 2 || !inputArgs[1].matches("\\d+") || Integer.parseInt(inputArgs[1]) > taskCount) {
                    printOutput("Invalid input. To mark a task as done, say 'mark X' where X is the task to mark as done."
                            + "\nMake sure X is a valid positive integer from 1 to " + taskCount + " (number of tasks). "
                            + "\nFor example, calling 'mark 2' marks the second task as done."
                            + "\nSay 'list' to see the tasks you have stored.");
                } else {
                    int taskToMark = Integer.parseInt(input.split(" ")[1]);
                    taskList[taskToMark - 1].markDone();
                    printOutput("Nice! I've marked this task as done: \n" + taskList[taskToMark - 1].toString());
                }

            } else if (input.startsWith("unmark")) {
                String[] inputArgs = input.split(" ");
                if (inputArgs.length != 2 || !inputArgs[1].matches("\\d+") || Integer.parseInt(inputArgs[1]) > taskCount) {
                    printOutput("Invalid input. To mark a task as not done, say 'unmark X' where X is the task to mark as not done."
                            + "\nMake sure X is a valid positive integer from 1 to " + taskCount + " (number of tasks). "
                            + "\nFor example, calling 'unmark 2' marks the second task as not done."
                            + "\nSay 'list' to see the tasks you have stored.");
                } else {
                    int taskToMark = Integer.parseInt(input.split(" ")[1]);
                    taskList[taskToMark - 1].markNotDone();
                    printOutput("OK, I've marked this task as not done yet: \n" + taskList[taskToMark - 1].toString());
                }

            } else {
                taskList[taskCount] = new Task(input);
                taskCount++;
                printOutput("added: " + input);
            }
        }
    }
}
