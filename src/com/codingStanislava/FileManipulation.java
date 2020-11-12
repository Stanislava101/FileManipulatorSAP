package com.codingStanislava;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;


public class FileManipulation {
    protected String path = GUI.pathField.getText();

    public void writeToFile(String str) throws IOException {
           FileWriter fw = new FileWriter(path);
           fw.write(str);
           System.out.println("Text added to file");
           fw.close();
    }
    public void writeToFileNewText(List<String> text) throws IOException {
        FileWriter fw = new FileWriter(path);
        fw.write(String.valueOf(text));
        fw.close();
    }

    public void readFromFile(String filepath) throws FileNotFoundException {
        try
        {
            File file=new File(filepath);
            FileReader fr=new FileReader(file);
            BufferedReader br=new BufferedReader(fr);
            StringBuffer sb=new StringBuffer();
            String line;

            while((line=br.readLine())!=null)
            {
                sb.append(line);
                sb.append("\n");
            }
            fr.close();
            System.out.println(sb.toString());
            GUI.dataFromFile.setText(sb.toString());
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

    }
    public void switchLines(int index1, int index2) throws IOException {
        final List<String> lines = Files.readAllLines(Paths.get(path), StandardCharsets.UTF_8);
        if (lines.size() == 0) {
            System.out.println("File is empty");
            GUI.text.setText("File is empty");
        }
        Collections.swap(lines, index1, index2);
        for(String line: lines){
            System.out.println(line);
        }

        FileWriter fw = new FileWriter(path);
        for(String line: lines){
            fw.write(line + System.lineSeparator());
        }
        fw.close();
    }
    public void switchWord(int line1, int word1, int line2, int word2) throws IOException {
        final List<String> text = Files.readAllLines(Paths.get(path), StandardCharsets.UTF_8);
        List<String> finalText = new ArrayList<String>();
        List<String> newStr = new ArrayList<String>();
        List<String> newStr2 = new ArrayList<>();
        List<String> mString = new ArrayList<String>();
        List<String> mString2 = new ArrayList<String>();
        String newLine1 = "";
        String newLine2 = "";
        String str="";
        String str2="";
        String replacedWords, replacedWords2 = "";
        if (text.size() == 0) {
            System.out.println("File is empty");
            GUI.text.setText("File is empty");
        }
        if(line1<line2) {
                newLine1 = String.valueOf(text.get(line1));
                for (int j = 0; j < line1; j++) {
                    newStr2.add(text.get(j));
                }

                String[] words = newLine1.trim().split("\\s+");
                str = words[word1];

                newLine2 = String.valueOf(text.get(line2));
                String[] words2 = newLine2.trim().split("\\s+");
                str2 = words2[word2];


            System.out.println("Word 1 = " + str);
            System.out.println("Word 2 = " + str2);
            for (int k = line1 + 1; k < line2; k++) {
                mString.add(text.get(k));
            }
            replacedWords = newLine1.replace(str, str2);
            replacedWords2 = newLine2.replace(str2, str);
            newStr.add(replacedWords);
            newStr.addAll(mString);
            newStr.add(replacedWords2);
            int line3 = line2 + 1;
            List<String> subListData = text.subList(line3, text.size());

            finalText.addAll(newStr2);
            finalText.addAll(newStr);
            finalText.addAll(subListData);
            for (String t : finalText) {
                System.out.println(t);
            }

        }else if(line1>line2){

                newLine1 = String.valueOf(text.get(line1));
                for (int j = 0; j < line2; j++) {
                    mString2.add(text.get(j));

                }

                String[] words = newLine1.trim().split("\\s+");
                str = words[word1];

                newLine2 = String.valueOf(text.get(line2));
                String[] words3 = newLine2.trim().split("\\s+");
                str2 = words3[word2];

            System.out.println("Word 1 = " + str);
            System.out.println("Word 2 = " + str2);
            for (int q = line2+1; q < line1; q++) {
                mString.add(text.get(q));
            }
            replacedWords = newLine1.replace(str, str2);
            replacedWords2 = newLine2.replace(str2, str);
            newStr.add(replacedWords2);
            newStr.addAll(mString);
            newStr.add(replacedWords);
            List<String> subListData = text.subList(line1+1, text.size());

            finalText.addAll(mString2);
            finalText.addAll(newStr);
            finalText.addAll(subListData);
            for (String t : finalText) {
                System.out.println(t);
            }
        }
        FileWriter fw = new FileWriter(path);
        for(String t: finalText){
            fw.write(t + System.lineSeparator());
        }
        fw.close();
    }
}
