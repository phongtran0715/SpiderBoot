/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itc.edu.ulvideo.main;

import org.apache.log4j.Logger;

public class MainUpload {
    private static final Logger logger = Logger.getLogger(MainUpload.class);

    public static void main(String[] args) {
        start();
    }

    public static void start() {
        UploadTimerManager.getInstance().initTimerTask();
    }
}
