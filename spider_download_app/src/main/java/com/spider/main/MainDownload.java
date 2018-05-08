/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spider.main;

public class MainDownload {

	public static void main(String[] args) {
		start();
	}

	public static void start() {
		DownloadTimerManager.getInstance().initTimerTask();
	}
}
