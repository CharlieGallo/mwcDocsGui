/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mwcdocsgui;

/**
 *
 * @author Charlie
 */
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

// TODO - create a GUI (desktop or web)

/**
 *  Methods:
 *
 *  go through each folder and check the file names
 *  if the file name matches the user input then store the file path in a list
 *
 *  print out the matching file names (and maybe its prent folder) from the list
 *  user selects which file to open
 *
 *  open the file for the user
 * */
public class Docs {

    ArrayList<File> matchedFiles = new ArrayList<>();
    ArrayList<ArrayList<File>> fullDir = new ArrayList<>();

    // TODO - Method that creates fullDir for printing out. Eventually a cascading menu in a desktop or wed GUI.
    // Constructor
    public Docs() {

    }

    /**
     *  Create list of files. Add list to fullDir.
     *  When new folder - Create list starting with folder name and then files. Add list.
     *  Repeat until no files left.
     * */
    public void createFullDir(File dir){
        File [] files = dir.listFiles();
        ArrayList<File> aList = new ArrayList<>();

        for(File file : files){
            aList.add(file);
        }
        this.fullDir.add(aList);
    }
    /**
     *
     */
    public void matchFilenames(File dir, String fileToFind) {
//        String[] filenames = dir.list();
//
//        for(String file: filenames){
//
//            if(!file.)
//            System.out.println(file);
//
//        }
        File[] filenames = dir.listFiles();
        for (File file : filenames) {
            String fileWithoutExtension = file.getName().replaceFirst("[.][^.]+$", "");
            String fileWithExtension = file.getName();
            String filePath = file.getPath();

            if (!file.isDirectory()) {
                if (fileToFind.compareTo(fileWithoutExtension) == 0) {
//                    System.out.println(fileWithExtension);
//                    System.out.println("file path " + filePath);
                    this.matchedFiles.add(new File(filePath));
                }
            } else {
                matchFilenames(file, fileToFind);
            }
        }
    }

    public void printMatchedFiles() {
        int count = 1;
        String fileShortPath;
        for (File f : this.matchedFiles) {
            fileShortPath = cutPath(f.getPath());
            System.out.println(count + ": " + f.getName());
            System.out.println("Folder path: " + fileShortPath);
            count++;
        }
    }

    public String cutPath(String longPath){
        String shortPath = "";
        int index = (longPath.indexOf("Desktop") + 8);
        shortPath = longPath.substring(index);
        return shortPath;
    }

    /**
     * Opens a user specified file from a directory.
     * @param fileNum is an integer that specifies what file the user wants to open
     */
    public void openFile(int fileNum) {

        File fileToOpen = this.matchedFiles.get(fileNum - 1);
        try {
            if (!Desktop.isDesktopSupported())//check if Desktop is supported by Platform or not
            {
                System.out.println("not supported");
                return;
            }
            Desktop desktop = Desktop.getDesktop();
            if (fileToOpen.exists()) {        //checks file exists or not
                System.out.println("open file");
                desktop.open(fileToOpen); //opens the specified file

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//}

    public static void main(String[] args) {
    }
}



