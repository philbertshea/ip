package phil.main;

import phil.exception.CommandType;
import phil.exception.InvalidArgumentException;
import phil.exception.InvalidCommandException;
import phil.exception.PhilException;
import phil.model.Note;
import phil.model.TaskList;
import phil.model.Todo;
import phil.model.Event;
import phil.model.Deadline;
import phil.model.NoteList;
import phil.storage.Storage;

import java.util.Arrays;
import java.util.List;

/**
 * Represents the Parser which contains logic to process input strings passed in.
 */
public class Parser {
    private TaskList taskList;
    private NoteList noteList;
    private Storage storage;

    /**
     * Sets up the Parser object.
     *
     * @param taskList list of tasks.
     * @param noteList list of notes.
     * @param storage storage.
     */
    public Parser(TaskList taskList, NoteList noteList, Storage storage) {
        this.taskList = taskList;
        this.storage = storage;
        this.noteList = noteList;
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
        if (inputArgs.size() < 2) {
            return true;
        }
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

    private boolean matchesFirstArg(List<String> inputArgs, String argToMatch) {
        return inputArgs.get(0).equals(argToMatch);
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
        int numNotes = this.noteList.getNumberOfNotes();

        if (input.equals("bye")) {
            String output = "";
            try {
                this.storage.save(this.taskList, this.noteList);
            } catch (Exception e) {
                output += "Data not saved: " + e.getMessage() + "\n";
            }
            return output + "Bye. Hope to see you again soon.";
        } else if (matchesFirstArg(inputArgs, "list")) {
            return this.taskList.toString();
        } else if (matchesFirstArg(inputArgs, "delete")) {
            if (isInputInvalidForMarkUnmarkDelete(inputArgs, numTasks)) {
                throw new InvalidArgumentException(CommandType.DELETE_TASK, numTasks);
            } else {
                int taskToRemove = Integer.parseInt(input.split(" ")[1]);
                return this.taskList.deleteTask(taskToRemove);
            }
        } else if (matchesFirstArg(inputArgs, "mark")) {
            if (isInputInvalidForMarkUnmarkDelete(inputArgs, numTasks)) {
                throw new InvalidArgumentException(CommandType.MARK_DONE, numTasks);
            } else {
                int taskToMark = Integer.parseInt(input.split(" ")[1]);
                return this.taskList.markTaskAsDone(taskToMark);
            }
        } else if (matchesFirstArg(inputArgs, "unmark")) {
            if (isInputInvalidForMarkUnmarkDelete(inputArgs, numTasks)) {
                throw new InvalidArgumentException(CommandType.MARK_UNDONE, numTasks);
            } else {
                int taskToMark = Integer.parseInt(input.split(" ")[1]);
                return this.taskList.markTaskAsNotDone(taskToMark);
            }
        } else if (matchesFirstArg(inputArgs, "todo")) {
            if (isInputInvalidForFindAndTaskCreation(inputArgs, 2)) {
                throw new InvalidArgumentException(CommandType.CREATE_TODO);
            } else {
                // Get the whole input except for the first word 'todo' as the task description
                String description = String.join(" ", inputArgs.subList(1, inputArgs.size()));
                return this.taskList.addTask(new Todo(description));
            }
        } else if (matchesFirstArg(inputArgs, "deadline")) {
            if (isInputInvalidForFindAndTaskCreation(inputArgs, 4, "/by")) {
                throw new InvalidArgumentException(CommandType.CREATE_DEADLINE);
            } else {
                int byIndex = inputArgs.indexOf("/by");
                String description = String.join(" ", inputArgs.subList(1, byIndex));
                String byDate = String.join(" ", inputArgs.subList(byIndex + 1, inputArgs.size()));
                return this.taskList.addTask(new Deadline(description, byDate));
            }
        } else if (matchesFirstArg(inputArgs, "event")) {
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
        } else if (matchesFirstArg(inputArgs, "find")) {
            if (isInputInvalidForFindAndTaskCreation(inputArgs, 2)) {
                throw new InvalidArgumentException(CommandType.FIND_TASK);
            } else {
                // Get the whole input except for the first word 'find' as the task description
                String searchTerm = String.join(" ", inputArgs.subList(1, inputArgs.size()));
                return this.taskList.filteredTasksToString(searchTerm);
            }
        } else if (matchesFirstArg(inputArgs, "new-note")) {
            if (isInputInvalidForFindAndTaskCreation(inputArgs, 2)) {
                throw new InvalidArgumentException(CommandType.CREATE_NOTE);
            } else {
                // Get the whole input except for the first word 'new-note' as the note
                String note = String.join(" ", inputArgs.subList(1, inputArgs.size()));
                return this.noteList.addNote(new Note(note));
            }
        } else if (matchesFirstArg(inputArgs, "delete-note")) {
            if (isInputInvalidForMarkUnmarkDelete(inputArgs, numNotes)) {
                throw new InvalidArgumentException(CommandType.DELETE_NOTE, numNotes);
            } else {
                // Get the whole input except for the first word 'new-note' as the note
                int noteToRemove = Integer.parseInt(input.split(" ")[1]);
                return this.noteList.deleteNote(noteToRemove);
            }
        } else if (input.equals("list-note")) {
            return this.noteList.toString();
        } else {
            throw new InvalidCommandException(input);
        }
    }
}
