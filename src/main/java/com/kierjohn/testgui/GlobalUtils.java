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
import java.util.Set;
import java.util.HashSet;
import javax.swing.filechooser.*;

/**
 *
 * @author LENOVO
 */
public class GlobalUtils {

    public static Color DEFAULT_BUTTON_COLOR = new Color(255, 255, 51);
    public static JFileChooser FILE_CHOOSER;
    public static JFileChooser FOLDER_CHOOSER;
    static protected File REVIEWERS_DIR, SAVES_DIR, DEFAULT_DIR;

    static public Set<String> reviewerNames = new HashSet<>();
    static public DefaultListModel<String> reviewersListModel = new DefaultListModel<>();
    static public DefaultComboBoxModel<String> reviewersComboBoxModel = new DefaultComboBoxModel<>();

// update the reviewer models every 15 seconds seconds
    private static final Timer updateTimer = new Timer(500, (java.awt.event.ActionEvent evt1) -> {
//        SwingUtilities.invokeLater(() -> {
        updateReviewerModels();
//        });
    });

    public static void initDirectories() {
        FILE_CHOOSER = new JFileChooser();
        FILE_CHOOSER.setFileFilter(new FileNameExtensionFilter("JSON file", "json"));
        FILE_CHOOSER.setDialogTitle("Save reviewer to JSON");
        FILE_CHOOSER.setAcceptAllFileFilterUsed(false);

        FOLDER_CHOOSER = new JFileChooser();
        FOLDER_CHOOSER.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        REVIEWERS_DIR = SAVES_DIR = DEFAULT_DIR = FOLDER_CHOOSER.getFileSystemView().getDefaultDirectory();
        repopulateReviewerModels();
        updateTimer.start();
    }

    public static Font getFont(int style, int size) {
        return new Font("Josefin Sans", style, size);
    }

    public static File[] getReviewerFiles() {
        return REVIEWERS_DIR.listFiles((File dir, String name)
                -> name.toLowerCase().endsWith(".json"));
    }

    public static Set<String> getReviewerFileNamesSet() {
        Set<String> fileNames = new HashSet<>();
        for (File f : getReviewerFiles()) {
            fileNames.add(f.getName());
        }
        return (HashSet<String>) fileNames;
    }

    public static void updateReviewerModels() {
        // TODO : fix : sets not quite correct...
        Set<String> newFiles, newFilesTemp, removedFiles;

        newFiles = getReviewerFileNamesSet();
        newFilesTemp = new HashSet<>(newFiles);

        System.out.println("old: " + reviewerNames.toString());
        System.out.println("new: " + newFiles.toString());
        System.out.println("the same? " + newFiles.toString().equalsIgnoreCase(reviewerNames.toString()));

        if (!newFiles.toString().equalsIgnoreCase(reviewerNames.toString())) {
            System.out.println("File changes found. Running updates...");
            removedFiles = new HashSet<>(reviewerNames);
            removedFiles.removeAll(newFiles);
            newFiles.removeAll(new HashSet<>(reviewerNames));
            
            System.out.println("\tadded files: " + newFiles.toString());
            System.out.println("\tdeled files: " + removedFiles.toString());

            for (String deletedFile : removedFiles) {
                removeFromReviewers(deletedFile);
            }

            for (String newFile : newFiles) {
                addToReviewers(newFile);
            }
            System.out.println("new list: " + reviewerNames.toString());
        } else {
            System.out.println("No file changes found.");
        }
    }

    public static void repopulateReviewerModels() {
        // TODO : fix : when changing dir, list is updated instead of re-initialized
        reviewerNames = getReviewerFileNamesSet();
        if (!reviewerNames.isEmpty()) {
            clearReviewers();
            for (String reviewer : reviewerNames) {
                addToReviewers(reviewer);
            }
        }
    }

    public static String fileToString(File f) throws IOException {
        return new String(Files.readAllBytes(f.toPath()));
    }

