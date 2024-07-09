import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Assignment {
    public static List<StudentAssignment> readStudents(String fileName) {
        List<StudentAssignment> students = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("~");
                String username = parts[0];
                String password = parts[1];
                List<String> assignments = new ArrayList<>();
                for (int i = 2; i < parts.length; i++) {
                    assignments.add(parts[i]);
                }
                students.add(new StudentAssignment(username, password, assignments));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return students;
    }
    public static List<Assignment1> readAssignments(String fileName) {
        List<Assignment1> assignments = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("~");
                String course = parts[0];
                String title = parts[1];
                String instructor = parts[2];
                int remainingDays = Integer.parseInt(parts[3]);
                double score = Double.parseDouble(parts[4]);
                String deadline = parts[5];
                String information = parts[6];
                int estimatedTime = Integer.parseInt(parts[7]);
                assignments.add(new Assignment1(course, title, instructor, remainingDays, score, deadline, information, estimatedTime));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return assignments;
    }
}
class StudentAssignment {
    String username;
    String password;
    List<String> assignments;

    public StudentAssignment(String username, String password, List<String> assignments) {
        this.username = username;
        this.password = password;
        this.assignments = assignments;
    }
}

class Assignment1 {
    String course;
    String title;
    String instructor;
    int remainingDays;
    double score;
    String deadline;
    String information;
    int estimatedTime;
    public Assignment1(String course, String title, String instructor, int remainingDays, double score, String deadline, String information, int estimatedTime) {
        this.course = course;
        this.title = title;
        this.instructor = instructor;
        this.remainingDays = remainingDays;
        this.score = score;
        this.deadline = deadline;
        this.information = information;
        this.estimatedTime = estimatedTime;
    }

}
