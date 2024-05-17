package MiniProject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Student {
    //Properties
    private String firstName;
    private String lastName;
    private int studentID;
    private int currentSemester;
    private int numberOfCourses;
    private ArrayList<Course> courses;
    private Map<Course,Double> grades;
    private int totalUnits;
    private double overallGPA;
    private double currentTermGPA;


    // Constructor
    public Student(String firstName, String lastName, int studentID,int currentSemester) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.studentID = studentID;
        this.currentSemester=currentSemester;
        this.grades=new HashMap<>();
        this.numberOfCourses = 0;
        this.courses = new ArrayList<>();
        this.totalUnits = 0;
        this.overallGPA = 0.0;
        this.currentTermGPA = 0.0;
    }
    //Setters and getters
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getStudentID() {
        return studentID;
    }

    public int getCurrentSemester() {
        return currentSemester;
    }

    public void setCurrentSemester(int currentSemester) {
        this.currentSemester = currentSemester;
    }

    public int getNumberOfCourses() {
        return numberOfCourses;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void setGrade(Course course, double grade) {
        this.grades.put(course,grade);
    }

    public void addCourse(Course course) {
        numberOfCourses++;
        this.courses.add(course);
        setTotalUnits();
    }
    public int getTotalUnits() {
       setTotalUnits();
        return totalUnits;
    }

    public void setTotalUnits() {
        int total=0;
        for(Course course : courses){
            total+=course.getUnits();
        }
        this.totalUnits=total;
    }

    public void setOverallGPA() {

    }
    public void removeCourse(Course course){
        courses.remove(course);
    }

    public double getOverallGPA() {
        return overallGPA;
    }

    public double getCurrentTermGPA() {
        setCurrentTermGPA();
        return currentTermGPA;
    }
    public double getGradeFoTheCourse(Course course){
        return grades.get(course);
    }
    public void setCurrentTermGPA() {
        double numberOfGradedCoursesUnits=0;
        double sumOfGrades=0;
        for (Course course: courses){
                if (grades.get(course)!=null) {
                    numberOfGradedCoursesUnits += course.getUnits();
                    sumOfGrades += grades.get(course) * course.getUnits();
                }
        }
        if (numberOfGradedCoursesUnits==0){
            this.currentTermGPA=0;
        }else {
            this.currentTermGPA=sumOfGrades/numberOfGradedCoursesUnits;
        }
    }

    // Methods
    public void printEnrolledCourses() {
        for (Course course : courses) {
            System.out.println(course.getName());
        }
    }



}


