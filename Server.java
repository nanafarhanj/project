import java.io.*;
import java.net.*;

public class Server {
    private static final int PORT = 12345;
    private static final String FILE_PATH = "F:\\App\\users.txt";

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server is listening on port " + PORT);

            while (true) {
                Socket socket = serverSocket.accept();
                new ClientHandler(socket).start();
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static class ClientHandler extends Thread {
        private Socket socket;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

                String request = in.readLine();
                String[] parts = request.split("-");

                if (parts[0].equals("signup")) {
                    String username = parts[1];
                    String password = parts[2];
                    handleSignup(username, password, out);
                } else if (parts[0].equals("login")) {
                    String username = parts[1];
                    String password = parts[2];
                    handleLogin(username, password, out);
                }

            } catch (IOException ex) {
                ex.printStackTrace();
            } finally {
                try {
                    socket.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }

        private void handleSignup(String username, String password, PrintWriter out) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
                writer.write(username + "-" + password);
                writer.newLine();
                out.println("Signup successful");
            } catch (IOException ex) {
                ex.printStackTrace();
                out.println("Signup failed");
            }
        }

        private void handleLogin(String username, String password, PrintWriter out) {
            try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
                String line;
                boolean found = false;

                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split("-");
                    if (parts[0].equals(username) && parts[1].equals(password)) {
                        found = true;
                        break;
                    }
                }

                if (found) {
                    out.println("Login successful");
                } else {
                    out.println("Login failed");
                }
            } catch (IOException ex) {
                ex.printStackTrace();
                out.println("Login failed");
            }
        }
    }
}

