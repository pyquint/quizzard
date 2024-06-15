/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kierjohn.testgui;

/**
 * @author LENOVO
 */
public class Question {

    private final String type;
    private final String question;
    private final String answer;
    private String[] incorrectAnswers;
    private String category;
    private String difficulty;

    public Question(String question, String answer, String[] incorrectAnswers, String category, String difficulty) {
        this.type = "multiple";
        this.question = question;
        this.answer = answer;
        this.incorrectAnswers = incorrectAnswers;
        this.category = category;
        this.difficulty = difficulty;
    }

    public Question(String question, boolean answer, String category, String difficulty) {
        this.type = "boolean";
        this.question = question;
        this.answer = (answer) ? "true" : "false";
    }

    public Question(String question, String answer, String category, String difficulty) {
        type = "identification";
        this.question = question;
        this.answer = answer;
        this.category = category;
        this.difficulty = difficulty;
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

    protected String[] getIncorrectAnswers() {
        return incorrectAnswers;
    }

    protected String getIncorrectAnswerAtIndex(int index) {
        return incorrectAnswers[index];
    }

    protected String getCategory() {
        return category;
    }

    protected String getDifficulty() {
        return difficulty;
    }

    protected String getType() {
        return type;
    }
}
