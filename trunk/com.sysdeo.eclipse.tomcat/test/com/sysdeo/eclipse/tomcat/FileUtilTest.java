package com.sysdeo.eclipse.tomcat;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

public class FileUtilTest {

	@Test
	public void testRemoveDir() {
		final File work =  new File( "D:\\apache-tomcat-6.0.24\\work" ) ;
		
		if ( !work.exists() ) {
			work.mkdirs();
		}
		
		FileUtil.removeDir( work );
		assertEquals( "清空目录" , true,  work.mkdir() );
	}

}
