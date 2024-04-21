/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.crossword.puzzle;

import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author truon
 */
public class AboutScreen {

    private JFrame frame;

    public AboutScreen() {
        frame = new JFrame("Crossword Puzzle");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1000, 800);
        frame.setResizable(false);
        ImageIcon image = new ImageIcon("data/icon.png");
        frame.setIconImage(image.getImage());

        JLabel txtTitle = new JLabel("ABOUT");
        txtTitle.setBounds(425, 30, 150, 50);
        txtTitle.setFont(new Font("Arial", Font.BOLD, 30));
        txtTitle.setHorizontalAlignment(JTextField.CENTER);
        
        JLabel txtTitle2 = new JLabel();
        txtTitle.setBounds(425, 30, 150, 50);
        txtTitle.setFont(new Font("Arial", Font.BOLD, 30));
        txtTitle.setHorizontalAlignment(JTextField.CENTER);

        JLabel txtContent = new JLabel("<html>Read the suggestions: Read all the suggestions carefully and think about words that might match each suggestion.\n<br>"
                + "Determine word length: If you know the length of the word you need to fill in, find words that match this number of characters.\n<br>"
                + "Use existing letters: If there are letters already filled in the crossword, consider words that match the known letters.\n<br>"
                + "Use vocabulary knowledge: Use your vocabulary knowledge to think of words that match the meaning of the suggestion.\n<br>"
                + "Consider the context: If there are words filled in in the surrounding crossword, consider the context to help determine the word to fill in.\n<br>"
                + "Try combinations: Try each word one by one and see if it fits with the words filled in the surrounding crossword.\n<br>"
                + "Be patient and experiment: Crosswords may require you to experiment with many different keywords before finding the right one.\n<br>"
                + "Check again: Once you've filled in all the words you think are correct, check each box again to make sure they match the suggestions.\n<br>"
                + "Try other words: If the word you fill in doesn't match the other suggestions, try other words to see if they fit better.\n<br>"
                + "Ask others for suggestions: If you get stuck, ask friends or others for extra help.</html>");
        
        txtContent.setBounds(100, 50, 600, 600);
        txtContent.setFont(new Font("Arial", Font.PLAIN, 24));
        txtContent.setHorizontalAlignment(JTextField.CENTER);

        frame.add(txtTitle);
        frame.add(txtTitle2);
        frame.add(txtContent);
    }

    public void hide() {
        frame.setVisible(false);
    }

    public void show() {
        frame.setVisible(true);
    }
}
