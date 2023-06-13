package Exercise3.server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class WordCountServerApplication {
    public static void main(String[] args) throws IOException {
        int portNo = 4228;
        ServerSocket serverSocket = new ServerSocket(portNo);

        // Counter to keep track of the number of requested connections
        int totalRequest = 0;

        WordCountGenerator countGenerator = new WordCountGenerator();
        
        // Server needs to be alive forever
        while (true) {

            // Accept client request for connection
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected: " + clientSocket.getInetAddress().getHostAddress());

            // Create stream to read from the network
            BufferedReader inputStream = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            // Create stream to write on the network
            DataOutputStream outputStream = new DataOutputStream(clientSocket.getOutputStream());

            // Read the text from the client
            String text = inputStream.readLine();

            // Count the number of words in the text
            int wordCount = countGenerator.countWords(text);

            // Send the word count back to the client
            outputStream.writeInt(wordCount);

            System.out.println("Accepted connection to from the "
					+ "client. Total request = " + ++totalRequest );
            // Close the streams and the socket
            inputStream.close();
            outputStream.close();
            clientSocket.close();
        }
    }

}