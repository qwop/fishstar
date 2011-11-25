package com.tan;

import java.io.File;
import java.util.Properties;

public class Replacer {
	private String fileType; // properties, xml
	private String searchFile;
	private Main main;
	static final String urls[] = { "ImageUrl", "ECLoginUrl", "AreaLoginUrl",
			"SILoginUrl" };

	public Replacer(String searchFile, Main main) {
		this.searchFile = searchFile;
		searchFile = searchFile.toLowerCase();
		this.main = main;
		if (searchFile.endsWith(".properties")) {
			fileType = "properties";
		} else if (searchFile.endsWith(".xml")) {
			fileType = "xml";
		}
		
	}	
	
	public Replacer(File searchFile, Main main) {
		this( searchFile.getAbsolutePath() , main );
	}
	
	public void replace(final String key, final String newValue, final boolean replaceMode) {
		String content = IOUtil.toString(searchFile, "utf-8"),
			oldContent = content;
		// adc_ows init.properties.
		if ("properties".equals(fileType)) { // for properties file.
			// properties file.
			Properties p = IOUtil.load(searchFile);
			if ("ows.serviceUrl".equals(key) && p.containsKey(key)) {
				String oldValue = p.getProperty(key);
				if (!oldValue.equals(newValue)) {
					content = content.replace(key + "=" + oldValue, key
							+ "=" + newValue);
				}
			}
		} else if ("xml".equals(fileType)) { // for xml file
			if (searchFile.toLowerCase().endsWith("web.xml")) {
				if (content.indexOf("adc_ows") >= 0) { // is adc_ows's web.xml
														// file
					for (final String url : urls) {
						String oldValue = StringUtil.searchOwsXml(content, url);
						if (null != oldValue) {
							content = content.replace(oldValue, StringUtil.concatHttpUrl(
									main.getFrontIp(), main.getFrontPort(),
									getSuffix(url)));
						}
					}
				}
				
			}
			
			if (searchFile.endsWith("SqlMapConfig.xml")) {
				if (content.indexOf("ibatis") >= 0) { // is adc_ows's
					// web.xml
					// file
					content =   StringUtil.sarchSqlMap( content,  "JDBC.ConnectionURL", "jdbc:oracle:thin:@" + main.getDbIP() + ":" + main.getDbPort() + ":" + main.getSid() ) ;
					content =   StringUtil.sarchSqlMap( content,  "JDBC.Username", main.getReportDbUser() ) ;
					content =   StringUtil.sarchSqlMap( content,  "JDBC.Password", main.getReportDbPass() ) ;
					
				}
			}
			
			
			if (searchFile.endsWith("SqlMapConfig_adc.xml")) {
				if (content.indexOf("ibatis") >= 0) { // is adc_ows's // web.xml // file
					content =   StringUtil.sarchSqlMap( content,  "JDBC.ConnectionURL", "jdbc:oracle:thin:@" + main.getDbIP() + ":" + main.getDbPort() + ":" + main.getSid() ) ;
					content =   StringUtil.sarchSqlMap( content,  "JDBC.Username", main.getDbUser() ) ;
					content =   StringUtil.sarchSqlMap( content,  "JDBC.Password", main.getDbPass() ) ;

				}
			}
		}
		
		// save the change.
		if (!oldContent.equals(content)) {
			
			if ( replaceMode ) {
				IOUtil.save(searchFile, content, "utf-8");
			} else {
				File file = new File( searchFile );
				
				if ( file.isFile() ) {
					IOUtil.save( file.getName(), content, "utf-8");
				}
			}
		}
	}

	private String getSuffix(String url) {
		// "ImageUrl", "ECLoginUrl" , "AreaLoginUrl", "SILoginUrl"
		if (url.equals(urls[0])) {
			return "/adc_ec/file/";
		} else if (url.equals(urls[1])) {
			return "/adc_pc/";
		} else if (url.equals(urls[2])) {
			return "/adc_area/login.jsp";
		} else if (url.equals(urls[3])) {
			return "/adc_si/login.jsp";
		}
		return "/login.jsp";
	}

	public static void main(String[] args) {
		String key = "ows.serviceUrl";
		Properties p = IOUtil.load("c:\\1.properties");
		final String[] keys = { "one", "two", "three", "four", "five" };
		for (final String k : keys) {
			System.out.println(p.get(k));
		}
		final String value = "one=1\r\ntwo = 2\r\nthree		= 3\r\nfour =	4        \r\nfive					=				5					";
		final String[] lines = value.split("\r\n");
		for (int i = 0; i < lines.length; i++) {
			System.out.println(lines[i] + "\r\n\t"
					+ lines[i].matches(keys[i] + "\\s*=\\s*.+"));
		}
	}

}
