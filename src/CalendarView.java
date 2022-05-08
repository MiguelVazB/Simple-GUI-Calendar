import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

public class CalendarView extends JPanel implements ChangeListener{

    private final CalendarContentModel calendarContentModel;
    private JPanel monthGridPanel;
    private JLabel currentSelectedDay;
    private LocalDate currentDateDisplayed;

    CalendarView(CalendarContentModel calendarContent){
        this.setLayout(new BorderLayout());
        this.calendarContentModel = calendarContent;
        displayMonthCalendar();
    }

    public void displayMonthCalendar(){

        currentDateDisplayed = this.calendarContentModel.getCurrentDate();
        monthGridPanel = new JPanel();
        final int SIZE_OF_GRID = 49;

        //month and year displayed on top of calendar
        String currentMonth = currentDateDisplayed.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        String currentYear = String.valueOf(currentDateDisplayed.getYear());
        JLabel monthAndYear = new JLabel(currentMonth + " " + currentYear, SwingConstants.CENTER);

        JPanel monthView = new JPanel();
        monthView.setLayout(new BorderLayout());
        monthView.add(monthAndYear, BorderLayout.NORTH);
        monthView.add(monthGridPanel, BorderLayout.CENTER);

        this.add(monthView, BorderLayout.LINE_START);

        monthGridPanel.setLayout(new GridLayout());
        int fullGridCounter = 0;
        monthGridPanel.setLayout(new GridLayout(7 ,7));
        //Display days of week
        LocalDate displayDaysOfWeek = LocalDate.of(currentDateDisplayed.getYear(), currentDateDisplayed.getMonth(), 1);
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
        LocalDate currentDay = LocalDate.of(currentDateDisplayed.getYear(), currentDateDisplayed.getMonth(), 1);
        if (currentDay.getDayOfWeek().getValue() != 7) {     //if day of week is sunday, then it doesn't need empty spaces
            for (int i=0; i<currentDay.getDayOfWeek().getValue(); i++){
                monthGridPanel.add(new JLabel());
                fullGridCounter++;
            }
        }

        //generate days of month
        while (currentDay.getMonth() == currentDateDisplayed.getMonth()){
            //Display today's date
            JLabel dayToAdd = new JLabel(String.valueOf(currentDay.getDayOfMonth()), SwingConstants.CENTER);;
            if (currentDay.equals(LocalDate.now())){
                dayToAdd.setBackground(new Color(207, 203, 200));
                dayToAdd.setOpaque(true);
            }
            monthGridPanel.add(dayToAdd);

            if (currentDay.getDayOfMonth() == currentDateDisplayed.getDayOfMonth()){
                currentSelectedDay = dayToAdd;
                currentSelectedDay.setBorder(new LineBorder(Color.BLACK));
            }
            currentDay = currentDay.plusDays(1);
            fullGridCounter++;
        }

        //generate empty spaces
        while (fullGridCounter != SIZE_OF_GRID){
            monthGridPanel.add(new JLabel());
            fullGridCounter++;
        }

        monthGridPanel.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                JLabel dayPressed = (JLabel) monthGridPanel.getComponentAt(e.getX(),e.getY());
                if (selectableLabel(dayPressed.getText())) {
                    if (currentSelectedDay != null){
                        currentSelectedDay.setBorder(null);
                    }
                    currentSelectedDay = dayPressed;
                    currentSelectedDay.setBorder(new LineBorder(Color.BLACK));
                    calendarContentModel.setCurrentDate(LocalDate.of(currentDateDisplayed.getYear(),
                            currentDateDisplayed.getMonth(), Integer.parseInt(dayPressed.getText())));
                }
                updateUI();
            }
        });
    }

    private boolean selectableLabel(String keyPressed){
        if (keyPressed.isEmpty()){
            return false;
        }
        return switch (keyPressed) {
            case "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun" -> false;
            default -> true;
        };
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        this.removeAll();
        displayMonthCalendar();
        updateUI();
    }
}