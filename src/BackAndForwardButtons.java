import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class BackAndForwardButtons extends JPanel {

    private CalendarContentModel calendarContent;

    BackAndForwardButtons(CalendarContentModel calendarContentModel){
        this.calendarContent = calendarContentModel;
        displayButtons();
    }

    private void displayButtons(){
        JButton backButton = new JButton("<");
        backButton.setHorizontalTextPosition(JButton.CENTER);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LocalDate previousDate = calendarContent.getCurrentDate();
                calendarContent.setCurrentDate(previousDate.minusDays(1));
            }
        });
        JButton forwardButton = new JButton(">");
        forwardButton.setHorizontalTextPosition(JButton.CENTER);
        forwardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("hello there Im forward button");
            }
        });

        this.add(backButton);
        this.add(forwardButton);
    }
}
