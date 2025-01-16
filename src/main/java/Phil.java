import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class Phil {
    public static void printOutput(String output) {
        System.out.println("\n-----------------------------------------\n");
        System.out.println(output);
        System.out.println("\n-----------------------------------------\n");
    }

    public static void main(String[] args) {
        TaskList taskList = new TaskList();
        Scanner sc = new Scanner(System.in);
        printOutput("Hello. I'm Phil.\nWhat can I do for you?" +
                    "\nTip: say 'list' to get a list of actions to do, say 'bye' to end the conversation.");

        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            List<String> inputArgs = Arrays.asList(input.split(" "));
            if (input.equals("bye")) {
                printOutput("Bye. Hope to see you again soon.");
                break;
            } else if (input.equals("list")) {
                printOutput(taskList.toString());

            } else if (input.startsWith("mark")) {
                int numTasks = taskList.getNumberOfTasks();
                if (inputArgs.size() != 2 || !inputArgs.get(1).matches("\\d+") || Integer.parseInt(inputArgs.get(1)) > numTasks) {
                    printOutput("Invalid input. To mark a task as done, say 'mark X' where X is the task to mark as done."
                            + "\nMake sure X is a valid positive integer from 1 to " + numTasks + " (number of tasks). "
                            + "\nFor example, calling 'mark 2' marks the second task as done."
                            + "\nSay 'list' to see the tasks you have stored.");
                } else {
                    int taskToMark = Integer.parseInt(input.split(" ")[1]);
                    printOutput(taskList.markTaskAsDone(taskToMark));
                }

            } else if (input.startsWith("unmark")) {
                int numTasks = taskList.getNumberOfTasks();
                if (inputArgs.size() != 2 || !inputArgs.get(1).matches("\\d+") || Integer.parseInt(inputArgs.get(1)) > numTasks) {
                    printOutput("Invalid input. To mark a task as not done, say 'unmark X' where X is the task to mark as not done."
                            + "\nMake sure X is a valid positive integer from 1 to " + numTasks + " (number of tasks). "
                            + "\nFor example, calling 'unmark 2' marks the second task as not done."
                            + "\nSay 'list' to see the tasks you have stored.");
                } else {
                    int taskToMark = Integer.parseInt(input.split(" ")[1]);
                    printOutput(taskList.markTaskAsNotDone(taskToMark));
                }

            } else if (input.startsWith("todo")) {
                if (inputArgs.size() < 2) {
                    printOutput("Invalid input. A Todo task requires a description of minimally one word."
                                + "\n For example, 'todo read' creates the task 'read'.");
                } else {
                    // The whole input except for the first word 'todo' is the task description
                    String description = String.join(" ", inputArgs.subList(1, inputArgs.size()));
                    printOutput(taskList.addTask(new Todo(description)));
                }
            } else if (input.startsWith("deadline")) {
                if (inputArgs.size() < 2 || !inputArgs.contains("/by")) {
                    printOutput("Invalid input. A Deadline task requires a description AND a deadline, specified as a string after '/by'."
                            + "\n For example, 'deadline read /by Tuesday' creates the task 'read' with a deadline of 'Tuesday'.");
                } else {
                    int byIndex = inputArgs.indexOf("/by");
                    String description = String.join(" ", inputArgs.subList(1, byIndex));
                    String byDate = String.join(" ", inputArgs.subList(byIndex + 1, inputArgs.size()));
                    printOutput(taskList.addTask(new Deadline(description, byDate)));
                }
            } else if (input.startsWith("event")) {
                if (inputArgs.size() < 2 || !inputArgs.contains("/from") || !inputArgs.contains("/to")) {
                    printOutput("Invalid input. An Event task requires a description AND a from date, specified as a string after '/by'."
                            + "\n And a to date, specified as a string after '/to'."
                            + "\n For example, 'event reading /from Monday /to Tuesday' creates the event 'reading'"
                            + "\n from 'Monday' to 'Tuesday'.");
                } else {
                    int fromIndex = inputArgs.indexOf("/from");
                    int toIndex = inputArgs.indexOf("/to");
                    String description = String.join(" ", inputArgs.subList(1, fromIndex));
                    String fromDate = String.join(" ", inputArgs.subList(fromIndex + 1, toIndex));
                    String toDate = String.join(" ", inputArgs.subList(toIndex + 1, inputArgs.size()));
                    printOutput(taskList.addTask(new Event(description, fromDate, toDate)));
                }
            } else {
                printOutput("Invalid Input. These are the supported commands: \n"
                + "'list' to see the list of tasks.\n"
                + "'todo <some task>' to create a new Todo task. \n"
                + "'deadline <some task> \\by <deadline-date>' to create a new Deadline task. \n"
                + "'event <some task> \\from <start-date> \\end <end-date>' to create a new Event task. \n"
                + "'bye' to end the conversation. \n");
            }
        }
    }
}
