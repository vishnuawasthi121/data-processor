package com.ogivetechnology.processor.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DataProcessorUtils {

	public Date convertToDate(String dateStr) {
		Date parse = null;
		try {
			// dd-M-yyyy hh:mm:ss
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			parse = formatter.parse(dateStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return parse;
	}

}
