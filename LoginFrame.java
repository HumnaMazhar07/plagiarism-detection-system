package application;

import javax.swing.JFrame;
import javax.swing.*;

public class LoginFrame extends JFrame{
	public LoginFrame() {
        setTitle("Login");
        setSize(300,200);
        setLayout(null);
        setLocationRelativeTo(null);

        JButton student = new JButton("Student Login");
        JButton teacher = new JButton("Teacher Login");

        student.setBounds(70,40,150,30);
        teacher.setBounds(70,90,150,30);

        add(student); add(teacher);

        student.addActionListener(e -> {
            new StudentDashboard().setVisible(true);
            dispose();
        });

        teacher.addActionListener(e -> {
            new TeacherDashboard().setVisible(true);
            dispose();
        });

        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
	

}
