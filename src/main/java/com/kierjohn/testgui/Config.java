package com.kierjohn.testgui;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.*;
import java.util.stream.Collectors;

public class Config {

    public static Set<String> REVIEWER_NAMES = new HashSet<>();
    public static DefaultListModel<String> reviewersListModel = new DefaultListModel<>();
    public static DefaultComboBoxModel<String> reviewersComboBoxModel = new DefaultComboBoxModel<>();

    private static final File DEFAULT_DIR = new File(new JFileChooser().getFileSystemView().getDefaultDirectory(), "Quizzard");
    private static final File PROPERTIES_FILE = new File(getDefaultPath(), "quizzard.properties");
    private static Properties PROPERTIES = new Properties();
    public static final String FILE_EXT = ".json";

    public static JFileChooser FILE_CHOOSER, FOLDER_CHOOSER;
    private static File REVIEWERS_DIR, SAVES_DIR;

    // update the reviewer models every 15 seconds
    private static final javax.swing.Timer updateTimer = new javax.swing.Timer(500, (ActionEvent _) -> updateReviewerModels());

    public static Color DEFAULT_BUTTON_COLOR = new Color(255, 255, 51);
    public static Color DEFAULT_BG_COLOR = new Color(106, 49, 144);

    public static void initializeConfigurations() {
        initializeJFileChoosers();

        if (DEFAULT_DIR.mkdirs()) {
            System.out.println("Created folder: " + DEFAULT_DIR.getAbsolutePath());
        }

        if (!PROPERTIES_FILE.exists()) {
            resetProperties();
            writeProperties();
        }

        loadProperties();

        repopulateReviewerModels();
        updateTimer.start();
    }

    private static void resetProperties() {
        PROPERTIES = new Properties();
        PROPERTIES.setProperty("rev_dir", getDefaultPath());
    }

    public static void loadProperties() {
        try {
            PROPERTIES.load(new FileReader(PROPERTIES_FILE));
            REVIEWERS_DIR = new File(PROPERTIES.getProperty("rev_dir"));
        } catch (IOException e) {
            MainFrame.FRAME.abruptlyClose("""
                    Error loading app config. Try deleting the .property file.
                    The application will now close.""");
        }
    }

    public static void writeProperties() {
        try {
            PROPERTIES.setProperty("rev_dir", getReviewerSavesPath());
            PROPERTIES.store(new FileWriter(PROPERTIES_FILE.getAbsolutePath()), "CONFIG");
        } catch (IOException e) {
            MainFrame.FRAME.abruptlyClose("Error writing to config. The application will now close.");
        }
    }

    private static void initializeJFileChoosers() {
        FILE_CHOOSER = new JFileChooser();
        FILE_CHOOSER.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                return f.isDirectory() || f.getName().toLowerCase().endsWith(FILE_EXT);
            }

            @Override
            public String getDescription() {
                return "Quizzard JSON Files (*" + FILE_EXT + ")";
            }
        });

        FILE_CHOOSER.setDialogTitle("Save reviewer to JSON");
        FILE_CHOOSER.setAcceptAllFileFilterUsed(false);

        FOLDER_CHOOSER = new JFileChooser();
        FOLDER_CHOOSER.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    }

    public static void updateReviewerModels() {
        Set<String> newFiles, tempNewFiles, removedFiles;
        newFiles = getReviewerFileNames();

//        System.out.println("old: " + REVIEWER_NAMES);
//        System.out.println("new: " + newFiles);
//        System.out.println("the same? " + newFiles.equals(REVIEWER_NAMES));

        if (!newFiles.equals(REVIEWER_NAMES)) {
            // removedFiles first before newFiles
            removedFiles = new HashSet<>(REVIEWER_NAMES);
            removedFiles.removeAll(newFiles);

            newFiles.removeAll(new HashSet<>(REVIEWER_NAMES));

//            System.out.println("\tadded files: " + newFiles);
//            System.out.println("\tdeled files: " + removedFiles);

            for (String deletedFile : removedFiles) {
                removeFromReviewers(deletedFile);
            }

            for (String newFile : newFiles) {
                addToReviewers(newFile);
            }
//            System.out.println("new list: " + REVIEWER_NAMES);
        }
    }

    public static Font getFont(int style, int size) {
        return new Font("Josefin Sans", style, size);
    }

    public static List<File> getReviewerFiles() {
        File[] reviewers = REVIEWERS_DIR.listFiles((File _, String name) -> GlobalUtils.hasExtension(name));
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
        REVIEWER_NAMES = getReviewerFileNames();
        REVIEWER_NAMES.forEach(Config::addToReviewerModels);
    }

    public static void addToReviewers(String s) {
        if (REVIEWER_NAMES.add(s)) {
            addToReviewerModels(s);
            System.out.println("Added \"" + s + "\"");
        }
    }

    public static void removeFromReviewers(String s) {
        if (REVIEWER_NAMES.remove(s)) {
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
        REVIEWER_NAMES.clear();
        clearReviewerModels();
    }

    private static void clearReviewerModels() {
        SwingUtilities.invokeLater(() -> {
            reviewersComboBoxModel.removeAllElements();
            reviewersListModel.removeAllElements();
        });
    }

    public static boolean deleteReviewer(String fileName) {
        File f = new File(DEFAULT_DIR, fileName);

        updateTimer.stop();
        boolean success = f.delete();
        updateTimer.start();

        return success;
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

    public static File getReviewersDir() {
        return REVIEWERS_DIR;
    }

    public static String getGameSavesPath() {
        return SAVES_DIR.getAbsolutePath();
    }

    public static String getDefaultPath() {
        return DEFAULT_DIR.getAbsolutePath();
    }

    public static boolean hasReviewers() {
        return !REVIEWER_NAMES.isEmpty();
    }

    public static String getFileExtension() {
        return FILE_EXT;
    }
}
