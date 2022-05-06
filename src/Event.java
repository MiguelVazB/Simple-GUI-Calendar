import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class Event {

    private LocalTime startingTime;
    private LocalTime endingTime;
    private LocalDate date;
    private String eventTitle;

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
}
