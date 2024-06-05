import 'package:flutter_application_4/Course.dart';

class Teacher {
  String firstName;
  String lastName;
  int numberOfCourses;
  List<Course> courses;

  Teacher({required this.firstName,
   required this.lastName})
      : numberOfCourses = 0,
        courses = [];
}
