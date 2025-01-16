import java.util.ArrayList;
import java.util.List;

public class TaskList {
    List<Task> listOfTasks;
    public TaskList() {
        this.listOfTasks = new ArrayList<Task>();
    }

    public int getNumberOfTasks() {
        return listOfTasks.size();
    }

    public String addTask(Task task) {
        listOfTasks.add(task);
        return "Got it! I've added this task: \n" + task.toString()
                + "\nNow you have " + this.getNumberOfTasks() + " tasks in the list.";
    }

    public String deleteTask(int index) {
        String resultStr = "Noted. I've removed this task: \n" + listOfTasks.get(index - 1).toString();
        listOfTasks.remove(index - 1);
        return resultStr + "\nNow you have " + this.getNumberOfTasks() + " tasks in the list.";
    }

    public String markTaskAsDone(int index) {
        listOfTasks.get(index - 1).markDone();
        return "Nice! I've marked this task as done: \n" + listOfTasks.get(index - 1).toString();
    }

    public String markTaskAsNotDone(int index) {
        listOfTasks.get(index - 1).markNotDone();
        return "OK, I've marked this task as not done: \n" + listOfTasks.get(index - 1).toString();
    }

    public String toString() {
        StringBuilder listToPrint = new StringBuilder();
        listToPrint.append("Here are the tasks in your list: \n");
        for (int i = 0; i < listOfTasks.size(); i++) {
            listToPrint.append((i + 1)).append(". ").append(listOfTasks.get(i).toString()).append("\n");
        }
        return listToPrint.toString();
    }
}
