/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itc.edu.dlvideo.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author donlv
 */
public class FileUtils {

    private static Logger logger = Logger.getLogger(FileUtils.class);

    /**
     * Get name's file from path of File
     *
     * @param pathFile
     * @return
     */
    public static String getFileName(String pathFile) {
        int pos;

        if (null == pathFile || pathFile.isEmpty()) {
            return "";
        }
        pos = pathFile.lastIndexOf("/");
        if (pos < 0) {
            pos = pathFile.lastIndexOf("\\");
        }
        if (pos < 0) {
            return pathFile;
        }
        return pathFile.substring(pos);
    }

    /**
     * Get path of file
     *
     * @param pathFile
     * @return empty if path of file is null or empty else return folder contain
     * file except first directory
     */
    public static String getDirectFile(String pathFile) {
        int pos;

        if (null == pathFile || pathFile.isEmpty()) {
            return "";
        }
        pos = pathFile.indexOf("/", 1);
        if (pos < 0) {
            pos = pathFile.indexOf("\\", 1);
        }
        if (pos < 0) {
            return pathFile;
        }
        return pathFile.substring(pos);
    }

    /**
     * Get folder name from path of file
     *
     * @param pathFile
     * @return empty if path of file is null or empty else return folder contain
     * file
     */
    public static String getFolder(String pathFile) {
        int pos;

        if (null == pathFile || pathFile.isEmpty()) {
            return "";
        }
        pos = pathFile.lastIndexOf("/");
        if (pos < 0) {
            pos = pathFile.lastIndexOf("\\");
        }
        if (pos < 0) {
            return pathFile;
        }
        return pathFile.substring(0, pos);
    }

    /**
     * Delete file if file exists
     *
     * @param pathFile
     * @return false if path of file is null or empty, true if file not exists,
     * true if delete successful
     */
    public static boolean deleteFile(String pathFile) {
        File file;

        /*if (null == pathFile || pathFile.trim().isEmpty()) {
			logger.error("No file to delete");
            return false;
        }
        file = new File(pathFile.trim());
        if (file.exists()) {
			logger.info("file need to delete ((((()))))" + file);
            return file.delete();
        }*/
		try{
			file = new File(pathFile.trim());
			return file.delete();
		}
		catch (Exception ex){
			logger.error(ex.getMessage());
                        return false;
		}
        //return true;
    }

    /**
     * Check file exists
     *
     * @param pathFile
     * @return true if file exists
     */
    public static boolean fileExists(String pathFile) {
        File file;

        if (null == pathFile || pathFile.trim().isEmpty()) {
            return false;
        }
        file = new File(pathFile.trim());
        return (file.exists() && file.isFile());
    }

    /**
     * Move file to new path, if file exists in new path write replace file
     *
     * @param file
     * @param newPath
     * @return true if move successful
     */
    public static boolean moveFile(String oldPath, String newPath) {
        File fileBakup, folderBackUp, oldFile;
//        Path pathSrc, pathDes;

        if (null == oldPath || oldPath.isEmpty()) {
            return false;
        }
        oldFile = new File(oldPath);
        if (!oldFile.exists()) {
            return false;
        }
        fileBakup = new File(newPath);
        folderBackUp = fileBakup.getParentFile();
        if (!folderBackUp.exists()) {
            if (!folderBackUp.mkdirs()) {
                return false;
            }
        }
        return oldFile.renameTo(fileBakup);
    }

    /**
     * Di chuyen file
     *
     * @param file
     * @param newPath
     * @return
     */
    public static boolean moveFile(File file, String newPath) {
        File fileBakup, folderBackUp;
//        Path pathSrc, pathDes;

        fileBakup = new File(newPath);
        folderBackUp = fileBakup.getParentFile();
        if (!folderBackUp.exists()) {
            if (!folderBackUp.mkdirs()) {
                return false;
            }
        }
        if (fileBakup.exists()) {
            fileBakup.delete();
        }
        return file.renameTo(fileBakup);
    }

    /**
     * Get all file in folder
     *
     * @param folder
     * @return
     */
    public static File[] getListFiles(String folder) {
        File file = new File(folder);
        try {
            return file.listFiles();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Read all line in file
     *
     * @param file
     * @return
     */
    public static List<String> getLine(File file) {
        List<String> lines = new ArrayList<String>();
        FileReader fileReader = null;
        BufferedReader bufferReader = null;
        String line;

        if (!file.exists()) {
            return null;
        }
        try {
            fileReader = new FileReader(file);
            bufferReader = new BufferedReader(fileReader);
            while ((line = bufferReader.readLine()) != null) {
                lines.add(line);
            }
        } catch (Exception e) {
            logger.error("Error while read file ".concat(file.getName()), e);
        } finally {
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException ex) {
                }
            }
            if (null != bufferReader) {
                try {
                    bufferReader.close();
                } catch (IOException ex) {
                }
            }
        }
        return lines;
    }

    /**
     * Make folder contain file if folder not exists
     *
     * @param pathFile
     * @return
     */
    public static boolean makeFolder(String pathFile) {
        if (null == pathFile || pathFile.isEmpty()) {
            return false;
        }
        File file = new File(pathFile);
        File folder;
        folder = file.getParentFile();
        if (null != folder && !folder.exists()) {
            return folder.mkdirs();
        }
        return false;
    }
}
