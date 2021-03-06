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

import com.tan.util.Editor;
import com.tan.util.StringUtil;

/**
 * 使用特定的编辑器打开
 * 编辑器可以在 preferences -> Jutils 配置 
 * @author Dolphin.
 */
public class EditplusAction implements IWorkbenchWindowActionDelegate {
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
	
	public EditplusAction() {
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

	private void loadEditplus() {
		configEditorPath = SourceManipulator.PREF_STORE.getString(PreferenceConstants.EDITOR_PATH);
		
		// 如果配置了编辑器的路径
		if ( StringUtil.isNotEmpty( configEditorPath ) ) {
			
			if ( !checkEditplusRight( configEditorPath ) ) {
				log(
						new Object[]{
								"编辑器配置有误！:", configEditorPath
						}
				);
			}
		}
		
		editplusPath = configEditorPath;
		
		if ( StringUtil.isEmpty( editplusPath ) ) {
			editplusPath = new Editor().getEditplusPath();
			if ( checkEditplusRight( editplusPath ) ) {
				// add by qwop 2013 06 08
				SourceManipulator.PREF_STORE.setValue( PreferenceConstants.EDITOR_PATH, editplusPath);
			}
		}
		
	}

	private boolean checkEditplusRight( final String path ) {
		if (StringUtil.isEmpty( path )) {
			log(
					new Object[]{
							"Editplus路径为空:", path
					}
			);
			return false ;
		} else {
			File f ;
			String absolutePath = path ;
			if (absolutePath.charAt(0) == '\"') {
				absolutePath = absolutePath.substring(1);
			}
			int len = absolutePath.length();
			if (absolutePath.charAt(len - 1) == '\"')  {
				absolutePath = absolutePath.substring(0, len - 1);
			}
			f = new File(absolutePath);
			if (f.exists() && 
				f.isFile() && 
				f.getName().toLowerCase().indexOf(".exe") >= 0) {
				isEditplusRight = true;
				return true;
			} else {
				log(
						new Object[] {
								"文件不存在或者是目录或者文件后缀名不为Exe:",editplusPath
						}
				);
			}
			f = null;
			absolutePath = null;
		}
		
		return false;
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
			StringBuffer locations = new StringBuffer();
			for (int i = 0; i < paths.length; i++) {
				TreePath path = paths[i];
				IResource resource = null;
				String location = null;
				Object segment = path.getLastSegment();
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
								line,
								"Resource:" , resource,line,
								"Location:" , location,line,
								"TreePath:" , path
						}
				);
				
				if (location == null && resource == null) {
					continue;
				}
				
				if (null == location) {
					if ((resource instanceof IFile)) {
						final IFile file = (IFile) resource;
						if (file.exists()) {
							location = file.getLocation().toOSString() ;
						} else {
							location =  file.getParent().getLocation().toOSString() ;
						}
					} else {
						location =  resource.getLocation().toOSString() ;
					}
					
					// 如果问文件的话，并且不是目录, 不是jar文件.
					if (new File(location).isFile()  && !location.toLowerCase().endsWith(".jar")) {
						locations.append( " \"" ).append(location).append( "\"" );
					}
				}
			}
			command(locations.toString()); 
			paths = null;
		}
	}
	
	
	private void command( final String location ) {
		
		if ( isWindows) { // 如果是 windows 则加载Editplus.
			loadEditplus();
		}
		
		if ( !isEditplusRight ) {
			MessageDialog.openInformation(null,
					"编辑器文件未找到",
					"编辑器文件未找到,请安装或配置!");
//			action.setEnabled(false);
			return;
		}
		
		
		if ( StringUtil.isEmpty( location ) ) {
			return;
		}
		StringBuffer command = new StringBuffer();
		Runtime runtime = Runtime.getRuntime();
		try {
			if ( isWindows ) {
				command.append(editplusPath)
				.append(" \"")
				.append(location)
				.append("\"")
				;
			} 
			else {
				command.append(systemBrowser)
				.append(" \"")
				.append(location)
				.append("\"")
				;
			}
			runtime.exec(command.toString());
		} catch (IOException e) {
			MessageDialog.openError(window.getShell(),
					"jExploer Error", "Can't open " + location);
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
	 * @see IAction#run()
	 */
	public void run() {
		if (window == null)
			return;
		IEditorPart editorPart = window.getActivePage().getActiveEditor();
		IEditorInput editorInput = editorPart.getEditorInput();
		IFile file = null;
		if (editorInput instanceof org.eclipse.ui.part.FileEditorInput) {
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