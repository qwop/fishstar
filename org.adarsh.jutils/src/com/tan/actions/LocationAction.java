package com.tan.actions;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Locale;

import org.adarsh.jutils.internal.SourceManipulator;
import org.adarsh.jutils.preferences.PreferenceConstants;
import org.eclipse.core.internal.resources.ResourceInfo;
import org.eclipse.core.internal.resources.Workspace;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.part.FileEditorInput;

import com.tan.util.StringUtil;

/**
 * 定位Action
 * @author Dolphin.
 * @see IWorkbenchWindowActionDelegate
 */
public class LocationAction implements IWorkbenchWindowActionDelegate {
	private IWorkbenchWindow window;
	private ISelection currentSelection;
	public static final String WINDOWS = "win32";
	public static final String LINUX = "linux";
	private String systemBrowser = "explorer";
	private boolean isWindows;
	private boolean isLogger = false; // Debug.
	
	public LocationAction() {
		String os = System.getProperty("osgi.os");
		if (WINDOWS.equalsIgnoreCase(os)){
			systemBrowser = "explorer";
			isWindows = true;
		}
		else if (LINUX.equalsIgnoreCase(os)) {
			systemBrowser = "nautilus";
		}
		
		/*
		log(new Object[]{
				"操作系统:",os,StringUtil.LN
		});
		*/
	}

	public void run(IAction action) {
		if (currentSelection instanceof ITextSelection) {
			run();
			return;
		}
		if ((currentSelection != null)
				&& ((currentSelection instanceof TreeSelection))) {
			TreeSelection treeSelection = (TreeSelection) currentSelection;
			TreePath[] paths = treeSelection.getPaths();
			for (int i = 0; i < paths.length; i++) {
				TreePath path = paths[i];
				IResource resource = null;
				Object segment = path.getLastSegment();
				String location = null;
				if ((segment instanceof IResource))
					resource = (IResource) segment;
				else if ((segment instanceof IJavaElement)) {
					resource = ((IJavaElement) segment).getResource();
					
					 
				}
				
				if (null == resource) {
					String lastSegment = path.getLastSegment().toString().toLowerCase(Locale.ENGLISH);
					int idx = lastSegment.indexOf(".jar");
					if (idx >= 0) {
						location = lastSegment.substring(0, idx) + ".jar";
					}
				}
				
				log(
						new Object[]{
								//(null == resource ? "NULL" : resource) ,
								StringUtil.LN,
								"资源:" , resource,StringUtil.LN,
								"路径:" , location,StringUtil.LN,
								"TreePath:" , path
						}
				);
				
				if (location == null && resource == null) {
					continue;
				}
				
				if (null == location) {
					if ((resource instanceof IFile)) {
						IFile file = (IFile) resource;
						if (file.exists()) {
							location = file.getLocation().toOSString();
						} else {
							location = file.getParent().getLocation().toOSString();
						}
					} else {
						location = resource.getLocation().toOSString();
					}
				}
				command(location); 
			}
			paths = null;
		}
	}
	private void command(String location) {
		StringBuffer command = new StringBuffer();
		Runtime runtime = Runtime.getRuntime();
		String explorerPath =  SourceManipulator.PREF_STORE.getString(PreferenceConstants.EXPLORER_PATH);
		try {
			if ( !StringUtil.isEmpty( explorerPath ) && new File( explorerPath ).exists() ) {
				final File file = new File( location );
				if ( file.isFile() ) {
					location = file.getParentFile().getAbsolutePath();
				}
				command
				.append("\"")
				.append(explorerPath)
				.append("\" \"")
				.append(location)
				.append("\"");
			}
			else if ( isWindows && isFile(location)) {
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
			
			log(new Object[]{
					 "命令行:", command,StringUtil.LN 
			});
			
			runtime.exec(command.toString());
		} catch (IOException e) {
			MessageDialog.openError(window.getShell(),
					"jExploer Error", "Can't open " + location);
			e.printStackTrace();
		} finally {
			command = null;
			runtime = null;
			location = null;
		}
	}

	/*
	 * @see IAction#run()
	 */
	public void run() {
		if (window == null)
			return;
		IEditorPart editorPart = window.getActivePage().getActiveEditor();
		IEditorInput editorInput = editorPart.getEditorInput();
		IFile file = null;
		if (editorInput instanceof org.eclipse.ui.part.FileEditorInput) {//com.google.gwt.eclipse.core.editors.java.GWTJavaEditor@211fc4dd  org.eclipse.ui.part.FileEditorInput(/SmartGwtDemo/src/com/demo/gwt/client/ContentPanel.java)
			FileEditorInput fileEditorInput = (FileEditorInput) editorInput;
			file = fileEditorInput.getFile();
		}
		/*
		IWorkingCopyManager manager = JavaUI.getWorkingCopyManager();
		ITextEditor editor = (ITextEditor) editorPart;
		ITextSelection selection = (ITextSelection) editor
				.getSelectionProvider().getSelection();

		ICompilationUnit compUnit = manager.getWorkingCopy(editorInput);
		IJavaElement suspect = null;
		try {
			suspect = compUnit.getElementAt(selection.getOffset());
		} catch (JavaModelException e) {
		}
		IJavaProject project = null;
		if (suspect != null) {
			project = suspect.getJavaProject();
		}*/
		Workspace currentWorkspace = (Workspace) ResourcesPlugin.getWorkspace();
		IProject project = file.getProject();
		String projectName = project.getName();
		IPath projectFullPath = project.getFullPath();
		ResourceInfo resource = currentWorkspace.getResourceInfo(projectFullPath, true, false);
		URI uri = resource.getFileStoreRoot().computeURI(projectFullPath);
		log( new Object[]{ "【URI】", uri });
		// workspace's project path.
		String wpProjectPath = uri2dir(uri);
		String projectPath = projectFullPath.toString();
		int idx = wpProjectPath.indexOf(projectPath);
		if (idx >= 0) {
			wpProjectPath = wpProjectPath.substring(0, idx);
		}
		String path = wpProjectPath + file.getFullPath().toOSString();
		if (path.charAt(0) == '/') {
			path = path.substring(1);
		}
		path = path.replace('/', File.separatorChar).replace('\\', File.separatorChar);
		if (isFile(path)) {
			command(path);
		} else {// 项目名和项目路径名不同.
			path = StringUtil.replace(path, projectName + File.separatorChar, "");
		}
		
		// d:\svn\src/cn/gov/most/nsccsz/client/data/CommandTreeNode.java
		if (isFile(path)) {
			command(path);
		} else {
			log( new Object[]{ "【", path,"】", "不是一个有效的路径" });
		}
	}

	private static String uri2dir(URI uri) {
		String path = uri.getPath();
		File file = new File ( path );
		if ( file.isDirectory() ) {
			return file.getAbsolutePath();
		} else {
			file = new File( uri.toString().replaceFirst("file:/", "") );
			if ( file.isDirectory() ) return file.getAbsolutePath();
		}
		return path;
	}
	
	public static void main(String[] args) {
		try {
			System.out.println( uri2dir( new URI(  "file:/d:/svn/most.src/most") ));
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		
		
	}
	
	private boolean isFile(final String location) {
		if (null == location) return false;
		return new File(location).isFile();
	}

	private void log(final Object[] objects) {
		if (isLogger) {
			if (null !=  objects && objects.length > 0) {
				StringBuffer buf = new StringBuffer();
				for (int i = 0; i < objects.length; i++) {
					buf.append(objects[i]);
				}
				MessageDialog.openInformation( null ,"提示"  , buf.toString());
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