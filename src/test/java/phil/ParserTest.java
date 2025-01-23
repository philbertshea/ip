package phil;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    
    @Test
    public void processInputTest(){
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
