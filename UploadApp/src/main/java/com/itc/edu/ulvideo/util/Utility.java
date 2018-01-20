/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itc.edu.ulvideo.util;
import java.io.File;
import org.apache.log4j.Logger;
/**
 *
 * @author hanht
 */
public class Utility {
    
private static final Logger logger = Logger.getLogger(Utility.class);
  
  public static boolean existsArray(String[] array, String content)
  {
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
  
  public static boolean fileValid(String[] expansion, File fileName)
  {
    String name = fileName.getName();
    
    int size = expansion.length;
    for (int i = 0; i < size; i++) {
      if ((null != expansion[i]) && (name.toLowerCase().endsWith(expansion[i].toLowerCase()))) {
        return true;
      }
    }
    return false;
  }
  
  public static void createFolder(String folderName)
  {
    File file = new File(folderName);
    if (!file.exists()) {
      file.mkdirs();
    }
  }
  
  public static void sleep(long time)
  {
    try
    {
      Thread.sleep(time);
    }
    catch (Exception e)
    {
      logger.error("Error sleep", e);
    }
  }
  
  public static int getCoreCPU()
  {
    return Runtime.getRuntime().availableProcessors();
  }
  
  public static String getMsisdn(String lineCard)
  {
    if ((null == lineCard) || (lineCard.trim().isEmpty())) {
      return null;
    }
    String[] data = lineCard.split("[|]");
    if (data.length < 19) {
      return null;
    }
    return data[8];
  }
  
  public static int msisdnToIndex(String msisdn)
  {
    StringBuilder result = new StringBuilder();
    String temp;
    if (msisdn.startsWith("0"))
    {
      temp = "84" + msisdn.substring(1);
    }
    else
    {
      if (!msisdn.startsWith("84")) {
        temp = "84" + msisdn;
      } else {
        temp = msisdn;
      }
    }
    if (temp.startsWith("849")) {
      result.append(msisdn.substring(3));
    } else {
      result.append("1").append(msisdn.substring(4));
    }
    temp = result.toString();
    try
    {
      return Integer.parseInt(temp);
    }
    catch (NumberFormatException ex)
    {
      logger.error("Msisdn invalid " + msisdn, ex);
    }
    return 0;
  }
}
