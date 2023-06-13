package Exercise7.client;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class TranslatorClientFrame extends JFrame {
    private JRadioButton arabicRadioButton;
    private JRadioButton koreanRadioButton;
    private JRadioButton malayRadioButton;
    private JTextField textField;
    private JLabel translatedTextLabel;

    public TranslatorClientFrame() {
        // Set JFrame properties
        setTitle("Translator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 300);
        setLayout(new GridLayout(6, 1));

        // Create radio buttons
        arabicRadioButton = new JRadioButton("Arabic");
        koreanRadioButton = new JRadioButton("Korean");
        malayRadioButton = new JRadioButton("Malay");

        // Create a button group for radio buttons
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(arabicRadioButton);
        buttonGroup.add(koreanRadioButton);
        buttonGroup.add(malayRadioButton);

        // Create text field
        textField = new JTextField();

        // Create button
        JButton translateButton = new JButton("Translate");

        // Create label
        translatedTextLabel = new JLabel();

        // Add components to the frame
        add(arabicRadioButton);
        add(koreanRadioButton);
        add(malayRadioButton);
        add(textField);
        add(translateButton);
        add(translatedTextLabel);

        // Add action listener to the translate button
        translateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get selected language option
                String languageOption;
                if (arabicRadioButton.isSelected()) {
                    languageOption = "1";
                } else if (koreanRadioButton.isSelected()) {
                    languageOption = "2";
                } else if (malayRadioButton.isSelected()) {
                    languageOption = "3";
                } else {
                    // No language option selected
                    JOptionPane.showMessageDialog(null, "Please select a language option", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Get text from the text field
                String text = textField.getText();

                int serverPort = 4228;

                try {
                    // Connect to the server
                    Socket clientSocket = new Socket(InetAddress.getLocalHost(), serverPort);
                    System.out.println("Connected to server: " + clientSocket.getInetAddress().getHostAddress());

                    // Create streams to read and write to the server
                    BufferedReader inputFromServer = new BufferedReader(
                            new InputStreamReader(clientSocket.getInputStream(), StandardCharsets.UTF_8));
                    DataOutputStream outputToServer = new DataOutputStream(clientSocket.getOutputStream());

                    // Send the word and language option to the server
                    outputToServer.writeBytes(text + "\n");
                    outputToServer.writeBytes(languageOption + "\n");

                    // Receive the translated text from the server
                    String translatedText = inputFromServer.readLine();
                    translatedTextLabel.setText(translatedText);

                    // Close the streams and the socket
                    inputFromServer.close();
                    outputToServer.close();
                    clientSocket.close();
                } catch (UnknownHostException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}
