/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kierjohn.testgui;

/**
 *
 * @author LENOVO
 */
public class Question {
    private int choicesCount = 4;
    private String question;
    private String answer;
    private String[] wrongChoices;
    private String category;
    private String difficulty;
    
    public Question(String question, String answer, String[] wrongChoices, String category, String difficulty) {
        this.question = question;
        this.answer = answer;
        if (wrongChoices.length != this.choicesCount - 1)
            throw new IllegalArgumentException("Number of wrong choices must be 1 less than choice count (currently " + choicesCount + ").");
        this.wrongChoices = wrongChoices;
        this.category = category;
        this.difficulty = difficulty;
    }
    
    protected void setChoiceCount(int newCount) {
        choicesCount = newCount;
    }
    
    protected int getChoicesCount() {
        return choicesCount;
    }
    
    protected String getQuestion() {
        return question;
    }
    
    protected String getAnswer() {
        return answer;
    }
    
    protected String[] getWrongChoices() {
        return wrongChoices;
    }
    
    protected String getWrongChoiceAtIndex(int index) {
        return wrongChoices[index];
    }
    
    protected String getCategory() {
        return category;
    }
    
    protected String getDifficulty() {
        return difficulty;
    }
    
}
