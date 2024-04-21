/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.crossword.puzzle;

/**
 *
 * @author truon
 */
public class QuestionModel {
    private String title;
    private String answer;
    private int sloved;
    private int start;


    public QuestionModel() {
    }

    public QuestionModel(String title, String answer, int sloved, int start) {
        this.title = title;
        this.answer = answer;
        this.sloved = sloved;
        this.start = start;
    }
    
    

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getSloved() {
        return sloved;
    }

    public void setSloved(int sloved) {
        this.sloved = sloved;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }
    
    
    
    
}
