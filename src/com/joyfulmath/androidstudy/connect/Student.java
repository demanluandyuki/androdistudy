package com.joyfulmath.androidstudy.connect;

import java.io.Serializable;

import com.joyfulmath.androidstudy.connect.xmlparser.BaseXmlObj;

public class Student extends BaseXmlObj implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public String GradeID, GradeName, ClassID, ClassName, UserID, UserName;
	
	public Student() {
	}
	
	@Override
	public String[] getNodes() {
		return new String[] { "GradeID", "GradeName", "ClassID", "ClassName", "UserID", "UserName" };
	}


	
	@Override
	public String toString() {
		String su = "@student:"+"GradeID:"+GradeID
				+"GradeName:"+GradeName
				+"ClassID:"+ClassID
				+"ClassName:"+ClassName
				+"UserID:"+UserID
				+"UserName:"+UserName;
		return NetWorkUtils.replaceBlank(su);
	}
	
	
}
