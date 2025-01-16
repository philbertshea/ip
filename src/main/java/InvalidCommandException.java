public class InvalidCommandException extends PhilException {
    public InvalidCommandException(String invalidCommand) {
        super(invalidCommand + " is not a valid command. \n"
                + "These are the supported commands: \n"
                + "'list' to see the list of tasks.\n"
                + "'todo <some task>' to create a new Todo task. \n"
                + "'deadline <some task> \\by <deadline-date>' to create a new Deadline task. \n"
                + "'event <some task> \\from <start-date> \\end <end-date>' to create a new Event task. \n"
                + "'bye' to end the conversation. \n");
    }
}
