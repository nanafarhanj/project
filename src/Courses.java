import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;

public class Courses {
    public static List<StudentCourse> readStudentCourse(String filename) throws IOException {
        List<StudentCourse> students = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split("~");
            String username = parts[0];
            String password = parts[1];
            List<String> enrolledClasses = new ArrayList<>(Arrays.asList(parts).subList(2, parts.length));
            students.add(new StudentCourse(username, password, enrolledClasses));
        }
        br.close();
        return students;
    }

    public static List<ClassInfo> readClasses(String filename) throws IOException {
        List<ClassInfo> classes = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split("~");
            String courseName = parts[0];
            String instructor = parts[1];
            int units = Integer.parseInt(parts[2]);
            int remainingAssignments = Integer.parseInt(parts[3]);
            String topStudent = parts[4];
            classes.add(new ClassInfo(courseName, instructor, units, remainingAssignments, topStudent));
        }
        br.close();
        return classes;
    }
}


class StudentCourse {
    private String username;
    private String password;
    private List<String> enrolledClasses;

    public StudentCourse(String password, String username, List<String> enrolledClasses) {
        this.username = username;
        this.password = password;
        this.enrolledClasses = enrolledClasses;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public List<String> getEnrolledClasses() {
        return enrolledClasses;
    }
}

class ClassInfo {
    private String courseName;
    private String instructor;
    private int units;
    private int remainingAssignments;
    private String topStudent;

    public ClassInfo(String courseName, String instructor, int units, int remainingAssignments, String topStudent) {
        this.courseName = courseName;
        this.instructor = instructor;
        this.units = units;
        this.remainingAssignments = remainingAssignments;
        this.topStudent = topStudent;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getInstructor() {
        return instructor;
    }

    public int getUnits() {
        return units;
    }

    public int getRemainingAssignments() {
        return remainingAssignments;
    }

    public String getTopStudent() {
        return topStudent;
    }
}
