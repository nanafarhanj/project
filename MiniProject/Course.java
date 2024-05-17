package MiniProject;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Course {
    private Teacher teacher;
    private String name;
    private final int units;
    private LocalDate examDate;
    private ArrayList<Student> students;
    private ArrayList<Assignment> assignments;
    private boolean isActive;
    private int numberOfStudents;
    private int numberOfAssignments;

    // Constructor
    public Course(String name, Teacher teacher, int units,String examDate) {
        this.name = name;
        this.teacher = teacher;
        this.teacher.addCourse(this);
        this.units = units;
        this.examDate=LocalDate.parse(examDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        this.students = new ArrayList<>();
        this.assignments = new ArrayList<>();
        this.numberOfStudents = 0;
        this.numberOfAssignments = 0;
    }
    //Setters and getters
    public String getName() {
        return name;
    }
    public Teacher getTeacher() {
        return teacher;
    }
    public void setTeacher(Teacher teacher){
            this.teacher.removeCourse(this);
            this.teacher = teacher;
            this.teacher.addCourse(this);
    }
    public void gradeStudent(Student student,double grade){
        if (students.contains(student)){
            student.setGrade(this, grade);
        }
    }

    public int getUnits() {
        return units;
    }
    public LocalDate getExamDate() {
        return examDate;
    }
    public ArrayList<Student> getStudents() {
        return students;
    }
    public ArrayList<Assignment> getAssignments() {
        return assignments;
    }

    public int getNumberOfStudents() {
        return numberOfStudents;
    }

    public int getNumberOfAssignments() {
        return numberOfAssignments;
    }

    public void setNumberOfAssignments(int numberOfAssignments) {
        this.numberOfAssignments = numberOfAssignments;
    }

    public boolean isActive() {
        int a = (examDate.getYear() - LocalDate.now().getYear());
        if(a<0)isActive=false;
        else{
            a=(examDate.getMonthValue()-LocalDate.now().getMonthValue());
            if(a<0)isActive=false;
            else{
                a=examDate.getDayOfMonth()-LocalDate.now().getDayOfMonth();
                if(a<0)isActive=false;
            }
        }
        return isActive;
    }


    // Methods
    public void printRegisteredStudents() {
        System.out.println("List of Students: ");
        for (Student student : students) {
            System.out.println(student.getFirstName() + " " + student.getLastName());
        }
    }
    public void printAssignmentDescription(){
        for(Assignment assignment : assignments){
            System.out.println("Assignment Name: "+assignment.getName());
            System.out.println("Assignment DeadLine: "+assignment.getDeadline());
            System.out.println("Assignment Type: "+assignment.getTypeAssignment());
            assignment.howMuchTimeIsLeft();
        }
    }
    public void addStudent(Student student) {
        if (!students.contains(student)) {
            students.add(student);
            student.addCourse(this);
            numberOfStudents++;
        }
    }

    public void removeStudent(Student student) {
        if (students.contains(student)) {
            students.remove(student);
            student.removeCourse(this);
            numberOfStudents--;
        }
    }

    public void addAssignment(Assignment assignment) {
        if(!assignments.contains(assignment)) {
            assignments.add(assignment);
            numberOfAssignments++;
        }
    }

    public void removeAssignment(Assignment assignment) {
        if(assignments.contains(assignment)) {
            assignments.remove(assignment);
            numberOfAssignments--;
        }
    }
    public void highestGrade(){
        ArrayList<Student> students1=new ArrayList<>();
        double a=0.0;
        for(Student student : students){
            if(a<student.getGradeFoTheCourse(this)){
                a=student.getGradeFoTheCourse(this);
            }
        }
        for(Student student: students){
            if(a==student.getGradeFoTheCourse(this)){
                students1.add(student);
            }
        }
        System.out.println("The highest grade of this course is "+a);
        System.out.println("List of the students with this grade in this course: ");
        for(Student student:students1){
            System.out.println(student.getFirstName()+" "+student.getLastName());
        }


    }



}

