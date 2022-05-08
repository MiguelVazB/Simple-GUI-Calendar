import javax.swing.*;
import java.awt.*;

public class SimpleCalendarTester {
    private static final int FRAME_WIDTH = 1000;
    private static final int FRAME_HEIGHT = 250;

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setLayout(new BorderLayout());
        frame.setSize(FRAME_WIDTH,FRAME_HEIGHT);

        CalendarContentModel calendarContent = new CalendarContentModel();

        CalendarView calendarView = new CalendarView(calendarContent);
        DayView dayView = new DayView(calendarContent);
        BackAndForwardButtons backAndForwardButtons = new BackAndForwardButtons(calendarContent);
        EventCreation eventCreation = new EventCreation(calendarContent);
        SaveEventsAndQuit quitButton = new SaveEventsAndQuit(calendarContent);

        JScrollPane scrollPane = new JScrollPane(dayView);
        scrollPane.setBorder(null);

        calendarContent.attachListener(calendarView);
        calendarContent.attachListener(dayView);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(backAndForwardButtons, BorderLayout.CENTER);
        topPanel.add(eventCreation, BorderLayout.WEST);
        topPanel.add(quitButton, BorderLayout.EAST);

        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(calendarView, BorderLayout.LINE_START);
        frame.add(scrollPane, BorderLayout.CENTER);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
