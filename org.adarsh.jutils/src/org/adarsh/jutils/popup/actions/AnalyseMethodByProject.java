package org.adarsh.jutils.popup.actions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.Flags;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

import com.tan.util.StringUtil;

public class AnalyseMethodByProject implements IObjectActionDelegate {

	private Shell shell;
	
	/**
	 * The selection.
	 */
	private ISelection seleciton;
	
	private File file;
	
	private RandomAccessFile raf;
	/**
	 * Constructor for Action1.
	 */
	public AnalyseMethodByProject() {
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
		
		if ( null != project ) {
			String pn;
			try {
					pn = project.getName();
					file = new File("c:\\output_" + pn + ".log");
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
			
			
			if ( null != file && file.exists() && file.isFile() ) {
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
		for (IPackageFragment mypackage : packages) {
			// Package fragments include all packages in the
			// classpath
			// We will only look at the package from the source
			// folder
			// K_BINARY would include also included JARS, e.g.
			// rt.jar
			if (mypackage.getKind() == IPackageFragmentRoot.K_SOURCE) {
				appendToFile("包名:  " + mypackage.getElementName() + StringUtil.LN);
				printICompilationUnitInfo(mypackage);
			}

		}
	}
	private void printICompilationUnitInfo(IPackageFragment mypackage)
			throws JavaModelException {
		
		
		for (ICompilationUnit unit : mypackage.getCompilationUnits()) {
			// System.out.println("Has number of lines: " + doc.getNumberOfLines());
			// Document doc = new Document(unit.getSource());
			// adc_ejb 的以Bean结尾的java文件
			if ( unit.getElementName().toLowerCase().endsWith("bean.java") ) {
				printIMethods(unit);
			}
		}
	}

	private void printIMethods(ICompilationUnit unit) throws JavaModelException {
		IType[] allTypes = unit.getAllTypes();
		
		appendToFile( "源文件: " + unit.getPath().toOSString() + "\t" + unit.getElementName()  + StringUtil.LN);
		for (IType type : allTypes) {
			IMethod[] methods = type.getMethods();
			for (IMethod method : methods) {
				
				if ( Flags.isPublic(  method.getFlags() ) ){ 
					printMethodInfo(method, unit);
				}
			}
		}
	}
	
	private boolean isRight(String source) {
		return null != source && source.trim().length() > 0 ;
	}
	
	private void printMethodInfo(IMethod method, ICompilationUnit unit ) throws JavaModelException {
		final String source =   method.getSource() ;
		if ( isRight( source ) ) {
			String defined = "BeanDataHandler";
			int idx1 = source.indexOf( defined );
			
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
								"\t方法名: " + method.getElementName() +  "\t ********release comment********!"
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
							"\t方法名: " + method.getElementName() +  "\t ********no release********!"
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
								"\t方法名: " + method.getElementName() +  "\t ********warning had try but not had finally ********!"
								);
					} if ( idx4 < 0 ) {
						StringUtil.appendln( buf, 
								"\t方法名: " + method.getElementName() +  "\t ********warning not had finally ********!"
								);
					}
				}
				
				appendToFile( buf );
			}
		}
		
	}
	

	
	
	private void appendToFile( CharSequence buf ) {
		if ( buf.length() == 0 ) {
			return;
		}
		if ( raf == null ) {
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
