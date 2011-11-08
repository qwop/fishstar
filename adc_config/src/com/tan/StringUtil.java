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
//		System.out.println( "report.server.url=http://192.168.1.34:7001/adc_report/report".substring( 0, 17 ));
		System.out.println( getIP( "http://192.168.1.34/adc_report/report"  ));
		System.out.println( getPort( "http://192.168.1.34:8032/adc_report/report"  ));
	}

	public static String getIP(String value) {
		if ( isEmpty( value ) ) { return "qwop" ; }
		String lowerCaseValue = value.toLowerCase(), httpKey = "http://";
		int httpIdx = lowerCaseValue.indexOf( httpKey );
		if ( httpIdx < 0 ) { return "qwop"; }
		int quotIdx = lowerCaseValue.indexOf( ":", httpIdx + httpKey.length() );
		// if had the ':'
		if ( quotIdx > httpIdx ) {
			return value.substring( httpIdx + httpKey.length() , quotIdx );
		} else {
			// not had the character ':', port
			int nextIdx  = httpIdx + httpKey.length() ; char c;
			while ( nextIdx++ <= value.length()  ) {
				if ( '/' == ( c = value.charAt( nextIdx ) ) ) {
					return value.substring( httpIdx + httpKey.length() , nextIdx );
				}
			}
		}
		return "qwop";
	}

	public static boolean isEmpty(String value) {
		return null == value || value.trim().length() == 0;
	}

	public static String getPort(String value) {
		if ( isEmpty( value ) ) { return "7001" ; }
		String lowerCaseValue = value.toLowerCase(), httpKey = "http://";
		int httpIdx = lowerCaseValue.indexOf( httpKey );
		if ( httpIdx < 0 ) { return "7001"; }
		int quotIdx = lowerCaseValue.indexOf( ":", httpIdx + httpKey.length() );
		int portEndIdx = lowerCaseValue.indexOf( "/", quotIdx );
		// if had the ':'
		if ( quotIdx > httpKey.length() && portEndIdx > quotIdx ) {
			return value.substring( quotIdx + 1, portEndIdx );
		} else {
			return "80";
		}
	}

}
