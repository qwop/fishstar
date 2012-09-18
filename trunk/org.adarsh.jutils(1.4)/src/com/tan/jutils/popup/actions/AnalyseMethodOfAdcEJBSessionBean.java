package com.tan.jutils.popup.actions;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import org.adarsh.jutils.JUtilsPlugin;
import org.adarsh.jutils.preferences.PreferenceConstants;
import org.eclipse.core.filebuffers.FileBuffers;
import org.eclipse.core.filebuffers.ITextFileBufferManager;
import org.eclipse.core.filebuffers.LocationKind;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jdt.core.Flags;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.ISourceRange;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

import com.tan.util.StringUtil;

public class AnalyseMethodOfAdcEJBSessionBean implements IObjectActionDelegate {
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
	
	private File file;
	
	private RandomAccessFile raf;
	
	IPath path ;


	private String	visitedControlStyle;
	
	/**
	 * Constructor for Action1.
	 */
	public AnalyseMethodOfAdcEJBSessionBean() {
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
		TreeSelection tree = (TreeSelection) seleciton;
		
		IProject project = (IProject) tree.getFirstElement();
		
		visitedControlStyle = PREF_STORE.getString(PreferenceConstants.VISITED_CONTROL_STYLE);
		
		if ( null != project ) {
			String pn;
			try {
					pn = project.getName();
					file = new File("c:\\output_" + pn + "_" + style() +".log");
					appendToFile("项目名称: " + pn + StringUtil.LN);
					try {
						printProjectInfo(project);
					} catch (CoreException e) {
						e.printStackTrace();
					}
			} finally {
				if ( null != raf )  {
					try {
						raf.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				} raf = null;
			}
			
			
			if ( null != file && file.exists() && file.isFile() && file.length() > 0 ) {
				MessageDialog.openInformation(
						shell,
						"TanUtil提示",
						"分析类中的方法成功，查看文件: 	" + file );
			}
		}
	}

	private void printProjectInfo(IProject project) throws CoreException,
			JavaModelException {
		// Check if we have a Java project
		if (project.isNatureEnabled("org.eclipse.jdt.core.javanature")) {
			IJavaProject javaProject = JavaCore.create(project);
			printPackageInfos(javaProject);
		}
	}
	
	private void printPackageInfos(IJavaProject javaProject)
			throws JavaModelException {
		IPackageFragment[] packages = javaProject.getPackageFragments();
		for ( int i = 0; i < packages.length; i++ ) {
			// Package fragments include all packages in the
			// classpath
			// We will only look at the package from the source
			// folder
			// K_BINARY would include also included JARS, e.g.
			// rt.jar
			if ( packages[ i ].getKind() == IPackageFragmentRoot.K_SOURCE) {
				// appendToFile("包名:  " + mypackage.getElementName() + StringUtil.LN);
				printICompilationUnitInfo( packages[ i ] );
			}

		}
	}
	

	private void printICompilationUnitInfo(IPackageFragment mypackage)
			throws JavaModelException {
		ICompilationUnit[] units = mypackage.getCompilationUnits();
		for ( int i = 0; i < units.length; i++) {
			// System.out.println("Has number of lines: " + doc.getNumberOfLines());
			// Document doc = new Document(unit.getSource());
			// adc_ejb 的以Bean结尾的java文件
			
			if ( units[ i ].getElementName().toLowerCase().endsWith("bean.java") ) {
				path = units[ i ].getPath();
				printIMethods( units[ i ] );
			}
		}
	}

	private void printIMethods(ICompilationUnit unit) throws JavaModelException {
		IType[] allTypes = unit.getAllTypes();
		
		appendToFile( "源文件: " + "\t" + unit.getElementName()  + "\t" + unit.getPath().toOSString() + StringUtil.LN);
		for (int i = 0; i < allTypes.length; i++) {
			IMethod[] methods = allTypes[ i ].getMethods();
			for (int j = 0; j < methods.length; j++) {
				
				if ( rightFlag( methods[ j ] ) ){ 
					printMethodInfo( methods[ j ] , unit);
				}
			}
		}
	}

	private boolean rightFlag( IMethod method ) throws JavaModelException {
		if ( PreferenceConstants.STR_VISITED_CONTROL_PUBLIC_TYPE2.equals( visitedControlStyle )  ) {
			return Flags.isPublic( method.getFlags() ) ;
		}
		else if ( PreferenceConstants.STR_VISITED_CONTROL_PRIVATE_TYPE3.equals( visitedControlStyle )  ) {
			return Flags.isPrivate( method.getFlags() ) ;
		}
		else if ( PreferenceConstants.STR_VISITED_CONTROL_PROTECTED_TYPE4.equals( visitedControlStyle )  ) {
			return Flags.isProtected( method.getFlags() ) ;
		}
		else if ( PreferenceConstants.STR_VISITED_CONTROL_ALL_TYPE5.equals( visitedControlStyle )  ) {
			return true ;
		} 
		else // if ( PreferenceConstants.STR_VISITED_CONTROL_DEFAULT_TYPE1.equals( visitedControlStyle )  ) {
			return 	
					!Flags.isPublic( method.getFlags() ) &&
					!Flags.isPrivate( method.getFlags() ) &&
					!Flags.isProtected( method.getFlags() )
					;
	}
	
	private Object style()  {
		if ( PreferenceConstants.STR_VISITED_CONTROL_PUBLIC_TYPE2.equals( visitedControlStyle )  ) {
			return "public";
		}
		else if ( PreferenceConstants.STR_VISITED_CONTROL_PRIVATE_TYPE3.equals( visitedControlStyle )  ) {
			return "private";
		}
		else if ( PreferenceConstants.STR_VISITED_CONTROL_PROTECTED_TYPE4.equals( visitedControlStyle )  ) {
			return "protected";
		}
		else if ( PreferenceConstants.STR_VISITED_CONTROL_ALL_TYPE5.equals( visitedControlStyle )  ) {
			return "all";
		} 
		else // if ( PreferenceConstants.STR_VISITED_CONTROL_DEFAULT_TYPE1.equals( visitedControlStyle )  ) {
			return "default";
	}
	
	private boolean isRight(String source) {
		return null != source && source.trim().length() > 0 ;
	}
	
	private void printMethodInfo(IMethod method, ICompilationUnit unit ) throws JavaModelException {
		final String source =   method.getSource() ;
		if ( isRight( source ) ) {
			
			/***************** Handler 处理 ******************/
			String defined = "BeanDataHandler";
			int idx1 = source.indexOf( defined );
			// 一. 判断 release
			if ( idx1 >= 0 ) { 
				// 1. had the 'BeanDataHandler' Defined
				String judgeRealse = ".release()";
				StringBuffer buf = new StringBuffer();
				
				// judge the 'BeanDataHandler' is release ?
				int  idx2 = source.indexOf( judgeRealse );
				if ( idx2 > idx1 ) {
					
					// 是否被注释掉 ？
					if ( !StringUtil.isRightReleaseCode( source ) ) {
						StringUtil.appendln( buf, 
								"\t方法名: " + descMethod( method ) +  "\t ********release 被注释********!"
						);
					} else {
						/*appendln( buf, 
								"\t方法名: " + method.getElementName() +  "\t right!"
						);*/
					}
					
					
				} else {
//					System.out.println("Signature " + method.getSignature());
//					System.out.println("Return Type " + method.getReturnType());
					
					StringUtil.appendln( buf, 
							"\t方法名: " + descMethod( method )+  "\t ********release 未释放********!"
					);
				}
				
				if ( buf.length() == 0 ) {
					// 3. warning the had try but not had the finally ..
					String tryKey = "try";
					String hadFinally = "finally";
					int  idx3 = source.indexOf( tryKey );
					int  idx4 = source.indexOf( hadFinally );

					if ( idx3 > 0 && idx4 < 0 ) {
						StringUtil.appendln( buf, 
								"\t方法名: " + descMethod( method ) +  "\t ********try no finally 警告********!"
								);
					} if ( idx4 < 0 ) {
						StringUtil.appendln( buf, 
								"\t方法名: " + descMethod( method ) +  "\t ********no finally 警告********!"
								);
					}
				}
				/***************** Handler 处理 ******************/
				
				appendToFile( buf );
			}
		}
		
	}
	
	
	/**
	 * get the description of the method.
	 * @param method
	 * @return
	 */
	private String descMethod(IMethod method) {
		ISourceRange range = null;
		String comment = null;
		try {
			final ITextFileBufferManager fileBufferManager = FileBuffers
					.getTextFileBufferManager();
			fileBufferManager.connect(path, LocationKind.NORMALIZE, null);
			IDocument document = fileBufferManager.getTextFileBuffer(path,
					LocationKind.NORMALIZE).getDocument();
			range = method.getJavadocRange();
			if (range != null) {
				comment = StringUtil.getComment(method.getElementName(),
						document.get(range.getOffset(), range.getLength()),
						true);
			} else {
				range = method.getSourceRange();
				if (null != range) {
					comment = StringUtil.getComment(method.getElementName(),
							document.get(range.getOffset(), range.getLength()),
							false);
				}
			}
		} catch (JavaModelException e) {
			e.printStackTrace();
		} catch (BadLocationException e) {
			e.printStackTrace();
		} catch (CoreException e) {
			e.printStackTrace();
		}
		if ( null != comment ) {
			return method.getElementName() + "\t" + StringUtil.filterJavaDoc( comment );
 		}
		return method.getElementName() ;
	}

	
	/**
	 * 追加内容到文件中
	 * @param buf
	 */
	private void appendToFile( CharSequence buf ) {
		if ( buf.length() == 0 ) {
			return;
		}
		if ( raf == null ) {// the first time for initial the raf
			try {
				raf = new RandomAccessFile( file, "rw" );
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			try {
				raf.seek( raf.getFilePointer() );
				raf.write( buf.toString().getBytes() );
			} catch (IOException e) {
				e.printStackTrace();
			} 
		}
	}
	/**
	 * @see IActionDelegate#selectionChanged(IAction, ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		this.seleciton = selection;
	}

}
