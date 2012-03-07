// Copyright (c) 2003-2012, Jodd Team (jodd.org). All Rights Reserved.

package jodd.io;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import jodd.io.findfile.FindFile;
import jodd.io.findfile.RegExpFindFile;
import jodd.io.findfile.WildcardFileScanner;
import jodd.io.findfile.WildcardFindFile;
import junit.framework.TestCase;

public class FindFileTest extends TestCase {

	protected String dataRoot;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		if (dataRoot != null) {
			return;
		}
		URL data = FileUtilTest.class.getResource("data");
		dataRoot = data.getFile().substring(1);
	}

	public void testWildcardFile() {
		FindFile ff = new WildcardFindFile("*file/a*")
				.setRecursive(true)
				.setIncludeDirs(true)
				.searchPath(dataRoot);

		int countDirs = 0;
		int countFiles = 0;

		File f;
		while ((f = ff.nextFile()) != null) {
			if (f.isDirectory() == true) {
				countDirs++;
			} else {
				countFiles++;
				String path = f.getAbsolutePath();
				path = FileNameUtil.separatorsToUnix(path);
				boolean matched =
						path.equals(dataRoot + "/file/a.png") ||
						path.equals(dataRoot + "/file/a.txt");

				assertTrue(matched);

			}
		}

		assertEquals(0, countDirs);
		assertEquals(2, countFiles);

		ff.searchPath(dataRoot);

		countDirs = 0;
		countFiles = 0;

		Iterator<File> iterator = ff.iterator();

		while (iterator.hasNext()) {
			f = iterator.next();

			if (f.isDirectory() == true) {
				countDirs++;
			} else {
				countFiles++;
				String path = f.getAbsolutePath();
				path = FileNameUtil.separatorsToUnix(path);
				boolean matched =
						path.equals(dataRoot + "/file/a.png") ||
						path.equals(dataRoot + "/file/a.txt");

				assertTrue(matched);
			}
		}

		assertEquals(0, countDirs);
		assertEquals(2, countFiles);

	}


	public void testWildcardPath() {
		FindFile ff = new WildcardFindFile("**/file/*", true)
				.setRecursive(true)
				.setIncludeDirs(true)
				.searchPath(dataRoot);

		int countDirs = 0;
		int countFiles = 0;

		File f;
		while ((f = ff.nextFile()) != null) {
			if (f.isDirectory() == true) {
				countDirs++;
			} else {
				countFiles++;
				String path = f.getAbsolutePath();
				path = FileNameUtil.separatorsToUnix(path);
				boolean matched =
						path.equals(dataRoot + "/file/a.png") ||
						path.equals(dataRoot + "/file/a.txt");

				assertTrue(matched);

			}
		}

		assertEquals(0, countDirs);
		assertEquals(2, countFiles);
	}

	public void testDumpPropertiesInJar() {
		WildcardFileScanner wfs = new WildcardFileScanner("**", true);
		wfs.setRecursive(true);
		wfs.setIncludeDirs(true);
		
		List<File> files = wfs.list( "E:/Projects/indiff/eclise-plugins/babel3.6/eclipse");
		for ( final File  file : files ) {
			dump( file);
		}
	}
	
	private void dump(File file) {
		try {
			
			if ( file.exists() && file.canRead() ) {
				JarFile jarFile = new JarFile( file );
				Enumeration<JarEntry> entries = jarFile.entries();
				while ( entries.hasMoreElements() ) {
					dump( file, jarFile, entries.nextElement() );
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

	private void dump( File path, JarFile file, JarEntry entry) {
		if ( null != entry && !entry.isDirectory() ) {
			String name = entry.getName().toLowerCase();
			if ( name.indexOf( ".properties" ) >= 0 ) {
				Properties prop = entry2properties( file, entry );
				prop.containsValue( "新的文本文件行定界符" );
				System.out.println( path.getName() + "->" + entry.getName() + "{\r\n}");
			}
		}
	}

	private  Properties entry2properties(JarFile jarFile, JarEntry jarEntry) {
		Properties prop = new Properties();
		try {
			prop.load( jarFile.getInputStream(jarEntry) );
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}
	
	public void testWildcardFileScanner() {
		WildcardFileScanner wfs = new WildcardFileScanner("**/file/**", true);

		List<File> files = wfs.list(dataRoot);
		assertEquals(1, files.size());

		wfs.setRecursive(true);

		wfs.setIncludeDirs(true);
		files = wfs.list(dataRoot);
		assertEquals(3, files.size());

		wfs.setIncludeDirs(false);
		files = wfs.list(dataRoot);
		assertEquals(2, files.size());

		assertTrue(files.contains(new File(dataRoot + "/file/a.png")));
		assertTrue(files.contains(new File(dataRoot + "/file/a.txt")));
	}

	public void testRegexp() {
		FindFile ff = new RegExpFindFile(".*/a[.].*")
				.setRecursive(true)
				.setIncludeDirs(true)
				.searchPath(dataRoot);

		int countDirs = 0;
		int countFiles = 0;

		File f;
		while ((f = ff.nextFile()) != null) {
			if (f.isDirectory() == true) {
				countDirs++;
			} else {
				countFiles++;
				String path = f.getAbsolutePath();
				path = FileNameUtil.separatorsToUnix(path);
				boolean matched =
						path.equals(dataRoot + "/file/a.png") ||
						path.equals(dataRoot + "/file/a.txt");

				assertTrue(matched);

			}
		}

		assertEquals(0, countDirs);
		assertEquals(2, countFiles);
	}

}
