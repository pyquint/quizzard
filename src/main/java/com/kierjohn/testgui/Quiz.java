/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kierjohn.testgui;

import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author LENOVO
 */
public class Quiz {

    private ArrayList<Question> questions;
    private ArrayList<String> categories;
    private int qCount;

    protected Quiz() {
        questions = new ArrayList<>();
        categories = new ArrayList<>();
    }

    protected Quiz(String category) {
        this();
        categories.add(category);
    }

    protected Quiz(Collection<String> categories) {
        this();
        for (String cat : categories) {
            if (this.categories.contains(cat)) {
                System.out.println("Removed duplicate category \"" + cat + "\".");
            } else {
                this.categories.add(cat);
                System.out.println("Successfully added \"" + cat + "\"");
            }
        }
    }

    protected void addQuestion(String question, String correctAnswer, String[] wrongChoices, String category, String difficulty) {
        if (!categories.contains(category)) {
            System.out.println("Invalid category.");
        }
        addQuestion(new Question(question, correctAnswer, wrongChoices, category, difficulty));
    }

    protected void addQuestion(Question q) {
        questions.add(q);
    }

    protected Question getQuestionAtIndex(int index) {
        return questions.get(index);
    }

    protected int getQCount() {
        return questions.size();
    }

}
