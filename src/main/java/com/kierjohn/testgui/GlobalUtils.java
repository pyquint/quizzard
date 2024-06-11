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
import javax.swing.filechooser.*;

/**
 *
 * @author LENOVO
 */
public class GlobalUtils {

    public static JFileChooser fileChooser;
    public static JFileChooser folderChooser;
    static protected File reviewerSavesDir, gameSavesDir, defaultDir;

    public static void initFileChoosers() {
        fileChooser = new JFileChooser();
        folderChooser = new JFileChooser();
        reviewerSavesDir = gameSavesDir = defaultDir = folderChooser.getFileSystemView().getDefaultDirectory();
        setReviewersModelFromReviewerSavesDir();

        fileChooser.setFileFilter(new FileNameExtensionFilter("JSON file", "json"));
        fileChooser.setDialogTitle("Save reviewer to JSON");
        fileChooser.setAcceptAllFileFilterUsed(false);

        folderChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    }

    static public ArrayList<String> reviewerNames;
    static public DefaultListModel<String> reviewersListModel = new DefaultListModel<>();
    static public DefaultComboBoxModel<String> reviewersComboBoxModel = new DefaultComboBoxModel<>();

    public static Font getFont(int style, int size) {
        return new Font("Josefin Sans", style, size);
    }

    public static void setReviewersModelFromReviewerSavesDir() {
        File[] jsonFiles = reviewerSavesDir.listFiles((File dir, String name)
                -> name.toLowerCase().endsWith(".json"));
        
        if (jsonFiles.length > 0) {
            String[] fileNames = new String[jsonFiles.length];
            for (int i = 0; i < jsonFiles.length; i++) {
                File jsonFile = jsonFiles[i];
                fileNames[i] = jsonFile.getName();
            }
            reviewersComboBoxModel.removeAllElements();
            reviewersListModel.removeAllElements();
            for (String fileName : fileNames) {
                reviewersComboBoxModel.addElement(fileName);
                reviewersListModel.addElement(fileName);
            }
            reviewerNames = new ArrayList(Arrays.asList(fileNames));
            MainFrame.frame.setQuizReviewersComboBoxModel(reviewersComboBoxModel);
        }
    }

    public static String fileToString(File f) throws IOException {
        String content = new String(Files.readAllBytes(f.toPath()));
        return content;
    }

    public static Quiz fileToQuiz(File f) throws IOException {
        String content = new String(Files.readAllBytes(f.toPath()));
        return APIHandler.jsonObjectToQuiz(new JSONObject(content), "reviewer");
    }

    public static Color DEFAULT_BUTTON_COLOR = new Color(255, 255, 51);

    public static void addElementToModel(String s) {
        if (reviewerNames.isEmpty() || !reviewerNames.contains(s)) {
            reviewersComboBoxModel.addElement(s);
            reviewersListModel.addElement(s);
            reviewerNames.add(s);
        }
    }

    public static void writeQuizToChosenFile(Quiz quiz) {
        File file = new File(quiz.getName() + ".json");
        fileChooser.setSelectedFile(file);
        file = fileChooser.getSelectedFile();
        if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            writeQuizToFile(quiz, file);
            addElementToModel(file.getName());
        }
    }

    public static void writeQuizToFile(Quiz quiz) {
        writeQuizToFile(quiz, new File(fileChooser.getCurrentDirectory(), quiz.getName() + ".json"));
    }

    public static void writeQuizToFile(Quiz quiz, File file) {
        try {
            file.createNewFile();
            FileWriter f = new FileWriter(file);
            f.write(APIHandler.quizToJson(quiz).toString());
            f.close();
            addElementToModel(quiz.getName() + ".json");
            System.out.println("Successfully saved reviewer to " + file.getName());
        } catch (IOException ex) {
            ex.printStackTrace();
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
