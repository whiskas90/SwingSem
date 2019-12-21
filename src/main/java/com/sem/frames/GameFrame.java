package com.sem.frames;

import com.sem.logic.KeyLogic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameFrame {
    private boolean isOkWord = false;

    public GameFrame(){
        JFrame frame = getFrame();
        JPanel mainPanel = new JPanel();
        JPanel rightPanel = new JPanel();
        JPanel bottomPanel = new JPanel();
        JPanel bottomLeftPanel = new JPanel();
        bottomPanel.setBackground(Color.LIGHT_GRAY);

        mainPanel.setLayout(new BorderLayout());
        JTextArea textArea = new JTextArea();
        JTextArea bottomTextArea = new JTextArea(10,110);
        bottomTextArea.setLineWrap(true);
        bottomTextArea.setWrapStyleWord(true);
        bottomPanel.add(bottomTextArea, BorderLayout.EAST);

        KeyLogic keysLogic = new KeyLogic();
        if(!keysLogic.text.isEmpty()) {
            textArea.setText(keysLogic.text);
            textArea.setEnabled(false);
            textArea.setDisabledTextColor(Color.BLACK);
        } else {
            textArea.setText("LOL");
        }

        JTextArea rightTextArea = new JTextArea(10,10);
        rightTextArea.setLineWrap(true);
        rightTextArea.setWrapStyleWord(true);

        JButton exitButton = new JButton("Выход");
        exitButton.setMaximumSize(new Dimension(Integer.MAX_VALUE,exitButton.getMinimumSize().height));
        exitButton.addActionListener((ActionEvent e)->{
            JOptionPane.showConfirmDialog(frame,"Уверены?");
        });

        JButton sendButton = new JButton("Отправить");
        sendButton.setMaximumSize(new Dimension(Integer.MAX_VALUE,sendButton.getMinimumSize().height));

        JProgressBar progressBar = new JProgressBar();
        progressBar.setStringPainted(true);
        progressBar.setMinimum(0);
        progressBar.setMaximum(100);

        String[] textArr = keysLogic.text.split("");
        bottomTextArea.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                if((e.getKeyCode() >= 32 & e.getKeyCode() <= 126) || e.getKeyCode() == KeyEvent.VK_BACK_SPACE){
                    int currentTextLength = bottomTextArea.getText().length();
                    String currentTextString = "";
                    for (int i = 0; i < currentTextLength; i++) {
                        currentTextString += textArr[i];
                    }
                    if(bottomTextArea.getText().equals(currentTextString)){
                        System.out.println("OK");
                        isOkWord = true;
                        bottomTextArea.setForeground(Color.BLACK);
                        if(bottomTextArea.getText().equals(keysLogic.text)){
                            JOptionPane.showMessageDialog(frame,"Игра закончена");
                        }
                    } else {
                        System.out.println("NO");
                        isOkWord = false;
                        bottomTextArea.setForeground(Color.RED);
                    }
                    if(isOkWord) {
                        double progressCountDouble = (double) currentTextLength / keysLogic.text.length();
                        double progressCount1Double = progressCountDouble * 100;
                        progressBar.setValue((int) progressCount1Double);
                    }
                }
            }
        });
        bottomLeftPanel.setLayout(new BoxLayout(bottomLeftPanel, BoxLayout.Y_AXIS));
        bottomLeftPanel.add(progressBar);
        bottomPanel.add(bottomLeftPanel,FlowLayout.LEFT);

        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        JScrollPane jScrollPane = new JScrollPane(textArea);
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
//        mainPanel.add(textArea);
        mainPanel.add(jScrollPane, BorderLayout.CENTER);


        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.add(exitButton);
        rightPanel.add(rightTextArea);
        rightPanel.add(sendButton);
        rightPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        mainPanel.add(rightPanel, BorderLayout.EAST);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        frame.add(mainPanel);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setResizable(false);
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
