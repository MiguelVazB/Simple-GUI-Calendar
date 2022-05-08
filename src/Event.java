import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Event
 * @author Miguel Vazquez
 * @version 1.0 5/6/22
 *
 * Contains starting time of event, ending time, current date, and event title.
 */

public class Event implements Serializable {

    private final LocalTime startingTime;
    private final LocalTime endingTime;
    private final LocalDate date;
    private final String eventTitle;

    /**
     * @param date current day
     * @param startingTime starting time of event
     * @param endingTime   ending time of event
     * @param eventTitle    title of event
     */
    public Event(LocalDate date, LocalTime startingTime, LocalTime endingTime, String eventTitle) {
        this.date = date;
        this.eventTitle = eventTitle;
        this.startingTime = startingTime;
        this.endingTime = endingTime;
    }

    /**
     * @return starting time
     */
    public LocalTime getStartingTime() {
        return startingTime;
    }

    /**
     * @return ending time
     */
    public LocalTime getEndingTime() {
        return endingTime;
    }

    /**
     * @return current day
     */
    public LocalDate getDayOfEvent(){
        return this.date;
    }

    /**
     * @return title of event
     */
    public String getEventTitle(){
        return eventTitle;
    }
}
