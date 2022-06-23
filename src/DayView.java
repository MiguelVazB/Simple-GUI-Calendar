import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Locale;

/**
 * DayView
 * @author Miguel Vazquez
 * @version 1.0 5/6/22
 *
 * Displays current day and all the times and events in current day
 */

public class DayView extends JPanel implements ChangeListener {

    private final CalendarContentModel calendarContent;
    private LocalDate currentDay;
    private JPanel eventsPanel;
    //size of event times displayed each day
    private final Dimension sizeOfTimesOFDay = new Dimension(35,35);

    /**
     * @param calendarContent calendar events
     */
    public DayView(CalendarContentModel calendarContent) {
        this.setLayout(new BorderLayout());
        this.calendarContent = calendarContent;
        displayCurrentDay();
    }

    /**
     * Display current day with the structure of the events
     */
    private void displayCurrentDay() {
        currentDay = calendarContent.getCurrentDate();
        String formattedDay = currentDay.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH)
                + " " + currentDay.getMonthValue() + "/" + currentDay.getDayOfMonth();
        JLabel currentDayDisplay = new JLabel(formattedDay, SwingConstants.CENTER);

        JPanel eventsTimePanel = new JPanel(new GridLayout(24,1));
        JLabel eventTime;
        String timeOfDay = "am";
        int afterNoon = 0;

        JPanel eventsTitlePanel = new JPanel(new GridLayout(24,1));
        JLabel eventTitle;

        for(int i=0; i<24; i++){
            if (i == 12){
                timeOfDay = "pm";
            }
            else if (i >= 12){
                timeOfDay = "pm";
                afterNoon = 12;
            }
            //display structure of events in current day
            eventTime = new JLabel(i-afterNoon+" "+timeOfDay, SwingConstants.CENTER);
            eventTime.setBackground(Color.WHITE);
            eventTime.setOpaque(true);
            eventTime.setBorder(new LineBorder(Color.LIGHT_GRAY));

            eventTime.setPreferredSize(sizeOfTimesOFDay);
            eventsTimePanel.add(eventTime);

            JPanel twoRows = new JPanel();
            twoRows.setLayout(new GridLayout(2,1));
            for (int j=0; j<2; j++){
                eventTitle = new JLabel("", SwingConstants.CENTER);
                eventTitle.setBorder(new LineBorder(Color.LIGHT_GRAY));
                twoRows.add(eventTitle);
            }

            eventsTitlePanel.add(twoRows);
        }

        this.eventsPanel = eventsTitlePanel;       //panel with events

        this.add(currentDayDisplay, BorderLayout.NORTH);
        this.add(eventsTimePanel, BorderLayout.LINE_START);
        this.add(eventsTitlePanel, BorderLayout.CENTER);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        this.removeAll();
        displayCurrentDay();
        updateUI();
    }

    //paint() is used to paint the events on top of the day view
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        ArrayList<Event> dayEvents = null;
        for (Day day : calendarContent.getCalendarEvents()){
            if (day.getDate().isEqual(currentDay)) {
                dayEvents = day.getDayEvents();     //if same day, it finds existing events
            }
        }
        if (dayEvents != null) {    //paint current events
            for (Event event : dayEvents) {
                int totalMinutes = event.getStartingTime().getHour()*60 + event.getStartingTime().getMinute();
                double startingYPosition = totalMinutes*sizeOfTimesOFDay.getHeight()/60;
                totalMinutes = event.getEndingTime().getHour()*60 + event.getEndingTime().getMinute();
                double endingYPosition = totalMinutes*sizeOfTimesOFDay.getHeight()/60;
                double heightOfEventRec = endingYPosition - startingYPosition;

                //event displayed on top of component
                Rectangle2D rec = new Rectangle2D.Double(sizeOfTimesOFDay.getWidth(),
                            (sizeOfTimesOFDay.getHeight()/2) + startingYPosition , eventsPanel.getWidth(), heightOfEventRec);
                Graphics2D g2 = (Graphics2D) g;
                g2.setColor(Color.ORANGE);
                g2.fill(rec);
                float eventTitleXPosition = (float) rec.getCenterX();
                float eventTitleYPosition = (float) rec.getCenterY();
                g2.setColor(Color.BLACK);
                g2.drawString(event.getEventTitle(), eventTitleXPosition, eventTitleYPosition);
            }
        }
    }
}