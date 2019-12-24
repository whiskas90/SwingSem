package com.sem.frames;

import com.sem.socketsapp.clients.ChatClient;
import keeptoo.KButton;
import keeptoo.KTextField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class RegistrationFrame {

    private JTextField login;
    private JPasswordField password;
    private String stringPassword;

    private ChatClient chatClient = new ChatClient();

    public RegistrationFrame() {
        JFrame frame = getFrame();
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(255, 255, 255, 0));

        JLabel text = new JLabel("Registration");
        text.setFont(new Font("TimesRoman", Font.BOLD, 25));
        text.setPreferredSize(new Dimension(50, 50));

        JLabel loginText = new JLabel("Login ");
        loginText.setFont(new Font("TimesRoman", Font.PLAIN, 20));

        JLabel passwordText = new JLabel("Password");
        passwordText.setFont(new Font("TimesRoman", Font.PLAIN, 20));

        login = new JTextField(20);
        login.setFont(new Font("TimesRoman", Font.PLAIN, 20));

        password = new JPasswordField(20);
        password.setFont(new Font("TimesRoman", Font.PLAIN, 20));

        KButton start = new KButton();
        start.setText("GO!");
        start.kBackGroundColor = Color.BLUE;
        start.setPreferredSize(new Dimension(75,75));
        start.setFont(new Font("TimesRoman", Font.BOLD, 20));
        start.setSize(start.getPreferredSize());
        start.setBounds(start.getX(), start.getY(), start.getWidth(), start.getHeight());

        start.addActionListener((ActionEvent e)->{
            stringPassword = new String(password.getPassword());
            if(!login.getText().equals("") && !stringPassword.equals("")){
                Connection connection = new Connection();
                connection.start();
                frame.dispose();
                Game.chatClient = chatClient;
                new Game();
            } else {
                JOptionPane.showMessageDialog(frame,"Не оставляйте поля пустыми");
            }
        });


        Box bText = Box.createHorizontalBox();
        bText.add(text);
        Box logText = Box.createHorizontalBox();
        logText.add(loginText);
        Box log = Box.createHorizontalBox();
        log.add(login);
        Box pass = Box.createHorizontalBox();
        pass.add(password);
        Box passText = Box.createHorizontalBox();
        passText.add(passwordText);
        Box button = Box.createHorizontalBox();
        button.add(start);
        Box box = Box.createVerticalBox();
        box.add(Box.createRigidArea(new Dimension(10,0)));
        box.add(bText);
        box.add(logText);
        box.add(log);
        box.add(passText);
        box.add(pass);
        box.add(button);
        mainPanel.add(box);

        frame.setContentPane(new PaintImage("/c.jpg"));
        Container container = frame.getContentPane();
        container.add(mainPanel);
        frame.setVisible(true);
    }

    private JFrame getFrame() {
        JFrame jFrame = new JFrame() {
        };
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setResizable(false);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        jFrame.setBounds(dimension.width / 2 - 250, dimension.height / 2 - 150, 500, 300);
        return jFrame;
    }

    class Connection extends Thread{
        @Override
        public void run() {
            String serverIpAddress = "localhost";
            int port = 8888;
            String userConnect = chatClient.createJSON(login.getText(), stringPassword);
            chatClient.startConnection(serverIpAddress, port);
            System.out.println(userConnect);
            chatClient.sendMessage(userConnect);
        }
    }
}

