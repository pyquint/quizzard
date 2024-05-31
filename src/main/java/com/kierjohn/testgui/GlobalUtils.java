/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kierjohn.testgui;

import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import javax.swing.*;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author LENOVO
 */
public class GlobalUtils {

    public static final JFileChooser fileChooser = new JFileChooser();

    public void initFileChooser() {
        fileChooser.setFileFilter(new FileNameExtensionFilter("JSON file", "json"));
        fileChooser.setDialogTitle("Save reviewer to JSON");
        fileChooser.setAcceptAllFileFilterUsed(false);
    }

    static public ArrayList<String> reviewerNames;
    static public DefaultListModel<String> reviewersListModel = new DefaultListModel<>();
    static public DefaultComboBoxModel<String> reviewersComboBoxModel = new DefaultComboBoxModel<>();

    public static Font getFont(int style, int size) {
        return new Font("Josefin Sans", style, size);
    }

    public static void setReviewersModel(String[] str) {
        reviewersComboBoxModel.removeAllElements();
        reviewersListModel.removeAllElements();
        for (String s : str) {
            reviewersComboBoxModel.addElement(s);
            reviewersListModel.addElement(s);
        }
        reviewerNames = new ArrayList(Arrays.asList(str));
    }

    public static Quiz fileToQuiz(File f) throws IOException {
        String content = new String(Files.readAllBytes(f.toPath()));
        return APIHandler.jsonObjectToQuiz(new JSONObject(content));
    }
    
    public static Color DEFAULT_BUTTON_COLOR = new Color(255,255,51);

    public static void addElementToModel(String s) {
        if (!reviewerNames.contains(s)) {
            reviewersComboBoxModel.addElement(s);
            reviewersListModel.addElement(s);
            reviewerNames.add(s);
        }
    }

    public static void writeQuizToFile(Quiz quiz) {
        writeQuizToFile(quiz, quiz.getName());
    }
    
    public static void writeQuizToFile(Quiz quiz, String fileName) {
        File file = new File(fileName + ".json");
        fileChooser.setSelectedFile(file);
        if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            try {
                file = fileChooser.getSelectedFile();
                file.createNewFile();
                FileWriter f = new FileWriter(file);
                f.write(APIHandler.quizToJson(quiz).toString());
                f.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            addElementToModel(quiz.getName() + ".json");
            System.out.println("Successfully saved reviewer to " + file.getName());
        }
    }
    
    

    static class ReviewerItemCellRenderer extends DefaultListCellRenderer {

        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            label.setText("<html>" + value.toString().replace("\n", "<br/>") + "</html>");
            return label;
        }
    }
}
