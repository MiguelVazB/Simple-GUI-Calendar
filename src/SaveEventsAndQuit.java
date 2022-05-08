import javax.swing.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * SaveEventsAndQuit
 * @author Miguel Vazquez
 * @version 1.0 5/6/22
 *
 * Saves current calendar events to events.txt file using serialization and closes the app
 */

public class SaveEventsAndQuit extends JButton implements Serializable {

    private final CalendarContentModel calendarContent;

    /**
     * @param calendarContentModel events in calendar
     */
    SaveEventsAndQuit(CalendarContentModel calendarContentModel){
        this.calendarContent = calendarContentModel;
        this.setText("Quit");

        this.addActionListener(e -> {
            try {
                FileOutputStream fileOutput = new FileOutputStream("events.txt");
                ObjectOutputStream output = new ObjectOutputStream(fileOutput);
                ArrayList<Day> eventsInCalendar = calendarContent.getCalendarEvents();
                for (Day day : eventsInCalendar) {  //write all events to events.txt
                    output.writeObject(day);
                }
                fileOutput.close();
                output.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            System.exit(0); //close app
        });
    }
}
