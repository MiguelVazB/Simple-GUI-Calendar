import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.io.*;
import java.time.*;
import java.util.*;

public class CalendarContentModel{

    private final ArrayList<Day> calendarEvents;
    private LocalDate currentDate;
    private final ArrayList<ChangeListener> listeners = new ArrayList<>();
    private int elementsInCalendar = 0;

    public CalendarContentModel(){
        calendarEvents = new ArrayList<>();
        this.currentDate = LocalDate.now();
        generateEventsFromFile();
    }

    private void generateEventsFromFile() {

        FileInputStream fileInput;
        try {
            fileInput = new FileInputStream("events.txt");
        } catch (FileNotFoundException e) {
            System.out.println("events file not found!");
            return;
        }
        try {
            if (fileInput.available() == 0){
                System.out.println("empty events file!");
                return;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        boolean empty = false;
        ArrayList<Day> days = new ArrayList<>();
        try{
            ObjectInputStream inputStream = new ObjectInputStream(fileInput);
            while(!empty){
                Day existingDay = null;
                if (fileInput.available() != 0){
                    Object inputObject = inputStream.readObject();
                    Day day = (Day) inputObject;
                    if (!days.isEmpty()){
                        for (Day existingDayInDays : days){
                            if (existingDayInDays.getDate().isEqual(day.getDate())){
                                existingDay = existingDayInDays;
                            }
                        }
                        if (existingDay != null){
                            Event event = day.getDayEvents().get(0);
                            existingDay.addEventToDay(event);
                        }else{
                            Event eventNew = day.getDayEvents().get(0);
                            day.addEventToDay(eventNew);
                            days.add(day);
                            elementsInCalendar++;
                        }
                    }else{
                        days.add(day);
                    }
                }else{
                    fileInput.close();
                    inputStream.close();
                    empty = true;
                    elementsInCalendar++;
                }
            }
            calendarEvents.addAll(days);
        } catch (ClassNotFoundException | IOException exception) {
            exception.printStackTrace();
        }
    }

    private void createEventsFile(String fileName) throws IOException {
        new FileOutputStream(fileName).close();
    }

    public void addDayToCalendar(Event newEvent, LocalDate date){
        if (!calendarEvents.isEmpty()) {
            boolean noEventConflict;
            Day existingDay = null;
            for (Day day : calendarEvents) {
                if (day.getDate().isEqual(date)) {
                    existingDay = day;
                }
            }
            if (existingDay != null) {          //day found
                noEventConflict = existingDay.addEventToDay(newEvent);  //check if conflict
                if (!noEventConflict) {  //if conflicting event
                    JOptionPane.showMessageDialog(null, "Time Conflict with another event!");
                }
            } else {    //day not found
                Day newDay = new Day(date);
                newDay.addEventToDay(newEvent);
                calendarEvents.add(newDay);
                elementsInCalendar++;
            }
        }else{
            Day newDay = new Day(date);
            newDay.addEventToDay(newEvent);
            calendarEvents.add(newDay);
            elementsInCalendar++;
        }
    }

    public LocalDate getCurrentDate(){
        return this.currentDate;
    }

    public void setCurrentDate(LocalDate currentDate){
        this.currentDate = currentDate;
        for (ChangeListener listener : listeners){
            listener.stateChanged(new ChangeEvent(currentDate));
        }
    }

    public int getElementsInCalendar() {
        for(Day day: calendarEvents){
            day.displayEvents();
        }
        return elementsInCalendar;
    }

    public void attachListener(ChangeListener listener){
        this.listeners.add(listener);
    }

    public ArrayList<Day> getCalendarEvents() {
        return calendarEvents;
    }
}
