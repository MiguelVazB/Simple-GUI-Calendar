import java.time.LocalDate;
import java.util.ArrayList;

public class Day {

    private ArrayList<Event> dayEvents;
    private LocalDate date;

    public Day() {
        dayEvents = new ArrayList<>();
    }

    public boolean addEventToDay(Event newEvent){
        if (!dayEvents.isEmpty()) {
            for (Event event : dayEvents) {
                if (newEvent.getStartingTime().isBefore(event.getStartingTime())
                        && newEvent.getEndingTime().isAfter(event.getEndingTime())){
                    return false;
                }
                if (newEvent.getStartingTime().isBefore(event.getEndingTime())
                        && newEvent.getEndingTime().isAfter(event.getStartingTime())){
                    return false;
                }
            }
        }
        this.date = newEvent.getDayOfEvent();
        this.dayEvents.add(newEvent);
        return true;
    }

    public LocalDate getDate() {
        return date;
    }
}
