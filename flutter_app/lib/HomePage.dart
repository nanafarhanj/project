import 'package:flutter/material.dart';
import 'package:line_awesome_flutter/line_awesome_flutter.dart';
import 'package:flutter_application_4/Tasks.dart';
import 'package:flutter_application_4/Assignments.dart';
import 'package:flutter_application_4/userInformation.dart';
import 'package:flutter_application_4/events.dart';
import 'package:flutter_application_4/Classes.dart';

class HomePage extends StatefulWidget {
  @override
  HomePageState createState() => HomePageState();
}

class HomePageState extends State<HomePage> {
  int selectedIndex = 0;

  static List<Widget> _pages = <Widget>[
    HomeSection(),
    NewsPage(),
    ClassesPage(),
    TasksPage(),
    AssignmentPage(),
    userInformation(),
  ];

  void _onItemTapped(int index) {
    setState(() {
      selectedIndex = index;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: const Color.fromARGB(255, 55, 123, 241),
        title: Text('Home'),
      ),
      body: _pages[selectedIndex],
      bottomNavigationBar: BottomNavigationBar(
        items: const <BottomNavigationBarItem>[
          BottomNavigationBarItem(
            icon: Icon(Icons.home),
            label: 'Home',
          ),
          BottomNavigationBarItem(
            icon: Icon(Icons.newspaper),
            label: 'News',
          ),
          BottomNavigationBarItem(
            icon: Icon(Icons.book),
            label: 'Courses',
          ),
          BottomNavigationBarItem(
            icon: Icon(Icons.assignment),
            label: 'Tasks',
          ),
          BottomNavigationBarItem(
            icon: Icon(Icons.assignment),
            label: 'Assignment',
          ),
          BottomNavigationBarItem(
            icon: Icon(Icons.person),
            label: 'Profile',
          ),
        ],
        currentIndex: selectedIndex,
        selectedItemColor: Colors.blue,
        onTap: _onItemTapped,
      ),
    );
  }
}

class HomeSection extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return SingleChildScrollView(
      child: Column(
        children: [
          // Summary Section
          SummarySection(),
          // Today's Tasks Section
          TodayTasksSection(),
          // Completed Assignments Report Section
          CompletedAssignmentsSection(),
        ],
      ),
    );
  }
}

class SummarySection extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Card(
      color: Colors.pinkAccent,
      margin: EdgeInsets.all(10.0),
      child: Padding(
        padding: EdgeInsets.all(10.0),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Text(
              'Summary',
              style: TextStyle(fontSize: 20.0, fontWeight: FontWeight.bold),
            ),
            SizedBox(height: 10.0),
            Text('Incomplete Assignments: 5'),
            Text('Incomplete Exams: 2'),
            Text('Deadlines: 3'),
            Text('Best Grade: 100'),
            Text('Worst Grade: 10'),
          ],
        ),
      ),
    );
  }
}

class TodayTasksSection extends StatelessWidget {
  final List<String> tasks = [
    'Complete Math Assignment',
    'Study for Physics Exam',
    'Test Assignment',
  ];

  @override
  Widget build(BuildContext context) {
    return Card(
      color: Colors.orange,
      margin: EdgeInsets.all(10.0),
      child: Padding(
        padding: EdgeInsets.all(10.0),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Text(
              'Today\'s Tasks',
              style: TextStyle(fontSize: 20.0, fontWeight: FontWeight.bold),
            ),
            SizedBox(height: 10.0),
            ...tasks.map((task) {
              return ListTile(
                title: Text(task),
                trailing: Row(
                  mainAxisSize: MainAxisSize.min,

                  children: [
                    IconButton(
                        onPressed: () {
                          
                        },
                        icon: const Icon(LineAwesomeIcons.check_circle_solid)),
                    IconButton(
                        onPressed: () {
                          
                        },
                        icon: Icon(LineAwesomeIcons.trash_alt_solid)),
                  ],
                ),
              );
            }).toList(),
          ],
        ),
      ),
    );
  }
}

class CompletedAssignmentsSection extends StatelessWidget {
  final List<String> completedAssignments = [
    'Math Assignment - Due Date: 2024-07-10',
    'AP Project - Due Date: 2024-07-15',
  ];

  @override
  Widget build(BuildContext context) {
    return Card(
      shadowColor: Colors.black,
      color: Colors.green[100],
      margin: EdgeInsets.all(10.0),
      child: Padding(
        padding: EdgeInsets.all(10.0),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Text(
              'Completed Assignments',
              style: TextStyle(fontSize: 20.0, fontWeight: FontWeight.bold),
            ),
            SizedBox(height: 10.0),
            ...completedAssignments.map((assignment) {
              return ListTile(
                title: Text(assignment),
              );
            }).toList(),
          ],
        ),
      ),
    );
  }
}
