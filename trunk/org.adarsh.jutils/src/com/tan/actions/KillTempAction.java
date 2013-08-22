package com.tan.actions;

import java.io.File;
import java.io.IOException;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

import com.tan.util.StringUtil;

/**
 * 使用特定的编辑器打开
 * 编辑器可以在 preferences -> Jutils 配置 
 * @author Dolphin.
 */
public class KillTempAction implements IWorkbenchWindowActionDelegate {
	private IWorkbenchWindow window;
	private ISelection currentSelection;
	public static final String WINDOWS = "win32";
	public static final String LINUX = "linux";
	private String systemBrowser = "explorer";
	private String line;
	private boolean isWindows;
	private boolean isLogger = false; // Debug.
	private String editplusPath = null;// "\"C:\\Program Files\\EditPlus 3\\EDITPLUS.EXE\"";
	private String configEditorPath = null; // 检查 editorPath 是否变更
	private boolean isEditplusRight;
	
	public KillTempAction() {
		String os = System.getProperty( "osgi.os" );
		if ( WINDOWS.equalsIgnoreCase(os) ){
			systemBrowser = "explorer";
			isWindows = true;
		}
		else if (LINUX.equalsIgnoreCase(os) ) {
			systemBrowser = "nautilus";
		}
		line = System.getProperty("line.separator", "\r\n");
		log(new Object[]{
				"操作系统:",os,line
		});
		
	}
	
	private String tempdir,
					tmpdir,
				 tempIEDir;
	
	private boolean findDir = false;
	public void run(IAction action) {
		if ( isWindows ) {
			process() ;
		} else {
			MessageDialog.openInformation(null, "提示", "仅支持Windows");
		}
	}
	
	String msg = "";
	private void process() {
		msg = "";
		tempdir =  System.getProperty("java.io.tmpdir") ;
		if ( isDir( tempdir )  ) {
			delete( new File( tempdir ) );
			msg += "TEMP目录删除文件" + COUNT + "个\r\n";;
			COUNT = 0;
			
			String path ;
			findDir = true;
			path =  tempdir.replaceAll( "Temp.+", "Tmp" ) ;
			if ( isDir( path ) ) {
				tmpdir = path;
				delete( new File( tmpdir ) );
				msg += "TEM目录删除文件" + COUNT + "个\r\n";;
				COUNT = 0;
			}
			path =  tempdir.replaceAll( "Temp.+", "Microsoft\\\\Windows\\\\Temporary Internet Files" ) ;
			if ( isDir( path ) ) {
				tempIEDir = path;
				delete( new File( tempIEDir ) );
				msg += "IE缓存目录删除文件" + COUNT + "个\r\n";;
				COUNT = 0;
			}
			path = null;
			
			// process over 
			System.gc();
		} else {
			findDir = false;
		}
		
		MessageDialog.openInformation(null, "提示",  msg );
	}

	private boolean isDir(String path) {
		File file = new File( path );
		boolean exists =  file.exists() ;
		boolean isDir =  file.isDirectory();
		if ( !exists ) {
			msg += ( file + "目录未找到\r\n");
		} 
		if ( !isDir ) {
			msg += ( file + "不是目录\r\n");
		}
		return exists && isDir;
	}

	public static void main(String[] args) {
		delete( new File( "C:\\Users\\Administrator\\AppData\\Local\\Microsoft\\Windows\\Temporary Internet Files" ) );
	}
	
	public static int COUNT = 0;
	
	/*public static void delete1( File file ) {
		if ( file.isFile() ) {
			System.out.println("Delete file:"  + file );
			try {
				FileDeleteStrategy.FORCE.delete( file );
			} catch (IOException e) {
				e.printStackTrace();
			}
			COUNT++;
		}
		else if ( file.isDirectory() ) {
			File[] files = file.listFiles();
			for ( final File f : files ) {
				delete( f ); 
			}
			System.out.println("Delete dir:"  + file ); 
			try {
				FileDeleteStrategy.FORCE.delete( file );
			} catch (IOException e) {
				e.printStackTrace();
			}
			COUNT++;
		} 
	}*/
	
	public static void delete( File file ) {
		if ( file.isFile() ) {
			System.out.println("Delete file:"  + file );
			file.delete();
			COUNT++;
		}
		else if ( file.isDirectory() ) {
			File[] files = file.listFiles();
			for ( final File f : files ) {
				delete( f ); 
			}
			System.out.println("Delete dir:"  + file ); 
			file.delete();
			COUNT++;
		} 
	}
	
	private static void command( String command ) {
//		if ( isWindows) { // 如果是 windows 则加载Editplus. }
		if ( StringUtil.isEmpty( command ) ) {
			return;
		}
		Runtime runtime = Runtime.getRuntime();
		try {
			runtime.exec(command);
		} catch (IOException e) {
//			MessageDialog.openError(window.getShell(),
//					"jExploer Error", "Can't open " + locations);
			e.printStackTrace();
		} finally {
			/*log(new Object[]{
					 "command:", command 
			});*/
			command = null;
			runtime = null;
		}
	}


	private void log(final Object[] objects) {
		if (isLogger) {
			if (null !=  objects && objects.length > 0) {
				StringBuffer buf = new StringBuffer();
				for (int i = 0; i < objects.length; i++) {
					buf.append(objects[i]);
				}
				System.out.println(buf);
				buf = null;
			}
		}
	}

	public void selectionChanged(IAction action, ISelection selection) {
		currentSelection = selection;
	}

	public void dispose() {
	}

	public void init(IWorkbenchWindow w) {
		window = w;
	}
}