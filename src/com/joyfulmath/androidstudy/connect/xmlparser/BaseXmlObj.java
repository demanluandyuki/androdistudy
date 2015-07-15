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

import java.lang.reflect.Field;

public abstract class BaseXmlObj {
	public abstract String[] getNodes();

	public void setParamater(String tag, Object value) {

		try {

			Field field = getClass().getField(tag);

			field.setAccessible(true);

			field.set(this, value);

		} catch (SecurityException e) {

			e.printStackTrace();

		} catch (NoSuchFieldException e) {

			e.printStackTrace();

		} catch (IllegalArgumentException e) {

			e.printStackTrace();

		} catch (IllegalAccessException e) {

			e.printStackTrace();

		}

	}
}
