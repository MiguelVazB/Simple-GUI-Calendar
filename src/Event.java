import java.util.Date;

public class Event {

    private Date startingTime;
    private Date endingTime;
    private Date date;
    private String eventTitle;

    public Event(Date startingTime, Date endingTime, Date date, String eventTitle) {
        this.startingTime = startingTime;
        this.endingTime = endingTime;
        this.date = date;
        this.eventTitle = eventTitle;
    }

    public Date getStartingTime() {
        return startingTime;
    }

    public void setStartingTime(Date startingTime) {
        this.startingTime = startingTime;
    }

    public Date getEndingTime() {
        return endingTime;
    }

    public void setEndingTime(Date endingTime) {
        this.endingTime = endingTime;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }
}
