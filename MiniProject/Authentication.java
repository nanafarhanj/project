package MiniProject;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;


public class Authentication {

    Authentication() {
    }
    public void login(String username,String password)throws IOException{
        Thread loginThread=new Thread(()-> {
            synchronized (username) {
                String regexPattern = "^(?=.*[a-zA-Z])(?=.*\\d).+$";
                String regexPattern2 = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$";
                boolean a=false,b=false;
                if (username.matches(regexPattern) && password.matches(regexPattern2) && (password != username)) {
                    BufferedReader reader;
                    try {
                        reader = new BufferedReader(new FileReader("\"C:\\Users\\ASUS\\IdeaProjects\\AP\\src\\text\""));
                        String line;
                        String[] info;
                        while ((line = reader.readLine()) != null) {
                            info = line.split("-");
                            if (info[0].equals(username)) {
                                if (info[1].equals(password)) {
                                    break;
                                }
                                else {
                                    System.out.println("password doesn't match to the username");
                                }
                            }
                        }
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println("login was successful");

                } else if (!username.matches(regexPattern)) {
                    System.out.println("username is not valid");
                } else if (!password.matches(regexPattern2)) {
                    System.out.println("password is not valid");

                }
            }
        });
        loginThread.start();
    }
    public void signup(String username, String password, String email, String id)throws IOException{
        Thread signupThread = new Thread(()-> {
            synchronized (username){
            String userPattern = "^(?=.*[a-zA-Z])(?=.*\\d).+$";
            String passwordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$";
            String emailRegex = "^[0-9A-Za-z-_]+@(gmail)\\.(com)$";
            String idRegex = "\\d{9}";

            if (username.matches(userPattern) && password.matches(passwordPattern) && (!password.equals(username)) && email.matches(emailRegex) && id.matches(idRegex)) {
                Path file = Path.of("\"C:\\Users\\ASUS\\IdeaProjects\\AP\\src\\text\"");
                try{
                Files.writeString(file, username);
                Files.writeString(file, "-");
                Files.writeString(file, password);
                Files.writeString(file, "\n");
                }
                catch (Exception e){ System.out.println(e);}
                System.out.println("signup was successful");
            } else if (!username.matches(userPattern)) {
                System.out.println("username is not valid.");
            } else if (!password.matches(passwordPattern)) {
                System.out.println("password is not valid.");
            }
            else if (!email.matches(emailRegex)) {
                System.out.println("email is not valid.");
            }
            else if (!id.matches(idRegex)) {
                System.out.println("id is not valid.");
            }
            }

        });
        signupThread.start();
    }
    public void logout(){

    }
}
