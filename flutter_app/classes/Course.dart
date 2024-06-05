import 'package:flutter_application_4/classes/Assignment.dart';
import 'package:flutter_application_4/classes/Student.dart';
import 'package:flutter_application_4/classes/Teacher.dart';

class Course {
  Teacher teacher;
  String name;
  final int units;
  DateTime examDate;
  List<Student> students;
  List<Assignment> assignments;
  bool isActive;
  int numberOfStudents;
  int numberOfAssignments;

  Course(
      {required this.name,
      required this.teacher,
      required this.units,
      required this.examDate})
      : students = [],
        assignments = [],
        isActive = false,
        numberOfAssignments = 0,
        numberOfStudents = 0;

}
