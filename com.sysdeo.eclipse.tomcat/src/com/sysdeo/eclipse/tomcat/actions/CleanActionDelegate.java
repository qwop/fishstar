package com.sysdeo.eclipse.tomcat.actions;

/*
 * (c) Copyright Sysdeo SA 2001, 2002.
 * All Rights Reserved.
 */

import java.io.File;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

import com.sysdeo.eclipse.tomcat.FileUtil;
import com.sysdeo.eclipse.tomcat.TomcatLauncherPlugin;

/**
 * 清空Tomcat Work目录
 * @author tanyuanji
 *
 */
public class CleanActionDelegate implements IWorkbenchWindowActionDelegate {
	private IWorkbenchWindow window;
	private boolean isLogger = true; // Debug.
	private String title = "清空目录";
	public CleanActionDelegate() {
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
		
		if(TomcatLauncherPlugin.checkTomcatSettingsAndWarn()) {
			final File tomcatDir = new File( TomcatLauncherPlugin.getDefault().getTomcatDir() ) ;
			boolean rmdirWork = rmdir( new File( tomcatDir, "work" ) ),
					 rmdirTemp = rmdir( new File( tomcatDir, "temp" ) );
			if ( rmdirWork && rmdirTemp) {
				alert( tomcatDir  + " work/temp 目录清空！");
			} else if ( rmdirWork && !rmdirTemp ) {
				alert( tomcatDir  + " work 目录清空！");
			} else if ( !rmdirWork && rmdirTemp ) {
				MessageDialog.openInformation(window.getShell(),
						title, tomcatDir  + " temp 目录清空！");
			} else if ( !rmdirWork && !rmdirTemp ) {
				MessageDialog.openInformation(window.getShell(),
						title, tomcatDir  + " work/temp 目录未清空！");
			}
		}
	}

	private void alert( final Object msg ) {
		MessageDialog.openInformation(window.getShell(),
				title, String.valueOf( msg ));
	}
	
	
	private boolean rmdir( File directory) {
		if (  null == directory ) { 
			return false;
		}
		if ( !directory.exists() ) { 
			log( directory + " 不存在！" );
			return false;
		}
		if ( !directory.isDirectory() ) {
			log( directory + "文件不为目录！" );
			return false;
		}
		FileUtil.removeDir( directory );
		directory.mkdir();
		
		return directory.exists() && directory.isDirectory() && directory.list().length == 0 ;
	}

	private void log( final Object msg ) {
		if ( isLogger ) {
			System.out.println( msg );
		}
	}

	/*
	 * @see IActionDelegate#selectionChanged(IAction, ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {

	}

}

