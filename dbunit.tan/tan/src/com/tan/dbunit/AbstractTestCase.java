package com.tan.dbunit;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

import junit.framework.TestCase;

import com.tan.dbunit.oracle.OracleUtil;

/**
 * 
 * @author dolphin
 *
 * 2011-10-21 下午4:58:29
 */
public abstract class AbstractTestCase extends TestCase{
	private static List<File> propList = new ArrayList<File>();
	
	private static final Logger logger = Logger.getLogger( "AbstracttTestCase" );
	
	private static String[] searchDirs = { "bin", "build", "classes" };
	
	private static PropFileFilter filter  = new PropFileFilter() ;
	
	static class PropFileFilter implements FileFilter {
		@Override
		public boolean accept(File file) {
			if ( file.isFile() && file.getName().endsWith( ".properties" )) {
				propList.add( file );
			} 
			// 不递归
//			else if ( file.isDirectory() ) {
//				file.listFiles( this );
//			}
			// ignore the result.
			return false;
		}
	}
	public AbstractTestCase() {
		super();
		loadProperties();
	}

	private void loadProperties() {
		// scan the class path then 
		final String userDir = System.getProperty("user.dir");
		
		if ( null != userDir ) {
			final File dir = new File( userDir );
			// find the dir
			if ( dir.isDirectory() && dir.exists() ) {
				logger.info( "查询目录 " + dir.getAbsolutePath()  + " 搜索properties文件");
				
				dir.listFiles(filter);
				
				// scan the sub dir.
				for ( final String subDir : searchDirs ) {
					listFiles( new File( dir, subDir ) );
				}
				
				// log the propList.
				logPropFiles();
			} else {
				logger.warning("The user dir not exists or the dir is not the Directory!");
			}
		} else {
			logger.warning("Can not get the user dir!");
		}
	}

	private void logPropFiles() {
		if ( null != propList && propList.size() > 0 ) {
			StringBuilder sb = new StringBuilder();
			sb.append( "加载Properties{\r\n" );
			for ( final File f : propList ) {
				sb.append( "\t" + f + "\r\n" );
			}
			sb.append( "}" );
			logger.info( sb.toString() );
		}
	}

	private void listFiles(File file) {
		if ( file.exists() && file.isDirectory() ) {
			file.listFiles(filter);
		}
	}
	
	
	public void allTableDataset( final String outputDir ) {
		Properties p = null;
		boolean find = false;
		for ( final File f : propList ) {
			p = new Properties();
			try {
				p.load( new FileInputStream( f ) );
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			if ( p.containsKey( "user" ) &&  p.containsKey( "password" ) &&  p.containsKey( "url" )&&  p.containsKey( "class" ) ) {
				find = true;
				break;
			}
		}
		
		if ( p != null && find ) {
			try {
				Class.forName( (String) p.remove( "class") );
				OracleUtil.allTableDataset( outputDir, p );
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			logger.warning("缺少数据库连接信息！");
		}
	}
}
