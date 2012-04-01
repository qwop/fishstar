package com.tan;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class AnalyseEJB {

	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final File ejbRoot = new File( "D:\\bea\\user_projects\\workspaces\\default\\adc_ejb\\ejbModule" );
		if ( ejbRoot.isDirectory() ) {
			final List<File> files = new ArrayList<File>();
			ejbRoot.listFiles(new FileFilter() {
				@Override
				public boolean accept(File file) {
					if ( file.isDirectory() ) {
						file.listFiles( this );
					} else if ( file.isFile() ) {
						if ( file.getName().endsWith( "Bean.java" ) ) {
							files.add( file );
						}
					}
					return false;
				}
			});
			//String regularExpression = "";
			//Pattern p  = Pattern.compile( regularExpression );
			for ( final File f : files ) {
				final String content = IOUtil.toString( f, "utf-8" );
				
				dump( f.getAbsolutePath(), content );
				
			}
		}
		
		
	}

	private static void dump( final String path, final String value) {
		int getHandlerIdx1 = value.indexOf( ".getHandler();" );
		if ( getHandlerIdx1 > 0 ) {
			int handlerReleaseIdx2 = value.indexOf( ".release();", getHandlerIdx1 );
			
			
			if ( handlerReleaseIdx2 > 0 ) {
				System.out.println( path + "\t" + ( handlerReleaseIdx2 - getHandlerIdx1 )  + " \t right ");
				dump( path, value.substring( handlerReleaseIdx2 ));
			} else {
				System.out.println( value.substring( getHandlerIdx1, handlerReleaseIdx2 ) );
				System.err.println( path + "\tno release" );
				dump( path, value.substring( getHandlerIdx1 ));
			}
			
		}
		
	}

}
