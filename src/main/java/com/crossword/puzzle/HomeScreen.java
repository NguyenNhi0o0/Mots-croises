package com.crossword.puzzle;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HomeScreen {

    private JFrame frame;

    public HomeScreen() {
        frame = new JFrame("Crossword Puzzle");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 800);
        frame.setResizable(false);
        ImageIcon image = new ImageIcon("data/icon.png");
        frame.setIconImage(image.getImage());
        addLayoutHome();
    }

    public void hide() {
        frame.setVisible(false);
    }

    public void show() {
        frame.setVisible(true);
    }

    private void addLayoutHome() {
        JButton b1 = new JButton("PLAY");
        b1.setBounds(625, 200, 150, 50);
        JButton b2 = new JButton("ABOUT");
        b2.setBounds(625, 300, 150, 50);
        JButton b3 = new JButton("EXIT");
        b3.setBounds(625, 400, 150, 50);
        
        ImageIcon imageHome = new ImageIcon("data/icon.png");
        JLabel labelHome = new JLabel(imageHome);
        labelHome.setBounds(100, 150, 400, 400);
       
        Font font = new Font("Arial", Font.BOLD, 30);
        b1.setFont(font);
        b2.setFont(font);
        b3.setFont(font);

        b1.addActionListener((ActionEvent e) -> {
            LevelScreen playGame = new LevelScreen();
            playGame.show();
            hide();
        });

        b2.addActionListener((ActionEvent e) -> {
            AboutScreen about = new AboutScreen();
            about.show();
        });

        b3.addActionListener((ActionEvent e) -> {
            System.exit(0);
        });
        
        frame.add(b1);
        frame.add(b2);
        frame.add(b3);
        frame.add(labelHome, BorderLayout.CENTER);
        frame.setLayout(null);    
    }

}
