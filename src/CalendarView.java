import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Locale;

public class CalendarView extends JPanel implements ChangeListener{

    private CalendarContentModel calendarContentModel;
    private LocalDate currentDateDisplayed;
    private final int SIZE_OF_GRID = 49;
    private JPanel monthGridPanel;

    CalendarView(CalendarContentModel calendarContent){
        this.setLayout(new BorderLayout());
        this.calendarContentModel = calendarContent;
        monthGridPanel = new JPanel();
        currentDateDisplayed = LocalDate.now();
        displayMonthCalendar(currentDateDisplayed);

        //month and year displayed on top of calendar
        String currentMonth = currentDateDisplayed.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        String currentYear = String.valueOf(currentDateDisplayed.getYear());
        JLabel monthAndYear = new JLabel(currentMonth + " " + currentYear, SwingConstants.CENTER);

        this.add(monthAndYear, BorderLayout.NORTH);
        this.add(monthGridPanel, BorderLayout.CENTER);
    }

    public void displayMonthCalendar(LocalDate currentDate){
        monthGridPanel.setLayout(new GridLayout());
        int fullGridCounter = 0;
        monthGridPanel.setLayout(new GridLayout(7 ,7));
        //Display days of week
        LocalDate displayDaysOfWeek = LocalDate.of(currentDate.getYear(), currentDate.getMonth(), 1);
        while (displayDaysOfWeek.getDayOfWeek().getValue() != 7){
            displayDaysOfWeek = displayDaysOfWeek.plusDays(1);
        }
        for (int i=1; i<=7; i++){
            JLabel daysOfWeekLabel = new JLabel(displayDaysOfWeek.getDayOfWeek().getDisplayName(TextStyle.SHORT,
                                                                            Locale.ENGLISH), SwingConstants.CENTER);
            monthGridPanel.add(daysOfWeekLabel);
            displayDaysOfWeek = displayDaysOfWeek.plusDays(1);
            fullGridCounter++;
        }

        //fill out empty spaces
        LocalDate currentDay = LocalDate.of(currentDate.getYear(),currentDate.getMonth(), 1);
        if (currentDay.getDayOfWeek().getValue() != 7) {     //if day of week is sunday, then it doesn't need empty spaces
            for (int i=0; i<currentDay.getDayOfWeek().getValue(); i++){
                monthGridPanel.add(new JLabel());
                fullGridCounter++;
            }
        }

        //generate days of month
        while (currentDay.getMonth() == currentDate.getMonth()){
            //Display today's date
            if (currentDay.getDayOfMonth() == LocalDate.now().getDayOfMonth()){
                JLabel today = new JLabel(String.valueOf(currentDay.getDayOfMonth()), SwingConstants.CENTER);
                today.setBorder(new LineBorder(Color.BLACK, 1));
                today.setBackground(new Color(207, 203, 200));
                today.setOpaque(true);
                monthGridPanel.add(today);
            }else {
                monthGridPanel.add(new JLabel(String.valueOf(currentDay.getDayOfMonth()), SwingConstants.CENTER));
            }
            currentDay = currentDay.plusDays(1);
            fullGridCounter++;
        }

        //generate empty spaces
        while (fullGridCounter != SIZE_OF_GRID){
            monthGridPanel.add(new JLabel());
            fullGridCounter++;
        }
    }

    public void displayDayEvents(LocalDate currentDay){

    }

    @Override
    public void stateChanged(ChangeEvent e) {
        System.out.println("hi");
    }
}
