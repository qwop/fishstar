package com.sysdeo.eclipse.tomcat.actions;

/*
 * (c) Copyright Sysdeo SA 2001, 2002.
 * All Rights Reserved.
 */

import java.io.File;
import java.io.IOException;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

import com.sysdeo.eclipse.tomcat.TomcatLauncherPlugin;
import com.tan.util.Editor;

/**
 * 打开Tomcat配置文件目录
 * @author tanyuanji
 *
 */
public class PathActionDelegate implements IWorkbenchWindowActionDelegate {
	private IWorkbenchWindow window;
	public static final String WINDOWS = "win32";
	public static final String LINUX = "linux";	
	private boolean isWindows;
	private boolean isLogger = false; // Debug.
	private String line;	
	private String systemBrowser = "explorer";
	private String editplusPath = null;// "\"C:\\Program Files\\EditPlus 3\\EDITPLUS.EXE\"";

	public PathActionDelegate() {
		String os = System.getProperty("osgi.os");
		if (WINDOWS.equalsIgnoreCase(os)){
			systemBrowser = "explorer";
			isWindows = true;
		}
		else if (LINUX.equalsIgnoreCase(os)) {
			systemBrowser = "nautilus";
		}
		line = System.getProperty("line.separator", "\r\n");
		
		editplusPath = Editor.getEditplusPath();
	}
	
	/*
	 * @see IWorkbenchWindowActionDelegate#dispose()
	 */
	public void dispose() {
	}

	/*
	 * @see IWorkbenchWindowActionDelegate#init(IWorkbenchWindow)
	 */
	public void init(IWorkbenchWindow window) {
		this.window = window;
	}

	/*
	 * @see IActionDelegate#run(IAction)
	 */
	public void run(IAction action) {
		final String tomcatDir = TomcatLauncherPlugin.getDefault().getTomcatDir();
		
		if(TomcatLauncherPlugin.checkTomcatSettingsAndWarn()) {				
			//TomcatLauncherPlugin.log(TomcatLauncherPlugin.getResourceString("msg.start"));
			
			// 1.	只打开配置文件 
			// 2.	同时 定位文件夹 和 配置文件 
			// 3.	只定位文件夹 
			
			if ( TomcatLauncherPlugin.getDefault().isOpenConfigFolder() ) {
				explorerCommand( tomcatDir + File.separatorChar +  "conf" + File.separatorChar + "server.xml" );
			}
			
			if ( TomcatLauncherPlugin.getDefault().isOpenConfigXml() ) {
				openFileCommand( "\"" + tomcatDir + File.separatorChar +  "conf" + File.separatorChar + "server.xml\"" );
				log(  "\"" + tomcatDir + File.separatorChar +  "conf" + File.separatorChar + "server.xml\""  );
			}
		}
	}
	
	
	private void explorerCommand(String location) {
		StringBuffer command = new StringBuffer();
		Runtime runtime = Runtime.getRuntime();
		try {
			if ( isWindows ) {
				command.append(systemBrowser)
				.append(" /select, \"")
				.append(location)
				.append("\"");
			} else {
				command.append(systemBrowser)
				.append(" \"")
				.append(location)
				.append("\"");
			}
			runtime.exec(command.toString());
		} catch (IOException e) {
			MessageDialog.openError(window.getShell(),
					"PathActionDelegate", "Can't open " + location);
			e.printStackTrace();
		} finally {
			log( command );
			command = null;
			runtime = null;
			location = null;
		}
	}
	
	private void log( final Object msg ) {
		if ( isLogger ) {
			System.out.println( msg );
		}
	}
	
	private void openFileCommand( final String location ) {
		StringBuffer command = new StringBuffer();
		Runtime runtime = Runtime.getRuntime();
		try {
			if ( isWindows ) {
				command.append(editplusPath).append( " " )
				.append(location);
			} 
			else {
				command.append(systemBrowser)
				.append(location);
			}
			
			log( command );
			runtime.exec(command.toString());
		} catch (IOException e) {
			MessageDialog.openError(window.getShell(),
					"PathAction Delegate Error", "Can't open " + location);
			e.printStackTrace();
		} finally {
			log(new Object[]{
					 "command:", command,line 
			});
			command = null;
			runtime = null;
		}
	}
	/*
	 * @see IActionDelegate#selectionChanged(IAction, ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {

	}

}

