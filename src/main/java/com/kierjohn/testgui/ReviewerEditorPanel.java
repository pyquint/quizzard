/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.kierjohn.testgui;

import java.awt.Component;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author LENOVO
 */
public class ReviewerEditorPanel extends javax.swing.JPanel {

    /**
     * Creates new form ReviewerCreationPanel
     */
    public ReviewerEditorPanel() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        controlsPanel = new javax.swing.JPanel();
        saveReviewerBtn = new javax.swing.JButton();
        questionFormCount = new javax.swing.JLabel();
        clearFormBtn = new javax.swing.JButton();
        addQuestionFormBtn = new javax.swing.JButton();
        qCountLiteralLabel = new javax.swing.JLabel();
        jScrollPane = new javax.swing.JScrollPane();
        questionFormContainerPanel = new javax.swing.JPanel();
        reviewerTitleTextField = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        reviewersList = new javax.swing.JList<>();

        setBackground(new java.awt.Color(106, 49, 144));
        setPreferredSize(new java.awt.Dimension(1280, 645));

        controlsPanel.setBackground(new java.awt.Color(106, 49, 144));
        controlsPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 51)));

        saveReviewerBtn.setBackground(new java.awt.Color(204,255,204));
        saveReviewerBtn.setFont(GlobalUtils.getFont(1, 16)
        );
        saveReviewerBtn.setText("Save Quiz");
        saveReviewerBtn.setBorder(null);
        saveReviewerBtn.setBorderPainted(false);
        saveReviewerBtn.setDefaultCapable(false);
        saveReviewerBtn.setFocusPainted(false);
        saveReviewerBtn.setFocusable(false);
        saveReviewerBtn.setName("playQuizBtn"); // NOI18N
        saveReviewerBtn.setPreferredSize(new java.awt.Dimension(128, 32));
        saveReviewerBtn.setRequestFocusEnabled(false);
        saveReviewerBtn.setRolloverEnabled(false);
        saveReviewerBtn.setVerifyInputWhenFocusTarget(false);
        saveReviewerBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveReviewerBtnActionPerformed(evt);
            }
        });

        questionFormCount.setFont(GlobalUtils.getFont(1, 16)
        );
        questionFormCount.setForeground(new java.awt.Color(250, 242, 249));
        questionFormCount.setText("0");

        clearFormBtn.setBackground(new java.awt.Color(204,255,204));
        clearFormBtn.setFont(GlobalUtils.getFont(1, 16)
        );
        clearFormBtn.setText("Clear Form");
        clearFormBtn.setBorder(null);
        clearFormBtn.setBorderPainted(false);
        clearFormBtn.setDefaultCapable(false);
        clearFormBtn.setFocusPainted(false);
        clearFormBtn.setFocusable(false);
        clearFormBtn.setName("playQuizBtn"); // NOI18N
        clearFormBtn.setPreferredSize(new java.awt.Dimension(128, 32));
        clearFormBtn.setRequestFocusEnabled(false);
        clearFormBtn.setRolloverEnabled(false);
        clearFormBtn.setVerifyInputWhenFocusTarget(false);
        clearFormBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearFormBtnActionPerformed(evt);
            }
        });

        addQuestionFormBtn.setBackground(new java.awt.Color(204,255,204));
        addQuestionFormBtn.setFont(GlobalUtils.getFont(1, 16)
        );
        addQuestionFormBtn.setText("Add Question");
        addQuestionFormBtn.setBorder(null);
        addQuestionFormBtn.setBorderPainted(false);
        addQuestionFormBtn.setDefaultCapable(false);
        addQuestionFormBtn.setFocusPainted(false);
        addQuestionFormBtn.setFocusable(false);
        addQuestionFormBtn.setName("playQuizBtn"); // NOI18N
        addQuestionFormBtn.setPreferredSize(new java.awt.Dimension(128, 32));
        addQuestionFormBtn.setRequestFocusEnabled(false);
        addQuestionFormBtn.setRolloverEnabled(false);
        addQuestionFormBtn.setVerifyInputWhenFocusTarget(false);
        addQuestionFormBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addQuestionFormBtnActionPerformed(evt);
            }
        });

        qCountLiteralLabel.setFont(GlobalUtils.getFont(1, 16)
        );
        qCountLiteralLabel.setForeground(new java.awt.Color(250, 242, 249));
        qCountLiteralLabel.setText("Question Count:");

        javax.swing.GroupLayout controlsPanelLayout = new javax.swing.GroupLayout(controlsPanel);
        controlsPanel.setLayout(controlsPanelLayout);
        controlsPanelLayout.setHorizontalGroup(
            controlsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(controlsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(controlsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(saveReviewerBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addQuestionFormBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(clearFormBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(qCountLiteralLabel)
                    .addComponent(questionFormCount))
                .addContainerGap())
        );
        controlsPanelLayout.setVerticalGroup(
            controlsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(controlsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(addQuestionFormBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addComponent(clearFormBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(saveReviewerBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(qCountLiteralLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(questionFormCount)
                .addContainerGap())
        );

        jScrollPane.setBorder(null);
        jScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane.setMinimumSize(new java.awt.Dimension(0, 0));
        jScrollPane.setViewportView(questionFormContainerPanel);
        jScrollPane.getVerticalScrollBar().setUnitIncrement(16);

        questionFormContainerPanel.setBackground(new java.awt.Color(106, 49, 144));
        questionFormContainerPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 51)));
        questionFormContainerPanel.setFocusable(false);
        questionFormContainerPanel.setRequestFocusEnabled(false);
        questionFormContainerPanel.setVerifyInputWhenFocusTarget(false);
        questionFormContainerPanel.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentRemoved(java.awt.event.ContainerEvent evt) {
                questionFormContainerPanelComponentRemoved(evt);
            }
        });
        questionFormContainerPanel.setLayout(new java.awt.GridLayout(0, 1, 8, 0));
        jScrollPane.setViewportView(questionFormContainerPanel);

        reviewerTitleTextField.setBackground(new java.awt.Color(106, 49, 144));
        reviewerTitleTextField.setFont(GlobalUtils.getFont(0, 25)
        );
        reviewerTitleTextField.setForeground(new java.awt.Color(204, 204, 204));
        reviewerTitleTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        reviewerTitleTextField.setText("Reviewer Name");
        reviewerTitleTextField.setBorder(null);

        reviewersList.setBackground(new java.awt.Color(106, 49, 144));
        reviewersList.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 51)));
        reviewersList.setFont(GlobalUtils.getFont(0, 25)
        );
        reviewersList.setForeground(new java.awt.Color(255, 255, 0));
        reviewersList.setModel(com.kierjohn.testgui.GlobalUtils.reviewersListModel);
        reviewersList.setCellRenderer(new com.kierjohn.testgui.GlobalUtils.ReviewerItemCellRenderer());
        reviewersList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                reviewersListValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(reviewersList);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 601, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(controlsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(339, 339, 339)
                        .addComponent(reviewerTitleTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 525, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(205, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addComponent(reviewerTitleTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 595, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 595, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(controlsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void clearFormBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearFormBtnActionPerformed
        questionFormContainerPanel.removeAll();
        questionFormContainerPanel.revalidate();
        questionFormContainerPanel.repaint();
        questionFormCount.setText("0");
    }//GEN-LAST:event_clearFormBtnActionPerformed

    private void addQuestionFormBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addQuestionFormBtnActionPerformed
        addQuestionForm(new ReviewerQuestionForm(questionFormContainerPanel));
    }//GEN-LAST:event_addQuestionFormBtnActionPerformed

    private int formCount() {
        return questionFormContainerPanel.getComponentCount();
    }

    private void saveReviewerBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveReviewerBtnActionPerformed
        if (questionFormContainerPanel.getComponentCount() != 0) {
            Quiz quiz = new Quiz(reviewerTitleTextField.getText());
            for (Component comp : questionFormContainerPanel.getComponents()) {
                ReviewerQuestionForm qForm = (ReviewerQuestionForm) comp;
                quiz.addQuestion(qForm.getQuestion());
            }
            GlobalUtils.writeQuizToFile(quiz);
        }
    }//GEN-LAST:event_saveReviewerBtnActionPerformed

    private void addQuestionForm(ReviewerQuestionForm qForm) {
        int index = formCount();

        qForm.deleteBtn.addActionListener((java.awt.event.ActionEvent evt1) -> {
            deletedFormActionPerformed(evt1);
        });

        qForm.setIndex(index);
        questionFormContainerPanel.add(qForm);
        questionFormContainerPanel.revalidate();
        questionFormContainerPanel.repaint();
        questionFormCount.setText(index + 1 + "");
    }
    
    private void loadReviewer(Quiz quiz) {
        questionFormContainerPanel.removeAll();
        reviewerTitleTextField.setText(quiz.getName());
        for (Question question : quiz.getQuestions()) {
            addQuestionForm(new ReviewerQuestionForm(questionFormContainerPanel, question));
        }
        questionFormCount.setText(quiz.getQCount() + "");
        questionFormContainerPanel.revalidate();
        questionFormContainerPanel.repaint();
    }

    protected void deletedFormActionPerformed(java.awt.event.ActionEvent evt) {
        JButton deletedBtn = (JButton) evt.getSource();
        ReviewerQuestionForm deletedForm = (ReviewerQuestionForm) deletedBtn.getParent().getParent().getParent();
        int delIndex = deletedForm.getIndex();
        for (int i = delIndex; i < formCount(); i++) {
            ReviewerQuestionForm qForm = (ReviewerQuestionForm) questionFormContainerPanel.getComponent(i);
            qForm.setIndex(i - 1);
        }
    }

    protected JTextField getReviewerTitleTextField() {
        return reviewerTitleTextField;
    }

    private void questionFormContainerPanelComponentRemoved(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_questionFormContainerPanelComponentRemoved

    }//GEN-LAST:event_questionFormContainerPanelComponentRemoved

    private void reviewersListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_reviewersListValueChanged
        if (!evt.getValueIsAdjusting()) {
            try {
                File selectedQuizFile = new File(MainFrame.reviewerSavesDir, reviewersList.getSelectedValue());
                loadReviewer(GlobalUtils.fileToQuiz(selectedQuizFile));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_reviewersListValueChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addQuestionFormBtn;
    private javax.swing.JButton clearFormBtn;
    private javax.swing.JPanel controlsPanel;
    private javax.swing.JScrollPane jScrollPane;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel qCountLiteralLabel;
    private javax.swing.JPanel questionFormContainerPanel;
    private javax.swing.JLabel questionFormCount;
    protected javax.swing.JTextField reviewerTitleTextField;
    private javax.swing.JList<String> reviewersList;
    private javax.swing.JButton saveReviewerBtn;
    // End of variables declaration//GEN-END:variables
}
