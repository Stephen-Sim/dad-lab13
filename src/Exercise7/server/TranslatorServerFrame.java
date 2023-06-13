package Exercise7.server;

import java.awt.BorderLayout;
import java.awt.Font;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class TranslatorServerFrame extends JFrame {
    private JLabel requestLabel;
    private JTextArea requestDetailsTextArea;
    private int totalRequests;

    public TranslatorServerFrame() {
        // Set JFrame properties
        setTitle("Translator Server");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);

        // Create and configure labels
        requestLabel = new JLabel("Total Requests: 0");
        requestLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        requestLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Create and configure text area for request details
        requestDetailsTextArea = new JTextArea();
        requestDetailsTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(requestDetailsTextArea);

        // Create panel to hold the labels and text area
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(requestLabel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Add panel to the frame
        add(panel);
    }

    public void incrementTotalRequests() {
        totalRequests++;
        updateRequestLabel();
    }
    
    public void addRequestDetail(String detail) {
        requestDetailsTextArea.append("The translated text is " + detail + "\n");
    }

    private void updateRequestLabel() {
        requestLabel.setText("Total Requests: " + totalRequests);
    }
}
