
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class MoodTracker {

    static private Scanner scanner = new Scanner(System.in);
    static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy,HH:mm");

    public static String userMoodInput() {
        System.out.print("Enter Your Mood : ");
        String userMood = scanner.nextLine();

        return userMood;
    }

    public static String userNoteInput() {
        System.out.print("Enter Your Notes : ");
        String userNotes = scanner.nextLine();

        return userNotes;
    }

    public static LocalDateTime userDateTime() {
        System.out.print("Enter Date (DD-MM-YYYY) : ");
        String userDate = scanner.nextLine();

        System.out.print("Enter Time (HH:MM) : ");
        String userTime = scanner.nextLine();

        LocalDate date = LocalDate.parse(userDate, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        LocalTime time = LocalTime.parse(userTime, DateTimeFormatter.ofPattern("HH:mm"));

        return LocalDateTime.of(date, time);
    }

    public static void addMood(ArrayList<Mood> moods) {
        try {
            // User Input for Mood
            String userMood = userMoodInput();

            // User Input for Notes
            String notes = userNoteInput();

            // User Input for Date And Time
            LocalDateTime finalLocalDateTime = userDateTime();

            for (int i = 0; i < moods.size(); i++) {
                if (moods.get(i).getDateTime().equals(finalLocalDateTime)) {
                    throw new Exception(
                            "Mood is Already Exist on " + moods.get(i).getDateTime().format(dateTimeFormatter)
                                    + "\n Please Chage Date or Time to add mood");
                }
            }

            Mood mood = new Mood(userMood, finalLocalDateTime, notes);

            moods.add(mood);
        } catch (Exception e) {
            System.out.println("Error Occured : " + e.getMessage());
            return;
        }
    }

    public static void EditNotes(ArrayList<Mood> moods) {
        try {

            // User Input for Mood
            String userMood = userMoodInput();

            // User Input for Date And Time
            LocalDateTime finalLocalDateTime = userDateTime();

            for (Mood mood : moods) {
                if (mood.getName().equals(userMood) && mood.getDateTime().equals(finalLocalDateTime)) {
                    System.out.print("To update the Notes, ");
                    String notes = userNoteInput();
                    mood.setNotes(notes);
                    return;
                }
            }

            throw new Exception("Mood Not find for given date and Time");

        } catch (Exception e) {
            System.out.println("Error Occured " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        // Scanner object to take input from user

        ArrayList<Mood> moods = new ArrayList<Mood>();
        LocalDateTime date = LocalDateTime.of(2001, 11, 9, 10, 55);
        moods.add(new Mood("Test", date, "Test"));
        System.out.println(moods);

        while (true) {

            System.out.println(
                    "1. Press A for Add Mood\n2. Press N for Edit Notes \n3. Press D for  Delete Mood\n4. Press s for Search Mood\n5. Press W for writing in file. \n6. Press R for Reading from file\n7. Press E for Exit");

            System.out.print("Please Select one option : ");
            // Read user input
            String userChoice = scanner.next();
            // Consume the newline character left by next()
            scanner.nextLine();
            // Print user input
            switch (userChoice) {

                // Choice to Add Mood
                case "A":
                    addMood(moods);
                    System.out.println(moods);
                    break;

                case "N":
                    EditNotes(moods);
                    break;

                default:
                    break;
            }

            if (userChoice.equalsIgnoreCase("E")) {
                break;
            }
        }
        scanner.close();
    }
}
