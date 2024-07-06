import 'package:flutter/material.dart';
import 'package:time_planner/time_planner.dart';

class ClassesPage extends StatefulWidget {
  @override
  _ClassesPageState createState() => _ClassesPageState();
}

class _ClassesPageState extends State<ClassesPage> {
  List<Class> classes = [];

  void _addClass(Class newClass) {
    setState(() {
      classes.add(newClass);
    });
  }

  void _showAddClassDialog() {
    String courseNumber = '';
    String courseName = '';
    String instructor = '';
    int units = 0;
    int remainingAssignments = 0;
    String topStudent = '';
    DateTime classTime = DateTime.now();
    Duration duration = Duration(hours: 1);

    showDialog(
      context: context,
      builder: (BuildContext context) {
        return AlertDialog(
          title: Text('Add New Class'),
          content: SingleChildScrollView(
            child: Column(
              children: [
                TextField(
                  onChanged: (value) => courseNumber = value,
                  decoration: InputDecoration(labelText: 'Course Number'),
                ),
                TextField(
                  onChanged: (value) => courseName = value,
                  decoration: InputDecoration(labelText: 'Course Name'),
                ),
                TextField(
                  onChanged: (value) => instructor = value,
                  decoration: InputDecoration(labelText: 'Instructor'),
                ),
                TextButton(
                  onPressed: () async {
                    final dateTime = await showDatePicker(
                      context: context,
                      initialDate: DateTime.now(),
                      firstDate: DateTime(2000),
                      lastDate: DateTime(2100),
                    );
                    if (dateTime != null) {
                      setState(() {
                        classTime = dateTime;
                      });
                    }
                  },
                  child: Text('Select Class Date'),
                ),
                TextButton(
                  onPressed: () async {
                    final time = await showTimePicker(
                      context: context,
                      initialTime: TimeOfDay.now(),
                    );
                    if (time != null) {
                      setState(() {
                        classTime = DateTime(
                          classTime.year,
                          classTime.month,
                          classTime.day,
                          time.hour,
                          time.minute,
                        );
                      });
                    }
                  },
                  child: Text('Select Class Time'),
                ),
                TextField(
                  keyboardType: TextInputType.number,
                  onChanged: (value) => duration = Duration(minutes: int.parse(value)),
                  decoration: InputDecoration(labelText: 'Duration (minutes)'),
                ),
              ],
            ),
          ),
          actions: [
            TextButton(
              child: Text('Cancel'),
              onPressed: () {
                Navigator.of(context).pop();
              },
            ),
            TextButton(
              child: Text('Add'),
              onPressed: () {
                final newClass = Class(
                  courseNumber: courseNumber,
                  courseName: courseName,
                  instructor: instructor,
                  units: units,
                  remainingAssignments: remainingAssignments,
                  topStudent: topStudent,
                  classTime: classTime,
                  duration: duration,
                );
                _addClass(newClass);
                Navigator.of(context).pop();
              },
            ),
          ],
        );
      },
    );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Classes'),
      ),
      body: Column(
        children: [
          Expanded(
            child: ListView.builder(
              itemCount: classes.length,
              itemBuilder: (context, index) {
                final classItem = classes[index];
                return Card(
                  color: Colors.primaries[index % Colors.primaries.length],
                  child: ListTile(
                    title: Text('${classItem.courseName} (${classItem.courseNumber})'),
                    subtitle: Column(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        Text('Instructor: ${classItem.instructor}'),
                        Text('units: ${classItem.units}'),
                        Text('Remaining Assignments: ${classItem.remainingAssignments}'),
                        Text('Top Student: ${classItem.topStudent}'),
                      ],
                    ),
                  ),
                );
              },
            ),
          ),
          Expanded(
            child: TimePlanner(
              startHour: 0,
              endHour: 23,
              headers: List.generate(
                7,
                (index) => TimePlannerTitle(
                  title: 'Day',
                  date: "${index + 1}",
                ),
              ),
              tasks: classes.map((classItem) {
                return TimePlannerTask(
                  color: Colors.primaries[classes.indexOf(classItem) % Colors.primaries.length],
                  dateTime: TimePlannerDateTime(
                    day: classItem.classTime.weekday,
                    hour: classItem.classTime.hour,
                    minutes: classItem.classTime.minute,
                  ),
                  minutesDuration: classItem.duration.inMinutes,
                  child: Text(classItem.courseName),
                );
              }).toList(),
            ),
          ),
        ],
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: _showAddClassDialog,
        child: Icon(Icons.add),
      ),
    );
  }
}
class Class {
  final String courseNumber;
  final String courseName;
  final String instructor;
  final int units;
  final int remainingAssignments;
  final String topStudent;
  final DateTime classTime;
  final Duration duration;

  Class({
    required this.courseNumber,
    required this.courseName,
    required this.instructor,
    required this.units,
    required this.remainingAssignments,
    required this.topStudent,
    required this.classTime,
    required this.duration,
  });
}