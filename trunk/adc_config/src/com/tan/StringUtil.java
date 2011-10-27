package com.tan;

public class StringUtil {
	
	public static String concatHttpUrl( final String ip, final String port, final String suffix ) {
		StringBuffer buf = new StringBuffer();
		buf.append( "http://").append(ip);
		
		if ( "80".equals( port )) {
			buf.append(suffix);
		} else {
			buf.append( ":" ).append( port ).append(suffix);
		}
		return buf.toString();
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println( "report.server.url=http://192.168.1.34:7001/adc_report/report".substring( 0, 17 ));
	}

}
