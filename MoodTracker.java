import java.time.*;
import java.time.format.DateTimeFormatter;
import java.io.*;
import java.util.*;

public class MoodTracker {

    static private Scanner scanner = new Scanner(System.in);
    static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy,HH:mm");

    private static String userMoodInput() {
        System.out.print("Enter Your Mood : ");
        String userMood = scanner.nextLine();

        return userMood;
    }

    private static String userNoteInput() {
        System.out.print("Enter Your Notes : ");
        String userNotes = scanner.nextLine();

        return userNotes;
    }

    private static LocalDate userDate() {
        System.out.print("Enter Date (DD-MM-YYYY) : ");
        String userDate = scanner.nextLine();

        LocalDate date = LocalDate.parse(userDate, DateTimeFormatter.ofPattern("dd-MM-yyyy"));

        return date;
    }

    private static LocalTime userTime() {
        System.out.print("Enter Time (HH:MM) : ");
        String userTime = scanner.nextLine();
        LocalTime time = LocalTime.parse(userTime, DateTimeFormatter.ofPattern("HH:mm"));

        return time;
    }

    public static void addMood(ArrayList<Mood> moods) {
        try {
            // User Input for Mood
            String userMood = userMoodInput();

            // User Input for Notes
            String notes = userNoteInput();

            // User Input for Date And Time
            LocalDateTime finalLocalDateTime = LocalDateTime.of(userDate(), userTime());

            for (int i = 0; i < moods.size(); i++) {
                if (moods.get(i).getDateTime().equals(finalLocalDateTime)) {
                    throw new Exception(
                            "Mood is Already Exist on " + moods.get(i).getDateTime().format(dateTimeFormatter)
                                    + "\n Please Change Date or Time to add mood");
                }
            }

            Mood mood = new Mood(userMood, finalLocalDateTime, notes);

            moods.add(mood);
            System.out.println("==================================================");
            System.out.println("Mood Added Successfully");
            System.out.println("==================================================");
        } catch (Exception e) {
            System.out.println("Error Occured : " + e.getMessage());
            return;
        }
    }

    public static void EditNotes(ArrayList<Mood> moods) {
        try {

            if (moods.isEmpty()) {
                throw new Exception("No Moods Found");
            }

            // User Input for Mood
            String userMood = userMoodInput();

            // User Input for Date And Time
            LocalDateTime finalLocalDateTime = LocalDateTime.of(userDate(), userTime());

            for (Mood mood : moods) {
                if (mood.getName().equals(userMood) && mood.getDateTime().equals(finalLocalDateTime)) {
                    System.out.print("To update the Notes, ");
                    String notes = userNoteInput();
                    mood.setNotes(notes);
                    System.out.println("==================================================");
                    System.out.println("Notes Updated Successfully");
                    System.out.println("==================================================");
                    return;
                }
            }

            throw new Exception("Mood Not found for given date and Time");

        } catch (Exception e) {
            System.out.println("Error Occured " + e.getMessage());
        }
    }

    private static void DeleteByDate(ArrayList<Mood> moods) {
        try {

            if (moods.isEmpty()) {
                throw new Exception("No Moods Found");
            }
            
            boolean flag = false;
            LocalDate date = userDate();
            for (Mood mood : moods) {
                if (mood.getDateTime().toLocalDate().equals(date)) {
                    moods.remove(mood);
                    System.out.println("==================================================");
                    System.out.println("Mood Deleted Successfully");
                    System.out.println("==================================================");
                    flag = true;
                }
            }

            if (!flag)
                throw new Exception("Mood Not found for given date");

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Error Occured " + e.getMessage());
        }
    }

    private static void DeleteByDetails(ArrayList<Mood> moods) {
        try {
            if (moods.isEmpty()) {
                throw new Exception("No Moods Found");
            }
            String userMood = userMoodInput();
            LocalDateTime finalLocalDateTime = LocalDateTime.of(userDate(), userTime());
            boolean flag = false;
            for (Mood mood : moods) {
                if (mood.getName().equals(userMood) && mood.getDateTime().equals(finalLocalDateTime)) {
                    moods.remove(mood);
                    System.out.println("==================================================");
                    System.out.println("Mood Deleted Successfully");
                    System.out.println("==================================================");
                    flag = true;
                }
            }
            if (!flag)
                throw new Exception("Mood Not found for given date and Time");
        } catch (Exception e) {
            System.out.println("Error Occured " + e.getMessage());
        }
    }

    private static void searchByDate(ArrayList<Mood> moods) {
        try {

            if (moods.isEmpty()) {
                throw new Exception("No Moods Found");
            }
            LocalDate date = userDate();
            boolean flag = false;
            for (Mood mood : moods) {
                if (mood.getDateTime().toLocalDate().equals(date)) {
                    System.out.println("==================================================");
                    System.out.print("Please Find Mood\n" + mood);
                    System.out.println("==================================================");
                    flag = true;
                }
            }

            if (!flag)
                throw new Exception("Mood Not found");

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Error Occured " + e.getMessage());
        }
    }

