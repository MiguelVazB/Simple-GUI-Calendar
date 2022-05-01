import java.time.LocalTime;
import java.util.Date;

public class Event {

    private LocalTime startingTime;
    private LocalTime endingTime;
    private LocalTime date;
    private String eventTitle;

    public Event(LocalTime date, String eventTitle) {
        this.date = date;
        this.eventTitle = eventTitle;
    }
}
