/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.kierjohn.testgui;

import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.JComboBox;

/**
 *
 * @author LENOVO
 */
public class QuizQuestionForm extends javax.swing.JPanel {
    private final java.awt.Container parent;
    /**
     * Creates new form QuizQuestionForm
     * @param parentContainer
     */
    public QuizQuestionForm(java.awt.Container parentContainer) {
        initComponents();
        parent = parentContainer;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        formPanel = new javax.swing.JPanel();
        upperFormPanel = new javax.swing.JPanel();
        qTypeInput = new javax.swing.JComboBox<>();
        questionTextField3 = new javax.swing.JTextField();
        deleteBtn = new javax.swing.JButton();
        titleLabel = new javax.swing.JLabel();
        indexLabel = new javax.swing.JLabel();
        bottonFormPanelContainer = new javax.swing.JPanel();
        multipleChoiceFormPanel = new javax.swing.JPanel();
        correctAnswer1 = new javax.swing.JTextField();
        wrongChoiceTextField3 = new javax.swing.JTextField();
        wrongChoiceTextField5 = new javax.swing.JTextField();
        wrongChoiceTextField6 = new javax.swing.JTextField();
        trueOrFalseFormPanel = new javax.swing.JPanel();
        jToggleButton3 = new javax.swing.JToggleButton();
        defaultBooleanLabel1 = new javax.swing.JPanel();
        correctAnswerLabe2 = new javax.swing.JLabel();
        jToggleButton4 = new javax.swing.JToggleButton();
        identificationFormPanel = new javax.swing.JPanel();
        identificationAnswer = new javax.swing.JTextField();
        barrier = new javax.swing.JPanel();

        setBackground(new java.awt.Color(106, 49, 144));
        setOpaque(false);
        setRequestFocusEnabled(false);

        formPanel.setBackground(new java.awt.Color(106, 49, 144));
        formPanel.setLayout(new java.awt.GridBagLayout());

        upperFormPanel.setBackground(new java.awt.Color(106, 49, 144));

        qTypeInput.setBackground(new Color(255, 255, 255));
        qTypeInput.setFont(new java.awt.Font("DejaVu Sans Condensed", 0, 16)); // NOI18N
        qTypeInput.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Multiple Choice", "Identification", "True or False" }));
        qTypeInput.setToolTipText("");
        qTypeInput.setName("qDiff"); // NOI18N
        qTypeInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                qTypeInputActionPerformed(evt);
            }
        });

        questionTextField3.setBackground(new java.awt.Color(204, 204, 204));
        questionTextField3.setFont(new java.awt.Font("DejaVu Sans Condensed", 0, 16)); // NOI18N
        questionTextField3.setForeground(new java.awt.Color(51, 51, 51));
        questionTextField3.setText("Question");

        deleteBtn.setBackground(new Color(255, 255, 255));
        deleteBtn.setFont(new java.awt.Font("DejaVu Sans Condensed", 0, 16)); // NOI18N
        deleteBtn.setText("Delete");
        deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtnActionPerformed(evt);
            }
        });

        titleLabel.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 16)); // NOI18N
        titleLabel.setForeground(new java.awt.Color(255, 255, 255));
        titleLabel.setText("Question #");

        indexLabel.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 16)); // NOI18N
        indexLabel.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout upperFormPanelLayout = new javax.swing.GroupLayout(upperFormPanel);
        upperFormPanel.setLayout(upperFormPanelLayout);
        upperFormPanelLayout.setHorizontalGroup(
            upperFormPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(upperFormPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(upperFormPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(upperFormPanelLayout.createSequentialGroup()
                        .addComponent(qTypeInput, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(titleLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(indexLabel)
                        .addGap(95, 95, 95)
                        .addComponent(deleteBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(questionTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 557, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(42, Short.MAX_VALUE))
        );
        upperFormPanelLayout.setVerticalGroup(
            upperFormPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(upperFormPanelLayout.createSequentialGroup()
                .addGap(0, 15, Short.MAX_VALUE)
                .addGroup(upperFormPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(qTypeInput, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deleteBtn)
                    .addComponent(titleLabel)
                    .addComponent(indexLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(questionTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 15;
        gridBagConstraints.ipady = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        formPanel.add(upperFormPanel, gridBagConstraints);

        bottonFormPanelContainer.setBackground(new java.awt.Color(51, 51, 51));
        bottonFormPanelContainer.setLayout(new java.awt.CardLayout());

        multipleChoiceFormPanel.setBackground(new java.awt.Color(106, 49, 144));
        multipleChoiceFormPanel.setPreferredSize(new java.awt.Dimension(600, 218));

        correctAnswer1.setFont(new java.awt.Font("DejaVu Sans Condensed", 0, 16)); // NOI18N
        correctAnswer1.setText("Correct Answer");
        correctAnswer1.setPreferredSize(new java.awt.Dimension(64, 32));

        wrongChoiceTextField3.setFont(new java.awt.Font("DejaVu Sans Condensed", 0, 16)); // NOI18N
        wrongChoiceTextField3.setText("Wrong Choice #1");
        wrongChoiceTextField3.setPreferredSize(new java.awt.Dimension(64, 32));

        wrongChoiceTextField5.setFont(new java.awt.Font("DejaVu Sans Condensed", 0, 16)); // NOI18N
        wrongChoiceTextField5.setText("Wrong Choice #2");
        wrongChoiceTextField5.setPreferredSize(new java.awt.Dimension(64, 32));

        wrongChoiceTextField6.setFont(new java.awt.Font("DejaVu Sans Condensed", 0, 16)); // NOI18N
        wrongChoiceTextField6.setText("Wrong Choice #3");
        wrongChoiceTextField6.setPreferredSize(new java.awt.Dimension(64, 32));

        javax.swing.GroupLayout multipleChoiceFormPanelLayout = new javax.swing.GroupLayout(multipleChoiceFormPanel);
        multipleChoiceFormPanel.setLayout(multipleChoiceFormPanelLayout);
        multipleChoiceFormPanelLayout.setHorizontalGroup(
            multipleChoiceFormPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(multipleChoiceFormPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(multipleChoiceFormPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(wrongChoiceTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 558, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(wrongChoiceTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 558, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(wrongChoiceTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 558, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(correctAnswer1, javax.swing.GroupLayout.PREFERRED_SIZE, 558, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        multipleChoiceFormPanelLayout.setVerticalGroup(
            multipleChoiceFormPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(multipleChoiceFormPanelLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(correctAnswer1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(wrongChoiceTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(wrongChoiceTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(wrongChoiceTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        bottonFormPanelContainer.add(multipleChoiceFormPanel, "multiple");

        trueOrFalseFormPanel.setBackground(new java.awt.Color(106, 49, 144));
        trueOrFalseFormPanel.setPreferredSize(new java.awt.Dimension(600, 218));

        jToggleButton3.setBackground(new Color(255, 255, 255));
        jToggleButton3.setFont(new java.awt.Font("DejaVu Sans Condensed", 0, 16)); // NOI18N
        jToggleButton3.setText("Custom Labels");

        defaultBooleanLabel1.setBackground(new java.awt.Color(106, 49, 144));

        correctAnswerLabe2.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 16)); // NOI18N
        correctAnswerLabe2.setForeground(new java.awt.Color(255, 242, 248));
        correctAnswerLabe2.setText("Correct Answer:");

        jToggleButton4.setBackground(new Color(255, 255, 255));
        jToggleButton4.setFont(new java.awt.Font("DejaVu Sans Condensed", 1, 16)); // NOI18N
        jToggleButton4.setText("True");

        javax.swing.GroupLayout defaultBooleanLabel1Layout = new javax.swing.GroupLayout(defaultBooleanLabel1);
        defaultBooleanLabel1.setLayout(defaultBooleanLabel1Layout);
        defaultBooleanLabel1Layout.setHorizontalGroup(
            defaultBooleanLabel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(defaultBooleanLabel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(correctAnswerLabe2)
                .addGap(36, 36, 36)
                .addComponent(jToggleButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        defaultBooleanLabel1Layout.setVerticalGroup(
            defaultBooleanLabel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(defaultBooleanLabel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(defaultBooleanLabel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(correctAnswerLabe2)
                    .addComponent(jToggleButton4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout trueOrFalseFormPanelLayout = new javax.swing.GroupLayout(trueOrFalseFormPanel);
        trueOrFalseFormPanel.setLayout(trueOrFalseFormPanelLayout);
        trueOrFalseFormPanelLayout.setHorizontalGroup(
            trueOrFalseFormPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(trueOrFalseFormPanelLayout.createSequentialGroup()
                .addComponent(defaultBooleanLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(trueOrFalseFormPanelLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jToggleButton3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        trueOrFalseFormPanelLayout.setVerticalGroup(
            trueOrFalseFormPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(trueOrFalseFormPanelLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jToggleButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(defaultBooleanLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        bottonFormPanelContainer.add(trueOrFalseFormPanel, "boolean");

        identificationFormPanel.setBackground(new java.awt.Color(106, 49, 144));
        identificationFormPanel.setPreferredSize(new java.awt.Dimension(600, 218));

        identificationAnswer.setBackground(new java.awt.Color(153, 153, 153));
        identificationAnswer.setFont(new java.awt.Font("DejaVu Sans Condensed", 0, 25)); // NOI18N
        identificationAnswer.setForeground(new java.awt.Color(255, 255, 255));
        identificationAnswer.setText("Correct Answer");
        identificationAnswer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                identificationAnswerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout identificationFormPanelLayout = new javax.swing.GroupLayout(identificationFormPanel);
        identificationFormPanel.setLayout(identificationFormPanelLayout);
        identificationFormPanelLayout.setHorizontalGroup(
            identificationFormPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, identificationFormPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(identificationAnswer, javax.swing.GroupLayout.PREFERRED_SIZE, 564, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        identificationFormPanelLayout.setVerticalGroup(
            identificationFormPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(identificationFormPanelLayout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(identificationAnswer, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        bottonFormPanelContainer.add(identificationFormPanel, "identification");

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 12;
        gridBagConstraints.ipady = -1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.ABOVE_BASELINE;
        formPanel.add(bottonFormPanelContainer, gridBagConstraints);

        barrier.setBackground(new java.awt.Color(51, 0, 51));
        barrier.setRequestFocusEnabled(false);

        javax.swing.GroupLayout barrierLayout = new javax.swing.GroupLayout(barrier);
        barrier.setLayout(barrierLayout);
        barrierLayout.setHorizontalGroup(
            barrierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        barrierLayout.setVerticalGroup(
            barrierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 605;
        gridBagConstraints.ipady = 12;
        formPanel.add(barrier, gridBagConstraints);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(formPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(formPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
        // TODO add your handling code here:
        parent.remove(this);
        parent.revalidate();
        parent.repaint();
    }//GEN-LAST:event_deleteBtnActionPerformed

    private void qTypeInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_qTypeInputActionPerformed
        JComboBox btn = (JComboBox) evt.getSource();
        CardLayout card = (CardLayout) bottonFormPanelContainer.getLayout();
        String qType = ((String) btn.getSelectedItem()).toLowerCase();
        switch (qType) {
            case "multiple choice" -> qType = "multiple";
            case "true or false" -> qType = "boolean";
        }
        card.show(bottonFormPanelContainer, qType);
    }//GEN-LAST:event_qTypeInputActionPerformed

    private void identificationAnswerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_identificationAnswerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_identificationAnswerActionPerformed

    protected void setIndex(int i) {
        index = i;
        indexLabel.setText(i + 1 + "");
    }
    
    protected int getIndex() {
        return index;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    protected javax.swing.JPanel barrier;
    protected javax.swing.JPanel bottonFormPanelContainer;
    protected javax.swing.JTextField correctAnswer1;
    protected javax.swing.JLabel correctAnswerLabe2;
    protected javax.swing.JPanel defaultBooleanLabel1;
    protected javax.swing.JButton deleteBtn;
    protected javax.swing.JPanel formPanel;
    protected javax.swing.JTextField identificationAnswer;
    protected javax.swing.JPanel identificationFormPanel;
    protected javax.swing.JLabel indexLabel;
    protected javax.swing.JToggleButton jToggleButton3;
    protected javax.swing.JToggleButton jToggleButton4;
    protected javax.swing.JPanel multipleChoiceFormPanel;
    protected javax.swing.JComboBox<String> qTypeInput;
    protected javax.swing.JTextField questionTextField3;
    protected javax.swing.JLabel titleLabel;
    protected javax.swing.JPanel trueOrFalseFormPanel;
    protected javax.swing.JPanel upperFormPanel;
    protected javax.swing.JTextField wrongChoiceTextField3;
    protected javax.swing.JTextField wrongChoiceTextField5;
    protected javax.swing.JTextField wrongChoiceTextField6;
    // End of variables declaration//GEN-END:variables
    private int index;
}
