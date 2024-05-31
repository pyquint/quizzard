/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.kierjohn.testgui;

/**
 *
 * @author LENOVO
 */
public class ReviewerItem extends javax.swing.JPanel {
    protected Quiz reviewer;
    
    /**
     * Creates new form ReviewerItem
     * @param reviewer
     */
    public ReviewerItem(Quiz reviewer) {
        initComponents();
        this.reviewer = reviewer;
        reviewerNameLabel.setText(reviewer.getName());
        reviewerCountLabel.setText(reviewer.getQCount() + "");
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

        reviewerNameLabel = new javax.swing.JLabel();
        reviewerCountLabel = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 51));
        setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        setDoubleBuffered(false);
        setFocusable(false);
        setMinimumSize(new java.awt.Dimension(0, 0));
        setPreferredSize(new java.awt.Dimension(0, 0));
        setRequestFocusEnabled(false);
        setVerifyInputWhenFocusTarget(false);
        setLayout(new java.awt.GridBagLayout());

        reviewerNameLabel.setFont(new java.awt.Font("Josefin Sans", 0, 20)); // NOI18N
        reviewerNameLabel.setForeground(new java.awt.Color(0, 0, 102));
        reviewerNameLabel.setText(" ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(13, 7, 0, 237);
        add(reviewerNameLabel, gridBagConstraints);

        reviewerCountLabel.setFont(new java.awt.Font("Josefin Sans", 0, 16)); // NOI18N
        reviewerCountLabel.setForeground(new java.awt.Color(0, 0, 102));
        reviewerCountLabel.setText(" ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 7, 9, 0);
        add(reviewerCountLabel, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    protected Quiz getReviewer() {
        return reviewer;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel reviewerCountLabel;
    private javax.swing.JLabel reviewerNameLabel;
    // End of variables declaration//GEN-END:variables
}