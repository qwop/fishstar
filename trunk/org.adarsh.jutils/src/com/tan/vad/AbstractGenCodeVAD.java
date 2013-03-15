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

package com.tan.vad;

import org.adarsh.jutils.JUtilsException;
import org.adarsh.jutils.JUtilsPlugin;
import org.adarsh.jutils.Messages;
import org.adarsh.jutils.internal.Logger;
import org.adarsh.jutils.internal.SourceManipulator;
import org.adarsh.jutils.preferences.PreferenceConstants;
import org.eclipse.core.filebuffers.FileBuffers;
import org.eclipse.core.filebuffers.ITextFileBufferManager;
import org.eclipse.core.filebuffers.LocationKind;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IField;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.ISourceRange;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.ui.IWorkingCopyManager;
import org.eclipse.jdt.ui.JavaUI;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorActionDelegate;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.texteditor.ITextEditor;

import com.tan.util.StringUtil;

/**
 * The Viewer Action Delegate for toString.
 * 
 * @author Dolphin
 * 
 * @version 1.0, 2005
 * 
 * @version 2.0, 14th April 2006
 * 
 * @version 3.0, 10th December 2006
 */
public abstract class AbstractGenCodeVAD implements IEditorActionDelegate  {
	/**
	 * The preference store associated with the plugin.
	 */
	private static final IPreferenceStore PREF_STORE = JUtilsPlugin
			.getDefault().getPreferenceStore();

	/**
	 * The associated <tt>IEditorPart</tt>.
	 */
	private IEditorPart editorPart;
	
	/**
	 * The generate code .
	 */
	protected StringBuffer code ;

	/**
	 * {@inheritDoc}
	 */
	public void setActiveEditor(IAction action, IEditorPart targetEditor) {
		this.editorPart = targetEditor;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void run(IAction action) {
		code =  new StringBuffer();
		
		IWorkingCopyManager manager = JavaUI.getWorkingCopyManager();

		IEditorInput editorInput = this.editorPart.getEditorInput();

		ITextEditor editor = (ITextEditor) this.editorPart;

		ITextSelection selection = (ITextSelection) editor
				.getSelectionProvider().getSelection();

		ICompilationUnit compUnit = manager.getWorkingCopy(editorInput);

		Shell shell = this.editorPart.getSite().getShell();
		
		try {
			IJavaElement suspect = compUnit.getElementAt(selection.getOffset());

			if (suspect == null) {
				MessageDialog.openInformation(shell,
						Messages.getString("tostring.failure.title"),
						Messages.getString("tostring.failure.message"));

				return;
			}

			IType theType = null;

			if (suspect.getElementType() == IJavaElement.TYPE) {
				theType = (IType) suspect;
			} else {
				IJavaElement ancestor = suspect.getAncestor(IJavaElement.TYPE);

				if (ancestor != null) {
					theType = (IType) ancestor;
				}
			}

			if (theType != null && theType.isClass()) {
				IField[] fields = theType.getFields();
		        final ITextFileBufferManager fileBufferManager = FileBuffers.getTextFileBufferManager();
		        final IPath path = compUnit.getPath();
		        try {
					fileBufferManager.connect(path, LocationKind.NORMALIZE, null);
				} catch (CoreException e) {
					e.printStackTrace();
				}
		        IDocument document = fileBufferManager.getTextFileBuffer(path, LocationKind.NORMALIZE).getDocument();
		        ISourceRange range = null;
		        String comment = "";
		        String javaName = theType.getElementName();
		        
		        
		        // TODO 1. change generate by java name 
//		        Generate.generateDummyObjects(generateCode, javaName);
		        define( javaName );
		        
				for (int i = 0; i < fields.length; i++) {
					range = fields[i].getJavadocRange();
					if (range != null) {
						 comment = StringUtil.getComment(
								 fields[i].getElementName(),
								document.get(range.getOffset(),range.getLength()),
								true
								);
					} else {
						range = fields[i].getSourceRange();
						if (null != range) {
							comment = StringUtil.getComment(
									 fields[i].getElementName(),
									document.get(range.getOffset(),range.getLength()),
									false
									);
						}
					}
				 
					// TODO 2.	change -> generate by element, comment.
/*					Generate.generateDummyGetter(
						 generateCode, 
						 fields[i].getElementName(), 
						 comment, 
						 style);*/
					variable( fields[i], comment );
				}
				
				
				// TODO 3.	change -> create method.
				wrap();
				
				SourceManipulator.createMethod(theType,
						code.toString()
				);
				
				if (PREF_STORE.getBoolean(PreferenceConstants.TOSTRING_AUTOSAVE)) {
					compUnit.commitWorkingCopy(false, new NullProgressMonitor());
				}
			} else {
				MessageDialog.openInformation(shell,
						Messages.getString("tostring.failure.title"),
						Messages.getString("tostring.failure.message"));
			}
		} catch (JavaModelException e) {
			MessageDialog.openError(shell,
					Messages.getString("exception.title"),
					Messages.getString("exception.message"));

			Logger.error("Error generating toString through VAD", e);
		} catch (JUtilsException e) {
			MessageDialog.openError(shell,
					Messages.getString("exception.title"),
					Messages.getString("exception.message"));

			Logger.error("Fatal error while generating toString", e);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}
	
/**
	 生成如下代码
	// 3 wrap method.
 	public static void main( String[] args ) {
 	 	// 1. define
		Vo dest = new Vo();
		Vo src = new Vo();
		
		// 2. variables
		// 获取姓名.
		dest.getName(  );
	}
*/
	public abstract void define( final String javaName );
	
	public abstract void variable( final IField field, final String comment );
	
	public abstract void wrap();
	
	/**
	 * {@inheritDoc}
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		// selection has changed. But, do nothing.
	}


}
