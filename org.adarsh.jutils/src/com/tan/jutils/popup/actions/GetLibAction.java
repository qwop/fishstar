package com.tan.jutils.popup.actions;

import org.adarsh.jutils.JUtilsPlugin;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jdt.core.IClasspathContainer;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.internal.launching.JREContainer;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

import com.tan.util.StringUtil;

public class GetLibAction implements IObjectActionDelegate {
	/**
	 * The preference store associated with the plugin.
	 */
	private static final IPreferenceStore PREF_STORE = JUtilsPlugin
			.getDefault().getPreferenceStore();
	
	
	private Shell shell;
	
	/**
	 * The selection.
	 */
	private ISelection seleciton;
	
	
	private StringBuffer buf;
	private IJavaProject javaProject ;

	
	/**
	 * Constructor for Action1.
	 */
	public GetLibAction() {
		super();
	}

	/**
	 * @see IObjectActionDelegate#setActivePart(IAction, IWorkbenchPart)
	 */
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		shell = targetPart.getSite().getShell();
	}

	/**
	 * @see IActionDelegate#run(IAction)
	 */
	public void run(IAction action) {
//		TreeSelection tree = (TreeSelection) seleciton;
		StructuredSelection tree = (StructuredSelection) seleciton;
		
		IProject project = (IProject) tree.getFirstElement();
		
		if ( null != project ) {
			String pn;
			try {
					pn = project.getName();
					buf = new StringBuffer( pn );
					try {
						printProjectInfo(project);
					} catch (CoreException e) {
						e.printStackTrace();
					}
			} finally {
			}
			
				MessageDialog.openInformation(
						shell,
						"TanUtil提示",
						buf.toString() );
				System.out.println(  buf );
				buf = null;
		}
	}

	private void printProjectInfo(IProject project) throws CoreException,
			JavaModelException {
		// Check if we have a Java project
		if (project.isNatureEnabled("org.eclipse.jdt.core.javanature")) {
			javaProject = JavaCore.create(project);
			buildClasspathInfo();
		}
	}
	
	private void buildClasspathInfo()
			throws JavaModelException {
		// buf
		IClasspathEntry referencedClasspaths[] = javaProject.getReferencedClasspathEntries();
		
		IClasspathEntry rawClasspaths[] = javaProject.getRawClasspath();
		
		boolean ignoreUnresolvedEntry = false;
		IClasspathEntry resolvedClasspaths[] = javaProject.getResolvedClasspath( ignoreUnresolvedEntry );
		
		add( referencedClasspaths, rawClasspaths, resolvedClasspaths );
//		add( resolvedClasspaths );
	}
	

	private void add(IClasspathEntry[] referencedClasspaths,
			IClasspathEntry[] rawClasspaths,
			IClasspathEntry[] resolvedClasspaths) {
		
		StringUtil.appendln( buf , "referencedClasspaths:" );
		add( referencedClasspaths );
		
		StringUtil.appendln( buf , "rawClasspaths:" );
		add( rawClasspaths );
		
		StringUtil.appendln( buf , "resolvedClasspaths:" );
		add( resolvedClasspaths );
	}

	private void add(IClasspathEntry[] ces) {
		for ( IClasspathEntry entry : ces ) {
			
			if ( entry.getEntryKind() == IClasspathEntry.CPE_LIBRARY ) {
				StringUtil.appendln( buf , 
						entry.getPath().toFile().getAbsolutePath()
				);
			}
			
			 if (entry.getEntryKind() == IClasspathEntry.CPE_CONTAINER) {
                 IClasspathContainer classpathContainer = null;
				try {
					classpathContainer = JavaCore.getClasspathContainer(entry.getPath(), javaProject);
				} catch (JavaModelException e) {
					e.printStackTrace();
				}
				
				
                 if (classpathContainer != null ) {
                	 if ( (classpathContainer instanceof JREContainer) ) {
                		 JREContainer jreContainer = ( JREContainer ) classpathContainer;
                		 IPath path = jreContainer.getPath();
                		 StringUtil.appendln( buf, ("***********" + path.toOSString() ) );
                	 } else {
                		  IClasspathEntry[] classpathEntries = classpathContainer.getClasspathEntries();
                          for (IClasspathEntry iClasspathEntry : classpathEntries) {
                              IPath path = iClasspathEntry.getPath();
                              if (isValidPath(path)) {
                             	 StringUtil.appendln( buf, (path.toOSString() ) );
                              }
                          }
                	 } // end for else .
                 } // end for resolve the container.
             }

		}
	}

    /**
     * @param path may be null
     * @return true if the path is considered as valid for the classpath
     */
    private static boolean isValidPath(IPath path) {
        // segmentCount() > 1 is workaround for https://bugs.eclipse.org/bugs/show_bug.cgi?id=281189
        // classpath contains unlikely one of root directories like /lib/ "as is"
        return path != null && path.segmentCount() > 1 && path.toFile().exists();
    }
    
    
	private Object getKind(int entryKind) {
		switch ( entryKind ) {
			case IClasspathEntry.CPE_CONTAINER : {
				return "CPE_CONTAINER";
			}
			case IClasspathEntry.CPE_LIBRARY : {
				return "CPE_LIBRARY";
			}
			case IClasspathEntry.CPE_PROJECT : {
				return "CPE_PROJECT";
			}
			case IClasspathEntry.CPE_VARIABLE : {
				return "CPE_VARIABLE";
			}
			case IClasspathEntry.CPE_SOURCE : {
				return "CPE_SOURCE";
			}
		}
		return null;
	}

	/**
	 * @see IActionDelegate#selectionChanged(IAction, ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		this.seleciton = selection;
	}

}
