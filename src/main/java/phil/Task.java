package phil;

/** Represents the abstract Task class.
 *
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /** Constructor for the Task object
     *
     * @param description String which describes the event task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /** Mark the task as done.
     *
     */
    public void markDone() {
        this.isDone = true;
    }

    /** Mark the task as not done.
     *
     */
    public void markNotDone() {
        this.isDone = false;
    }

    /** Get the status icon based on whether the task is done (X) or not ( ).
     *
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /** Returns the string representation of Task object for printing when list is called.
     *
     * @return String representation of the Task object.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /** Returns String representation of Task object for use by Storage class.
     *
     * @return String representation of the Task object to be stored and loaded by Storage class.
     */
    public String toLoadString() {
        return this.getStatusIcon() + " - " + this.description;
    }
}
