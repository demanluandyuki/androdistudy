/***********************************************************

 *@description : This class function is TODO 

 *

 * @create author : deman.lu

 * @create date   :2015-07-15

 * @modify author :

 * @modify date   :

 * @contact: joyfulmath.china@gmail.com

 *

 **********************************************************/

package com.joyfulmath.androidstudy.connect.xmlparser;

import java.io.InputStream;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.content.res.XmlResourceParser;

public class XmlParserUtils {

	private static String encode = "utf-8";
	public static XmlPullParser pullParser;
	static {

		try {
			pullParser = XmlPullParserFactory.newInstance().newPullParser();
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @description :解析节点中的内容，封装成对象模型。
	 * 
	 * @author : deman.lu
	 * 
	 * @create :2015-07-15
	 * 
	 * @param in	network data contains xml
	 * 
	 * @param obj
	 * 
	 * @throws Exception
	 * 
	 * @return :T extends BaseXmlObj
	 */
	public static <T extends BaseXmlObj> void streamText2Model(InputStream in,
			T obj) throws Exception {

		pullParser.setInput(in, encode);
		int eventType = pullParser.getEventType();
		String[] nodes = obj.getNodes();
		String nodeName = null;
		boolean success = true;

		while (eventType != XmlPullParser.END_DOCUMENT && success) {

			switch (eventType) {
			case XmlPullParser.START_DOCUMENT:
				break;

			case XmlPullParser.START_TAG:
				nodeName = pullParser.getName();
				break;

			case XmlPullParser.TEXT:
				for (int i = 0; i < nodes.length; i++) {
					if (nodes[i].equals(nodeName)) {
						obj.setParamater(nodeName, pullParser.getText());
					}
				}
				break;

			case XmlPullParser.END_TAG:
				break;
			}

			eventType = pullParser.next();
		}
	}
	
	/**
	 * 
	 * @description :解析节点中的内容，封装成对象模型。
	 * 
	 * @author : deman.lu
	 * 
	 * @create :2015-07-15
	 * 
	 * @param resParser		XmlResourceParser read form resource
	 * 
	 * @param obj
	 * 
	 * @throws Exception
	 * 
	 * @return :void
	 */
	public static <T extends BaseXmlObj> void streamText2Model(XmlResourceParser resParser,
			T obj) throws Exception {

		int eventType = resParser.getEventType();
		String[] nodes = obj.getNodes();
		String nodeName = null;
		boolean success = true;

		while (eventType != XmlPullParser.END_DOCUMENT && success) {

			switch (eventType) {
			case XmlPullParser.START_DOCUMENT:
				break;

			case XmlPullParser.START_TAG:
				nodeName = resParser.getName();
				break;

			case XmlPullParser.TEXT:
				for (int i = 0; i < nodes.length; i++) {
					if (nodes[i].equals(nodeName)) {
						obj.setParamater(nodeName, resParser.getText());
					}
				}
				break;

			case XmlPullParser.END_TAG:
				break;
			}

			eventType = resParser.next();
		}
	}
	
}
