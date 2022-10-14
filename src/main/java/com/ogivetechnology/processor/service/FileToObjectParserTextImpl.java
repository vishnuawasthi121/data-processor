package com.ogivetechnology.processor.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.ogivetechnology.processor.entities.ShipmentDetail;

public class FileToObjectParserTextImpl implements FileToObjectParser {

	@Override
	public Object convert(File file) {
		
		List<ShipmentDetail> records = new ArrayList<ShipmentDetail>();
		
		try (FileInputStream fis = new FileInputStream(file); BufferedReader br = new BufferedReader(new FileReader(file));) {
			// Declaring a string variable
			String st;
			// Condition holds true till
			// there is character in a string
			while ((st = br.readLine()) != null) {
				// Print the string
				//System.out.println(st);
				
				String[] dataPairs = st.split(",");
				
				if(null != dataPairs && dataPairs.length > 0) {
					System.out.println("dataPairs   : "+Arrays.toString(dataPairs));
				}
				
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
