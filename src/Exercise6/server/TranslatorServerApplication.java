package Exercise6.server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class TranslatorServerApplication {

    public static void main(String[] args) throws IOException {
        int portNo = 4228;
        ServerSocket serverSocket = new ServerSocket(portNo);

        // Counter to keep track of the number of requested connections
        int totalRequest = 0;

        Translator translator = new Translator();

        // Server needs to be alive forever
        while (true) {
            // Accept client request for connection
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected: " + clientSocket.getInetAddress().getHostAddress());

            // Create stream to read from the network
            BufferedReader inputStream = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            // Create stream to write on the network
            DataOutputStream outputStream = new DataOutputStream(clientSocket.getOutputStream());

            // Read the English text from the client
            String englishText = inputStream.readLine();

            // Read the language option from the client
            String languageOption = inputStream.readLine();

            // Translate the English text based on the language option
            String translatedText = translateText(englishText, languageOption, translator);

            // Send the translated text back to the client
            outputStream.writeUTF(translatedText);;

            // Close the streams and the socket
            inputStream.close();
            outputStream.close();
            clientSocket.close();
        }
    }

    private static String translateText(String text, String languageOption, Translator translator) {
        String translatedText;
        switch (languageOption) {
            case "1":
                translatedText = translator.translateToArabic(text);
                break;
            case "2":
                translatedText = translator.translateToKorean(text);
                break;
            case "3":
                translatedText = translator.translateToMalay(text);
                break;
            default:
                translatedText = "Invalid language option";
                break;
        }
        return translatedText;
    }
}
