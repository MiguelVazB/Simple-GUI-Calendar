import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Day
 * @author Miguel Vazquez
 * @version 1.0 5/6/22
 *
 * Contains events in day. Also, it checks for time conflicts
 */

public class Day implements Serializable {

    private final ArrayList<Event> dayEvents;
    private LocalDate date;

    /**
     * @param currentDate current date
     */
    public Day(LocalDate currentDate) {
        this.date = currentDate;
        dayEvents = new ArrayList<>();
    }

    /**
     * @param newEvent event to be added
     * @return false if conflicting time
     */
    public boolean addEventToDay(Event newEvent){
        if (!dayEvents.isEmpty()) {
            for (Event event : dayEvents) {
                if (event.getStartingTime() == newEvent.getStartingTime()
                        && event.getEndingTime() == newEvent.getEndingTime()){
                    return false;
                }
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

    /**
     * @return current date
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * @return return events in current day
     */
    public ArrayList<Event> getDayEvents() {
        return dayEvents;
    }
}
