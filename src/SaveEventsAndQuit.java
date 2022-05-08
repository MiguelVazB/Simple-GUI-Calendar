import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class SaveEventsAndQuit extends JButton implements Serializable {

    private CalendarContentModel calendarContent;

    SaveEventsAndQuit(CalendarContentModel calendarContentModel){
        this.calendarContent = calendarContentModel;
        this.setText("Quit");

        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    FileOutputStream fileOutput = new FileOutputStream("events.txt");
                    ObjectOutputStream output = new ObjectOutputStream(fileOutput);
                    ArrayList<Day> eventsInCalendar = calendarContent.getCalendarEvents();
                    for (Day day : eventsInCalendar) {
                        output.writeObject(day);
                    }
                    fileOutput.close();
                    output.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                System.exit(0);
            }
        });
    }
}
