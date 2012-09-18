package com.tan.jutils.popup.actions;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import org.adarsh.jutils.JUtilsPlugin;
import org.eclipse.core.resources.IFile;
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
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

import com.tan.util.StringUtil;

/**
 * Get the jar information to the jars.txt.
 * @author qwop
 *
 */
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
		
		int size = tree.size();
		if ( size == 1 ) {
			IProject project = (IProject) tree.getFirstElement();
			handleProject(project);
		} else if ( size > 1 ) {
			Object[] objs = tree.toArray();
			if ( null != objs ) {
				for ( int i = 0; i < objs.length; i++ ) {
					if ( objs[ i ] instanceof IProject ) {
						handleProject( ( IProject ) objs[ i ] );
					}
				}
			}
		}
		
		
	}

	private void handleProject(IProject project) {
		if ( null != project ) {
			String pn;
			try {
					pn = project.getName();
					buf = new StringBuffer( pn );
					try {
						buildJarsBuffer(project);
					} catch (CoreException e) {
						e.printStackTrace();
					}
					
					IFile jarsFile = project.getFile( 
//							EclipseUtil.findBestSourceFolderForRebelXml( javaProject )
							project
							.getProjectRelativePath()
							.append("jars.txt")
					);
				    InputStream input = null;
				    try
				    {
				      input = new ByteArrayInputStream(  buf.toString().getBytes("UTF-8") );
				    } catch (UnsupportedEncodingException e) {
				      e.printStackTrace();
				    }
				    if (jarsFile.exists())
				      jarsFile.setContents(input, 1, null);
				    else {
				      jarsFile.create(input, true, null);
				    }
				    jarsFile.setCharset("UTF-8", null);
			} catch (JavaModelException e1) {
				e1.printStackTrace();
			} catch (CoreException e) {
				e.printStackTrace();
			} finally {
				buf = null;
			}
		}
	}

	private void buildJarsBuffer(IProject project) throws CoreException,
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
//		IClasspathEntry referencedClasspaths[] = javaProject.getReferencedClasspathEntries();
//		boolean ignoreUnresolvedEntry = false;
//		IClasspathEntry resolvedClasspaths[] = javaProject.getResolvedClasspath( ignoreUnresolvedEntry );
		
		IClasspathEntry rawClasspaths[] = javaProject.getRawClasspath();
		
//		add( referencedClasspaths, rawClasspaths, resolvedClasspaths );
		add( rawClasspaths );
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
		for ( int i = 0; i < ces.length; i++) {
			
			if ( ces[ i ].getEntryKind() == IClasspathEntry.CPE_LIBRARY ) {
				StringUtil.appendln( buf , 
						ces[ i ].getPath().toOSString()
				);
			}
			
			 if ( ces[ i ].getEntryKind() == IClasspathEntry.CPE_CONTAINER) {
                 IClasspathContainer container = null;
				 try {
					 container = JavaCore.getClasspathContainer( ces[ i ].getPath(), javaProject );
				 } catch (JavaModelException e) {
					e.printStackTrace();
				 }
				
                 if (container != null  ) {
                	 if ( (container instanceof JREContainer) ) {
                		 JREContainer jreContainer = ( JREContainer ) container;
                		 IClasspathEntry[] classpathEntries = jreContainer.getClasspathEntries();
                		 for ( int z = 0; z < classpathEntries.length; z++) {
                             IPath path = classpathEntries[ z ].getPath();
                             if ( isValidJavaHomePath(path) ) {
                            	 StringUtil.appendln( buf,
                            			 ( path.toFile().getParentFile().getParentFile().getParent() ) );
                            	 
                            	 break;
                             }
                         }
                	 } else {
                		  IClasspathEntry[] classpathEntries = container.getClasspathEntries();
                          for ( int j = 0; j < classpathEntries.length; j++ ) {
                              IPath path = classpathEntries[ i ].getPath();
                              if (isValidPath(path)) {
                             	 StringUtil.appendln( buf,
                             			 ( path.toFile().getAbsolutePath() ) );
                              }
                          }
                	 } // end for else .
                } // end for resolve the container.
             }

		}
	}

    private boolean isValidJavaHomePath(IPath path) {
        // segmentCount() > 1 is workaround for https://bugs.eclipse.org/bugs/show_bug.cgi?id=281189
        // classpath contains unlikely one of root directories like /lib/ "as is"
        return path != null && path.segmentCount() > 1 && path.toFile().exists();

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
