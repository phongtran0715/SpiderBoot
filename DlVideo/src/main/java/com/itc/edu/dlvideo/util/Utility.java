/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itc.edu.dlvideo.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.BindException;
import java.net.InetAddress;
import java.net.ServerSocket;
import org.apache.commons.lang3.StringUtils;

import org.apache.log4j.Logger;

/*------------------------------------------------------------------------------
** History
21-01-2018, [CR-002] phapnd
    Cap nhat them ham prefixOS

22-01-2018, [CR-004] phapnd
    Cap nhat them ham replaceBadChars
 */
public class Utility {

    private static final Logger logger = Logger.getLogger(Utility.class);
    private static final int PORT = 9999;
    @SuppressWarnings("unused")
    private static ServerSocket socket;

    /**
     * Drop all foribiden characters from filename
     *
     * @param f input file name
     * @return normalized file name
     */
    public static String replaceBadChars(String f) {
        String replace = "_";
        f = f.replaceAll("/", replace);
        f = f.replaceAll("\\\\", replace);
        f = f.replaceAll(":", replace);
        f = f.replaceAll("\\?", replace);
        f = f.replaceAll("\\\"", replace);
        f = f.replaceAll("\\*", replace);
        f = f.replaceAll("<", replace);
        f = f.replaceAll(">", replace);
        f = f.replaceAll("\\|", replace);
        f = f.trim();
        f = StringUtils.removeEnd(f, ".");
        f = f.trim();

        String ff;
        while (!(ff = f.replaceAll(" ", "_")).equals(f)) {
            f = ff;
        }

        return f;
    }

    public static String prefixOS() {
        String strPrefix = null;
        String OS = System.getProperty("os.name").toLowerCase();
        if (OS.indexOf("win") >= 0) {
            strPrefix = "\\";
        } else {
            strPrefix = "/";
        }
        return strPrefix;
    }

    public static boolean existsArray(String[] array, String content) {
        if (null == content) {
            return false;
        }
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(content)) {
                return true;
            }
        }
        return false;
    }

    public static boolean fileValid(String[] expansion, File fileName) {
        String name = fileName.getName();

        int size = expansion.length;
        for (int i = 0; i < size; i++) {
            if ((null != expansion[i]) && (name.toLowerCase().endsWith(expansion[i].toLowerCase()))) {
                return true;
            }
        }
        return false;
    }

    public static void createFolder(String folderName) {
        File file = new File(folderName);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    public static void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (Exception e) {
            logger.error("Error sleep", e);
        }
    }

    public static int getCoreCPU() {
        return Runtime.getRuntime().availableProcessors();
    }

    public static String getMsisdn(String lineCard) {
        if ((null == lineCard) || (lineCard.trim().isEmpty())) {
            return null;
        }
        String[] data = lineCard.split("[|]");
        if (data.length < 19) {
            return null;
        }
        return data[8];
    }

    public static int msisdnToIndex(String msisdn) {
        StringBuilder result = new StringBuilder();
        String temp;
        if (msisdn.startsWith("0")) {
            temp = "84" + msisdn.substring(1);
        } else if (!msisdn.startsWith("84")) {
            temp = "84" + msisdn;
        } else {
            temp = msisdn;
        }
        if (temp.startsWith("849")) {
            result.append(msisdn.substring(3));
        } else {
            result.append("1").append(msisdn.substring(4));
        }
        temp = result.toString();
        try {
            return Integer.parseInt(temp);
        } catch (NumberFormatException ex) {
            logger.error("Msisdn invalid " + msisdn, ex);
        }
        return 0;
    }

    public static boolean checkIfRunning() {
        boolean isRunning = false;
        try {
            //Bind to localhost adapter with a zero connection queue 
            socket = new ServerSocket(PORT, 0, InetAddress.getByAddress(new byte[]{127, 0, 0, 1}));
            isRunning = false;
        } catch (BindException e) {
            System.err.println("Already running.");
            System.exit(1);
            isRunning = true;
        } catch (IOException e) {
            System.err.println("Unexpected error.");
            e.printStackTrace();
            System.exit(2);
        }
        return isRunning;
    }

    public void createFolderIfNotExist(String dirName) {
        String path = dirName;
        File theDir = new File(path);
        // if the directory does not exist, create it
        if (!theDir.exists()) {
            try {
                theDir.mkdir();
                System.out.println("Created directory : " + path);
            } catch (SecurityException se) {
                System.out.println("Can not creat video directory");
                se.printStackTrace();
            }
        }
    }

    public void deleteFolder(String dirName) {
        String path = dirName;
        File theDir = new File(path);
        if (theDir.isDirectory()) {
            //directory is empty, then delete it
            if (theDir.list().length == 0) {
                theDir.delete();
                System.out.println("Directory is deleted : "
                        + theDir.getAbsolutePath());

            } else {
                //list all the directory contents
                String files[] = theDir.list();
                for (String temp : files) {
                    //construct the file structure
                    //recursive delete
                    deleteFolder(dirName + "//" + temp);
                }
                theDir.delete();
            }
        } else {
            theDir.delete();
        }
    }

    public void executeBashCmd(String cmd) {
        try {
            Runtime rt = Runtime.getRuntime();
            Process pr = rt.exec(new String[]{"bash", "-c", cmd});

            BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream()));

            String line = null;

            while ((line = input.readLine()) != null) {
                System.out.println(line);
            }

            int exitVal = pr.waitFor();
            System.out.println("Exited with error code " + exitVal);

        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
        }
    }
}
