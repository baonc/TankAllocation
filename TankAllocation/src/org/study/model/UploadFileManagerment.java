package org.study.model;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import static java.nio.file.StandardOpenOption.*;

/**
 * Class manager upload file to server <br>
 * Managerment file name and path of file which user uploaded
 * 
 * @created 27 / 4 / 2015
 * @author baonc
 *
 */
public class UploadFileManagerment {
	private final String UPLOAD_PATH = "/home/baonc/data/fileName";
	
	/**
	 * Function get file name of file upload. <br>
	 * Get file to input of search algorithms, it will be get from UPLOAD_PATH
	 * 
	 * @return	: Path and file name of user upload
	 */
	public String getFileName() {
		String fileName = "";
		try(InputStream in = Files.newInputStream(Paths.get(UPLOAD_PATH));
				BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
			fileName = reader.readLine();
			reader.close();
			in.close();
		} catch(IOException ioe) {
			
		}
		
		return fileName;
	}
	
	/**
	 * Function set file name of the file upload<br>
	 * This file name will be use for input file in search algorithms, it will be 
	 * set to file UPLOAD_PATH
	 * 
	 * @param fileName	: fileName will be setted
	 */
	public void setFileName(String fileName) {
		try(OutputStream out = new BufferedOutputStream(Files.newOutputStream(Paths.get(UPLOAD_PATH), CREATE))) {
			out.write(fileName.getBytes());
			out.close();
		} catch(IOException ioe) {
			
		}
	}
}
