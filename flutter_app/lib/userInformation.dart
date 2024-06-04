import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';
import 'package:line_awesome_flutter/line_awesome_flutter.dart';
import 'package:flutter_application_4/UpdateProfile.dart';

class userInformation extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    var isDark = MediaQuery.of(context).platformBrightness == Brightness.dark;
    return Scaffold(
      appBar: AppBar(
        elevation: 0,
        backgroundColor: Colors.white,
        leading: IconButton(
          onPressed: () {},
          icon: const Icon(LineAwesomeIcons.angle_left_solid),
        ),
        title: Text(
          "Profile",
          textAlign: TextAlign.center,
          style: Theme.of(context).textTheme.headlineMedium,
        ),
        actions: [
          IconButton(
              onPressed: () {},
              icon: Icon(isDark ? LineAwesomeIcons.sun : LineAwesomeIcons.moon))
        ],
      ),
      body: SingleChildScrollView(
        child: Container(
          width: double.infinity,
          height: MediaQuery.of(context).size.height,
          padding: EdgeInsets.symmetric(horizontal: 30, vertical: 50),
          child: Column(
            children: [
              Stack(
                children: [
                  SizedBox(
                    width: 120,
                    height: 120,
                    child: ClipRRect(
                        borderRadius: BorderRadius.circular(100),
                        child: const Image(
                            image: AssetImage("assets/profile.png"))),
                  ),
                  Positioned(
                    bottom: 0,
                    right: 0,
                    child: Container(
                      width: 35,
                      height: 35,
                      decoration: BoxDecoration(
                        borderRadius: BorderRadius.circular(100),
                        color: Color.fromARGB(255, 178, 96, 214),
                      ),
                      child: const Icon(LineAwesomeIcons.pencil_alt_solid,
                          color: Colors.black, size: 20),
                    ),
                  ),
                ],
              ),
              const SizedBox(
                height: 10,
              ),
              Text("nazanin",
                  style: Theme.of(context).textTheme.headlineMedium),
              Text("nanafarhanj@gmail.com",
                  style: Theme.of(context).textTheme.bodyMedium),
              const SizedBox(
                height: 20,
              ),
              SizedBox(
                width: 200,
                child: ElevatedButton(
                  onPressed: () {
                    Navigator.push(
                        context,
                        MaterialPageRoute(
                            builder: (context) => UpdateProfile()));
                  },
                  style: ElevatedButton.styleFrom(
                      backgroundColor: Colors.blue,
                      side: BorderSide.none,
                      shape: const StadiumBorder()),
                  child: const Text("Edit Profile",
                      style: TextStyle(color: Colors.black)),
                ),
              ),
              const SizedBox(
                height: 30,
              ),
              const Divider(),
              const SizedBox(
                height: 10,
              ),
              Menu(
                  title: "Setting",
                  icon: LineAwesomeIcons.cog_solid,
                  onPress: () {}),
              Menu(
                  title: "User Management",
                  icon: LineAwesomeIcons.user_check_solid,
                  onPress: () {}),
              const Divider(color: Colors.grey),
              const SizedBox(height: 10),
              Menu(
                  title: "Information",
                  icon: LineAwesomeIcons.info_solid,
                  onPress: () {}),
              Menu(
                  title: "Logout",
                  icon: LineAwesomeIcons.sign_out_alt_solid,
                  textColor: Colors.red,
                  endIcon: false,
                  onPress: () {}),
            ],
          ),
        ),
      ),
    );
  }
}

class Menu extends StatelessWidget {
  const Menu({
    super.key,
    required this.title,
    required this.icon,
    required this.onPress,
    this.endIcon = true,
    this.textColor,
  });

  final String title;
  final IconData icon;
  final VoidCallback onPress;
  final bool endIcon;
  final Color? textColor;

  @override
  Widget build(BuildContext context) {
    return ListTile(
      leading: Container(
        width: 40,
        height: 40,
        decoration: BoxDecoration(
            borderRadius: BorderRadius.circular(100),
            color: Colors.grey.withOpacity(0.1)),
        child: Icon(icon, color: Colors.blue),
      ),
      title: Text(title,
          style:
              Theme.of(context).textTheme.bodyLarge?.apply(color: textColor)),
      trailing: endIcon
          ? Container(
              width: 30,
              height: 30,
              decoration: BoxDecoration(
                  borderRadius: BorderRadius.circular(100),
                  color: Colors.grey.withOpacity(0.1)),
              child: const Icon(LineAwesomeIcons.angle_right_solid,
                  size: 18.0, color: Colors.grey))
          : null,
    );
  }
}
