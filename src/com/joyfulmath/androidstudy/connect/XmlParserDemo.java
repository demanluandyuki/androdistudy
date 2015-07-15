package com.joyfulmath.androidstudy.connect;


import com.joyfulmath.androidstudy.connect.xmlparser.XmlParserUtils;

import android.content.Context;
import android.content.res.XmlResourceParser;

public class XmlParserDemo {
	
	public static Student parserStudentXml(Context context,int resId)
	{
		try {
			Student student = new Student();
			XmlResourceParser xml = context.getResources()
					.getXml(resId);
			XmlParserUtils.streamText2Model(xml, student);
			xml.close();
			return student;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
}
