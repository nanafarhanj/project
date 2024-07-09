import 'dart:io';
import 'package:flutter/material.dart';
import 'HomePage.dart';
import 'package:flutter_application_4/signup.dart';

class LoginPage extends StatefulWidget {
  @override
  LoginPageState createState() => LoginPageState();
}

class LoginPageState extends State<LoginPage> {
  TextEditingController studentID = TextEditingController();
  TextEditingController password = TextEditingController();
  bool userIDChecker = true, passwordChecker = true;
  bool isObscure = false;
  String response = '';
  // bool isObscure = true;
  // final usernameController = TextEditingController();
  // final passwordController = TextEditingController();

  // void login(String username, String password) async {
  //   Socket socket = await Socket.connect('192.168.1.37', 8080);
  //   socket.write('login-$username-$password\n');
  //   socket.flush();
  //   socket.listen((data) {
  //     print(String.fromCharCodes(data).trim());
  //     if (String.fromCharCodes(data).trim() == 'Login successful') {
  //       Navigator.push(
  //         context,
  //         MaterialPageRoute(builder: (context) => HomePage()),
  //       );
  //     } else {
  //       // show error message
  //     }

  //     socket.destroy();
  //   });
  // }

  Future<String> logIn() async {
    await Socket.connect("192.168.137.1", 8080).then((serverSocket) {
      serverSocket
          .write('GET: logInChecker~${studentID.text}~${password.text}\u0000');
      serverSocket.flush();
      serverSocket.listen((socketResponse) {
        setState(() {
          response = String.fromCharCodes(socketResponse);
        });
      });
    });
    print("----------   server response is:  { $response }");

    if (response == "401") {
      userIDChecker = true;
      passwordChecker = false;
    } else if (response == "404") {
      userIDChecker = false;
      passwordChecker = false;
    } else if (response == "200") {
      userIDChecker = true;
      passwordChecker = true;
    }
    return response;
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      resizeToAvoidBottomInset: false,
      backgroundColor: Colors.white,
      appBar: AppBar(
        elevation: 0,
        backgroundColor: Colors.white,
        leading: IconButton(
          onPressed: () {
            Navigator.pop(context);
          },
          icon: Icon(
            Icons.arrow_back_ios,
            size: 20,
            color: Colors.black,
          ),
        ),
      ),
      body: Container(
        height: MediaQuery.of(context).size.height,
        width: double.infinity,
        child: Column(
          mainAxisAlignment: MainAxisAlignment.spaceBetween,
          children: <Widget>[
            Expanded(
                child: Column(
              mainAxisAlignment: MainAxisAlignment.spaceEvenly,
              children: <Widget>[
                Column(
                  children: <Widget>[
                    Text(
                      "Login",
                      style:
                          TextStyle(fontSize: 30, fontWeight: FontWeight.bold),
                    ),
                    SizedBox(
                      height: 20,
                    ),
                    Text(
                      "Login to your account",
                      style: TextStyle(fontSize: 15, color: Colors.grey[700]),
                    )
                  ],
                ),
                Padding(
                  padding: EdgeInsets.symmetric(horizontal: 40),
                  child: Column(
                    children: <Widget>[
                      inputFile(label: "Username", controller: studentID),
                      inputFile(
                          label: "Password",
                          obscureText: true,
                          controller: password),
                    ],
                  ),
                ),
                Padding(
                  padding: EdgeInsets.symmetric(horizontal: 40),
                  child: Container(
                    padding: EdgeInsets.only(top: 3, left: 3),
                    decoration: BoxDecoration(
                        borderRadius: BorderRadius.circular(50),
                        border: Border.all(color: Colors.black)),
                    child: MaterialButton(
                      minWidth: double.infinity,
                      height: 60,
                      onPressed: () {
                        logIn();
                        if (userIDChecker && passwordChecker) {
                          Navigator.pushReplacement(
                            context,
                            MaterialPageRoute(
                              builder: (context) => HomePage(),
                            ),
                          );
                        }
                      },
                      color: Color(0xff0095FF),
                      elevation: 0,
                      shape: RoundedRectangleBorder(
                        borderRadius: BorderRadius.circular(50),
                      ),
                      child: Text(
                        "Login",
                        style: TextStyle(
                          fontWeight: FontWeight.w600,
                          fontSize: 18,
                          color: Colors.white,
                        ),
                      ),
                    ),
                  ),
                ),
                Row(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: <Widget>[
                    Text("Don't have an account?"),
                    GestureDetector(
                      onTap: () {
                        Navigator.push(
                          context,
                          MaterialPageRoute(builder: (context) => SignupPage()),
                        );
                      },
                      child: Text(
                        " Sign up",
                        style: TextStyle(
                          fontWeight: FontWeight.w600,
                          fontSize: 18,
                        ),
                      ),
                    ),
                  ],
                ),
                Container(
                  padding: EdgeInsets.only(top: 100),
                  height: 200,
                  decoration: BoxDecoration(
                    image: DecorationImage(
                        image: AssetImage("assets/background(1).png"),
                        fit: BoxFit.fitHeight),
                  ),
                )
              ],
            ))
          ],
        ),
      ),
    );
  }

  Widget inputFile({label, obscureText = false, controller}) {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: <Widget>[
        Text(
          label,
          style: TextStyle(
              fontSize: 15, fontWeight: FontWeight.w400, color: Colors.black87),
        ),
        SizedBox(
          height: 5,
        ),
        Row(
          children: <Widget>[
            Expanded(
              child: TextField(
                controller: controller,
                obscureText: obscureText && isObscure,
                decoration: InputDecoration(
                  contentPadding:
                      EdgeInsets.symmetric(vertical: 0, horizontal: 10),
                  enabledBorder: OutlineInputBorder(
                    borderSide:
                        BorderSide(color: Color.fromARGB(255, 90, 150, 180)),
                  ),
                  border: OutlineInputBorder(
                    borderSide:
                        BorderSide(color: Color.fromARGB(255, 90, 185, 200)),
                  ),
                ),
              ),
            ),
            if (obscureText)
              IconButton(
                icon: Icon(isObscure ? Icons.visibility_off : Icons.visibility),
                onPressed: () {
                  setState(() {
                    isObscure = !isObscure;
                  });
                },
              ),
          ],
        ),
        SizedBox(height: 10),
      ],
    );
  }
}
