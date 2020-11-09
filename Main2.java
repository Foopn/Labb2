package com.company;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

public class Main2 {

    private static void ScanDirectory(File dirPath, String searchWord) {
        try {
            File filesList[] = dirPath.listFiles();
            if(filesList != null) {
                for (File file : filesList) {
                    if (file.isFile()) {
                        SearchInFile(file, searchWord);
                    } else if (file.isDirectory() && !file.isHidden()) {
                        ScanDirectory(file, searchWord);
                    }
                }
            }
        }  catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

   public static void SearchInFile(File file, String searchWord){
       BufferedReader brFile = null;
       try {
           brFile = new BufferedReader(new FileReader(file.getAbsolutePath()));
           String row = "";
           while ((row = brFile.readLine()) != null) {
               if(row.toLowerCase().contains(searchWord.toLowerCase())){
                    System.out.println("The word: \""+searchWord+"\" found in file:"+ file.getAbsolutePath());
                    break;
               }
           }
       } catch (Exception e){
           System.err.println("Couldn't read file: " + file.getAbsolutePath());
           System.err.println("Error message: " + e.getMessage());
       }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Var vänlig ange ett sökord här: ");
        String searchWord = scan.nextLine();
        File startPoint = new File(".");
        ScanDirectory(startPoint, searchWord);
    }
}
