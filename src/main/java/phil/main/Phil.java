package phil.main;

import phil.exception.PhilException;
import phil.model.TaskList;
import phil.storage.Storage;

/**
 * Represents the Phil instance that stores the parser
 * and a function to get the response from parser based on user input.
 */
public class Phil {
    private Parser parser;

    /**
     * Sets up the Phil instance.
     */
    public Phil() {
        TaskList taskList;
        Storage storage = new Storage("data", "phil.txt");
        String loadMessage = "";
        try {
            taskList = storage.load();
            loadMessage += "Successfully loaded previous tasks.";
        } catch (Exception e) {
            taskList = new TaskList();
            loadMessage += "Error when loading tasks. Not loaded. \n" + e.getMessage();
        }
        this.parser = new Parser(taskList, storage);
    }

    /**
     * Return response from parser based on input.
     *
     * @param input String passed in as input.
     * @return String response to be returned by parser.
     */
    public String getResponse(String input) {
        // Assert parser is not null before accessing it.
        assert this.parser != null;

        try {
            return this.parser.generateStringOutputFromInput(input);
        } catch (PhilException e) {
            return e.getMessage();
        }
    }
}
