import javax.swing.*;
import javax.swing.border.EmptyBorder;
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

        JScrollPane scrollPane = new JScrollPane(dayView);
        scrollPane.setBorder(null);

        calendarContent.attachListener(calendarView);
        calendarContent.attachListener(dayView);

        frame.add(backAndForwardButtons, BorderLayout.NORTH);
        frame.add(calendarView, BorderLayout.LINE_START);
        frame.add(scrollPane, BorderLayout.CENTER);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
