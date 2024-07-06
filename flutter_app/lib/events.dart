import 'package:flutter/material.dart';


class NewsPage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return DefaultTabController(
      length: 5, 
      child: Scaffold(
        appBar: AppBar(
          title: Text('News Page'),
          bottom: TabBar(
            isScrollable: true,
            tabs: [
              Tab(text: 'News'),
              Tab(text: 'Events'),
              Tab(text: 'Reminders'),
              Tab(text: 'Deadline extension'),
              Tab(text: 'BDays'),
            ],
          ),
        ),
        body: TabBarView(
          children: [
            NewsTab(),
            EventsTab(),
            RemindersTab(),
            RenewalsTab(),
            BirthdaysTab(),
          ],
        ),
      ),
    );
  }
}

class NewsTab extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return ListView(
      children: [
        NewsCard(
          title: '1st Announcement',
          description: 'Educational Announcement',
          fullDescription: 'Further information',
        ),
        NewsCard(
          title: '2nd Announcement',
          description: 'Educational Announcement',
          fullDescription: 'Further information',
        ),
      ],
    );
  }
}

class NewsCard extends StatelessWidget {
  final String title;
  final String description;
  final String fullDescription;

  NewsCard({required this.title, required this.description, required this.fullDescription});

  @override
  Widget build(BuildContext context) {
    return Card(
      margin: EdgeInsets.all(10),
      child: ListTile(
        title: Text(title),
        subtitle: Text(description),
        onTap: () {
          Navigator.push(
            context,
            MaterialPageRoute(
              builder: (context) => NewsDetailPage(
                title: title,
                fullDescription: fullDescription,
              ),
            ),
          );
        },
      ),
    );
  }
}

class NewsDetailPage extends StatelessWidget {
  final String title;
  final String fullDescription;

  NewsDetailPage({required this.title, required this.fullDescription});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(title),
      ),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Text(fullDescription),
      ),
    );
  }
}




class EventsTab extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Center(child: Text('Events'));
  }
}

class RemindersTab extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Center(child: Text('Reminders'));
  }
}

class RenewalsTab extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Center(child: Text('Deadlines'));
  }
}

class BirthdaysTab extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Center(child: Text('Bdays'));
  }
}