import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    public String fromDate;
    public String toDate;
    public LocalDateTime fromDateInDateTime;
    public LocalDateTime toDateInDateTime;
    public static DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("MM/d/yyyy HHmm");
    public static DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM d, yyyy h.mma");


    public Event(String description, String fromDate, String toDate) {
        super(description);
        try {
            this.fromDateInDateTime = LocalDateTime.parse(fromDate, Event.inputFormatter);
            this.fromDate = null;
        } catch (DateTimeParseException e) {
            this.fromDate = fromDate;
            this.fromDateInDateTime = null;
        }
        try {
            this.toDateInDateTime = LocalDateTime.parse(toDate, Event.inputFormatter);
            this.toDate = null;
        } catch (DateTimeParseException e) {
            this.toDate = toDate;
            this.toDateInDateTime = null;
        }
    }

    public String fromDateToString(DateTimeFormatter formatter) {
        if (this.fromDate != null) {
            return this.fromDate;
        } else {
            return this.fromDateInDateTime.format(formatter);
        }
    }

    public String toDateToString(DateTimeFormatter formatter) {
        if (this.toDate != null) {
            return this.toDate;
        } else {
            return this.toDateInDateTime.format(formatter);
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.fromDateToString(Event.outputFormatter) + " to: " + this.toDateToString(Event.outputFormatter) + ")";
    }

    @Override
    public String toLoadString() {
        return "Event - " + super.toLoadString() + " - " + this.fromDateToString(Event.inputFormatter) + " - " + this.toDateToString(Event.inputFormatter);
    }
}
