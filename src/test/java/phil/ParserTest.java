package phil;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import phil.main.Parser;
import phil.model.TaskList;
import phil.model.Todo;
import phil.storage.Storage;


/**
 * Represents the Testing Class for Parser.
 */
public class ParserTest {

    /**
     * Tests the generateStringOutputFromInput method of the Parser class.
     */
    @Test
    public void generateStringOutputFromInput() {
        // Initialise with an empty TaskList
        TaskList taskList = new TaskList();
        TaskList taskListCompare = new TaskList();
        Parser parser = new Parser(taskList, new Storage("data", "phil.txt"));

        assertEquals(taskListCompare.addTask(new Todo("read a book")),
                     parser.generateStringOutputFromInput("todo read a book"));

        // Print list
        assertEquals(taskListCompare.toString(), parser.generateStringOutputFromInput("list"));
    }

}
