package application;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class TeacherDashboard extends JFrame {

    public TeacherDashboard() {

        setTitle("Assignment Similarity Checker");
        setSize(650, 550);
        setLayout(null);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(230, 245, 255));

        // Title
        JLabel title = new JLabel("Assignment-to-Assignment Similarity Checker");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setForeground(new Color(0, 70, 140));
        title.setBounds(50, 10, 550, 40);
        add(title);

        // TextArea to show results
        JTextArea resultArea = new JTextArea();
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        resultArea.setBackground(new Color(245, 245, 245));
        resultArea.setBorder(new LineBorder(new Color(0, 70, 140), 2, true));

        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setBounds(50, 70, 540, 350);
        add(scrollPane);

        // Check button
        JButton checkBtn = new JButton("Check Similarity");
        checkBtn.setBounds(225, 440, 200, 40);
        checkBtn.setBackground(new Color(0, 120, 215));
        checkBtn.setForeground(Color.WHITE);
        checkBtn.setFont(new Font("Arial", Font.BOLD, 16));
        checkBtn.setFocusPainted(false);
        checkBtn.setBorder(new LineBorder(new Color(0, 70, 140), 2, true));
        add(checkBtn);

        checkBtn.addActionListener(e -> showStudentWiseSimilarity(resultArea));

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void showStudentWiseSimilarity(JTextArea resultArea) {
        Assignment[] arr = Main.queue.toArray();

        if (arr.length < 2) {
            resultArea.setText("Upload at least 2 assignments!");
            return;
        }

        StringBuilder output = new StringBuilder();
        output.append("Student-wise Assignment Similarity\n");
        output.append("====================================\n");

        int maxSimilarity = 0;
        String maxPair = "";

        // For each student, compare with all other students
        for (int i = 0; i < arr.length; i++) {
            output.append(arr[i].student).append(" Comparison:\n");

            for (int j = 0; j < arr.length; j++) {
                if (i != j) {
                    int sim = SimilarityChecker.calculateApproximate(arr[i].text, arr[j].text);
                    output.append("  vs ").append(arr[j].student)
                            .append(" : ").append(sim).append("%\n");

                    // Track max similarity pair
                    if (sim > maxSimilarity) {
                        maxSimilarity = sim;
                        maxPair = arr[i].student + " & " + arr[j].student;
                    }
                }
            }
            output.append("\n");
        }

        output.append("====================================\n");
        output.append("Maximum Similarity Pair: ").append(maxPair)
                .append(" → ").append(maxSimilarity).append("%\n");

        resultArea.setText(output.toString());
    }

    public static void main(String[] args) {
        new TeacherDashboard();
    }
}
