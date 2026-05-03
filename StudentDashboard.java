package application;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;

public class StudentDashboard extends JFrame {

    JButton uploadFileBtn, writeTextBtn;
    JLabel title, info;

    public StudentDashboard() {

        setTitle("Student Assignment Upload Panel");
        setSize(600, 450);                 
        setLayout(null);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(230, 245, 255));
        panel.setBounds(0, 0, 600, 450);
        add(panel);

        // Title
        title = new JLabel("Student Assignment Upload");
        title.setBounds(120, 30, 400, 40);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(new Color(0, 70, 140));
        panel.add(title);

        // Info
        info = new JLabel("Upload ONLY 3 assignments (.txt) OR write text");
        info.setBounds(100, 80, 400, 25);
        info.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(info);

        // Upload File Button
        uploadFileBtn = new JButton("Upload Assignment File");
        uploadFileBtn.setBounds(150, 150, 300, 45);
        uploadFileBtn.setFont(new Font("Arial", Font.BOLD, 16));
        uploadFileBtn.setBackground(new Color(0, 120, 215));
        uploadFileBtn.setForeground(Color.WHITE);
        uploadFileBtn.setFocusPainted(false);
        panel.add(uploadFileBtn);

        // Write Text Button
        writeTextBtn = new JButton("Write/Paste Assignment Text");
        writeTextBtn.setBounds(150, 220, 300, 45);
        writeTextBtn.setFont(new Font("Arial", Font.BOLD, 16));
        writeTextBtn.setBackground(new Color(0, 170, 85)); // green
        writeTextBtn.setForeground(Color.WHITE);
        writeTextBtn.setFocusPainted(false);
        panel.add(writeTextBtn);

        // Action Listeners
        uploadFileBtn.addActionListener(e -> uploadAssignment());
        writeTextBtn.addActionListener(e -> writeAssignmentText());

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

   
 // Upload file
    private void uploadAssignment() {
        if (AssignmentQueue.count == 3) {
            JOptionPane.showMessageDialog(this, "3 assignments already uploaded.\nOpening Teacher Panel.");
            dispose();
            new TeacherDashboard().setVisible(true);
            return;
        }

        // Ask for student name
        String studentName = JOptionPane.showInputDialog(this, "Enter your name:");
        if (studentName == null || studentName.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Name cannot be empty!");
            return;
        }

        JFileChooser chooser = new JFileChooser();
        int r = chooser.showOpenDialog(this);

        if (r == JFileChooser.APPROVE_OPTION) {
            try {
                File f = chooser.getSelectedFile();
                String text = readFile(f);

                int id = AssignmentQueue.count + 1; // auto ID based on count
                Assignment a = new Assignment(id, studentName.trim(), text);
                Main.queue.enqueue(a);

                JOptionPane.showMessageDialog(this, "Assignment uploaded successfully!\nID: " + id + "\nName: " + studentName);

                if (AssignmentQueue.count == 3) {
                    JOptionPane.showMessageDialog(this, "All assignments uploaded.\nTeacher Panel opening.");
                    dispose();
                    new TeacherDashboard().setVisible(true);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error reading file");
            }
        }
    }

    // Write/Paste text
    private void writeAssignmentText() {
        if (AssignmentQueue.count == 3) {
            JOptionPane.showMessageDialog(this, "3 assignments already uploaded.\nOpening Teacher Panel.");
            dispose();
            new TeacherDashboard().setVisible(true);
            return;
        }

        // Ask for student name
        String studentName = JOptionPane.showInputDialog(this, "Enter your name:");
        if (studentName == null || studentName.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Name cannot be empty!");
            return;
        }

        JTextArea textArea = new JTextArea(10, 40);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(textArea);

        int option = JOptionPane.showConfirmDialog(this, scrollPane,
                "Write/Paste Your Assignment Text", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (option == JOptionPane.OK_OPTION) {
            String text = textArea.getText().trim();
            if (text.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Text cannot be empty!");
                return;
            }

            int id = AssignmentQueue.count + 1; // auto ID
            Assignment a = new Assignment(id, studentName.trim(), text);
            Main.queue.enqueue(a);

            JOptionPane.showMessageDialog(this, "Assignment added successfully!\nID: " + id + "\nName: " + studentName);

            if (AssignmentQueue.count == 3) {
                JOptionPane.showMessageDialog(this, "All assignments uploaded.\nTeacher Panel opening.");
                dispose();
                new TeacherDashboard().setVisible(true);
            }
        }
    }



    // Read file manually
    private String readFile(File f) throws Exception {
        FileInputStream fis = new FileInputStream(f);
        StringBuilder data = new StringBuilder();
        int ch;
        while ((ch = fis.read()) != -1) {
            data.append((char) ch);
        }
        fis.close();
        return data.toString();
    }
}
