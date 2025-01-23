package phil;

/**
 * Exception representing invalid commands passed when creating tasks.
 * Where the first word of the input line is not a valid command.
 *
 */
public class InvalidCommandException extends PhilException {

    /**
     * Constructor for InvalidCommandException.
     *
     * @param invalidCommand String representing first word of the command passed by the user.
     */
    public InvalidCommandException(String invalidCommand) {
        super(invalidCommand + " is not a valid command. \n"
                + "These are the supported commands: \n"
                + "'list' to see the list of tasks.\n"
                + "'todo <some task>' to create a new phil.Todo task. \n"
                + "'deadline <some task> \\by <deadline-date>' to create a new phil.Deadline task. \n"
                + "'event <some task> \\from <start-date> \\end <end-date>' to create a new phil.Event task. \n"
                + "'delete <some task>' to delete a task. \n"
                + "'bye' to end the conversation. \n");
    }
}
