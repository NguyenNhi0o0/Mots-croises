/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.crossword.puzzle;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author truon
 */
public class LevelScreen {

    private JFrame frame;

    ArrayList<Integer> listLevel;

    JButton[] listTitle;

    public LevelScreen() {
        frame = new JFrame("Crossword Puzzle");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE );
        frame.setSize(1000, 800);
        frame.setResizable(false);
        ImageIcon image = new ImageIcon("data/icon.png");
        frame.setIconImage(image.getImage());

        listLevel = DataUtil.getListLevel();

        JLabel txtTitle = new JLabel("ABOUT");
        txtTitle.setBounds(30, 30, 150, 50);
        txtTitle.setFont(new Font("Arial", Font.BOLD, 30));
        txtTitle.setHorizontalAlignment(JTextField.CENTER);

        listTitle = new JButton[100];
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 6; col++) {
                initView(row, col);
                frame.add(listTitle[row]);
            }
        }
    }

    private void initView(int row, int col) {
        listTitle[row] = new JButton("LEVEL " + ((row) * 6 + col + 1));
        listTitle[row].setBounds(30 + 155 * col, 30 + 55 * row, 150, 50);
        listTitle[row].setVisible((row ) * 6 + col + 1 <= listLevel.size());
        listTitle[row].addActionListener((ActionEvent e) -> {
            if ((row) * 6 + col + 1 <= listLevel.size()) {
                if (listLevel.get((row) * 6 + col )==0) {
                    PlayGame game = new PlayGame((row) * 6 + col +1, 0);
                    game.show();
                    hide();
                } else {
                    PlayGameV2 game = new PlayGameV2((row) * 6 + col +1, 0);
                    game.show();
                    hide();
                }
            }
        });
    }

    public void hide() {
        frame.setVisible(false);
    }

    public void show() {
        frame.setVisible(true);
    }
}
