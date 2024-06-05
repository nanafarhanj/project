
import 'package:flutter_application_4/classes/Course.dart';

class Student {
  String firstName;
  String lastName;
  int studentID;
  int currentSemester;
  int numberOfCourses;
  List<Course> courses;
  Map<Course, double> grades;
  int totalUnits;
  double overallGPA;
  double currentTermGPA;
  Student(
      {required this.firstName,
      required this.lastName,
      required this.studentID,
      required this.currentSemester})
      : numberOfCourses = 0,
        courses = [],
        grades = {},
        totalUnits = 0,
        overallGPA = 0.0,
        currentTermGPA = 0.0;
}
