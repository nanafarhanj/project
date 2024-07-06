/**import 'package:flutter/material.dart';
import 'package:intl/intl.dart';
import 'package:shared_preferences/shared_preferences.dart';
import 'dart:convert'; 

class TasksPage extends StatefulWidget {
  @override
  _TasksPageState createState() => _TasksPageState();
}

class _TasksPageState extends State<TasksPage> {
  List<Map<String, dynamic>> tasks = [];
  List<Map<String, dynamic>> completedTasks = [];
  SharedPreferences? prefs;

  @override
  void initState() {
    super.initState();
    _loadTasks();
  }

  Future<void> _loadTasks() async {
    prefs = await SharedPreferences.getInstance();
    setState(() {
      tasks = _getTasksFromPrefs('tasks');
      completedTasks = _getTasksFromPrefs('completedTasks');
    });
  }

  List<Map<String, dynamic>> _getTasksFromPrefs(String key) {
    List<String>? tasksJson = prefs?.getStringList(key);
    if (tasksJson == null) {
      return [];
    }
    
    return tasksJson.map((taskJson) {
      try {
        return jsonDecode(taskJson) as Map<String, dynamic>;
      } catch (e) {

        return <String, dynamic>{};
      }
    }).toList();
  }

  Future<void> _saveTasksToPrefs(String key, List<Map<String, dynamic>> tasks) async {
    List<String> tasksJson = tasks.map((task) => jsonEncode(task)).toList();
    await prefs?.setStringList(key, tasksJson);
  }

  void _addTask(String title, DateTime dateTime) {
    setState(() {
      tasks.add({'title': title, 'dateTime': dateTime, 'isDone': false});
      _saveTasksToPrefs('tasks', tasks);
    });
  }

  void _markAsDone(int index) {
    setState(() {
      Map<String, dynamic> task = tasks.removeAt(index);
      task['isDone'] = true;
      completedTasks.add(task);
      _saveTasksToPrefs('tasks', tasks);
      _saveTasksToPrefs('completedTasks', completedTasks);
    });
  }

  void _deleteTask(int index) {
    setState(() {
      tasks.removeAt(index);
      _saveTasksToPrefs('tasks', tasks);
    });
  }

  void _showAddTaskDialog() {
    String title = '';
    DateTime dateTime = DateTime.now();

    showDialog(
      context: context,
      builder: (BuildContext context) {
        return AlertDialog(
          title: Text('Add New Task'),
          content: Column(
            mainAxisSize: MainAxisSize.min,
            children: <Widget>[
              TextField(
                decoration: InputDecoration(labelText: 'Task Title'),
                onChanged: (value) {
                  title = value;
                },
              ),
              SizedBox(height: 10),
              ElevatedButton(
                child: Text('Select Date and Time'),
                onPressed: () {
                  showDatePicker(
                    context: context,
                    initialDate: DateTime.now(),
                    firstDate: DateTime.now(),
                    lastDate: DateTime(2100),
                  ).then((date) {
                    showTimePicker(
                      context: context,
                      initialTime: TimeOfDay.now(),
                    ).then((time) {
                      if (date != null && time != null) {
                        setState(() {
                          dateTime = DateTime(date.year, date.month, date.day, time.hour, time.minute);
                        });
                      }
                    });
                  });
                },
              ),
            ],
          ),
          actions: <Widget>[
            TextButton(
              child: Text('Cancel'),
              onPressed: () {
                Navigator.of(context).pop();
              },
            ),
            TextButton(
              child: Text('Add'),
              onPressed: () {
                if (title.isNotEmpty) {
                  _addTask(title, dateTime);
                  Navigator.of(context).pop();
                }
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
        title: Text('Today\'s Tasks'),
      ),
      body: Padding(
        padding: const EdgeInsets.all(8.0),
        child: Column(
          children: <Widget>[
            Expanded(
              child: ListView.builder(
                itemCount: tasks.length,
                itemBuilder: (context, index) {
                  final task = tasks[index];
                  return Card(
                    child: ListTile(
                      title: Text(task['title']),
                      subtitle: Text(DateFormat('yyyy-MM-dd – kk:mm').format(task['dateTime'])),
                      trailing: Row(
                        mainAxisSize: MainAxisSize.min,
                        children: <Widget>[
                          IconButton(
                            icon: Icon(Icons.check),
                            onPressed: () => _markAsDone(index),
                          ),
                          IconButton(
                            icon: Icon(Icons.delete),
                            onPressed: () => _deleteTask(index),
                          ),
                        ],
                      ),
                    ),
                  );
                },
              ),
            ),
            Divider(),
            Text('Completed Tasks'),
            Expanded(
              child: ListView.builder(
                itemCount: completedTasks.length,
                itemBuilder: (context, index) {
                  final task = completedTasks[index];
                  return Card(
                    child: ListTile(
                      title: Text(task['title']),
                      subtitle: Text(DateFormat('yyyy-MM-dd – kk:mm').format(task['dateTime'])),
                    ),
                  );
                },
              ),
            ),
          ],
        ),
      ),
      floatingActionButton: FloatingActionButton(
        child: Icon(Icons.add),
        onPressed: _showAddTaskDialog,
      ),
    );
  }
}
**/


