package com.spider.main;

import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONObject;

public class CrunchifyJSONFileWrite {
	@SuppressWarnings("unchecked")
	public boolean execute(String clientSecret, String clientId, String filePath){
		boolean isSuccess = false;
		JSONObject parentObj = new JSONObject();
		JSONObject obj = new JSONObject();
		obj.put("client_secret", clientSecret);
		obj.put("client_id", clientId);
		parentObj.put("installed", obj);
		
		// try-with-resources statement based on post comment below :)
		try (FileWriter file = new FileWriter(filePath)) {
			file.write(parentObj.toJSONString());
			System.out.println("Successfully Copied JSON Object to File...");
			System.out.println("\nJSON Object: " + obj);
			isSuccess = true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isSuccess;
	}
}