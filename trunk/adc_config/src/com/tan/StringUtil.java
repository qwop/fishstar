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
	
	public static String searchOwsXml( String xml, String key ) {
		int idx1 =  xml.indexOf( "<param-name>" + key + "</param-name>" ) ;
		int idx2, idx3;
		String key1 = "<param-value>", key2 = "</param-value>";
		if ( idx1 > 0 ) {
			idx2 = xml.indexOf( key1, idx1 );
			idx3 = xml.indexOf( key2, idx2 );
			if ( idx3 > idx2 && idx2 > 0 ) {
				return xml.substring( idx2 + key1.length() , idx3);
			}
		}
		return null;
	}
	
	public static String sarchSqlMap( String xml, String key, String newValue ) {
		key =  "<property name=\"" + key + "\" value=\"" ;
		int idx1 =  xml.indexOf( key ) , idx2;
		String key1 = "\"/>";
		if ( idx1 > 0 ) {
			idx2 = xml.indexOf( key1, idx1 );
			if ( idx2 > 0 ) {
				return xml.substring( 0 , idx1 + key.length() ) + newValue + xml.substring( idx2 );
			}
		}
		return xml;
	}	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println( "report.server.url=http://192.168.1.34:7001/adc_report/report".substring( 0, 17 ));
	}

}
