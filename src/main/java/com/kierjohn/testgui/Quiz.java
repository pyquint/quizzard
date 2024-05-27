/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kierjohn.testgui;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author LENOVO
 */
public class Quiz {

    private ArrayList<Question> questions;
    private ArrayList<String> categories;
    private String name;
    
    protected Quiz() {
        questions = new ArrayList<>();
        categories = new ArrayList<>();
    }

    protected Quiz(String name) {
        this();
        this.name = name;
    }

    protected Quiz(String name, String category) {
        this(name);
        categories.add(category);
    }

    protected Quiz(String name, Collection<String> categories) {
        this(name);
        for (String cat : categories) {
            if (this.categories.contains(cat)) {
                System.out.println("Removed duplicate category \"" + cat + "\".");
            } else {
                this.categories.add(cat);
                System.out.println("Successfully added \"" + cat + "\"");
            }
        }
    }

    protected void setName(String name) {
        this.name = name;
    } 
    
    protected String getName() {
        return name;
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

    protected Question[] getQuestions() {
        return questions.toArray(new Question[questions.size()]);
    }
}
