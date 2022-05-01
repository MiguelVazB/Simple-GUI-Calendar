import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.Date;
import java.util.Locale;

public class CalendarView extends JPanel {

    private CalendarContentModel calendarContentModel;
    private LocalDate currentDate;
    private final int SIZE_OF_GRID = 49;

    CalendarView(){
        currentDate = LocalDate.now();
        int fullGridCounter = 0;
        this.setLayout(new GridLayout(7 ,7));
        LocalDate displayDaysOfWeek = LocalDate.of(currentDate.getYear(), currentDate.getMonth(), 1);
        while (displayDaysOfWeek.getDayOfWeek().getValue() != 7){
            displayDaysOfWeek = displayDaysOfWeek.plusDays(1);
        }
        for (int i=1; i<=7; i++){
            this.add(new JLabel(displayDaysOfWeek.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.ENGLISH)));
            displayDaysOfWeek = displayDaysOfWeek.plusDays(1);
            fullGridCounter++;
        }
        LocalDate currentDay = LocalDate.of(currentDate.getYear(),currentDate.getMonth(), 1);
        while (currentDay.getDayOfWeek().getValue() != 7){
            this.add(new JLabel());
            fullGridCounter++;
        }
        while (currentDate.getMonth() == currentDay.getMonth()){
            this.add(new JLabel(String.valueOf(currentDay.getDayOfMonth())));
            fullGridCounter++;
        }
    }

    CalendarView(CalendarContentModel calendarContent){
        this.calendarContentModel = calendarContent;
        currentDate = LocalDate.now();
    }
}
