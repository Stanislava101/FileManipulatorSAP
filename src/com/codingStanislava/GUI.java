package com.codingStanislava;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class GUI implements ActionListener {
    private static JPanel panel;
    private static JLabel pathLabel, line1, line2, word1, word2, wordLine1, wordLine2, lines, words;
    protected static JTextField pathField, line1Field, line2Field, fieldWordLine1, fieldWordLine2, fieldWord1, fieldWord2;
    private static JButton readButton, newData, changeLines, changeWords, clearButton;
    protected static JLabel text,success;
    protected static JTextArea textArea,dataFromFile;
    private static JLabel backgroundImageLabel;
    private String extension = "";
    private String validatePath= "";

   public void design(){
        GUI gui = new GUI();
        JFrame frame = new JFrame("SAP Project");
        panel = new JPanel();
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        panel.setLayout(null);
        pathLabel = new JLabel("Path");
        pathLabel.setForeground(Color.WHITE);
        pathLabel.setBounds(10, 20, 80, 25);
        panel.add(pathLabel);
        pathField = new JTextField(20);
        pathField.setBounds(100, 20, 165, 25);
        pathField.setText("Enter file path");
        panel.add(pathField);

        lines = new JLabel("Lines");
        lines.setForeground(Color.WHITE);
        lines.setBounds(110, 60, 80, 25);
        panel.add(lines);

        line1 = new JLabel("Line 1");
        line1.setForeground(Color.WHITE);
        line1.setBounds(10, 90, 80, 25);
        panel.add(line1);
        line1Field = new JTextField(20);
        line1Field.setBounds(100, 90, 165, 25);
        line1Field.setText("0");
        panel.add(line1Field);

        line2 = new JLabel("Line 2");
        line2.setForeground(Color.WHITE);
        line2.setBounds(10, 130, 80, 25);
        panel.add(line2);
        line2Field = new JTextField(20);
        line2Field.setText("0");
        line2Field.setBounds(100, 130, 165, 25);
        panel.add(line2Field);

        words = new JLabel("Words");
        words.setForeground(Color.WHITE);
        words.setBounds(430, 60, 80, 25);
        panel.add(words);

        wordLine1 = new JLabel("Line 1");
        wordLine1.setForeground(Color.WHITE);
        wordLine1.setBounds(350, 90, 80, 25);
        panel.add(wordLine1);
        fieldWordLine1 = new JTextField(20);
        fieldWordLine1.setText("0");
        fieldWordLine1.setBounds(420, 90, 165, 25);
        panel.add(fieldWordLine1);

        word1 = new JLabel("Word 1");
        word1.setForeground(Color.WHITE);
        word1.setBounds(350, 130, 80, 25);
        panel.add(word1);
        fieldWord1 = new JTextField(20);
        fieldWord1.setText("0");
        fieldWord1.setBounds(420, 130, 165, 25);
        panel.add(fieldWord1);

        wordLine2 = new JLabel("Line 2");
        wordLine2.setForeground(Color.WHITE);
        wordLine2.setBounds(350, 170, 80, 25);
        panel.add(wordLine2);
        fieldWordLine2 = new JTextField(20);
        fieldWordLine2.setText("0");
        fieldWordLine2.setBounds(420, 170, 165, 25);
        panel.add(fieldWordLine2);

        word2 = new JLabel("Word 2");
        word2.setForeground(Color.WHITE);
        word2.setBounds(350, 210, 80, 25);
        panel.add(word2);
        fieldWord2 = new JTextField(20);
        fieldWord2.setText("0");
        fieldWord2.setBounds(420, 210, 165, 25);
        panel.add(fieldWord2);


        readButton = new JButton("Read");
        readButton.setBounds(70, 270, 200, 25);
        readButton.addActionListener(new GUI());
        panel.add(readButton);

        newData = new JButton("New Data");
        newData.setBounds(70, 360, 200, 25);
        newData.addActionListener(new GUI());
        panel.add(newData);

        changeLines = new JButton("Change Lines");
        changeLines.setBounds(70,460,200,25);
        changeLines.addActionListener(new GUI());
        panel.add(changeLines);

        changeWords = new JButton("Change Words");
        changeWords.setBounds(70,560,200,25);
        changeWords.addActionListener(new GUI());
        panel.add(changeWords);

        clearButton = new JButton("Clear textarea");
        clearButton.setBounds(70,650,200,25);
        clearButton.addActionListener(new GUI());
        panel.add(clearButton);

        textArea = new JTextArea("Enter data..");
        textArea.setBounds(350, 440, 400, 140);
        panel.add(textArea);

        text = new JLabel();
        text.setForeground(Color.WHITE);
        text.setBounds(350, 20, 165, 25);
        panel.add(text);

        success = new JLabel();
        success.setForeground(Color.WHITE);
        success.setBounds(350, 20, 165, 25);
        panel.add(success);

        dataFromFile = new JTextArea("Data from file");
        dataFromFile.setBounds(350, 270, 400, 140);
        panel.add(dataFromFile);
        dataFromFile.setEditable(false);

        gui.setBackgroundImage();
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        FileManipulation fm = new FileManipulation();
        GUI val = new GUI();
        String fileExtension;
        String newDataTArea = textArea.getText();
        String path = pathField.getText().toLowerCase();
        validatePath = path.toLowerCase();
        File file = new File(path);
        boolean exists = file.exists();
        int line1Button = Integer.parseInt(line1Field.getText());
        int line2Button = Integer.parseInt(line2Field.getText());
        int wordsLine1Button = Integer.parseInt(fieldWordLine1.getText());
        int words1Button = Integer.parseInt(fieldWord1.getText());
        int wordsLine2Button = Integer.parseInt(fieldWordLine2.getText());
        int words2Button = Integer.parseInt(fieldWord2.getText());
        fileExtension = getFileExtension();
        if (e.getActionCommand().equals("Read")) {
            val.validationPath(path,exists,fileExtension);
                try {
                    fm.readFromFile(path);
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
        }else if (e.getActionCommand().equals("New Data")) {
            val.validationPath(path,exists,fileExtension);
                try {
                    if (newDataTArea.length() == 0) {
                        text.setText("You didn't enter anything");
                        System.out.println("You didn't enter anything");
                    } else {
                        fm.writeToFile(newDataTArea);
                        text.setText("");
                    }
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
        }else if (e.getActionCommand().equals("Change Lines")) {
            val.validationPath(path,exists,fileExtension);
            try {
                fm.switchLines(line1Button, line2Button);
                success.setText("Lines switched");
                System.out.println("Lines switched");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }else if (e.getActionCommand().equals("Change Words")) {
            val.validationPath(path,exists,fileExtension);
            try {
                fm.switchWord(wordsLine1Button,words1Button,wordsLine2Button,words2Button);
                success.setText("Words switched");
                System.out.println("Words switched");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }else if (e.getActionCommand().equals("Clear textarea")) {
                textArea.setText("");
        }
    }

    public String getFileExtension(){
        int i = validatePath.lastIndexOf('.');
        if (i > 0) {
            extension = validatePath.substring(i+1);
        }
        return extension;
    }

    public void validationPath(String path,boolean exists,String fileExtension) {
        success.setText("");
        text.setText("");
        File file=new File(path);
        if (path.equals("")) {
            System.out.println("Enter file path");
            text.setText("Enter file path");
        } else if (exists == false) {
            System.out.println("Doesn't exist file name or path");
            text.setText("Enter valid file name or path");
        } else if (file.length() == 0) {
            System.out.println("File is empty");
            GUI.text.setText("File is empty");
        }
        else if (!(fileExtension.equals("txt"))) {
            System.out.println("It is NOT a text file");
            text.setText("It is NOT a text file");
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    dataFromFile.setText("Data from file");
                }
            });
        }
    }

    public void setBackgroundImage(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();
        backgroundImageLabel = new JLabel(new ImageIcon(getClass().getResource("sample.jpg")));
        backgroundImageLabel.setBounds(0,0,(int)width,(int)height);
        panel.add(backgroundImageLabel);
    }

}
