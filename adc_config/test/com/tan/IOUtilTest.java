package com.tan;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class IOUtilTest {

	@Test
	public void testToStringString() {
		final String content = IOUtil.toString( "E:\\adc\\LN_Version2\\build1027\\adc_ows\\WEB-INF\\web.xml", "utf-8" );
		String reuslt = StringUtil.searchOwsXml( content,  "AreaLoginUrl" );
		System.out.println( reuslt );
	}
	
	@Test
	public void testToStringString2() {
		String content = IOUtil.toString( "E:\\adc\\LN_Version2\\build1027\\adc_report\\WEB-INF\\classes\\res\\SqlMapConfig.xml", "utf-8" );
		content =   StringUtil.sarchSqlMap( content,  "JDBC.ConnectionURL", "jdbc:oracle:thin:@qwop:orcl" ) ;
		content =   StringUtil.sarchSqlMap( content,  "JDBC.Username", "username" ) ;
		System.out.println(  StringUtil.sarchSqlMap( content,  "JDBC.Password", "password" ) );
	}
}
