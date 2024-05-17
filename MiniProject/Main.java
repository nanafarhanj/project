package MiniProject;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args){
        Student student1=new Student("a","A",111,1);
        Student student2=new Student("b","B",112,1);
        Student student3=new Student("c","C",113,1);
        Student student4=new Student("d","d",114,1);
        Student student5=new Student("e","E",115,1);
        Student student6=new Student("f","F",116,1);
        Student student7=new Student("g","G",117,1);
        Student student8=new Student("h","H",118,1);
        Student student9=new Student("i","I",119,1);
        Student student10=new Student("j","J",120,1);
        Student student11=new Student("k","K",121,1);
        Teacher teacher1 = new Teacher("a1","A1");
        Teacher teacher2 = new Teacher("b1","B1");
        Teacher teacher3 = new Teacher("c1","C1");
        Teacher teacher4 = new Teacher("d1","D1");
        Term term2=new Term(4022);
        Course course1 = new Course("math1",teacher1,3,"2024-05-20");
        Course course2 = new Course("AP",teacher2,5,"2024-05-21");
        term2.addCourse(course1);
        term2.addCourse(course2);

          ArrayList<Course> courses=term2.getCourses();
         for (Course course:courses) System.out.println(course.getName());


       term2.removeCourse(course1);
      ArrayList <Course> courses2=term2.getCourses();
      for (Course course:courses) System.out.println(course.getName());

     Teacher teacher=course1.getTeacher();
      System.out.println(teacher.getFirstName()+" "+teacher.getLastName()+"\n"+"number of courses: "+teacher.getNumberOfCourses());
      if (teacher.getCourses().get(0)==course1){
       System.out.println("true");
      }else {
       System.out.println("false");
      }
      course1.setTeacher(teacher2);
      System.out.println("number of courses for teacher1: "+teacher1.getNumberOfCourses());
     System.out.println("number of courses for teacher2: "+teacher2.getNumberOfCourses());
     teacher2.addStudent(course1,student2);
     teacher2.addStudent(course2,student2);
     teacher2.addStudent(course1,student3);
     teacher2.removeStudent(course1,student3);
     ArrayList<Student> students= course1.getStudents();
     for (Student student: students){
      System.out.println(student.getFirstName()+" "+student.getLastName());
     }
     teacher2.assignGrade(course1,student2,13.5);
     System.out.println(student2.getGradeFoTheCourse(course1));
     System.out.println(student2.getCourses().get(0).getName());
     System.out.println(student2.getCurrentTermGPA());
     teacher2.assignGrade(course2,student2,15.5);
     System.out.println(student2.getCurrentTermGPA());
     Assignment assignment1=new Assignment("first","2024-05-01",TypeAssignment.HOMEWORK);
     Assignment assignment2=new Assignment("second","2024-05-06",TypeAssignment.PROJECT);
     teacher2.defineAssignment(course2,assignment1);
     teacher2.defineAssignment(course2,assignment2);
     assignment1.howMuchTimeIsLeft();
     teacher2.deleteAssignment(course2,assignment1);
     ArrayList<Assignment>assignments=course2.getAssignments();
     for(Assignment assignment: assignments){
      System.out.println(assignment.getName()+" "+assignment.getDeadline()+" "+assignment.getTypeAssignment());
     }
     student2.printEnrolledCourses();
     course2.printAssignmentDescription();
     course2.printRegisteredStudents();
     course2.addStudent(student3);
     course2.addStudent(student4);
     course2.addStudent(student5);
     teacher2.assignGrade(course2,student3,19.5);
     teacher2.assignGrade(course2,student4,18);
     teacher2.assignGrade(course2,student5,19.5);//highest grade
     course2.highestGrade();


    }

}
