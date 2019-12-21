package com.sem.frames;

import com.sem.logic.KeyLogic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class GameFrame {

    public GameFrame(){
        JFrame frame = getFrame();
        JPanel mainPanel = new JPanel();
        JPanel rightPanel = new JPanel();
        JPanel bottomPanel = new JPanel();

        mainPanel.setLayout(new BorderLayout());
        JTextArea textArea = new JTextArea();
        JTextArea bottomTextArea = new JTextArea(10,100);
        bottomTextArea.setLineWrap(true);
        bottomTextArea.setWrapStyleWord(true);
        bottomPanel.add(bottomTextArea);

        KeyLogic keysLogic = new KeyLogic();
        if(!keysLogic.text.isEmpty()) {
            textArea.setText(keysLogic.text);
            textArea.setEnabled(false);
            textArea.setDisabledTextColor(Color.BLACK);
        } else {
            textArea.setText("LOL");
        }
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        JScrollPane jScrollPane = new JScrollPane(textArea);
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
//        mainPanel.add(textArea);
        mainPanel.add(jScrollPane, BorderLayout.CENTER);


        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        mainPanel.add(rightPanel, BorderLayout.EAST);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        frame.add(mainPanel);
        frame.setVisible(true);
    }

    private JFrame getFrame() {
        JFrame mainFrame = new JFrame("My App");
        mainFrame.setBounds(150, 150, 800, 600);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JMenuBar menuBar = new JMenuBar();
        JMenu menuFile = new JMenu("File");
        JMenu menuSettings = new JMenu("Settings");
        JMenuItem itemMenuFile = new JMenuItem("Exit");
        JMenuItem aboutMenuSettings = new JMenuItem("About");

        itemMenuFile.addActionListener((ActionEvent e) -> {
            System.exit(0);
        });

        aboutMenuSettings.addActionListener((ActionEvent e) -> {
            JOptionPane.showMessageDialog(mainFrame, "About information");
        });
        menuFile.add(itemMenuFile);
        menuSettings.add(aboutMenuSettings);

        menuBar.add(menuFile);
        menuBar.add(menuSettings);


        mainFrame.getContentPane().add(BorderLayout.NORTH, menuBar);
        mainFrame.setVisible(true);
        mainFrame.revalidate();
        return mainFrame;
    }
}
