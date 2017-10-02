package spiderboot.helper;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WriteToFileExample {

	BufferedWriter bw = null;
	FileWriter fw = null;
	
	WriteToFileExample writeFile = null;
	
	public WriteToFileExample getInstance(){
		if(writeFile == null){
			writeFile = new WriteToFileExample();
		}
		return writeFile;
	}
	
	public void createNewFile(String filePath){
		try{
			fw = new FileWriter(filePath,true);
			bw = new BufferedWriter(fw);
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	public void write(String content) {
		try {
			bw.append(content);
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	public void closeFile(){
		try {
			if(bw != null)
				bw.close();
			
			if(fw != null)
				fw.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}