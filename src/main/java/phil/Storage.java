package phil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Storage representing the loading and storing of data to disk.
 *
 */
public class Storage {

    private Path dirPath;
    private Path dataPath;

    /**
     * Constructor of Storage object.
     *
     * @param dirPath directory path for storing data
     * @param dataPath file path for storing data
     */
    public Storage(String dirPath, String dataPath) {
        this.dirPath = Paths.get(dirPath);
        this.dataPath = Paths.get(dirPath, dataPath);
    }

    /**
     * Loads file specified at dirPath and datapath.
     *
     * @return task list obtained from loading file at datapath.
     * @throws IOException if directory or file cannot be opened or other IO issues.
     */
    public TaskList load() throws IOException {
        TaskList taskList = new TaskList();
        // Create folder 'data' and/or file 'phil.txt' if it does not exist
        if (!Files.exists(this.dirPath)) {
            Files.createDirectory(this.dirPath);
        }
        if (!Files.exists(this.dataPath)) {
            Files.createFile(this.dataPath);
        }

        // Load data from 'phil.txt'
        List<String> lines = Files.readAllLines(this.dataPath);
        for (String line : lines) {
            String[] line_args = line.split(" - ");
            if (line_args[0].equals("Todo")) {
                Task taskToAdd = new Todo(line_args[2]);
                if (line_args[1].equals("X")) {
                    taskToAdd.markDone();
                }
                taskList.addTask(taskToAdd);
            } else if (line_args[0].equals("Deadline")) {
                Task taskToAdd = new Deadline(line_args[2], line_args[3]);
                if (line_args[1].equals("X")) {
                    taskToAdd.markDone();
                }
                taskList.addTask(taskToAdd);
            } else if (line_args[0].equals("Event")) {
                Task taskToAdd = new Event(line_args[2], line_args[3], line_args[4]);
                if (line_args[1].equals("X")) {
                    taskToAdd.markDone();
                }
                taskList.addTask(taskToAdd);
            }
        }
        return taskList;
    }

    /**
     * Saves task list into a path
     *
     * @param taskList list of tasks to save.
     * @throws IOException thrown if there are IO errors when saving.
     */
    public void save(TaskList taskList) throws IOException {
        StringBuilder resultString = new StringBuilder();
        for (Task task : taskList.getListOfTasks()) {
            resultString.append(task.toLoadString()).append("\n");
        }
        Files.writeString(this.dataPath, resultString.toString());
    }
}