    private static void searchByDetails(ArrayList<Mood> moods) {
        try {

            if (moods.isEmpty()) {
                throw new Exception("No Moods Found");
            }
            String userMood = userMoodInput();
            LocalDateTime finalLocalDateTime = LocalDateTime.of(userDate(), userTime());
            for (Mood mood : moods) {
                if (mood.getName().equals(userMood) && mood.getDateTime().equals(finalLocalDateTime)) {
                    System.out.println("==================================================");
                    System.out.print("Please Find Mood\n" + mood);
                    System.out.println("==================================================");
                    return;
                }
            }
            throw new Exception("Mood Not found");
        } catch (Exception e) {
            System.out.println("Error Occured " + e.getMessage());
        }
    }

    private static void showMoods(ArrayList<Mood> moods) {
        try {

            if (moods.isEmpty()) {
                throw new Exception("No Moods Found");
            }

            for (Mood mood : moods) {
                System.out.println("==================================================");
                System.out.print(mood);
            }
        } catch (Exception e) {
            System.out.println("Error Occured " + e.getMessage());
        }
    }

    private static void writeInFile(ArrayList<Mood> moods) {
        try {
            if (moods.isEmpty()) {
                throw new Exception("No Moods Found");
            }
            FileWriter filewriter = new FileWriter("MoodTracker.txt");
            BufferedWriter bufferedwriter = new BufferedWriter(filewriter);

            for (Mood mood : moods) {
                bufferedwriter.write(mood.toString());
                bufferedwriter.newLine();
                bufferedwriter.flush();
            }

            System.out.println("==================================================");
            System.out.println("Moods are written in file Successfully");
            System.out.println("==================================================");
            bufferedwriter.close();

        } catch (Exception e) {
            System.out.println("Error Occured " + e.getMessage());
        }
    }

    private static void readFromFile(ArrayList<Mood> moods) {
        try {
            FileReader filereader = new FileReader("MoodTracker.txt");
            BufferedReader bufferedreader = new BufferedReader(filereader);
            String line;
            while ((line = bufferedreader.readLine()) != null) {
                System.out.println(line);
            }
            bufferedreader.close();
            System.out.println("==================================================");
            System.out.println("Moods are read from file Successfully");
            System.out.println("==================================================");

        } catch (Exception e) {
            System.out.println("Error Occured " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        // Scanner object to take input from user

        ArrayList<Mood> moods = new ArrayList<Mood>();
        LocalDateTime date = LocalDateTime.of(2001, 11, 9, 10, 55);
        moods.add(new Mood("Test", date, "Test"));
        date = LocalDateTime.of(2001, 10, 9, 10, 55);
        moods.add(new Mood("Test", date, "Test"));
        // System.out.println(moods);

        while (true) {

            System.out.println(
                    "Press A for Add Mood\nPress N for Edit Notes \nPress D for  Delete Mood\nPress S for Search Mood\nPress M to Show all Moods\nPress W for writing in file\nPress R for Reading from file\nPress E for Exit");

            System.out.print("Please Select one option : ");
            // Read user input
            String userChoice = scanner.next();
            // Consume the newline character left by next()
            scanner.nextLine();
            // Print user input
            switch (userChoice.toUpperCase()) {

                // Choice to Add Mood
                case "A":
                    addMood(moods);
                    break;

                case "N":
                    EditNotes(moods);
                    break;

                case "D":
                    if (moods.isEmpty()) {
                        System.out.println("No Moods Found");
                        System.out.println("==================================================");
                        break;
                    }
                    System.out.println("1. Press 1 for Delete by Date \n2. Press 2 for Delete By All Details.");
                    System.out.print("Enter your choice : ");
                    int choice = scanner.nextInt();
                    scanner.nextLine();

                    if (choice == 1) {
                        DeleteByDate(moods);
                    } else if (choice == 2) {
                        DeleteByDetails(moods);
                    } else {
                        System.out.println(
                                "Invalid Input");
                    }
                    break;

                case "S": // Search Mood
                    System.out.println("1. Press 1 for Search by Date \n2. Press 2 for Search By All Details.");
                    System.out.print("Enter your choice : ");
                    int searchChoice = scanner.nextInt();
                    scanner.nextLine();

                    if (searchChoice == 1) {
                        searchByDate(moods);
                    } else if (searchChoice == 2) {
                        searchByDetails(moods);
                    } else {
                        System.out.println(
                                "Invalid Input");
                    }
                    break;

                case "M":
                    showMoods(moods);
                    break;

                case "W":
                    writeInFile(moods);
                    break;

                case "R":
                    readFromFile(moods);
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
