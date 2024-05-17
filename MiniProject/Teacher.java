package MiniProject;
import java.util.ArrayList;
public class Teacher {
    private String firstName;
    private String lastName;
    private int numberOfCourses;
    private ArrayList<Course> courses;

    // Constructor
    public Teacher(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.numberOfCourses = 0;
        this.courses = new ArrayList<>();
    }

    // Methods
    public void addCourse(Course course) {
        if (!courses.contains(course)) {
            courses.add(course);
            numberOfCourses++;
        }
    }

    public void removeCourse(Course course) {
        if (courses.contains(course)) {
            courses.remove(course);
            numberOfCourses--;
        }
    }
    public void defineAssignment(Course course,Assignment assignment) {
        if (course.getTeacher()==this){
            course.addAssignment(assignment);
        }
    }
    public void addStudent(Course course,Student student){
        if (course.getTeacher()==this){
            course.addStudent(student);
        }
    }
    public void removeStudent(Course course,Student student){
        if (course.getTeacher()==this){
            course.removeStudent(student);
        }
    }

    public void deleteAssignment(Course course,Assignment assignment) {
        if (course.getTeacher()==this){
            course.removeAssignment(assignment);
        }
    }
    public void assignGrade(Course course,Student student,double grade) {
        if(course.getTeacher()==this) {
            course.gradeStudent(student, grade);
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getNumberOfCourses() {
        return numberOfCourses;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }
}
