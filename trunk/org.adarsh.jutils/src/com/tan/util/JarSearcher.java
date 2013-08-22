package com.tan.util;

import java.awt.BorderLayout;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * 查询jar文件中的class
 * @author tanyuanji
 *
 */
public class JarSearcher {
	private File file;
	private List<File> lists;
	private String keyWord;
	private String[] fileSuffixs;
	private String dir;
	
	
	public JarSearcher(final String dir) {
		lists = new ArrayList<File>();
		init(dir);
	}

	private void init(String dir) {
		this.setDir(dir);
		System.out.println("Dir:" + dir);
		this.file = new File(dir);
		file.listFiles(new FileFilter() {
			public boolean accept(File path) {
				if (path.isDirectory()) {
					path.listFiles(this);
				} else if (path.isFile()
						&& path.getName().toLowerCase().endsWith(".jar")) {
					lists.add(path);
				}
				return false; // ignore the return segment.
			}
		});
		
		System.out.println("\tJar文件数:" + size());
	}

	public int size() {
		if (null == lists) {
			return 0;
		}
		return lists.size();
	}

	public void process(boolean searchClass) {
		JarFile jarFile = null;
		JarEntry jarEntry = null;
		String name;
		File file;
		Enumeration<JarEntry> entries;
		for (Iterator<File> iter = lists.iterator(); iter.hasNext();) {
			file = (File) iter.next();
			try {
				jarFile = new JarFile(file);
			} catch (IOException e) {
				e.printStackTrace();
			}

			if (null != jarFile) {
				entries = jarFile.entries();
				while (entries.hasMoreElements()) {
					jarEntry = (JarEntry) entries.nextElement();
					name = jarEntry.getName();
					// process the entry's stream.
					name = name.toLowerCase();

					if (searchClass) { // class
						searchClassName(name,file,  jarEntry);
					} else {
						searchEntryContent(name, jarFile, jarEntry);
					}
				}
			}
		}
	}

	public void searchEntryContent(final String name, final JarFile jarFile,
			final JarEntry jarEntry) {
		
		boolean isRightSuffix = false;
		String[] suffixs = getFileSuffixs();
		for (int i = 0; i < suffixs.length; i++) {
			if (name.toLowerCase().endsWith('.' + suffixs[i].toLowerCase())) {
				isRightSuffix = true; break;
			}
		}
		if (!jarEntry.isDirectory() && isRightSuffix
		) {
			if (search(jarFile, jarEntry)) {
				System.out.println(jarFile.getName() + "\t" + jarEntry);
			}
		}
	}

	public void searchClassName(String name, final File jarFile, final JarEntry jarEntry) {
		if (name.endsWith(".class")) {
			name = name.replace('/', '.').replaceAll(".class", "");
			name.toLowerCase();
			
			if ( name.indexOf( keyWord ) >= 0 ) {
				System.out.println(jarFile + "\t" + jarEntry);
			}
		}
	}

	/**
	 * Jar文件数:1867
D:\Eclipses\MyEclipse\Common\plugins\org.eclipse.osgi_3.7.2.v20120110-1415.jar	org/eclipse/osgi/internal/signedcontent/SignedContentMessages.properties

SECURITY ALERT:  INTEGRITY CHECK ERROR

This product did not pass the MyEclipse integrity check.  This security check is a prerequisite for launch of MyEclipse.  Please reinstall MyEclipse and try again.

Specific Error: The signature chain used to sign MyEclipse is either not valid or does not belong to Genuitec. MyEclipse features will not start with an unsigned installation.  Reason: SHA1 digest error for org/eclipse/osgi/internal/signedcontent/DigestedInputStream.class

For additional assistance contact support@genuitec.com

	 * @param args
	 */
	public static void main(String[] args) {
		// most/isomorphic/skins/ToolSkinNative/load_skin.js
		// com.isomorphic.taglib.LoadISCTag
//		 temp();
		
		//1. Create the frame.
		JFrame frame = new JFrame("FrameDemo");
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			SwingUtilities.updateComponentTreeUI(frame);
		} catch (Exception e) {
			e.printStackTrace();
		}	 

		//2. Optional: What happens when the frame closes?
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//3. Create components and put them in the frame.
		//...create emptyLabel...
//		frame.getContentPane().add(emptyLabel, BorderLayout.CENTER);
		//4. Size the frame.
		frame.add( new JList(), BorderLayout.AFTER_LAST_LINE );
		frame.pack();
		frame.setBounds( 100, 200, 300, 500);

		//5. Show it.
		frame.setVisible(true);
	}

	private static void temp() {
		String 
		path = "D:\\GWT\\smartgwtee-4.0p\\lib";
		path = "D:\\GWT\\smartgwtee-4.0p\\samples\\showcase";
		path = "D:\\GWT\\smartgwtee-4.0p\\";
		
		
		JarSearcher searcher = new JarSearcher(path);
		
		searcher.setFileSuffixs( "MF"  );
		searcher.setFileSuffixs( "properties", "xml", "txt", "ini" );
		
		searcher.setKeyWord( "Generate rebel.xml" );
//		searcher.process(searchClass);
		searcher.searchfilename( 
				"LoadISCTag",
				"ToolSkinNative/load_skin.js",
				"framework.datasources"
//				"load_skin.js"
		);
	}
	

	public void searchfilename( final String...strings ) {
		for ( final String str : strings ) {
			searchfilename( str );
		}
	}
	
	public void searchfilename( String str ) {
		if ( !StringUtil.isEmpty( str ) ){
			System.out.println("For filename:" + str);
			JarFile jarFile = null;
			JarEntry jarEntry = null;
			String oName,lName;
			File file;
			Enumeration<JarEntry> entries;
			str = str.toLowerCase();
			for (Iterator<File> iter = lists.iterator(); iter.hasNext();) {
				file = (File) iter.next();
				try {
					jarFile = new JarFile(file);
				} catch (IOException e) {
					e.printStackTrace();
				}

				if (null != jarFile) {
					entries = jarFile.entries();
					while (entries.hasMoreElements()) {
						jarEntry = (JarEntry) entries.nextElement();
						oName = jarEntry.getName();
						// process the entry's stream.
						lName = oName.toLowerCase();
						if ( lName.indexOf( str ) >= 0  ) {
							System.out.println( "\t" + file.getName() + "\t\t\t" + oName + "\t\t\t"+ file );
						}
					} // 
				}
			}
		
		}
	}

	private void setKeyWord(final String keyWord) {
		this.keyWord = keyWord.toLowerCase();
	}

	private boolean search(JarFile jarFile, JarEntry jarEntry) {
		int len = -1;
		byte[] buf = new byte[2046];
		StringBuffer builder = new StringBuffer();
		InputStream in = null;
		try {
			in = jarFile.getInputStream(jarEntry);
			while (-1 != (len = in.read(buf, 0, 2046))) {
				builder.append(new String(buf, 0, len));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != in)
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			in = null;
		}
		if (builder.length() > 0) {
			if (builder.toString().toLowerCase().indexOf( keyWord ) >= 0) {
				return true;
			}
			builder = null;
		}
		return false;
	}

	public String[] getFileSuffixs() {
		if (null == fileSuffixs) {
			fileSuffixs = new String[]{
					"properties",
					"xml",
			};
		}
		return fileSuffixs;
	}

	public void setFileSuffixs(String ... fileSuffixs) {
		this.fileSuffixs = fileSuffixs;
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

}
