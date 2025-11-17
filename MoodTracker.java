
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class MoodTracker {

    static private Scanner scanner = new Scanner(System.in);
    static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy,HH:mm");

    public static void addMood(ArrayList<Mood> moods) {
        try {
            System.out.print("Enter Your Mood : ");
            String userMood = scanner.nextLine();

            System.out.print("Enter Notes : ");
            String notes = scanner.nextLine();

            System.out.print("Enter Date (DD-MM-YYYY) : ");
            String userDate = scanner.nextLine();

            System.out.print("Enter Time (HH:MM) : ");
            String userTime = scanner.nextLine();

            LocalDate date = LocalDate.parse(userDate, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            LocalTime time = LocalTime.parse(userTime, DateTimeFormatter.ofPattern("HH:mm"));

            LocalDateTime finalLocalDateTime = LocalDateTime.of(date, time);

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

    public static void deleteMood(ArrayList<Mood> moods) {
        try {

        } catch (Exception e) {
            System.out.println("Error Occured");
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
                    "1. Press A for Add Mood\n2. Press D for  Delete Mood\n3. Press s for Search Mood\n4. Press W for writing in file. \n5. Press R for Reading from file\n6. Press E for Exit");

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

                case "D":
                    deleteMood(moods);

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
