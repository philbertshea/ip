package phil.main;

import phil.exception.CommandType;
import phil.exception.InvalidArgumentException;
import phil.exception.InvalidCommandException;
import phil.exception.PhilException;
import phil.model.Deadline;
import phil.model.Event;
import phil.model.TaskList;
import phil.model.Todo;
import phil.storage.Storage;

import java.util.Arrays;
import java.util.List;

/**
 * Represents the Parser which contains logic to process input strings passed in.
 */
public class Parser {
    private TaskList taskList;
    private Storage storage;

    /**
     * Sets up the Parser object.
     *
     * @param taskList list of tasks.
     * @param storage storage.
     */
    public Parser(TaskList taskList, Storage storage) {
        this.taskList = taskList;
        this.storage = storage;
    }

    /**
     * Checks whether the given string input is valid
     * for Mark, Unmark, Delete commands, requiring only one argument.
     *
     * @param inputArgs String list from input String, delimited by space.
     * @param numTasks number of tasks in task list.
     * @return boolean representing if input is invalid.
     */
    private boolean isInputInvalidForMarkUnmarkDelete(List<String> inputArgs, int numTasks) {
        boolean hasTwoArgs = inputArgs.size() == 2;
        boolean secondArgIsInteger = inputArgs.get(1).matches("\\d+");
        boolean secondArgIsValidTaskNumber = Integer.parseInt(inputArgs.get(1)) <= numTasks;
        return !hasTwoArgs || !secondArgIsInteger || !secondArgIsValidTaskNumber;
    }

    /**
     * Checks whether the given string input is valid
     * for Find, Todo, Event, Deadline commands.
     *
     * @param inputArgs String list from input String, delimited by space.
     * @param minimumNumberOfArgs minimum number of arguments required.
     * @param requiredArgs varArgs for required args to check.
     * @return boolean representing if input is invalid.
     */
    private boolean isInputInvalidForFindAndTaskCreation(List<String> inputArgs, int minimumNumberOfArgs, String... requiredArgs) {
        boolean hasMinNumberOfArgs = inputArgs.size() >= minimumNumberOfArgs;
        boolean hasRequiredArgs = true;
        for (String arg : requiredArgs) {
            hasRequiredArgs = hasRequiredArgs && inputArgs.contains(arg);
        }
        return !hasMinNumberOfArgs || !hasRequiredArgs;
    }

    /**
     * Takes in a String input, and returns the corresponding output based on the command.
     *
     * @param input input by user representing the command.
     * @return output to print if command is valid.
     * @throws PhilException to be thrown if command or arguments are not valid.
     */
    public String generateStringOutputFromInput(String input) throws PhilException {
        // Assert taskList and storage are initialised, before potential accesses.
        assert this.taskList != null && this.storage != null;

        List<String> inputArgs = Arrays.asList(input.split(" "));
        int numTasks = this.taskList.getNumberOfTasks();

        if (input.equals("bye")) {
            String output = "";
            try {
                this.storage.save(this.taskList);
            } catch (Exception e) {
                output += "Data not saved: " + e.getMessage() + "\n";
            }
            return output + "Bye. Hope to see you again soon.";
        } else if (input.equals("list")) {
            return this.taskList.toString();
        } else if (input.startsWith("delete")) {
            if (isInputInvalidForMarkUnmarkDelete(inputArgs, numTasks)) {
                throw new InvalidArgumentException(CommandType.DELETE_TASK, numTasks);
            } else {
                int taskToRemove = Integer.parseInt(input.split(" ")[1]);
                return this.taskList.deleteTask(taskToRemove);
            }
        } else if (input.startsWith("mark")) {
            if (isInputInvalidForMarkUnmarkDelete(inputArgs, numTasks)) {
                throw new InvalidArgumentException(CommandType.MARK_DONE, numTasks);
            } else {
                int taskToMark = Integer.parseInt(input.split(" ")[1]);
                return this.taskList.markTaskAsDone(taskToMark);
            }
        } else if (input.startsWith("unmark")) {
            if (isInputInvalidForMarkUnmarkDelete(inputArgs, numTasks)) {
                throw new InvalidArgumentException(CommandType.MARK_UNDONE, numTasks);
            } else {
                int taskToMark = Integer.parseInt(input.split(" ")[1]);
                return this.taskList.markTaskAsNotDone(taskToMark);
            }
        } else if (input.startsWith("todo")) {
            if (isInputInvalidForFindAndTaskCreation(inputArgs, 2)) {
                throw new InvalidArgumentException(CommandType.CREATE_TODO);
            } else {
                // Get the whole input except for the first word 'todo' as the task description
                String description = String.join(" ", inputArgs.subList(1, inputArgs.size()));
                return this.taskList.addTask(new Todo(description));
            }
        } else if (input.startsWith("deadline")) {
            if (isInputInvalidForFindAndTaskCreation(inputArgs, 4, "/by")) {
                throw new InvalidArgumentException(CommandType.CREATE_DEADLINE);
            } else {
                int byIndex = inputArgs.indexOf("/by");
                String description = String.join(" ", inputArgs.subList(1, byIndex));
                String byDate = String.join(" ", inputArgs.subList(byIndex + 1, inputArgs.size()));
                return this.taskList.addTask(new Deadline(description, byDate));
            }
        } else if (input.startsWith("event")) {
            if (isInputInvalidForFindAndTaskCreation(inputArgs, 6, "/from", "/to")) {
                throw new InvalidArgumentException(CommandType.CREATE_EVENT);
            } else {
                int fromIndex = inputArgs.indexOf("/from");
                int toIndex = inputArgs.indexOf("/to");
                String description = String.join(" ", inputArgs.subList(1, fromIndex));
                String fromDate = String.join(" ", inputArgs.subList(fromIndex + 1, toIndex));
                String toDate = String.join(" ", inputArgs.subList(toIndex + 1, inputArgs.size()));
                return this.taskList.addTask(new Event(description, fromDate, toDate));
            }
        } else if (input.startsWith("find")) {
            if (isInputInvalidForFindAndTaskCreation(inputArgs, 2)) {
                throw new InvalidArgumentException(CommandType.CREATE_TODO);
            } else {
                // Get the whole input except for the first word 'find' as the task description
                String searchTerm = String.join(" ", inputArgs.subList(1, inputArgs.size()));
                return this.taskList.filteredTasksToString(searchTerm);
            }
        } else {
            throw new InvalidCommandException(input);
        }
    }
}
