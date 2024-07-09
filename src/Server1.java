import java.util.*;
import java.net.*;
import java.io.*;

class Server1 {
    public static void main(String[] args) throws IOException {
        System.out.println("Welcome to the server!");
        ServerSocket serverSocket = new ServerSocket(8080);
        while (true) {
            System.out.println("Waiting for client...");
            new ClientHandler(serverSocket.accept()).start();
        }
    }
}
class ClientHandler extends Thread {
    Socket socket;
    DataOutputStream dos;
    DataInputStream dis;
    List<String> loggedInUsers ;
//    Vector<User> usersInfo;

    public ClientHandler(Socket socket) throws IOException {
        this.socket = socket;
        dos = new DataOutputStream(socket.getOutputStream());
        dis = new DataInputStream(socket.getInputStream());
        System.out.println("connected to server");
    }

    // convert sever message to string
    public String listener() throws IOException {
        try{
            System.out.println("listener is activated");
            StringBuilder sb = new StringBuilder();
            int index = dis.read();
            while (index != 0) {
                sb.append((char) index);
                index = dis.read();
            }
            Scanner s = new Scanner(sb.toString());
            StringBuilder request = new StringBuilder();
            while (s.hasNextLine()) {
                request.append(s.nextLine());
            }
            System.out.println("listener2 -> read command successfully");
            return request.toString();}
        catch (IOException e) {
            System.out.println("error in listener : " + e);}
        return "Error!";
    }

    // send the response to server
    public void writer(String write) throws IOException {
        dos.writeBytes(write);
        dos.flush();
        dos.close();
        dis.close();
        socket.close();
        System.out.println(write);
        System.out.println("command finished and response sent to server");
    }

    @Override
    public void run() {
        super.run();
        String command;
        try {
            command = listener();
            System.out.println("command received: { " + command + " }");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String[] split = command.split("~");
//        for (String s : split)
//            System.out.println(s);
        switch (split[0]) {
            case "GET: logInChecker": {    //GET: logInChecker~402243056~MN45o9
                // 2 -> both user ID & password is correct so allow signing in
                // 1 -> user ID is correct but password is incorrect
                // 0 -> user ID is incorrect
                boolean signedIn = false;
                int responseOfDatabase = 100;
                try {
                    responseOfDatabase = DataBase.usersChecker(split[1], split[2]);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (responseOfDatabase == 2) {
                    signedIn = true;
                    System.out.println("status code is 200");
                    System.out.println("Successfully logged in!");
                    try {
                        writer("200");
                        loggedInUsers.add(split[1]);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                else if (responseOfDatabase == 1) {
                    signedIn = false;
                    System.out.println("status code is 401");
                    System.out.println("Password is incorrect!");
                    try {
                        writer("401");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                else if (responseOfDatabase == 0) {
                    signedIn = false;
                    System.out.println("status code is 404");
                    System.out.println("User not founded!");
                    try {
                        writer("404");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
            }
            case "signUp": {
                // checks the userName if it's taken, the response is zero and usr is not added
                // signup~email~userName~password
//                boolean duplicate = false;
//                String userName = split[2];
//                for (User user : usersInfo) {
//                    if (user.userName.equals(userName)) {
//                        try {
//                            writer("0");
//                            duplicate = true;
//                            break;
//                        } catch (IOException e) {
//                            throw new RuntimeException(e);
//                        }
//                    }
//                }
//                if (!duplicate) {
//                    User user = new User(split[1], split[2], split[3]);
//                    usersInfo.add(user);
//                    try {
//                        DataBase.addUser(user);
//                        writer("1");
//                    } catch (IOException e) {
//                        throw new RuntimeException(e);
//                    }
//                }
//                break;
            }
            case "GET: userInfo" : {
                if (loggedInUsers.contains(split[1])){
                    try {
                        writer(DataBase.userinfo(split[1]));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
            }
            case "Assignment" : {
                try (
                     PrintWriter writer = new PrintWriter(dos, true)) {
                    List<StudentAssignment> students = Assignment.readStudents("F:\\App\\userAssignment.txt");
                    List<Assignment1> assignments = Assignment.readAssignments("F:\\App\\assignment.txt");
                    // Sample: send student assignments
                    for (StudentAssignment student : students) {
                        writer.println("Student: " + student.username + ", " + student.password);
                        for (String assignmentCourse : student.assignments) {
                            for (Assignment1 assignment : assignments) {
                                if (assignment.course.equals(assignmentCourse)) {
                                    writer.println("Assignment: " + assignment.course + ", " + assignment.title + ", " + assignment.instructor + ", " +  ", " + assignment.remainingDays + ", " + assignment.score + ", " + assignment.deadline + ", " + assignment.information + ", " + assignment.estimatedTime);
                                }
                            }
                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            case "Course" : {
                try (
                        PrintWriter writer = new PrintWriter(dos, true)) {
                    List<StudentCourse> students = Courses.readStudentCourse("F:\\App\\userCourse.txt");
                    List<ClassInfo> courses = Courses.readClasses("F:\\App\\course.txt");
                    // Sample: send student assignments
                    for (StudentCourse student : students) {
                        writer.println("Student: " + student.getUsername() + ", " + student.getPassword());
                        for (String courseName : student.getEnrolledClasses()) {
                            for (ClassInfo course : courses) {
                                if (course.getCourseName().equals(courseName)) {
                                    writer.println("Courses: " + course.getCourseName() + ", " + course.getInstructor() + ", " + course.getUnits() + ", " + course.getRemainingAssignments() + ", " + course.getTopStudent());
                                }
                            }
                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            case "Task" : {
                try (
                        PrintWriter writer = new PrintWriter(dos, true)) {
                    List<StudentTasks> students = Tasks.readStudentTasks("F:\\App\\userTasks.txt");
                    List<Task> tasks = Tasks.readTasks("F:\\App\\tasks.txt");
                    // Sample: send student assignments
                    for (StudentTasks student : students) {
                        writer.println("Student: " + student.username + ", " + student.password);
                        for (String taskName : student.tasks) {
                            for (Task task : tasks) {
                                if (task.task.equals(taskName)) {
                                    writer.println("Tasks: " + task.task + ", " + task.inf);
                                }
                            }
                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            }
        }
    }
