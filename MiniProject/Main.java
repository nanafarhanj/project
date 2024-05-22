package MiniProject;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello...");
        System.out.println("You are a teacher or a student? ");
        System.out.println("if you are a teacher please enter 1 else enter 2");
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();
        scanner.nextLine();
        if (input == 1) {
        while (true) {
                Course course;
                Assignment assignment;
                Student student;
                Teacher teacher = teacherMethod(scanner);
                System.out.println("What do you wanna do?\nPlease enter the task's number");
                System.out.println("1.addCourse\n2.removeCourse\n3.defineAssignment\n4.addStudent\n5.removeStudent\n6.deleteAssignment\n7.assignGrade");
                System.out.println("if you want to exit the program please enter stop");
                String input1 = scanner.nextLine();
                if (input1.equals("stop")) {
                    break;
                }
                switch (input1) {
                    case "1":
                        course = courseMethod(teacher, scanner);
                        teacher.addCourse(course);
                        break;
                    case "2":
                        course = courseMethod(teacher, scanner);
                        teacher.removeCourse(course);
                        break;
                    case "3":
                        course = courseMethod(teacher, scanner);
                        assignment = assignmentMethod(scanner);
                        teacher.defineAssignment(course, assignment);
                        break;
                    case "4":
                        course = courseMethod(teacher, scanner);
                        student = studentMethod(scanner);
                        teacher.addStudent(course, student);
                        break;
                    case "5":
                        course = courseMethod(teacher, scanner);
                        student = studentMethod(scanner);
                        teacher.removeStudent(course, student);
                        break;
                    case "6":
                        course = courseMethod(teacher, scanner);
                        assignment = assignmentMethod(scanner);
                        teacher.deleteAssignment(course, assignment);
                        break;
                    case "7":
                        course = courseMethod(teacher, scanner);
                        assignment = assignmentMethod(scanner);
                        student = studentMethod(scanner);
                        System.out.println("Please enter student's grade");
                        double grade = scanner.nextDouble();
                        teacher.assignGrade(course, student, grade);
                        break;
                    default:
                        System.out.println("Your request is not on the list");
                        break;
                }
            }
        }
        else if (input == 2) {
            while (true) {
                Course course;
                Assignment assignment;
                Teacher teacher;
                Student student = studentMethod(scanner);
                System.out.println("What do you wanna do?\nPlease enter the task's number");
                System.out.println("1.addCourse\n2.removeCourse\n3.getTotalUnits\n4.getOverallGPA\n5.getCurrentTermGPA\n6.getGradeForTheCourse\n7.printEnrolledCourses");
                System.out.println("if you want to exit the program please enter stop");
                String input1 = scanner.nextLine();
                if (input1.equals("stop")) {
                    break;
                }
                switch (input1) {
                    case "1" :
                        teacher = teacherMethod(scanner);
                        course = courseMethod(teacher, scanner);
                        student.addCourse(course);
                        break;
                    case "2" :
                        teacher = teacherMethod(scanner);
                        course = courseMethod(teacher, scanner);
                        student.removeCourse(course);
                        break;
                    case "3" :
                        System.out.println(student.getTotalUnits());
                        break;
                    case "4" :
                        System.out.println(student.getOverallGPA());
                         break;
                    case "5" :
                        System.out.println(student.getCurrentTermGPA());
                        break;
                    case "6" :
                        teacher = teacherMethod(scanner);
                        course = courseMethod(teacher, scanner);
                        System.out.println(student.getGradeForTheCourse(course));
                        break;
                    case "7" :
                        student.printEnrolledCourses();
                        break;
                    default:
                        System.out.println("Your request is not on the list");
                        break;

                }
            }
        }
    }
    public static Teacher teacherMethod(Scanner scanner) {
        System.out.println("Please enter your first name and last name");
        String firstName = scanner.nextLine();
        String lastName = scanner.nextLine();
        return new Teacher(firstName, lastName);
    }
    public static Course courseMethod(Teacher teacher, Scanner scanner) {
        System.out.println("please enter course's name, number of units and exam date in yyyy-MM-dd format");
        String name = scanner.nextLine();
        int units = scanner.nextInt();
        scanner.nextLine();
        String examDate = scanner.nextLine();
        return new Course(name, teacher, units, examDate);
    }
    public static Assignment assignmentMethod(Scanner scanner) {
        System.out.println("Please enter assignment's name, deadline in yyyy-MM-dd format and type");
        String name = scanner.nextLine();
        String deadLine = scanner.nextLine();
        TypeAssignment typeAssignment = TypeAssignment.valueOf(scanner.nextLine());
        return new Assignment(name, deadLine, typeAssignment);
    }
    public static Student studentMethod(Scanner scanner) {
        System.out.println("Please enter student's first name, last name, id and current semester");
        String firstName = scanner.nextLine();
        String lastName = scanner.nextLine();
        int id = scanner.nextInt();
        int currentSemester = scanner.nextInt();
        return new Student(firstName, lastName, id, currentSemester);
    }
}


