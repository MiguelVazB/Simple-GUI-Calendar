import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ConcurrentModificationException;

public class EventCreation extends JPanel{

    private final CalendarContentModel calendarContentModel;

    EventCreation(CalendarContentModel calendarContent) {
        this.calendarContentModel = calendarContent;
        EventCreationButton b = new EventCreationButton("Create");
        b.setPreferredSize(new Dimension(80,30));
        this.add(b);

        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JTextField title = new JTextField(30);
                JTextField startingTime = new JTextField(5);
                JTextField endingTime = new JTextField(5);

                JPanel inputPanel = new JPanel(new BorderLayout());

                JPanel dateStartingAndEndingPanel = new JPanel();

                JLabel currentDay = new JLabel(calendarContentModel.getCurrentDate().toString());
                currentDay.setBorder(new LineBorder(Color.GRAY));
                dateStartingAndEndingPanel.add(currentDay);

                dateStartingAndEndingPanel.add(new JLabel("Starting time:"));
                dateStartingAndEndingPanel.add(startingTime);

                dateStartingAndEndingPanel.add(new JLabel("Ending time:"));
                dateStartingAndEndingPanel.add(endingTime);

                JPanel titlePanel = new JPanel();
                titlePanel.add(new JLabel("Title:"));
                titlePanel.add(title);

                inputPanel.add(titlePanel, BorderLayout.NORTH);
                inputPanel.add(dateStartingAndEndingPanel, BorderLayout.CENTER);

                int option = JOptionPane.showConfirmDialog(null, inputPanel,
                        "Enter the starting, ending times, and a title", JOptionPane.OK_CANCEL_OPTION);

                if (option == 0) {
                    try {
                        LocalTime start;
                        boolean startingEndAm = startingTime.getText().endsWith("am");
                        String[] hoursMinutes;
                        String[] minutes;

                        hoursMinutes = startingTime.getText().split(":"); //hoursMinutes[0] is hour
                        if (startingEndAm) {
                            minutes = hoursMinutes[1].split("am");             //minutes[0] is minutes
                            if (Integer.parseInt(hoursMinutes[0]) == 12) {
                                start = LocalTime.of(0, Integer.parseInt(minutes[0]));
                            } else{
                                start = LocalTime.of(Integer.parseInt(hoursMinutes[0]), Integer.parseInt(minutes[0]));
                            }
                        } else {
                            minutes = hoursMinutes[1].split("pm");             //minutes[0] is minutes
                            if (Integer.parseInt(hoursMinutes[0]) == 12) {
                                start = LocalTime.of(12, Integer.parseInt(minutes[0]));
                            } else {
                                start = LocalTime.of(Integer.parseInt(hoursMinutes[0]) + 12, Integer.parseInt(minutes[0]));
                            }
                        }

                        LocalTime ending;
                        boolean endingEndAm = endingTime.getText().endsWith("am");
                        String[] hoursMinutesEnd;
                        String[] minutesEnd;

                        hoursMinutesEnd = endingTime.getText().split(":"); //hoursMinutes[0] is hour
                        if (endingEndAm) {
                            minutesEnd = hoursMinutesEnd[1].split("am");             //minutes[0] is minutes
                            if (Integer.parseInt(hoursMinutesEnd[0]) == 12){
                                ending = LocalTime.of(0, Integer.parseInt(minutesEnd[0]));
                            }else {
                                ending = LocalTime.of(Integer.parseInt(hoursMinutesEnd[0]), Integer.parseInt(minutesEnd[0]));
                            }
                        } else {
                            minutesEnd = hoursMinutesEnd[1].split("pm");
                            if (Integer.parseInt(hoursMinutesEnd[0]) == 12) {
                                ending = LocalTime.of(12, Integer.parseInt(minutesEnd[0]));
                            } else {
                                ending = LocalTime.of(Integer.parseInt(hoursMinutesEnd[0]) + 12, Integer.parseInt(minutesEnd[0]));
                            }
                        }
                        if (start.isAfter(ending)){
                            JOptionPane.showMessageDialog(null,"Starting time is after ending time!");
                            return;
                        }
                        LocalDate currentDayForEvent = calendarContentModel.getCurrentDate();
                        Event newEvent = new Event(currentDayForEvent, start, ending, title.getText());
                        calendarContentModel.addDayToCalendar(newEvent, currentDayForEvent);
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
            }
        });
    }
}

class EventCreationButton extends JButton {

    EventCreationButton(String create){
       super(create);
    }
}