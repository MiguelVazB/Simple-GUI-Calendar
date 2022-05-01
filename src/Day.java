import java.util.ArrayList;

public class Day {

    ArrayList<Event> dayEvents;

    public Day(ArrayList<Event> dayEvents) {
        this.dayEvents = dayEvents;
    }

//    public boolean checkIfEventConflicts(Event eventConflict){
//        for (Event event: dayEvents){
//            if (event.getDate().compareTo(eventConflict.getDate()) == 0){
//                if (eventConflict.getStartingTime().before(event.getEndingTime())){
//
//                }
//            }
//        }
//    }

    public ArrayList<Event> getDayEvents() {
        return dayEvents;
    }

    public void setDayEvents(ArrayList<Event> dayEvents) {
        this.dayEvents = dayEvents;
    }
}
