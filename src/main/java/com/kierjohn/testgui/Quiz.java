/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kierjohn.testgui;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author LENOVO
 */
public class Quiz {

    private final ArrayList<Question> questions;
    private String name;

    protected Quiz() {
        questions = new ArrayList<>();
    }

    protected Quiz(String name) {
        this();
        this.name = name;
    }

    protected void add(Question q) {
        questions.add(q);
    }

    protected void setName(String name) {
        this.name = name;
    }

    protected String getName() {
        return name;
    }

    protected Question getQuestionAtIndex(int index) {
        return questions.get(index);
    }

    protected Iterable<Question> getQuestions() {
        return Collections.unmodifiableCollection(questions);
    }

    protected int size() {
        return questions.size();
    }
}
