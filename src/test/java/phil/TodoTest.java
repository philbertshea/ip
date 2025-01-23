package phil;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    
    @Test
    public void toStringTest(){
        Todo todo = new Todo("read a book");
        // to String test
        assertEquals("[T][ ] read a book", todo.toString());
        todo.markDone();
        assertEquals("[T][X] read a book", todo.toString());
        todo.markNotDone();
        assertEquals("[T][ ] read a book", todo.toString());
    }

    @Test
    public void toLoadStringTest(){
        Todo todo = new Todo("read a book");
        // to String test
        assertEquals("Todo -   - read a book", todo.toLoadString());
        todo.markDone();
        assertEquals("Todo - X - read a book", todo.toLoadString());
        todo.markNotDone();
        assertEquals("Todo -   - read a book", todo.toLoadString());
    }

}
