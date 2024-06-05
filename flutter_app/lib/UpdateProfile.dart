import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';
import 'package:line_awesome_flutter/line_awesome_flutter.dart';

class UpdateProfile extends StatelessWidget {
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        leading: IconButton(
            onPressed: () {
              Navigator.pop(context);
            },
            icon: const Icon(LineAwesomeIcons.angle_left_solid)),
        title: Text(
          "Edit Profile",
          textAlign: TextAlign.center,
          style: Theme.of(context).textTheme.headlineMedium,
        ),
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
                      child: const Icon(LineAwesomeIcons.camera_solid,
                          color: Colors.black, size: 20),
                    ),
                  ),
                ],
              ),
              const SizedBox(
                height: 50,
              ),
              Form(
                child: Column(
                  children: [
                    TextFormField(
                      decoration: InputDecoration(
                          label: Text(
                            "Username",
                          ),
                          prefixIcon: Icon(LineAwesomeIcons.user)),
                    ),
                    SizedBox(
                      height: 5,
                    ),
                    TextFormField(
                      decoration: InputDecoration(
                          label: Text(
                            "E-Mail",
                          ),
                          prefixIcon: Icon(LineAwesomeIcons.envelope)),
                    ),
                    SizedBox(
                      height: 5,
                    ),
                    TextFormField(
                      decoration: InputDecoration(
                          label: Text(
                            "Phone Number",
                          ),
                          prefixIcon: Icon(LineAwesomeIcons.phone_solid)),
                    ),
                    SizedBox(
                      height: 5,
                    ),
                    TextFormField(
                      decoration: InputDecoration(
                          label: Text(
                            "Password",
                          ),
                          prefixIcon: Icon(LineAwesomeIcons.fingerprint_solid)),
                    ),
                    SizedBox(
                      height: 25,
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
                    SizedBox(
                      height: 30,
                    ),
                    Row(
                      mainAxisAlignment: MainAxisAlignment.spaceBetween,
                      children: [
                        const Text.rich(
                          TextSpan(
                            text: "Joined ",
                            style: TextStyle(fontSize: 12),
                            children: [
                              TextSpan(
                                text: "4 June 2024",
                                style: TextStyle(
                                    fontWeight: FontWeight.bold, fontSize: 12),
                              ),
                            ],
                          ),
                        ),
                        ElevatedButton(
                          onPressed: () {},
                          style: ElevatedButton.styleFrom(
                              backgroundColor:
                                  Colors.redAccent.withOpacity(0.1),
                              elevation: 0,
                              foregroundColor: Colors.red,
                              shape: const StadiumBorder(),
                              side: BorderSide.none),
                          child: const Text("Delete"),
                        )
                      ],
                    )
                  ],
                ),
              )
            ],
          ),
        ),
      ),
    );
  }
}
