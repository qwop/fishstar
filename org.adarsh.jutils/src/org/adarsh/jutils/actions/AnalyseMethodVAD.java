/*
 * JUtils ToString Generator for Eclipse
 * 
 * Copyright (C) 2007  Adarsh Ramamurthy
 * 
 * This library is free software; you can redistribute it and/or modify it 
 * under the terms of the GNU Lesser General Public License as published by 
 * the Free Software Foundation; either version 2.1 of the License, or 
 * (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful, but 
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY 
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public 
 * License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License 
 * along with this library; if not, write to the Free Software Foundation, 
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA.
 * 
 * Plugin Home Page: http://eclipse-jutils.sourceforge.net
 */

package org.adarsh.jutils.actions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import org.adarsh.jutils.JUtilsPlugin;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
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
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IEditorActionDelegate;
import org.eclipse.ui.IEditorPart;

import com.tan.util.StringUtil;

/**
 * 分析方法
 * 
 * @author dolphin.
 * 
 */
public class AnalyseMethodVAD implements IEditorActionDelegate {
	/**
	 * The preference store associated with the plugin.
	 */
	private static final IPreferenceStore PREF_STORE = JUtilsPlugin.getDefault().getPreferenceStore();

	private static final Object LN = System.getProperty( "line.separator" );

	/**
	 * The associated <tt>IEditorPart</tt>.
	 */
	private IEditorPart editorPart;

	/**
	 * {@inheritDoc}
	 */
	public void setActiveEditor(IAction action, IEditorPart targetEditor) {
		this.editorPart = targetEditor;
	}

	/**
	 * {@inheritDoc}
	 */
	public void run(IAction action) {// Get the root of the workspace
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IWorkspaceRoot root = workspace.getRoot();
		// Get all projects in the workspace
		IProject[] projects = root.getProjects();
		// Loop over all projects
		String pn;
		
		try {
			System.setOut( new PrintStream( new File( "c:\\output_adc_ejb.log" )  ) );
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		for (IProject project : projects) {
			pn = project.getName();
			
			if ( pn.equals( "adc_ejb") ) {
				System.out.println("项目名称: " + pn);
				try {
					printProjectInfo(project);
				} catch (CoreException e) {
					e.printStackTrace();
				}
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
				System.out.println("包名:  " + mypackage.getElementName());
				printICompilationUnitInfo(mypackage);

			}

		}
	}
	private void printICompilationUnitInfo(IPackageFragment mypackage)
			throws JavaModelException {
		
		
		for (ICompilationUnit unit : mypackage.getCompilationUnits()) {
			// System.out.println("Has number of lines: " + doc.getNumberOfLines());
			// Document doc = new Document(unit.getSource());
			
			if ( unit.getElementName().toLowerCase().endsWith("bean.java") ) {
				printIMethods(unit);
			}
		}
	}

	private void printIMethods(ICompilationUnit unit) throws JavaModelException {
		IType[] allTypes = unit.getAllTypes();
		
		System.out.println( "源文件: " + unit.getPath().toOSString() + "\t" + unit.getElementName() );
		for (IType type : allTypes) {
			IMethod[] methods = type.getMethods();
			for (IMethod method : methods) {
				
				if ( Flags.isPublic(  method.getFlags() ) ){ 
					printMethodInfo(method, unit);
				}
			}
		}
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
						appendln( buf, 
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
					appendln( buf, 
							"\t方法名: " + method.getElementName() +  "\t ********no release********!"
					);
				}
				
				if ( buf.length() == 0 ) {
					// 3. warning the had try but not had the finally ..
					String hadTry = "try";
					String hadFinally = "finally";
					int  idx3 = source.indexOf( hadTry );
					int  idx4 = source.indexOf( hadFinally );

					if ( idx3 > 0 && idx4 < 0 ) {
						appendln( buf, 
								"\t方法名: " + method.getElementName() +  "\t ********warning had try but not had finally ********!"
								);
					} if ( idx4 < 0 ) {
						appendln( buf, 
								"\t方法名: " + method.getElementName() +  "\t ********warning not had finally ********!"
								);
					}
				}
				
				System.out.println( buf );
			}
		}
		
	}
	

	
	
	public static void main(String[] args) {

	}
	
	
	private void appendln( StringBuffer buf, Object ... args ) {
		for ( final Object arg : args ) {
			buf.append( arg ).append( LN );
		}
	}
	private boolean isRight(String source) {
		return null != source && source.trim().length() > 0 ;
	}

	/**
	 * Reads a ICompilationUnit and creates the AST DOM for manipulating the
	 * Java source file
	 * 
	 * @param unit
	 * @return
	 */

	private static CompilationUnit parse(ICompilationUnit unit) {
		ASTParser parser = ASTParser.newParser(AST.JLS3);
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		parser.setSource(unit);
		parser.setResolveBindings(true);
		return (CompilationUnit) parser.createAST(null); // parse
	}

	/**
	 * {@inheritDoc}
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		// selection has changed. But, do nothing.
	}
}
