package com.tan;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class IOUtil {
	
	public static String toString(String searchFile) {
		return toString(searchFile,null);
	}
	
	public static String toString(String searchFile,final String encoding) {
		File f = new File( searchFile );
		return toString( f, encoding );
	}
	
	public static String toString(File f,final String encoding) {
		int len = -1;
		byte[] bufs = new byte[2046];
		InputStream in = null;
		StringBuffer buf = new StringBuffer();
		try {
			in = new FileInputStream( f );
			if ( null == encoding ) {
				while ( -1 != ( len = in.read( bufs, 0 , 2046))) {
					buf.append( new String( bufs, 0, len));
				}
			} else {
				while ( -1 != ( len = in.read( bufs, 0 , 2046))) {
					buf.append( new String( bufs, 0, len, encoding));
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if ( null != in ) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}in = null;
		}
		return buf.toString();
	}

	public static Properties load(String searchFile) {
		return load( new File( searchFile ));
	}
	
	public static Properties load( final File file) {
		Properties p = new Properties();
		try {
			p.load( new FileInputStream( file ));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return p;
	}

	public static void save(String searchFile, String newContent, String encoding) {
		OutputStream out = null;
		try {
			out = new FileOutputStream( searchFile );
			if ( null == encoding ) {
				out.write( newContent.getBytes() );
			} else {
				out.write( newContent.getBytes(encoding) );
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if ( null != out ) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}	
			} out = null;
		}
	}
	
	public static void save(String searchFile, String newContent) {
		save( searchFile, newContent, null );
	}

}
