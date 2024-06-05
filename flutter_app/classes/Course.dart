
import 'package:flutter_application_4/Assignment.dart';
import 'package:flutter_application_4/Student.dart';
import 'package:flutter_application_4/Teacher.dart';

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
