package com.popov.portalOne.view;

import com.popov.portalOne.controller.DataController;
import com.popov.portalOne.core.FindNumbers;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.popov.portalOne.core.FindNumbers.*;

public class WindowView extends JFrame {
    public static final int MAIN_WINDOW_WIDTH = 600;
    public static final int MAIN_WINDOW_HEIGHT = 400;
    public static final JButton CHOOSE_TEXT_FILE_BUTTON = new JButton("Choose Text File");
    public static final JButton CALCULATION_BUTTON = new JButton("start the calculation");
    public static final Color MAIN_BACKGROUND_COLOR = Color.LIGHT_GRAY;
    private JTextField fileNameField;
    public static final JLabel END_LABEL = new JLabel("File analyzed success");
    public static final JPanel RESULT_PANEL = new JPanel(new GridLayout(3, 2, 5, 5));


    public WindowView() {
        mainWindowSettings();
        nameFieldSettings();
        END_LABEL.setVisible(false);
        CALCULATION_BUTTON.setEnabled(false);


        add(new JLabel("Selected file:"));
        add(fileNameField);
        add(CHOOSE_TEXT_FILE_BUTTON);
        add(END_LABEL);
        add(CALCULATION_BUTTON);
        add(RESULT_PANEL);

        CHOOSE_TEXT_FILE_BUTTON.addActionListener(this::handleFileSelection);
    }

    private void mainWindowSettings() {
        setSize(MAIN_WINDOW_WIDTH, MAIN_WINDOW_HEIGHT);
        getContentPane().setBackground(MAIN_BACKGROUND_COLOR);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        setLocationRelativeTo(null);
    }

    private void nameFieldSettings() {
        fileNameField = new JTextField(15);
        fileNameField.setEnabled(false);
        fileNameField.setDisabledTextColor(Color.BLACK);
    }

    private void handleFileSelection(ActionEvent e) {
        File selectedFile;
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Text Files", "txt"));

        int returnValue = fileChooser.showOpenDialog(this);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            selectedFile = fileChooser.getSelectedFile();
            fileNameField.setText(selectedFile.getName());
            FindNumbers.DATA = DataController.readDataFromFile(selectedFile);

            CALCULATION_BUTTON.setEnabled(true);
            CALCULATION_BUTTON.addActionListener(this::showResult);


            END_LABEL.setVisible(true);
        }
    }

    private void showResult(ActionEvent event) {
        long start = System.currentTimeMillis();

        List<ArrayList<Long>> increasingAndDecreasing = increasingAndDecreasingSequenceOfNum();
        ArrayList<Long> increasing = increasingAndDecreasing.get(0);
        ArrayList<Long> decreasing = increasingAndDecreasing.get(1);
        int arithmeticMean = arithmeticMean();
        int[] maxMinAndMedian = MaxMinMedian();

        resultFieldSettings(maxMinAndMedian[0], "Maximum number");
        resultFieldSettings(maxMinAndMedian[1], "Minimum number");
        resultFieldSettings(maxMinAndMedian[2], "Median number");
        resultFieldSettings(arithmeticMean, "Arithmetic mean number");
        resultComboBoxSettings(increasing, "Increasing number");
        resultComboBoxSettings(decreasing, "Decreasing number");

        double result =  (double) (System.currentTimeMillis() - start) / 1000;
        add(new JLabel("Time spent: " + String.format("%.2f", result) + " second"));

        RESULT_PANEL.revalidate();
    }

    private void resultComboBoxSettings(ArrayList<Long> list, String value) {
        String[] listToBox = new String[list.size()];
        for (int i = 0; i < listToBox.length; i++) {
            listToBox[i] = list.get(i).toString();
        }
        JComboBox<String> boxList = new JComboBox<>(listToBox);
        RESULT_PANEL.add(new JLabel(value));
        RESULT_PANEL.add(boxList);
    }

    private void resultFieldSettings(int result, String value) {
        JTextField field = new JTextField(8);
        field.setText(String.valueOf(result));
        field.setEnabled(false);
        field.setDisabledTextColor(Color.BLACK);
        RESULT_PANEL.add(new JLabel(value));
        RESULT_PANEL.add(field);
    }


    public static void main(String[] args) {
        new WindowView().setVisible(true);
    }
}
