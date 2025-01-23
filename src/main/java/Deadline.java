public class Deadline extends Task {
    public String byDate;

    public Deadline(String description, String byDate) {
        super(description);
        this.byDate = byDate;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.byDate + ")";
    }

    @Override
    public String toLoadString() {
        return "Deadline - " + super.toLoadString() + " - " + this.byDate;
    }
}
