import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

public class Phil {
    public static void printOutput(String output) {
        System.out.println("\n-----------------------------------------\n");
        System.out.println(output);
        System.out.println("\n-----------------------------------------\n");
    }

    public static void main(String[] args) {

        TaskList taskList = new TaskList();

        try {
            // Create folder 'data' and/or file 'phil.txt' if it does not exist
            Path dirPath = Paths.get("data");
            if (!Files.exists(dirPath)) {
                Files.createDirectory(dirPath);
            }
            Path dataPath = Paths.get("data", "phil.txt");
            if (!Files.exists(dataPath)) {
                Files.createFile(dataPath);
            }

            // Load data from 'phil.txt'
            List<String> lines = Files.readAllLines(dataPath);
            for (String line : lines) {
                String[] line_args = line.split(" - ");
                System.out.println(line_args[0]);
                if (line_args[0].equals("Todo")) {
                    taskList.addTask(new Todo(line_args[1]));
                } else if (line_args[0].equals("Deadline")) {
                    taskList.addTask(new Deadline(line_args[1], line_args[2]));
                } else if (line_args[0].equals("Event")) {
                    System.out.println("Hey");
                    taskList.addTask(new Event(line_args[1], line_args[2], line_args[3]));
                }
            }
        } catch (IOException e) {
            printOutput("IOException when accessing data history.");
        }

        Scanner sc = new Scanner(System.in);
        printOutput("Hello. I'm Phil.\nWhat can I do for you?" +
                    "\nTip: say 'list' to get a list of actions to do, say 'bye' to end the conversation.");

        while (sc.hasNextLine()) {
            try {
                String input = sc.nextLine();
                List<String> inputArgs = Arrays.asList(input.split(" "));
                int numTasks = taskList.getNumberOfTasks();
                if (input.equals("bye")) {

                    try {
                        Path dataPath = Paths.get("data", "phil.txt");
                        StringBuilder resultString = new StringBuilder();
                        for (Task task : taskList.getListOfTasks()) {
                            resultString.append(task.toLoadString()).append("\n");
                        }
                        Files.writeString(dataPath, resultString.toString());

                    } catch (IOException e) {
                        printOutput("IOException when saving data history.");
                    }
                    printOutput("Bye. Hope to see you again soon.");
                    break;
                } else if (input.equals("list")) {
                    printOutput(taskList.toString());
                } else if (input.startsWith("delete")) {
                    if (inputArgs.size() != 2 || !inputArgs.get(1).matches("\\d+") || Integer.parseInt(inputArgs.get(1)) > numTasks) {
                        throw new InvalidArgumentException(CommandType.DELETE_TASK, numTasks);
                    } else {
                        int taskToRemove = Integer.parseInt(input.split(" ")[1]);
                        printOutput(taskList.deleteTask(taskToRemove));
                    }
                } else if (input.startsWith("mark")) {
                    if (inputArgs.size() != 2 || !inputArgs.get(1).matches("\\d+") || Integer.parseInt(inputArgs.get(1)) > numTasks) {
                        throw new InvalidArgumentException(CommandType.MARK_DONE, numTasks);
                    } else {
                        int taskToMark = Integer.parseInt(input.split(" ")[1]);
                        printOutput(taskList.markTaskAsDone(taskToMark));
                    }
                } else if (input.startsWith("unmark")) {
                    if (inputArgs.size() != 2 || !inputArgs.get(1).matches("\\d+") || Integer.parseInt(inputArgs.get(1)) > numTasks) {
                        throw new InvalidArgumentException(CommandType.MARK_UNDONE, numTasks);
                    } else {
                        int taskToMark = Integer.parseInt(input.split(" ")[1]);
                        printOutput(taskList.markTaskAsNotDone(taskToMark));
                    }

                } else if (input.startsWith("todo")) {
                    if (inputArgs.size() < 2) {
                        throw new InvalidArgumentException(CommandType.CREATE_TODO);
                    } else {
                        // The whole input except for the first word 'todo' is the task description
                        String description = String.join(" ", inputArgs.subList(1, inputArgs.size()));
                        printOutput(taskList.addTask(new Todo(description)));
                    }
                } else if (input.startsWith("deadline")) {
                    if (inputArgs.size() < 2 || !inputArgs.contains("/by")) {
                        throw new InvalidArgumentException(CommandType.CREATE_DEADLINE);
                    } else {
                        int byIndex = inputArgs.indexOf("/by");
                        String description = String.join(" ", inputArgs.subList(1, byIndex));
                        String byDate = String.join(" ", inputArgs.subList(byIndex + 1, inputArgs.size()));
                        printOutput(taskList.addTask(new Deadline(description, byDate)));
                    }
                } else if (input.startsWith("event")) {
                    if (inputArgs.size() < 2 || !inputArgs.contains("/from") || !inputArgs.contains("/to")) {
                        throw new InvalidArgumentException(CommandType.CREATE_EVENT);
                    } else {
                        int fromIndex = inputArgs.indexOf("/from");
                        int toIndex = inputArgs.indexOf("/to");
                        String description = String.join(" ", inputArgs.subList(1, fromIndex));
                        String fromDate = String.join(" ", inputArgs.subList(fromIndex + 1, toIndex));
                        String toDate = String.join(" ", inputArgs.subList(toIndex + 1, inputArgs.size()));
                        printOutput(taskList.addTask(new Event(description, fromDate, toDate)));
                    }
                } else {
                    throw new InvalidCommandException(input);
                }
            } catch (PhilException e) {
                printOutput(e.getMessage());
            }
        }
    }
}
