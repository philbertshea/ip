package phil;

/**
 * Main Class of Phil.
 *
 */
public class Phil {

    /**
     * Main Method to be executed to initialise the chatbot.
     *
     */
    public static void main(String[] args) {

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
        Parser parser = new Parser(taskList, storage);
        Ui ui = new Ui(parser);
        ui.printOutput(loadMessage);

        ui.runBot();
    }
}
