import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.*;
import java.util.*;

public class CalendarContentModel {

    private ArrayList<Day> calendarDays;
    private LocalDate currentDateDisplayed;
    private ArrayList<ChangeListener> listeners = new ArrayList<>();

    public CalendarContentModel(){
        calendarDays = new ArrayList<>();
        this.currentDateDisplayed = LocalDate.now();
//        generateEventsFromFile();
    }

    private void generateEventsFromFile(){
        String fileName = "events.txt";
        File eventsFile = new File(fileName);
        //content separated by ////~
        ArrayList<String> csContent = new ArrayList<>();
        Scanner input = null;
        try {
            input = new Scanner(eventsFile);
        } catch (FileNotFoundException e) {
            System.out.println("File not found!\nCreating empty calendar.....");
            try {
                createEventsFile(fileName);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        if (input==null){
            return;
        }
        while (input.hasNextLine()){
            csContent.add(input.nextLine());
        }
        input.close();

        for (String event: csContent){
            String[] eventInfo = event.split("////~");
        }

        //serialization
//        try {
//            FileOutputStream file = new FileOutputStream("file.ser");
//            ObjectOutputStream out = new ObjectOutputStream(file);
//            out.writeObject(new Event(LocalTime.now(), "hello"));
//            out.close();
//            file.close();
//        }catch (IOException ex){
//            System.out.println("naaaaaaah");
//        }
    }

    private void createEventsFile(String fileName) throws IOException {
        new FileOutputStream(fileName).close();
    }

    public void addEventToCalendar(Day day){
        if (day == null){
            return;
        }
        if (!this.calendarDays.isEmpty()) {
            for (Day dayContent : calendarDays){
                System.out.println(dayContent.getDate());
            }
        }
        this.calendarDays.add(day.getDate().getMonthValue(), day);
    }

    public LocalDate getCurrentDate(){
        return this.currentDateDisplayed;
    }

    public void setCurrentDate(LocalDate currentDate){
        this.currentDateDisplayed = currentDate;
        for (ChangeListener listener : listeners){
            listener.stateChanged(new ChangeEvent(currentDate));
        }
    }

    public void attachListener(ChangeListener listener){
        this.listeners.add(listener);
    }
}
