package com.kh.spring.common.util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HelloSpringUtils {

	public static String getRenamedFilename(String originalFilename) {
		
		//확장자 추출
		int beginIndex = originalFilename.lastIndexOf(".");
		String ext = originalFilename.substring(beginIndex);
		
		//년월일_난수 formant
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmssSSS_");
		DecimalFormat df = new DecimalFormat("000");
		
		return sdf.format(new Date())+df.format(Math.random()*1000)+ext;
	}
}