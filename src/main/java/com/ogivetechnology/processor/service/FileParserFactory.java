package com.ogivetechnology.processor.service;

public class FileParserFactory {

	public static FileToObjectParser getInstance(String fileType, Integer  counter) {

		switch (fileType) {

		// xlsx
		case "xlsx": {
			return new FileToObjectParserXLSXImpl();
		}
		
		case "txt": {
			return new FileToObjectParserTextImpl();
		}

		default: {
			return null;
		}
		}

	}
}