import 'package:flutter/material.dart';


class TasksPage extends StatefulWidget {
  @override
  _TasksPageState createState() => _TasksPageState();
}

class _TasksPageState extends State<TasksPage> {
  DateTime _dateTime = DateTime.now();

  void _showDatePicker() {
    showDatePicker(
      context: context,
      initialDate: DateTime.now(),
      firstDate: DateTime(1990),
      lastDate: DateTime(2030),
    ).then((value) {
      setState(() {
        _dateTime = value!;
      });
    });
  }

  List<Map<String, dynamic>> tasks = [];
  List<Map<String, dynamic>> completedTasks = [];

  void _addTask(String title, DateTime dateTime) {
    setState(() {
      tasks.add({'title': title, 'dateTime': dateTime, 'isDone': false});
    });
  }

  void _markAsDone(int index) {
    setState(() {
      Map<String, dynamic> task = tasks.removeAt(index);
      task['isDone'] = true;
      completedTasks.add(task);
    });
  }

  void _deleteTask(int index) {
    setState(() {
      tasks.removeAt(index);
    });
  }

  void _showAddTaskDialog() {
    String title = '';
    DateTime dateTime = DateTime.now();

    showDialog(
      context: context,
      builder: (BuildContext context) {
        return AlertDialog(
          title: Text('Add New Task'),
          content: Column(
            mainAxisSize: MainAxisSize.min,
            children: <Widget>[
              TextField(
                decoration: InputDecoration(labelText: 'Task Title'),
                onChanged: (value) {
                  title = value;
                },
              ),
              SizedBox(height: 10),
              ElevatedButton(
                child: Text('Select Date and Time'),
                onPressed: _showDatePicker,
              ),
            ],
          ),
          actions: <Widget>[
            TextButton(
              child: Text('Cancel'),
              onPressed: () {
                Navigator.of(context).pop();
              },
            ),
            TextButton(
              child: Text('Add'),
              onPressed: () {
                if (title.isNotEmpty) {
                  _addTask(title, dateTime);
                  Navigator.of(context).pop();
                }
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
        title: Text('Today\'s Tasks'),
      ),
      body: Padding(
        padding: const EdgeInsets.all(8.0),
        child: Column(
          children: <Widget>[
            Expanded(
              child: ListView.builder(
                itemCount: tasks.length,
                itemBuilder: (context, index) {
                  final task = tasks[index];
                  return Card(
                    child: ListTile(
                      title: Text(task['title']),
                      subtitle: Text(_dateTime.toString(),
                          style: TextStyle(fontSize: 20)),
                      trailing: Row(
                        mainAxisSize: MainAxisSize.min,
                        children: <Widget>[
                          IconButton(
                            icon: Icon(Icons.check),
                            onPressed: () => _markAsDone(index),
                          ),
                          IconButton(
                            icon: Icon(Icons.delete),
                            onPressed: () => _deleteTask(index),
                          ),
                        ],
                      ),
                    ),
                  );
                },
              ),
            ),
            Divider(),
            Text('Completed Tasks'),
            Expanded(
              child: ListView.builder(
                itemCount: completedTasks.length,
                itemBuilder: (context, index) {
                  final task = completedTasks[index];
                  return Card(
                    child: ListTile(
                      title: Text(task['title']),
                      subtitle: Text(_dateTime.toString(),
                          style: TextStyle(fontSize: 20)),
                    ),
                  );
                },
              ),
            ),
          ],
        ),
      ),
      floatingActionButton: FloatingActionButton(
        child: Icon(Icons.add),
        onPressed: _showAddTaskDialog,
      ),
    );
  }
}

















