import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;

public class Month {

    private ArrayList<Day> daysInMonth;

    Month() {
        daysInMonth = new ArrayList<>();
    }

    public Month(Day day){
        this.daysInMonth = new ArrayList<>();
        this.daysInMonth.add(day);
    }

    public void addDayWithEvent(Day day) {
        this.daysInMonth.add(day);
    }

}
