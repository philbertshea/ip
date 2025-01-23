import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    public String byDate;
    public LocalDateTime byDateInDateTime;
    public static DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("MM/d/yyyy HHmm");
    public static DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM d, yyyy h.mma");

    public Deadline(String description, String byDate) {
        super(description);
// Check if byDate is in datetime format, and parse it as datetime if so.
        try {
            LocalDateTime byDateInDateTime = LocalDateTime.parse(byDate, Deadline.inputFormatter);
            this.byDate = null;
            this.byDateInDateTime = byDateInDateTime;
        } catch (DateTimeParseException e) {
            System.out.println(byDate + " Not parsable");
            this.byDate = byDate;
            this.byDateInDateTime = null;
        }
    }

    public String byDateToString(DateTimeFormatter formatter) {
        if (this.byDate != null) {
            return this.byDate;
        } else {
            return this.byDateInDateTime.format(formatter);
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.byDateToString(Deadline.outputFormatter) + ")";
    }

    @Override
    public String toLoadString() {
        return "Deadline - " + super.toLoadString() + " - " + this.byDateToString(Deadline.inputFormatter);
    }

}
