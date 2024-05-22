package MiniProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

    private static JFrame frame;
    private static JPanel panel;
    private static CardLayout cardLayout;
    private static Teacher currentTeacher;
    private static Student currentStudent;

    public static void main(String[] args) {
        frame = new JFrame("Mini Project");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        cardLayout = new CardLayout();
        panel = new JPanel(cardLayout);

        // Initial screen
        JPanel initialPanel = new JPanel();
        initialPanel.setLayout(new GridLayout(4, 1));
        JLabel welcomeLabel = new JLabel("Hello...");
        initialPanel.add(welcomeLabel);
        JLabel questionLabel = new JLabel("Are you a teacher or a student?");
        initialPanel.add(questionLabel);
        JButton teacherButton = new JButton("Teacher");
        JButton studentButton = new JButton("Student");
        initialPanel.add(teacherButton);
        initialPanel.add(studentButton);

        panel.add(initialPanel, "initial");

        // Teacher Panel
        JPanel teacherPanel = new JPanel();
        teacherPanel.setLayout(new GridLayout(8, 1));
        JButton addCourseButton = new JButton("Add Course");
        JButton removeCourseButton = new JButton("Remove Course");
        JButton defineAssignmentButton = new JButton("Define Assignment");
        JButton addStudentButton = new JButton("Add Student");
        JButton removeStudentButton = new JButton("Remove Student");
        JButton deleteAssignmentButton = new JButton("Delete Assignment");
        JButton assignGradeButton = new JButton("Assign Grade");
        JButton backButton1 = new JButton("Back");

        teacherPanel.add(addCourseButton);
        teacherPanel.add(removeCourseButton);
        teacherPanel.add(defineAssignmentButton);
        teacherPanel.add(addStudentButton);
        teacherPanel.add(removeStudentButton);
        teacherPanel.add(deleteAssignmentButton);
        teacherPanel.add(assignGradeButton);
        teacherPanel.add(backButton1);

        panel.add(teacherPanel, "teacher");

        // Student Panel
        JPanel studentPanel = new JPanel();
        studentPanel.setLayout(new GridLayout(8, 1));
        JButton studentAddCourseButton = new JButton("Add Course");
        JButton studentRemoveCourseButton = new JButton("Remove Course");
        JButton getTotalUnitsButton = new JButton("Get Total Units");
        JButton getOverallGPAButton = new JButton("Get Overall GPA");
        JButton getCurrentTermGPAButton = new JButton("Get Current Term GPA");
        JButton getGradeForCourseButton = new JButton("Get Grade for Course");
        JButton printEnrolledCoursesButton = new JButton("Print Enrolled Courses");
        JButton backButton2 = new JButton("Back");

        studentPanel.add(studentAddCourseButton);
        studentPanel.add(studentRemoveCourseButton);
        studentPanel.add(getTotalUnitsButton);
        studentPanel.add(getOverallGPAButton);
        studentPanel.add(getCurrentTermGPAButton);
        studentPanel.add(getGradeForCourseButton);
        studentPanel.add(printEnrolledCoursesButton);
        studentPanel.add(backButton2);

        panel.add(studentPanel, "student");

        // Action Listeners
        teacherButton.addActionListener(e -> {
            currentTeacher = teacherMethod();
            cardLayout.show(panel, "teacher");
        });
        studentButton.addActionListener(e -> {
            currentStudent = studentMethod();
            cardLayout.show(panel, "student");
        });
        backButton1.addActionListener(e -> cardLayout.show(panel, "initial"));
        backButton2.addActionListener(e -> cardLayout.show(panel, "initial"));

        addCourseButton.addActionListener(e -> handleTeacherAction("addCourse"));
        removeCourseButton.addActionListener(e -> handleTeacherAction("removeCourse"));
        defineAssignmentButton.addActionListener(e -> handleTeacherAction("defineAssignment"));
        addStudentButton.addActionListener(e -> handleTeacherAction("addStudent"));
        removeStudentButton.addActionListener(e -> handleTeacherAction("removeStudent"));
        deleteAssignmentButton.addActionListener(e -> handleTeacherAction("deleteAssignment"));
        assignGradeButton.addActionListener(e -> handleTeacherAction("assignGrade"));

        studentAddCourseButton.addActionListener(e -> handleStudentAction("addCourse"));
        studentRemoveCourseButton.addActionListener(e -> handleStudentAction("removeCourse"));
        getTotalUnitsButton.addActionListener(e -> handleStudentAction("getTotalUnits"));
        getOverallGPAButton.addActionListener(e -> handleStudentAction("getOverallGPA"));
        getCurrentTermGPAButton.addActionListener(e -> handleStudentAction("getCurrentTermGPA"));
        getGradeForCourseButton.addActionListener(e -> handleStudentAction("getGradeForTheCourse"));
        printEnrolledCoursesButton.addActionListener(e -> handleStudentAction("printEnrolledCourses"));

        frame.add(panel);
        frame.setVisible(true);
    }

    private static void handleTeacherAction(String action) {
        try {
            switch (action) {
                case "addCourse":
                    Course newCourse = courseMethod(currentTeacher);
                    currentTeacher.addCourse(newCourse);
                    JOptionPane.showMessageDialog(frame, "The course is added successfully");
                    break;
                case "removeCourse":
                    Course removeCourse = courseMethod(currentTeacher);
                    currentTeacher.removeCourse(removeCourse);
                    JOptionPane.showMessageDialog(frame, "The course is removed successfully");
                    break;
                case "defineAssignment":
                    Course assignmentCourse = courseMethod(currentTeacher);
                    Assignment newAssignment = assignmentMethod();
                    currentTeacher.defineAssignment(assignmentCourse, newAssignment);
                    JOptionPane.showMessageDialog(frame, "The assignment is defined successfully");
                    break;
                case "addStudent":
                    Course addStudentCourse = courseMethod(currentTeacher);
                    Student newStudent = studentMethod();
                    currentTeacher.addStudent(addStudentCourse, newStudent);
                    JOptionPane.showMessageDialog(frame, "The student is added successfully");
                    break;
                case "removeStudent":
                    Course removeStudentCourse = courseMethod(currentTeacher);
                    Student removeStudent = studentMethod();
                    currentTeacher.removeStudent(removeStudentCourse, removeStudent);
                    JOptionPane.showMessageDialog(frame, "The student is removed successfully");
                    break;
                case "deleteAssignment":
                    Course deleteAssignmentCourse = courseMethod(currentTeacher);
                    Assignment deleteAssignment = assignmentMethod();
                    currentTeacher.deleteAssignment(deleteAssignmentCourse, deleteAssignment);
                    JOptionPane.showMessageDialog(frame, "The assignment is removed successfully");
                    break;
                case "assignGrade":
                    Course gradeCourse = courseMethod(currentTeacher);
                    Assignment gradeAssignment = assignmentMethod();
                    Student gradeStudent = studentMethod();
                    double grade = Double.parseDouble(JOptionPane.showInputDialog("Please enter student's grade:"));
                    currentTeacher.assignGrade(gradeCourse, gradeStudent, grade);
                    JOptionPane.showMessageDialog(frame, "The grade is assigned to a student successfully");
                    break;
                default:
                    JOptionPane.showMessageDialog(frame, "Your request is not on the list");
                    break;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Invalid input! Please try again.", "Alert", JOptionPane.WARNING_MESSAGE);
        }
    }

    private static void handleStudentAction(String action) {
        try {
            switch (action) {
                case "addCourse":
                    Teacher addCourseTeacher = teacherMethod();
                    Course addCourse = courseMethod(addCourseTeacher);
                    currentStudent.addCourse(addCourse);
                    JOptionPane.showMessageDialog(frame, "The course is added successfully");
                    break;
                case "removeCourse":
                    Teacher removeCourseTeacher = teacherMethod();
                    Course removeCourse = courseMethod(removeCourseTeacher);
                    currentStudent.removeCourse(removeCourse);
                    JOptionPane.showMessageDialog(frame, "The course is removed successfully");
                    break;
                case "getTotalUnits":
                    JOptionPane.showMessageDialog(frame, "Total Units: " + currentStudent.getTotalUnits());
                    break;
                case "getOverallGPA":
                    JOptionPane.showMessageDialog(frame, "Overall GPA: " + currentStudent.getOverallGPA());
                    break;
                case "getCurrentTermGPA":
                    JOptionPane.showMessageDialog(frame, "Current Term GPA: " + currentStudent.getCurrentTermGPA());
                    break;
                case "getGradeForTheCourse":
                    Teacher gradeCourseTeacher = teacherMethod();
                    Course gradeCourse = courseMethod(gradeCourseTeacher);
                    JOptionPane.showMessageDialog(frame, "Grade for the course: " + currentStudent.getGradeForTheCourse(gradeCourse));
                    break;
                case "printEnrolledCourses":
                    currentStudent.printEnrolledCourses();
                    break;
                default:
                    JOptionPane.showMessageDialog(frame, "Your request is not on the list");
                    break;
            }
        } catch(Exception e) {
            JOptionPane.showMessageDialog(frame, "Invalid input! Please try again.", "Alert", JOptionPane.WARNING_MESSAGE);
            }
        }

    private static Teacher teacherMethod() {
        String firstName = JOptionPane.showInputDialog("Please enter your first name:");
        String lastName = JOptionPane.showInputDialog("Please enter your last name:");
        return new Teacher(firstName, lastName);
    }

    private static Course courseMethod(Teacher teacher) {
        String name = JOptionPane.showInputDialog("Please enter course's name:");
        int units = Integer.parseInt(JOptionPane.showInputDialog("Please enter number of units:"));
        String examDate = JOptionPane.showInputDialog("Please enter exam date in yyyy-MM-dd format:");
        return new Course(name, teacher, units, examDate);
    }

    private static Assignment assignmentMethod() {
        String name = JOptionPane.showInputDialog("Please enter assignment's name:");
        String deadline = JOptionPane.showInputDialog("Please enter deadline in yyyy-MM-dd format:");
        JRadioButton projectButton = new JRadioButton("PROJECT");
        JRadioButton homeworkButton = new JRadioButton("HOMEWORK");
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(projectButton);
        buttonGroup.add(homeworkButton);
        JPanel jPanel = new JPanel();
        jPanel.add(projectButton);
        jPanel.add(homeworkButton);
        JOptionPane.showMessageDialog(null, jPanel, "Assignment Type", JOptionPane.PLAIN_MESSAGE);
        TypeAssignment type;
        if (projectButton.isSelected()) {
            type = TypeAssignment.PROJECT;
        } else {
            type = TypeAssignment.HOMEWORK;
        }
        return new Assignment(name, deadline, type);
    }

    private static Student studentMethod() {
        String firstName = JOptionPane.showInputDialog("Please enter student's first name:");
        String lastName = JOptionPane.showInputDialog("Please enter student's last name:");
        int id = Integer.parseInt(JOptionPane.showInputDialog("Please enter student's ID:"));
        int currentSemester = Integer.parseInt(JOptionPane.showInputDialog("Please enter student's current semester:"));
        return new Student(firstName, lastName, id, currentSemester);
    }
}





