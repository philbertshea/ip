package phil;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import phil.main.Parser;
import phil.model.TaskList;
import phil.model.Todo;
import phil.storage.Storage;


/**
 * Represents Testing Class for Parser.
 */
public class ParserTest {

    /**
     * Test the processInput method of the Parser class.
     */
    @Test
    public void processInput() {
        // Initialise with an empty TaskList
        TaskList taskList = new TaskList();
        TaskList taskListCompare = new TaskList();
        Parser parser = new Parser(taskList, new Storage("data", "phil.txt"));

        assertEquals(taskListCompare.addTask(new Todo("read a book")),
                     parser.processInput("todo read a book"));

        // Print list
        assertEquals(taskListCompare.toString(), parser.processInput("list"));
    }

}
