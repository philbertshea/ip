public class Event extends Task {
    public String fromDate;
    public String toDate;

    public Event(String description, String fromDate, String toDate) {
        super(description);
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.fromDate + " to: " + this.toDate + ")";
    }

    @Override
    public String toLoadString() {
        return "Event - " + super.toLoadString() + " - " + this.fromDate + " - " + this.toDate;
    }
}
