package com.tan;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import sun.font.TrueTypeFont;

public class StringUtilTest {

	@Test
	public void testGetAdcProjectKeyWord() {
		assertEquals(
				"通过adc_关键字获取当前项目的名称",
				"adc_config",
				StringUtil
						.getAdcProjectKeyWord("D:\\bea\\user_projects\\workspaces\\default\\adc_config"));
		assertEquals(
				"通过adc_关键字获取当前项目的名称",
				"adc_op",
				StringUtil
						.getAdcProjectKeyWord("D:\\bea\\user_projects\\workspaces\\default\\adc_op\\suffix"));
	}

	@Test
	public void testCalendar() {
		int year = 2011, month = 11, date = 11;

		Calendar c = Calendar.getInstance();

		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, (month - 1));
		c.set(Calendar.DATE, date);

		Date d = c.getTime();

		System.out.println(d.toLocaleString());

		final String timeStr = "2011-11-11";

		final String[] splits = timeStr.split("-");
		System.out.println(splits.length);
		for (final String split : splits) {
			System.out.println(split);
		}
		if ( 1 == 1 )
			System.out.println(" 1 == 1 ");
		else {
			System.out.println(" 1 != 1 ");
		}
	}
	
	@Test
	public void forTestCodeTemplate() {
		Integer value = (Integer) Calendar.getInstance().get( Calendar.YEAR );
		System.out.println( value );
		do {
			value = 1 ;
			System.out.println( value++ );
		} while (value != 11 );
		
		System.out.println( value );
		///////
		String singleDay = "光棍节", doubleDay = "双人节";
		if ( singleDay.length() > doubleDay.length() ) {
			
		} else {
			
		}
		
	}

}