    public static Quiz fileToQuiz(File f) throws IOException {
        String content = new String(Files.readAllBytes(f.toPath()));
        return APIHandler.jsonObjectToQuiz(new JSONObject(content), "reviewer");
    }

    public static void addToReviewers(String s) {
        if (reviewerNames.add(s)) {
            addToReviewerModels(s);
            System.out.println("Added \"" + s + "\"");
        }
    }

    public static void removeFromReviewers(String s) {
        if (reviewerNames.remove(s)) {
            removeFromReviewerModels(s);
            System.out.println("Deleted \"" + s + "\"");
        }
    }

    private static void addToReviewerModels(String s) {
        SwingUtilities.invokeLater(() -> {
            reviewersComboBoxModel.addElement(s);
            reviewersListModel.addElement(s);
        });
    }

    private static void removeFromReviewerModels(String s) {
        SwingUtilities.invokeLater(() -> {
            reviewersComboBoxModel.removeElement(s);
            reviewersListModel.removeElement(s);
        });
    }

    public static void clearReviewers() {
        reviewerNames.clear();
        SwingUtilities.invokeLater(() -> {
            reviewersComboBoxModel.removeAllElements();
            reviewersListModel.removeAllElements();
        });
    }

    public static void writeQuizToChosenFile(Quiz quiz) {
        FILE_CHOOSER.setCurrentDirectory(REVIEWERS_DIR);
        if (FILE_CHOOSER.showSaveDialog(MainFrame.FRAME) == JFileChooser.APPROVE_OPTION) {
            String filePath = FILE_CHOOSER.getSelectedFile().getAbsolutePath();
            if (!filePath.toLowerCase().endsWith(".json")) {
                filePath += ".json";
            }
            writeQuizToFile(quiz, new File(filePath));
        }
    }

    public static void writeQuizToFile(Quiz quiz) {
        writeQuizToFile(quiz, new File(FILE_CHOOSER.getCurrentDirectory(), quiz.getName() + ".json"));
    }

    public static void writeQuizToFile(Quiz quiz, File file) {
        try {
            file.createNewFile();
            try (FileWriter f = new FileWriter(file)) {
                f.write(APIHandler.quizToJson(quiz).toString());
            }
            addToReviewers(file.getName());
            System.out.println("Successfully saved reviewer to \"" + file.getAbsolutePath() + "\".");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static boolean deleteFile(String fileName) {
        return deleteFile(new File(REVIEWERS_DIR, fileName));
    }

    public static boolean deleteFile(File file) {
        updateTimer.stop();
        removeFromReviewers(file.getName());
        updateTimer.start();
        return file.delete();
    }

    public static boolean openFolderChooserAndSelectDirectory(String title) {
        boolean isSuccessful;
        if (isSuccessful = GlobalUtils.FOLDER_CHOOSER.showOpenDialog(MainFrame.FRAME) == JFileChooser.APPROVE_OPTION) {
            GlobalUtils.FOLDER_CHOOSER.setDialogTitle(title);
        } else {
            System.out.println("Cancelled selecting directory.");
        }
        return isSuccessful;
    }

    public static void setReviewerSavesDirFromSelectedDir() {
        REVIEWERS_DIR = FOLDER_CHOOSER.getSelectedFile();
    }

    public static void resetReviewerSavesDirectory() {
        REVIEWERS_DIR = DEFAULT_DIR;
    }

    public static void resetGameSavesDirectory() {
        SAVES_DIR = DEFAULT_DIR;
    }

    public static String getReviewerSavesPath() {
        return REVIEWERS_DIR.getAbsolutePath();
    }

    public static String getGameSavesPath() {
        return SAVES_DIR.getAbsolutePath();
    }

    public static String getDefaultPath() {
        return DEFAULT_DIR.getAbsolutePath();
    }

    public static boolean hasReviewers() {
        return !reviewerNames.isEmpty();
    }
}
