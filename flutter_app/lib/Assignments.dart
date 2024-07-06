import 'package:flutter/material.dart';
import 'package:file_picker/file_picker.dart';


class AssignmentPage extends StatefulWidget {
  @override
  _AssignmentPageState createState() => _AssignmentPageState();
}

class _AssignmentPageState extends State<AssignmentPage> {
  DateTime selectedDate = DateTime.now();
  List<Assignment> assignments = [
    Assignment('Math Homework', DateTime.now().subtract(Duration(days: 1)),
        'Complete math exercises', 'A+', 'Well done', 2),
    Assignment('AP Project', DateTime.now().add(Duration(days: 1)),
        'University App', 'B', '', 5),
  ];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Assignments'),
        actions: [
          IconButton(
            icon: Icon(Icons.calendar_today),
            onPressed: () async {
              DateTime? pickedDate = await showDatePicker(
                context: context,
                initialDate: selectedDate,
                firstDate: DateTime(2000),
                lastDate: DateTime(2101),
              );
              if (pickedDate != null && pickedDate != selectedDate)
                setState(() {
                  selectedDate = pickedDate;
                });
            },
          ),
        ],
      ),
      body: Column(
        children: [
          Text('Assignments for: ${selectedDate.toLocal()}'.split(' ')[0]),
          Expanded(
            child: ListView(
              children: assignments.map((assignment) {
                if (assignment.dueDate.day == selectedDate.day &&
                    assignment.dueDate.month == selectedDate.month &&
                    assignment.dueDate.year == selectedDate.year) {
                  return ListTile(
                    title: Text(assignment.title),
                    subtitle: Text(assignment.dueDate.isBefore(DateTime.now())
                        ? 'Past due'
                        : 'Due in ${assignment.dueDate.difference(DateTime.now()).inDays} days'),
                    onTap: () => _showAssignmentDetails(context, assignment),
                  );
                }
                return SizedBox.shrink();
              }).toList(),
            ),
          ),
        ],
      ),
    );
  }

  void _showAssignmentDetails(BuildContext context, Assignment assignment) {
    TextEditingController descriptionController =
        TextEditingController(text: assignment.description);
    TextEditingController submissionDetailsController =
        TextEditingController(text: assignment.submissionDetails);
    TextEditingController estimatedTimeController =
        TextEditingController(text: assignment.estimatedTime.toString());

    showDialog(
      context: context,
      builder: (BuildContext context) {
        return AlertDialog(
          title: Text(assignment.title),
          content: SingleChildScrollView(
            child: ListBody(
              children: [
                Text(
                    'Days remaining: ${assignment.dueDate.difference(DateTime.now()).inDays}'),
                TextField(
                  controller: estimatedTimeController,
                  decoration:
                      InputDecoration(labelText: 'Estimated Time (hours)'),
                  keyboardType: TextInputType.number,
                ),
                TextField(
                  controller: descriptionController,
                  decoration: InputDecoration(labelText: 'Description'),
                ),
                TextField(
                  controller: submissionDetailsController,
                  decoration: InputDecoration(labelText: 'Submission Details'),
                ),
                ElevatedButton(
                  onPressed: () async {
                    FilePickerResult? result = await FilePicker.platform.pickFiles(type: FileType.custom, allowedExtensions: ['pdf']);
                    if (result != null) {
                      // Handle file upload
                    }
                  },
                  child: Text('Upload PDF'),
                ),
              ],
            ),
          ),
          actions: [
            TextButton(
              child: Text('Save'),
              onPressed: () {
                setState(() {
                  assignment.description = descriptionController.text;
                  assignment.submissionDetails =
                      submissionDetailsController.text;
                  assignment.estimatedTime =
                      int.parse(estimatedTimeController.text);
                });
                Navigator.of(context).pop();
              },
            ),
            TextButton(
              child: Text('Cancel'),
              onPressed: () {
                Navigator.of(context).pop();
              },
            ),
          ],
        );
      },
    );
  }
}

class Assignment {
  String title;
  DateTime dueDate;
  String description;
  String grade;
  String submissionDetails;
  int estimatedTime;

  Assignment(this.title, this.dueDate, this.description, this.grade,
      this.submissionDetails, this.estimatedTime);
}
