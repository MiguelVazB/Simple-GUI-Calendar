import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Day implements Serializable {

    private final ArrayList<Event> dayEvents;
    private LocalDate date;

    public Day(LocalDate currentDate) {
        this.date = currentDate;
        dayEvents = new ArrayList<>();
    }

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

    public LocalDate getDate() {
        return date;
    }

    public void displayEvents(){
        for(Event event: dayEvents){
            System.out.println(event.getDayOfEvent() +" "+ event.getEventTitle() +" "+ event.getStartingTime() +" "+ event.getEndingTime());
        }
    }

    public ArrayList<Event> getDayEvents() {
        return dayEvents;
    }
}
