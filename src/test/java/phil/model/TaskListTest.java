package phil.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * Represents Testing Class for TaskList Class.
 */
public class TaskListTest {

    /**
     * Tests the addTask method of the TaskList class with a new TaskList.
     */
    @Test
    public void addTask_newTaskList_correctStringOutput() {
        TaskList TaskList = new TaskList();
        assertEquals("Got it! I've added this task: \n[T][ ] read book\nNow you have 1 tasks in the list.",
                TaskList.addTask(new Todo("read book")));
    }

    /**
     * Tests the deleteTask method of the TaskList class with a TaskList of one task.
     */
    @Test
    public void deleteTask_newTaskListWithOneTask_correctStringOutput() {
        TaskList TaskList = new TaskList();
        TaskList.addTask(new Todo("read book"));
        assertEquals("Noted. I've removed this task: \n[T][ ] read book\nNow you have 0 tasks in the list.",
                TaskList.deleteTask(1));
    }

    /**
     * Tests the toString method of the TaskList class with a TaskList of two tasks.
     */
    @Test
    public void toString_newTaskListWithTwoTasks_correctStringOutput() {
        TaskList TaskList = new TaskList();
        TaskList.addTask(new Todo("read book"));
        TaskList.addTask(new Todo("watch tv"));
        assertEquals("Here are the tasks in your list: \n1. [T][ ] read book\n2. [T][ ] watch tv\n",
                TaskList.toString());
    }

}
