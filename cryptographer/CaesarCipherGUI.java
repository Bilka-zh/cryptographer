import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CaesarCipherGUI extends JFrame {

    private JTextField inputField;
    private JTextField keyField;
    private JTextArea outputArea;

    public CaesarCipherGUI() {
        setTitle("Caesar Cipher Encryption/Decryption");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.fill = GridBagConstraints.HORIZONTAL;

        JLabel inputLabel = new JLabel("Enter Message:");
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(inputLabel, constraints);

        inputField = new JTextField(20);
        constraints.gridx = 1;
        panel.add(inputField, constraints);

        JLabel keyLabel = new JLabel("Enter Key:");
        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(keyLabel, constraints);

        keyField = new JTextField(5);
        constraints.gridx = 1;
        panel.add(keyField, constraints);

        JButton encryptButton = new JButton("Encrypt");
        encryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                encryptMessage();
            }
        });
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.ipady = 20; 
        panel.add(encryptButton, constraints);

        JButton decryptButton = new JButton("Decrypt");
        decryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                decryptMessage();
            }
        });
        constraints.gridx = 1;
        panel.add(decryptButton, constraints);

        outputArea = new JTextArea(10, 30);
        outputArea.setLineWrap(true);
        outputArea.setWrapStyleWord(true);
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        constraints.ipady = 40; 
        panel.add(scrollPane, constraints);

        add(panel);
    }

    private void encryptMessage() {
        String message = inputField.getText();
        try {
            int shiftKey = Integer.parseInt(keyField.getText());
            String encryptedMessage = encrypt(message, shiftKey);
            outputArea.setText("Encrypted Message: " + encryptedMessage);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid key. Please enter a valid integer.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void decryptMessage() {
        String encryptedMessage = inputField.getText();
        try {
            int shiftKey = Integer.parseInt(keyField.getText());
            String decryptedMessage = decrypt(encryptedMessage, shiftKey);
            outputArea.setText("Decrypted Message: " + decryptedMessage);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid key. Please enter a valid integer.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static String encrypt(String message, int shiftKey) {
        StringBuilder encryptedMessage = new StringBuilder();
        for (char c : message.toCharArray()) {
            if (Character.isLetter(c)) {
                char shifted = (char) (c + shiftKey);
                if ((Character.isUpperCase(c) && shifted > 'Z') || (Character.isLowerCase(c) && shifted > 'z')) {
                    shifted = (char) (c - (26 - shiftKey)); 
                }
                encryptedMessage.append(shifted);
            } else {
                encryptedMessage.append(c); 
            }
        }
        return encryptedMessage.toString();
    }

    public static String decrypt(String encryptedMessage, int shiftKey) {
        return encrypt(encryptedMessage, -shiftKey); 
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CaesarCipherGUI().setVisible(true);
            }
        });
    }
}