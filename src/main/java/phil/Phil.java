package phil;

public class Phil {

    private Parser parser;

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

    public String getResponse(String input) {
        try {
            return this.parser.processInput(input);
        } catch (PhilException e) {
            return e.getMessage();
        }
    }
}
