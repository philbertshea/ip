package phil;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    List<Task> listOfTasks;
    public TaskList() {
        this.listOfTasks = new ArrayList<Task>();
    }

    public int getNumberOfTasks() {
        return this.listOfTasks.size();
    }

    public String addTask(Task task) {
        this.listOfTasks.add(task);
        return "Got it! I've added this task: \n" + task.toString()
                + "\nNow you have " + this.getNumberOfTasks() + " tasks in the list.";
    }

    public String deleteTask(int index) {
        String resultStr = "Noted. I've removed this task: \n" + this.listOfTasks.get(index - 1).toString();
        this.listOfTasks.remove(index - 1);
        return resultStr + "\nNow you have " + this.getNumberOfTasks() + " tasks in the list.";
    }

    public String markTaskAsDone(int index) {
        this.listOfTasks.get(index - 1).markDone();
        return "Nice! I've marked this task as done: \n" + this.listOfTasks.get(index - 1).toString();
    }

    public String markTaskAsNotDone(int index) {
        this.listOfTasks.get(index - 1).markNotDone();
        return "OK, I've marked this task as not done: \n" + this.listOfTasks.get(index - 1).toString();
    }

    public String toString() {
        StringBuilder listToPrint = new StringBuilder();
        listToPrint.append("Here are the tasks in your list: \n");
        for (int i = 0; i < this.listOfTasks.size(); i++) {
            listToPrint.append((i + 1)).append(". ").append(this.listOfTasks.get(i).toString()).append("\n");
        }
        return listToPrint.toString();
    }

    public List<Task> getListOfTasks() {
        return this.listOfTasks;
    }

    public String filteredTasksToString(String searchTerm) {
        StringBuilder listToPrint = new StringBuilder();
        listToPrint.append("Here are the matching tasks in your list: \n");
        int count = 0;
        for (int i = 0; i < this.listOfTasks.size(); i++) {
            Task task = this.listOfTasks.get(i);
            if (task.descContains(searchTerm)) {
                count = count + 1;
                listToPrint.append(count).append(". ").append(task.toString()).append("\n");
            }
        }
        return listToPrint.toString();
    }
}
