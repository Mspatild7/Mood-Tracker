import java.time.*;
import java.time.format.DateTimeFormatter;

public class Mood {

  private String name;
  private LocalDateTime dateTime;
  private String notes;       

  public Mood(String name,LocalDateTime dateTime, String notes) {
    this.name = name;
    this.notes = notes;
    this.dateTime = dateTime;
  }

  public String getName() {
    return name;
  }

  public String getNotes() {
    return notes;
  }

  public LocalDateTime getDateTime() {
    return dateTime;
  }

  public void setNotes(String notes) {
    this.notes = notes;
  }

  public String toString() {
    return name + "," + dateTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")) + "," + notes;
  }
}