/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spider.helper;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class Utility {

	public String prefixOS() {
		String strPrefix = null;
		String OS = System.getProperty("os.name").toLowerCase();
		if (OS.indexOf("win") >= 0) {
			strPrefix = "\\";
		} else {
			strPrefix = "/";
		}
		return strPrefix;
	}
	
	public void createFolderIfNotExist(String dirName) {
		String path = dirName;
		File theDir = new File(path);
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
			if (theDir.list().length == 0) {
				theDir.delete();
				System.out.println("Directory is deleted : "
						+ theDir.getAbsolutePath());

			} else {
				//list all the directory contents
				String files[] = theDir.list();
				for (String temp : files) {
					deleteFolder(dirName + "//" + temp);
				}
				theDir.delete();
			}
		} else {
			theDir.delete();
		}
	}

	public boolean createFile(String filePath, String content) {
		boolean isSuccessful = false;
		Writer writer = null;

		try {
			writer = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(filePath), "utf-8"));
			writer.write(content);
			isSuccessful = true;
		} catch (IOException ex) {
			// Report
		} finally {
			try {writer.close();} catch (Exception ex) {/*ignore*/}
		}
		return isSuccessful;
	}

	public boolean deleteFile(String pathFile) {
		File file;
		try{
			file = new File(pathFile.trim());
			return file.delete();
		}
		catch (Exception ex){
			System.out.println(ex.toString());
			return false;
		}
	}

	public boolean fileExists(String pathFile) {
		File file;

		if (null == pathFile || pathFile.trim().isEmpty()) {
			return false;
		}
		file = new File(pathFile.trim());
		return (file.exists() && file.isFile());
	}

	public boolean moveFile(String oldPath, String newPath) {
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
}

