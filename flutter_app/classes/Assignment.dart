import 'package:flutter_application_4/TypeAssignment.dart';

class Assignment {
  String name;
  DateTime deadline;
  bool isActive;
  TypeAssignment typeAssignment;
  Assignment(
      {required this.name,
      required this.deadline,
      required this.typeAssignment})
      : isActive = true;
}
