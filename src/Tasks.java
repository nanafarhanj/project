import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Tasks {
    public static List<StudentTasks> readStudentTasks(String fileName) {
        List<StudentTasks> students = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("~");
                String username = parts[0];
                String password = parts[1];
                List<String> tasks = new ArrayList<>();
                for (int i = 2; i < parts.length; i++) {
                    tasks.add(parts[i]);
                }
                students.add(new StudentTasks(username, password, tasks));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return students;
    }
    public static List<Task> readTasks(String fileName) {
        List<Task> tasks = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("~");
                String task = parts[0];
                String inf = parts[1];
                tasks.add(new Task(task, inf));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tasks;
    }
}
class StudentTasks {
    String username;
    String password;
    List<String> tasks;

    public StudentTasks(String username, String password, List<String> tasks) {
        this.username = username;
        this.password = password;
        this.tasks = tasks;
    }
}
class Task {
    String task;
    String inf;
    public Task(String task, String inf) {
        this.task = task;
        this.inf = inf;
    }

}
