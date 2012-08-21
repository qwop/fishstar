package com.tan.vad;

import java.util.HashMap;
import java.util.Map;

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

public abstract class AbstractGenVAD implements IEditorActionDelegate{
	/**
	 * The preference store associated with the plugin.
	 */
	public static final IPreferenceStore PREF_STORE = JUtilsPlugin
			.getDefault().getPreferenceStore();

	/**
	 * The associated <tt>IEditorPart</tt>.
	 */
	private IEditorPart editorPart;
	/*
	 * style
	 */
	protected Map headMap;
	protected Map middleMap;
	protected Map tailMap;
	protected StringBuffer content;
	
	protected abstract void getDoc();
	// order 1.
	protected abstract void head();
	// order 2.
	protected abstract void middle()  ;
	// order 3.
	protected abstract void tail() ;
	
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
		getDoc();
		
		IWorkingCopyManager manager = JavaUI.getWorkingCopyManager();

		IEditorInput editorInput = this.editorPart.getEditorInput();

		ITextEditor editor = (ITextEditor) this.editorPart;

		ITextSelection selection = (ITextSelection) editor
				.getSelectionProvider().getSelection();

		ICompilationUnit compUnit = manager.getWorkingCopy(editorInput);

		Shell shell = this.editorPart.getSite().getShell();
		
		/** 获取当前配置的的 Setter 样式**/
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
		        
		        content =  new StringBuffer();
		        
		        /**1. 头 **/
		    	headMap = new HashMap();
		    	/** #dto# */
		    	String dto = theType.getElementName();
		    	headMap.put( "#dto#", dto );
		        head();
		        
		        
		        /** variables */
		        String elementName;
				for (int i = 0; i < fields.length; i++) {
					
					elementName= fields[ i ].getElementName();
					
					/** filter value.*/
					if (elementName == null || "serialVersionUID".equals(elementName)){
						continue;
					}
					/** filter value.*/
					
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
					
					
					/** 2. 中间 */
			    	middleMap = new HashMap();
			    	/** #dto# */
			    	middleMap.put( "#dto#", dto );
			    	middleMap.put( "#name#", elementName );
			    	middleMap.put( "#comment#", comment );
			    	
					middle();
					
//					Generate.generateDummyCode(
//							content, 
//							fields[i], /**#name#*/
//							comment, /**#comment#*/
//							"" /**#dto#*/
//							);
				}
				/** 3. 尾部 */
				
				tailMap = new HashMap();
		    	/** #dto# */
				tailMap.put( "#dto#", dto );
				tail();
				
				SourceManipulator.createDummyHtml(theType,
						 content.toString()
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void selectionChanged(IAction action, ISelection selection) {
	}
}
