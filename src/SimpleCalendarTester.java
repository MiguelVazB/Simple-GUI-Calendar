import javax.swing.*;
import java.awt.*;

public class SimpleCalendarTester {
    private static final int FRAME_WIDTH = 300;
    private static final int FRAME_HEIGHT = 200;

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setLayout(new BorderLayout());
        frame.setSize(FRAME_WIDTH,FRAME_HEIGHT);

        CalendarView calendarView = new CalendarView();

        frame.add(calendarView, BorderLayout.CENTER);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
