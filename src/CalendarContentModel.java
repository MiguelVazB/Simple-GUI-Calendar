import javax.swing.event.ChangeListener;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

public class CalendarContentModel {

    private HashMap<Month, Day> calendarContent;
    private ArrayList<ChangeListener> listeners = new ArrayList<>();

    public CalendarContentModel(){
        calendarContent = new HashMap<>();
        generateEventsFromFile();
    }

    private void generateEventsFromFile(){
        String fileName = "events.txt";
        File eventsFile = new File(fileName);
        //content separated by ////~
        ArrayList<String> csContent = new ArrayList<>();
        Scanner input = null;
        try {
            input = new Scanner(eventsFile);
        } catch (FileNotFoundException e) {
            System.out.println("File not found!\nCreating empty calendar.....");
            try {
                createEventsFile(fileName);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
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

        //serialization
//        try {
//            FileOutputStream file = new FileOutputStream("file.ser");
//            ObjectOutputStream out = new ObjectOutputStream(file);
//            out.writeObject(new Event(LocalTime.now(), "hello"));
//            out.close();
//            file.close();
//        }catch (IOException ex){
//            System.out.println("naaaaaaah");
//        }
    }

    private void createEventsFile(String fileName) throws IOException {
        new FileOutputStream(fileName).close();
    }

    public void attachListener(ChangeListener listener){
        this.listeners.add(listener);
    }
}
