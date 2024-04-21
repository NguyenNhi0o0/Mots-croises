package com.crossword.puzzle;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class PlayGame {

    private JFrame frame;

    private int oritation;
    private int level;

    private int posTitleChoose = 0;

    private ArrayList<QuestionModel> listData;

    private Timer timer;
    int counter = 600;
    private JLabel[] listTitle;

    private JTextField[][] listPuzzle;
    private int[][] viewPuzzle;

    public PlayGame(int lev, int type) {
        listData = new ArrayList<>();
        listData.addAll(DataUtil.getDataLevel(lev));
        initView(lev);
    }

    //======================================================= InitView ----------------------------------------------------
    private void initView(int level) {
        this.level = level;
        frame = new JFrame("Crossword Puzzle");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1400, 800);
        frame.setResizable(false);
        frame.setLayout(null);
        ImageIcon image = new ImageIcon("data/icon.png");
        frame.setIconImage(image.getImage());

        Font font = new Font("Arial", Font.BOLD, 30);

        addPuzzle();
        addTitle();

        JButton b1 = new JButton("CHECK");
        b1.setBounds(800, 550, 150, 50);
        b1.setFont(font);
        b1.addActionListener((ActionEvent e) -> {
            testAnswer();
        });

        JLabel txtTime = new JLabel("10:00");
        txtTime.setBounds(1100, 550, 150, 50);
        txtTime.setFont(font);
        txtTime.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        txtTime.setHorizontalAlignment(JTextField.CENTER);

        JLabel txtLevel = new JLabel("LEVEL " + this.level);
        txtLevel.setBounds(900, 30, 150, 50);
        txtLevel.setFont(font);
        txtLevel.setHorizontalAlignment(JTextField.CENTER);

        ImageIcon imageHome = new ImageIcon("data/icon_home.png");
        JButton btnHome = new JButton(imageHome);
        btnHome.setBounds(1250, 30, 50, 50);
        btnHome.setFont(font);
        btnHome.addActionListener((ActionEvent e) -> {
            int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to go home?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                HomeScreen playGame = new HomeScreen();
                playGame.show();
                hide();
            }
        });
        
        frame.add(b1);
        frame.add(txtTime);
        frame.add(txtLevel);
        frame.add(btnHome);

        clickTitle(posTitleChoose, true);

        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                counter--;
                txtTime.setText(DataUtil.timeFomat(counter));
                if (counter == 0) {
                    timer.stop();
                    int result = JOptionPane.showConfirmDialog(null, "Time is up. Do you want to start again?", "Confirm", JOptionPane.YES_NO_OPTION);
                    if (result == JOptionPane.YES_OPTION) {
                        PlayGame playGame = new PlayGame(level, 0);
                        playGame.show();
                        hide();
                    } else {
                        HomeScreen playGame = new HomeScreen();
                        playGame.show();
                        hide();
                    }
                }
            }
        });

        timer.start();
    }

    private void testAnswer() {
        int oInput = 0;
        int oAnswer = 0;
        for (int row = 0; row < listData.size(); row++) {
            String ansAll = listData.get(row).getAnswer();
            int startAll = listData.get(row).getStart();
            for (int col = 0; col < 11; col++) {
                if (viewPuzzle[row][col] == 0) {
                    oAnswer++;
                    String value = listPuzzle[row][col].getText().trim();
                    if (value.length() != 0) {
                        int index = col - 1 - startAll;
                        String ansString = ansAll.substring(index, index + 1).toUpperCase();
                        if (ansString.equals(value)) {
                            listPuzzle[row][col].setBackground(Color.white);
                            oInput++;
                        } else {
                            listPuzzle[row][col].setBackground(Color.red);
                        }
                    } else {
                        listPuzzle[row][col].setBackground(Color.white);
                    }
                }
            }
        }

        if (oInput == oAnswer) {
            timer.stop();
            int result = JOptionPane.showConfirmDialog(null, "You have won the game. Click Yes to go home!", "Congratulations", JOptionPane.YES_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                HomeScreen playGame = new HomeScreen();
                playGame.show();
                hide();
            }
        }
    }

    //======================================================= Composenent ----------------------------------------------------
    private void addTitle() {
        JPanel titlePanel = new JPanel();
        titlePanel.setBounds(800, 100, 500, 400);
        titlePanel.setLayout(new GridLayout(11, 1));
        listTitle = new JLabel[10];
        for (int row = 0; row < 10; row++) {
            initTitle(row);
            titlePanel.add(listTitle[row]);
        }
        frame.add(titlePanel);
    }

    private void initTitle(int row) {
        listTitle[row] = new JLabel(String.format("%2d. %s", row + 1, listData.get(row).getTitle()));
        Font font = new Font("Arial", Font.PLAIN, 22);
        listTitle[row].setFont(font);
        listTitle[row].setBackground(Color.BLACK);
        listTitle[row].addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                clickTitle(row, true);
            }
        });

        listTitle[row].setBackground(Color.red);
    }

    private void clickTitle(int row, boolean isFocus) {
        for (int i = 0; i < 10; i++) {
            listTitle[i].setOpaque(true);
            listTitle[i].setBackground(null);
            listTitle[i].setBorder(null);
            listTitle[row].setForeground(Color.BLACK);
        }
        listTitle[row].setOpaque(true);
        listTitle[row].setBorder(BorderFactory.createLineBorder(Color.BLACK));

        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (viewPuzzle[i][j] == -1) {
                    if (i == row) {
                        listPuzzle[i][j].setBackground(Color.GRAY);
                        if (isFocus) {
                            listPuzzle[i][j + 1].requestFocus();
                        }
                    } else {
                        listPuzzle[i][j].setBackground(Color.getColor("#ffffff"));
                    }

                    break;
                }
            }
        }
    }

    private void addPuzzle() {
        JPanel puzzlePanel = new JPanel();
        puzzlePanel.setBounds(50, 50, 600, 600);
        puzzlePanel.setLayout(new GridLayout(11, 11, 0, 2));

        viewPuzzle = new int[11][11];

        for (int row = 0; row < 11; row++) {
            for (int col = 0; col < 11; col++) {
                viewPuzzle[row][col] = -2;
            }
        }

        for (int i = 0; i < listData.size(); i++) {
            String ans = listData.get(i).getAnswer();
            int jStart = listData.get(i).getStart();
            int jSloved = listData.get(i).getSloved();
            for (int j = 0; j < jStart; j++) {
                viewPuzzle[i][j] = -2;
            }
            viewPuzzle[i][jStart] = -1;
            for (int j = jStart + 1; j <= jStart + ans.length(); j++) {
                viewPuzzle[i][j] = 0;
            }
            for (int j = jStart + ans.length() + 1; j < 11; j++) {
                viewPuzzle[i][j] = -2;
            }
            //viewPuzzle[i][jStart + jSloved + 1] = 1;
        }

        listPuzzle = new JTextField[11][11];
        for (int row = 0; row < 11; row++) {
            for (int col = 0; col < 11; col++) {
                initPuzzleField(row, col);
                puzzlePanel.add(listPuzzle[row][col]);
            }
        }
        frame.add(puzzlePanel);
    }

    private void initPuzzleField(int row, int col) {
        listPuzzle[row][col] = new JTextField(1);
        listPuzzle[row][col].setPreferredSize(new Dimension(30, 30));
        listPuzzle[row][col].setHorizontalAlignment(JTextField.CENTER);
        //listPuzzle[row][col].setCaretColor(listPuzzle[row][col].getBackground());
        Font font = new Font("Arial", Font.BOLD, 30);
        listPuzzle[row][col].setFont(font);

        ((AbstractDocument) listPuzzle[row][col].getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                int currentLength = fb.getDocument().getLength();
                if (currentLength == 1) {
                    fb.replace(0, 1, text.toUpperCase(), attrs);
                } else {
                    super.replace(fb, offset, length, text.toUpperCase(), attrs);
                    listPuzzle[row][col].setBackground(Color.white);
                }
            }
        });
        
        if (viewPuzzle[row][col] == 0) {

            listPuzzle[row][col].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    // Xử lý khi người dùng nhấp chuột vào textField
                    clickTitle(row, false);
                }
            });
        }

        //Ô số thứ tự
        if (viewPuzzle[row][col] == -1) {
            listPuzzle[row][col].setFont(new Font("Arial", Font.PLAIN, 26));
            listPuzzle[row][col].setText("" + (row + 1));
            listPuzzle[row][col].setFocusable(false);
        }

        //Ẩn ô thừa
        listPuzzle[row][col].setVisible(viewPuzzle[row][col] != -2);

    }

    //======================================================= Show and Hide ----------------------------------------------------
    public void hide() {
        frame.setVisible(false);
    }

    public void show() {
        frame.setVisible(true);
    }

}
