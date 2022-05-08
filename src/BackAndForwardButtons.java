import javax.swing.*;
import java.time.LocalDate;

/**
 * BackAndForwardButtons
 * @author Miguel Vazquez
 * @version 1.0 5/6/22
 *
 * This gives the calendar the functionality to change the date back and forward
 */

public class BackAndForwardButtons extends JPanel {

    private final CalendarContentModel calendarContent;

    /**
     * @param calendarContentModel Calendar data
     */
    BackAndForwardButtons(CalendarContentModel calendarContentModel){
        this.calendarContent = calendarContentModel;
        displayButtons();
    }

    /**
     * Display buttons
     */
    private void displayButtons(){
        JButton backButton = new JButton("<");
        backButton.setHorizontalTextPosition(JButton.CENTER);
        backButton.addActionListener(e -> {
            LocalDate previousDate = calendarContent.getCurrentDate();
            calendarContent.setCurrentDate(previousDate.minusDays(1));  //decreases day by 1
        });
        JButton forwardButton = new JButton(">");
        forwardButton.setHorizontalTextPosition(JButton.CENTER);
        forwardButton.addActionListener(e -> {
            LocalDate previousDate = calendarContent.getCurrentDate();
            calendarContent.setCurrentDate(previousDate.plusDays(1));   //increases day by 1
        });

        this.add(backButton);
        this.add(forwardButton);
    }
}
