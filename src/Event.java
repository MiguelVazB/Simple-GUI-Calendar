import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

public class Event implements Serializable {

    private final LocalTime startingTime;
    private final LocalTime endingTime;
    private final LocalDate date;
    private final String eventTitle;

    public Event(LocalDate date, LocalTime startingTime, LocalTime endingTime, String eventTitle) {
        this.date = date;
        this.eventTitle = eventTitle;
        this.startingTime = startingTime;
        this.endingTime = endingTime;
    }

    public LocalTime getStartingTime() {
        return startingTime;
    }

    public LocalTime getEndingTime() {
        return endingTime;
    }

    public LocalDate getDayOfEvent(){
        return this.date;
    }

    public String getEventTitle(){
        return eventTitle;
    }
}
