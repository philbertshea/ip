package phil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a Event task which has a description and a from-date and an end-date.
 *
 */
public class Event extends Task {
    private String fromDate;
    private String toDate;
    private LocalDateTime fromDateInDateTime;
    private LocalDateTime toDateInDateTime;
    private final static DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("MM/d/yyyy HHmm");
    private final static DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM d, yyyy h.mma");

    /**
     * Constructor for the Event object
     *
     * @param description String which describes the event task.
     * @param fromDate String which represents the start date of the task.
     * @param toDate String which represents the end date of the task.
     */
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

    /**
     * Returns the string representation of the event's from-date.
     * If a LocalDateTime was provided as the fromdate, it formats it based on the formatter provided.
     * Else, the String fromdate is returned.
     *
     * @param formatter formattr to format the LocalDateTime object representing the from date to.
     * @return String representation of the from date.
     */
    public String fromDateToString(DateTimeFormatter formatter) {
        if (this.fromDate != null) {
            return this.fromDate;
        } else {
            return this.fromDateInDateTime.format(formatter);
        }
    }

    /**
     * Returns the string representation of the event's to-date.
     * If a LocalDateTime was provided as the to-date, it formats it based on the formatter provided.
     * Else, the String todate is returned.
     *
     * @param formatter formattr to format the LocalDateTime object representing the to date to.
     * @return String representation of the to date.
     */
    public String toDateToString(DateTimeFormatter formatter) {
        if (this.toDate != null) {
            return this.toDate;
        } else {
            return this.toDateInDateTime.format(formatter);
        }
    }

    /**
     * Returns the string representation of Event object for printing when list is called.
     *
     * @return String representation of the Event object.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: "
                + this.fromDateToString(Event.outputFormatter) + " to: "
                + this.toDateToString(Event.outputFormatter) + ")";
    }

    /**
     * Returns String representation of Event object for use by Storage class.
     *
     * @return String representation of the Event object to be stored and loaded by Storage class.
     */
    @Override
    public String toLoadString() {
        return "Event - " + super.toLoadString() + " - "
                + this.fromDateToString(Event.inputFormatter) + " - "
                + this.toDateToString(Event.inputFormatter);
    }
}
