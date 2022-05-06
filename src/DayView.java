import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.Locale;

public class DayView extends JPanel implements ChangeListener {

    private CalendarContentModel calendarContent;
    private LocalDate currentDay;
    private JPanel eventsPanel;

    public DayView(CalendarContentModel calendarContent) {
        this.setLayout(new BorderLayout());
        this.calendarContent = calendarContent;
        displayCurrentDay();
    }

    private void displayCurrentDay() {                          ///////////////////////////////////////////////////////////////
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
            eventTime = new JLabel(i-afterNoon+" "+timeOfDay, SwingConstants.CENTER);
            eventTime.setBackground(Color.WHITE);
            eventTime.setOpaque(true);
            eventTime.setBorder(new LineBorder(Color.LIGHT_GRAY));
            eventTime.setPreferredSize(new Dimension(50,50));
            eventsTimePanel.add(eventTime);

            JPanel twoRows = new JPanel();
            twoRows.setLayout(new GridLayout(2,1));
            for (int j=0; j<2; j++){
                eventTitle = new JLabel("title", SwingConstants.CENTER);
                eventTitle.setBorder(new LineBorder(Color.LIGHT_GRAY));
                twoRows.add(eventTitle);
            }
            eventsTitlePanel.add(twoRows);
        }

        this.eventsPanel = eventsTitlePanel;

        this.add(currentDayDisplay, BorderLayout.NORTH);
        this.add(eventsTimePanel, BorderLayout.LINE_START);
        this.add(eventsTitlePanel, BorderLayout.CENTER);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        currentDay = (LocalDate) e.getSource();
        this.removeAll();
        this.displayCurrentDay();
    }
}