/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kierjohn.testgui;

import org.json.JSONObject;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.*;
import java.util.stream.Collectors;

// TODO 1 : set default directory to app-specific folder
// TODO 2 : reviewer editor button enable/disable logic
// TODO 3 : json schema? #1 seem to provide alternative solution
// TODO 4 : replace most loops to streams and lambdas

/**
 * @author LENOVO
 */
public class GlobalUtils {

    public static Color DEFAULT_BUTTON_COLOR = new Color(255, 255, 51);
    public static Color DEFAULT_BG_COLOR = new Color(106, 49, 144);

    public static JFileChooser FILE_CHOOSER;
    public static JFileChooser FOLDER_CHOOSER;
    static protected File REVIEWERS_DIR, SAVES_DIR, DEFAULT_DIR;
    static private final String FILE_EXT = ".json";

    static public Set<String> reviewerNames = new HashSet<>();
    static public DefaultListModel<String> reviewersListModel = new DefaultListModel<>();
    static public DefaultComboBoxModel<String> reviewersComboBoxModel = new DefaultComboBoxModel<>();

    // update the reviewer models every 15 seconds
    private static final javax.swing.Timer updateTimer = new javax.swing.Timer(500, (java.awt.event.ActionEvent _) -> repopulateReviewerModels());

    public static void initDirectories() {
        FILE_CHOOSER = new JFileChooser();

        FILE_CHOOSER.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                return f.isDirectory() || f.getName().toLowerCase().endsWith(FILE_EXT);
            }

            @Override
            public String getDescription() {
                return "Quiz JSON Files (*" + FILE_EXT + ")";
            }
        });

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

    public static List<File> getReviewerFiles() {
        File[] reviewers = REVIEWERS_DIR.listFiles((File file, String name) -> hasExtension(name));
        return (reviewers != null) ? Arrays.asList(reviewers) : Collections.emptyList();
    }

    public static Set<String> getReviewerFileNames() {
        return getReviewerFiles()
                .stream()
                .map(File::getName)
                .collect(Collectors.toSet());
    }

    public static void repopulateReviewerModels() {
        clearReviewerModels();
        reviewerNames = getReviewerFileNames();
        reviewerNames.forEach(GlobalUtils::addToReviewerModels);
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
        clearReviewerModels();
    }
    
    public static void clearReviewerModels() {
        SwingUtilities.invokeLater(() -> {
            reviewersComboBoxModel.removeAllElements();
            reviewersListModel.removeAllElements();
        });
    }

    public static void writeQuizToChosenFile(Quiz quiz) {
        FILE_CHOOSER.setCurrentDirectory(REVIEWERS_DIR);

        if (FILE_CHOOSER.showSaveDialog(MainFrame.FRAME) == JFileChooser.APPROVE_OPTION) {
            String filePath = FILE_CHOOSER.getSelectedFile().getAbsolutePath();
            writeQuizToFile(quiz, new File(appendExtension(filePath)));
        }
    }

    public static boolean writeQuizToFile(Quiz quiz) {
        return writeQuizToFile(quiz, appendExtension(quiz.getName()));
    }

    public static boolean writeQuizToFile(Quiz quiz, String fileName) {
        return writeQuizToFile(quiz, new File(FILE_CHOOSER.getCurrentDirectory(), appendExtension(fileName)));
    }

    private static boolean writeQuizToFile(Quiz quiz, File file) {
        boolean isSaveOperationSuccessful = false;

        try {
            file.createNewFile();
            APIHandler.writeQuizToFile(quiz, file);
            addToReviewers(file.getName());
            isSaveOperationSuccessful = true;
            System.out.println("Successfully saved reviewer to \"" + file.getAbsolutePath() + "\".");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(MainFrame.FRAME, "Error saving quiz to file.", "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }

        return isSaveOperationSuccessful;
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
        boolean isApprovedOption = FOLDER_CHOOSER.showOpenDialog(MainFrame.FRAME) == JFileChooser.APPROVE_OPTION;

        if (isApprovedOption) {
            GlobalUtils.FOLDER_CHOOSER.setDialogTitle(title);
        } else {
            System.out.println("Cancelled selecting directory.");
        }

        return isApprovedOption;
    }

    public static boolean hasExtension(String name) {
        return name.toLowerCase().endsWith(FILE_EXT);
    }

    public static String getBaseName(String fileName) {
        return (hasExtension(fileName)) ? fileName.replaceAll("(?i)^\\." + FILE_EXT, "") : fileName;
    }

    public static String appendExtension(String name) {
        // Adds the file extension if and only if the String does not already have the suffix.
        return (hasExtension(name)) ? name : name + FILE_EXT;
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

    public static String getFileExtension() {
        return FILE_EXT;
    }
}
