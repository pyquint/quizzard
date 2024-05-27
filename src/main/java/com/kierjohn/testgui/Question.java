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

    private final String type;
    private final String question;
    private final String answer;
    private String[] wrongChoices;
//    private String category;
//    private String difficulty;
    
    public Question(String question, boolean answer) { //, String category, String difficulty) {
        this.type = "boolean";
        this.question = question;
        this.answer = (answer) ? "true" : "false";
    }
    
    public Question(String question, String answer) { //, String category, String difficulty) {
        this.type = "identification";
        this.question = question;
        this.answer = answer;
    }

    public Question(String question, String answer, String[] wrongChoices) { // , String category, String difficulty) {
        this.type = "multiple";
        this.question = question;
        this.answer = answer;
        this.wrongChoices = wrongChoices;
//        this.category = category;
//        this.difficulty = difficulty;
    }
    
    protected boolean isCorrect(String answer) {
        return this.answer.equals(answer);
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

//    protected String getCategory() {
//        return category;
//    }
//
//    protected String getDifficulty() {
//        return difficulty;
//    }
    
    protected String getType() {
        return type;
    }

}
