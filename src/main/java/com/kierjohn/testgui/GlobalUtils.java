/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kierjohn.testgui;

import org.json.JSONObject;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

// TODO 4 : replace most loops to streams and lambdas

/**
 * @author LENOVO
 */
public class GlobalUtils {

    public static String fileToString(File f) throws IOException {
        return new String(Files.readAllBytes(f.toPath()));
    }

    public static Quiz fileToQuiz(File f) throws IOException {
        String content = new String(Files.readAllBytes(f.toPath()));
        return APIHandler.jsonObjectToQuiz(new JSONObject(content), "reviewer");
    }

    public static boolean openFolderChooserAndSelectDirectory(String title) {
        boolean isApprovedOption = Config.FOLDER_CHOOSER.showOpenDialog(MainFrame.FRAME) == JFileChooser.APPROVE_OPTION;

        if (isApprovedOption) {
            Config.FOLDER_CHOOSER.setDialogTitle(title);
        } else {
            System.out.println("Cancelled selecting directory.");
        }

        return isApprovedOption;
    }

    public static boolean hasExtension(String name) {
        return name.toLowerCase().endsWith(Config.FILE_EXT);
    }

    public static String getBaseName(String fileName) {
        return (hasExtension(fileName)) ? fileName.replaceAll("(?i)^\\." + Config.FILE_EXT, "") : fileName;
    }

    public static String appendExtension(String name) {
        // Adds the file extension if and only if the String does not already have the suffix.
        return (hasExtension(name)) ? name : name + Config.FILE_EXT;
    }

    public static boolean writeQuizToChosenFile(Quiz quiz) {
        Config.FILE_CHOOSER.setCurrentDirectory(new File(Config.getReviewerSavesPath()));

        if (Config.FILE_CHOOSER.showSaveDialog(MainFrame.FRAME) == JFileChooser.APPROVE_OPTION) {
            String filePath = Config.FILE_CHOOSER.getSelectedFile().getAbsolutePath();
            return writeQuizToFile(quiz, appendExtension(filePath));
        }

        return false;
    }

    public static boolean writeQuizToReviewerDir(Quiz quiz, String fileName) {
        File targetFile;

        if (fileName.contains(Config.getReviewerSavesPath())) {
            targetFile = new File(appendExtension(appendExtension(fileName)));
        } else {
            targetFile = new File(Config.getReviewersDir(), appendExtension(fileName));
        }

        return writeQuizToFile(quiz, targetFile);
    }

    public static boolean writeQuizToFile(Quiz quiz, String filePath) {
        return writeQuizToFile(quiz, new File(filePath));
    }

    private static boolean writeQuizToFile(Quiz quiz, File file) {
        boolean isSaveOperationSuccessful = false;

        try {
            file.createNewFile();
            try (FileWriter f = new FileWriter(file)) {
                f.write(APIHandler.quizToJson(quiz).toString());
            }
            Config.addToReviewers(file.getName());
            isSaveOperationSuccessful = true;
            System.out.println("Successfully saved reviewer to \"" + file.getAbsolutePath() + "\".");
        } catch (IOException ex) {
            ex.printStackTrace();
            GlobalUtils.showErrorMessage("Error saving quiz to file.");
        }

        return isSaveOperationSuccessful;
    }

    public static void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(MainFrame.FRAME, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static boolean isOptionPaneShowYes(String message, String windowTitle) {
        return JOptionPane.showConfirmDialog(MainFrame.FRAME, message, windowTitle,
                JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION;
    }
}
