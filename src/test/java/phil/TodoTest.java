package phil;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import phil.model.Todo;


/**
 * Represents Testing Class for Todo Class.
 */
public class TodoTest {

    /**
     * Test the toString method of the Todo class.
     */
    @Test
    public void toString_newTodo_correctStringOutput() {
        Todo todo = new Todo("read a book");
        // to String test
        assertEquals("[T][ ] read a book", todo.toString());
        todo.markDone();
        assertEquals("[T][X] read a book", todo.toString());
        todo.markNotDone();
        assertEquals("[T][ ] read a book", todo.toString());
    }

    /**
     * Test the toLoadString method of the Todo class.
     */
    @Test
    public void toLoadString_newTodo_correctStringOutput() {
        Todo todo = new Todo("read a book");
        // to String test
        assertEquals("Todo -   - read a book", todo.toLoadString());
        todo.markDone();
        assertEquals("Todo - X - read a book", todo.toLoadString());
        todo.markNotDone();
        assertEquals("Todo -   - read a book", todo.toLoadString());
    }

}
