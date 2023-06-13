package Exercise6.client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class TranslateClientApplication {
    public static void main(String[] args) {

        int serverPort = 4228;

        try {
            // Connect to the server
            Socket clientSocket = new Socket(InetAddress.getLocalHost(), serverPort);
            System.out.println("Connected to server: " + clientSocket.getInetAddress().getHostAddress());

            // Create streams to read and write to the server
            BufferedReader inputFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), StandardCharsets.UTF_8));
            DataOutputStream outputToServer = new DataOutputStream(clientSocket.getOutputStream());

            // Read words from the user and send to the server
            BufferedReader inputFromUser = new BufferedReader(new InputStreamReader(System.in));

            // Prompt the user for a word
            System.out.print("Enter a word: ");
            String word = inputFromUser.readLine();

            // Prompt the user to choose the translation language
            System.out.println("\nChoose a language for translation:");
            System.out.println("1. Arabic");
            System.out.println("2. Korean");
            System.out.println("3. Malay");
            System.out.print("Enter the language option: ");
            String languageOption = inputFromUser.readLine();

            // Send the word and language option to the server
            outputToServer.writeBytes(word + "\n");
            outputToServer.writeBytes(languageOption + "\n");

            // Receive the translated text from the server
            String translatedText = inputFromServer.readLine();
            System.out.println("\nTranslated text received from server: " + translatedText);

            // Close the streams and the socket
            inputFromServer.close();
            outputToServer.close();
            inputFromUser.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
