import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class CalendarContentModel {

    private HashMap<Day, Month> calendarContent;

    public CalendarContentModel(){
        calendarContent = new HashMap<>();
        generateEventsFromFile();
    }

    private void generateEventsFromFile(){
        File eventsFile = new File("events.txt");
        //content separated by ////~
        ArrayList<String> csContent = new ArrayList<>();
        Scanner input = null;
        try {
            input = new Scanner(eventsFile);
        } catch (FileNotFoundException e) {
            System.out.println("File not found!\nCreating empty calendar.....");
        }
        if (input==null){
            return;
        }
        while (input.hasNextLine()){
            csContent.add(input.nextLine());
        }
        input.close();

        for (String event: csContent){
            String[] eventInfo = event.split("////~");
        }
    }

    public HashMap<Day, Month> getCalendarContent() {
        return calendarContent;
    }
}
