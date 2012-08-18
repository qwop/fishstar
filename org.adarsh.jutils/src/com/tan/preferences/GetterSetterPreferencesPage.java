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

package com.tan.preferences;

import org.adarsh.jutils.JUtilsPlugin;
import org.adarsh.jutils.internal.Util;
import org.adarsh.jutils.preferences.PreferenceConstants;
import org.adarsh.jutils.preferences.PreferenceUtils;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.FileFieldEditor;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.RadioGroupFieldEditor;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.tan.util.Editor;
import com.tan.util.StringUtil;

/**
 * The preference page handler for <tt>JUtils -> Getter Setter Style</tt>.
 * 
 * @author Dolphin
 * 
 */
public class GetterSetterPreferencesPage extends FieldEditorPreferencePage
		implements IWorkbenchPreferencePage {

	/**
	 * The lone instance of <tt>JUtilsPlugin</tt>.
	 */
	private final JUtilsPlugin jUtilsPlugin = JUtilsPlugin.getDefault();
	
	
	private String editorPath;
	
	private String explorer;
	
	private String getterSetterStyle = PreferenceConstants.STR_STYLE_BY_CONTENT; // 1,2,3
	
	private String  visitedControlStyle = PreferenceConstants.STR_VISITED_CONTROL_PUBLIC_TYPE2;
	/**
	 * The preference store associated with this plugin.
	 */
	private final IPreferenceStore prefStore = this.jUtilsPlugin
			.getPreferenceStore();


	/**
	 * Contains the text for displaying the Self-Define content in a
	 * <tt>SourceViewer</tt>.
	 */
	private Document formDoc = new Document();
	private Document theadDoc = new Document();
	private Document tbodyDoc = new Document();
	private Document tfootDoc = new Document();
	

	public GetterSetterPreferencesPage() {
		super(GRID);
		super.setPreferenceStore(this.prefStore);
	}

	/**
	 * {@inheritDoc}
	 */
	public void init(IWorkbench workbench) {
		this.editorPath = Editor.getEditplusPath();
		this.explorer = "";
		
		
		
		/** set the self defined.**/
		String form = this.prefStore.getString(PreferenceConstants.SELF_DEFINE_FORM_KEY);
		String thead = this.prefStore.getString(PreferenceConstants.SELF_DEFINE_THEAD_KEY);
		String tbody = this.prefStore.getString(PreferenceConstants.SELF_DEFINE_TBODY_KEY);
		String tfoot = this.prefStore.getString(PreferenceConstants.SELF_DEFINE_TFOOT_KEY);
		
		
		

		// if something is null, fetch the true copy.
		if (Util.isNullString(form)) {
			form = Util.getDefaultSelfDefineForm();
		}
		if (Util.isNullString(thead)) {
			thead = Util.getDefaultSelfDefineTHead();
		}
		if (Util.isNullString(tbody)) {
			tbody = Util.getDefaultSelfDefineTBody();
		}
		if (Util.isNullString(tfoot)) {
			tfoot = Util.getDefaultSelfDefineTFoot();
		}


		this.formDoc.set( form );
		this.theadDoc.set( thead );
		this.tbodyDoc.set( tbody );
		this.tfootDoc.set( tfoot );
		/** set the self defined.**/
		
		
		
		super.setPreferenceStore(this.prefStore);
		super.setDescription(PreferenceConstants.GETTER_SETTER_DESCRIPTION);
	}

	
	/**
	 * {@inheritDoc}
	 */
	protected void createFieldEditors() {
		Composite parent = super.getFieldEditorParent();

		GridData data = new GridData(GridData.FILL_BOTH);

		parent.setLayout(new GridLayout(3, true));
		parent.setLayoutData(data);

		// style
		RadioGroupFieldEditor rgfe = new RadioGroupFieldEditor(
				PreferenceConstants.GETTER_SETTER_STYLE,
				PreferenceConstants.GETTER_SETTER_STYLE_LABEL,
				3,
				new String[][] {
						{ PreferenceConstants.STYLE1,
								PreferenceConstants.STR_STYLE_BY_CONTENT },
						{ PreferenceConstants.STYLE2,
								PreferenceConstants.STR_STYLE_BY_GET },
						{ PreferenceConstants.STYLE3,
								PreferenceConstants.STR_STYLE3 } }, parent,
				true);

		addField(rgfe);
		
		// adc analyse method style
		RadioGroupFieldEditor rgfe1 = new RadioGroupFieldEditor(
				PreferenceConstants.VISITED_CONTROL_STYLE,
				"访问控制类型", //PreferenceConstants.GETTER_SETTER_STYLE_LABEL,
				5,
				new String[][] {
						{ PreferenceConstants.VISITED_CONTROL_PUBLIC_TYPE2, PreferenceConstants.STR_VISITED_CONTROL_PUBLIC_TYPE2 },
						{ PreferenceConstants.VISITED_CONTROL_PRIVATE_TYPE3, PreferenceConstants.STR_VISITED_CONTROL_PRIVATE_TYPE3 },
						{ PreferenceConstants.VISITED_CONTROL_PROTECTED_TYPE4, PreferenceConstants.STR_VISITED_CONTROL_PROTECTED_TYPE4 } ,
						{ PreferenceConstants.VISITED_CONTROL_DEFAULT_TYPE1, PreferenceConstants.STR_VISITED_CONTROL_DEFAULT_TYPE1 },
						{ PreferenceConstants.VISITED_CONTROL_ALL_TYPE5, PreferenceConstants.STR_VISITED_CONTROL_ALL_TYPE5 } ,
						}, 
						parent,
				true);

		addField(rgfe1);
		
		// 编辑器
		FileFieldEditor editorPathEditor = new FileFieldEditor(PreferenceConstants.EDITOR_PATH, 
				"&编辑器:", getFieldEditorParent());
		if (! StringUtil.isEmpty( editorPath ) ) {
			editorPathEditor.setStringValue( StringUtil.trimQuot( editorPath ) );
		}
		addField(editorPathEditor);
		
		// 资源管理器
		FileFieldEditor explorerEditor = new FileFieldEditor(PreferenceConstants.EXPLORER_PATH, 
				"&Explorer:", getFieldEditorParent());
		if (! StringUtil.isEmpty( explorer ) ) {
			explorerEditor.setStringValue( StringUtil.trimQuot( explorer ) );
		}
		addField(explorerEditor);
		
		
		// 配置自定义生成模板
		PreferenceUtils.createViewer(parent, PreferenceConstants.SELF_DEFINE_FORM_LABEL, this.formDoc);
		PreferenceUtils.createViewer(parent, PreferenceConstants.SELF_DEFINE_THEAD_LABEL, this.theadDoc);
		PreferenceUtils.createViewer(parent, PreferenceConstants.SELF_DEFINE_TBODY_LABEL, this.tbodyDoc);
		PreferenceUtils.createViewer(parent, PreferenceConstants.SELF_DEFINE_TFOOT_LABEL, this.tfootDoc);
	}
	
	
	/**
	 * {@inheritDoc}
	 */
	public void propertyChange(PropertyChangeEvent event) {
		super.propertyChange(event);

		// indicates if a different radio button is selected.
		boolean isChanged = !event.getNewValue().equals(event.getOldValue());

		// changed and string selected... populate appropriate values.
		
		if ( isChanged ) {
			if ( PreferenceConstants.STR_STYLE_BY_CONTENT.equals(event.getNewValue()) ) {
				getterSetterStyle = PreferenceConstants.STR_STYLE_BY_CONTENT;
			} else if ( PreferenceConstants.STR_STYLE_BY_GET.equals(event.getNewValue()) ) {
				getterSetterStyle = PreferenceConstants.STR_STYLE_BY_GET;
			} else if ( PreferenceConstants.STR_STYLE3.equals(event.getNewValue()) ) {
				getterSetterStyle = PreferenceConstants.STR_STYLE3;
			}
			
			
			visitedControlStyle = String.valueOf( event.getNewValue() );
		}
		
	}
	
	
	/**
	 * {@inheritDoc}
	 */
	public boolean performOk() {
		this.performApply();

		return super.performOk();
	}

	/**
	 * {@inheritDoc}
	 */
	protected void performApply() {
		this.prefStore.setValue(PreferenceConstants.EXPLORER_PATH,
				explorer);
		
		this.prefStore.setValue(PreferenceConstants.EDITOR_PATH,
				editorPath);
		
		this.prefStore.setValue(PreferenceConstants.GETTER_SETTER_STYLE,
				 getterSetterStyle );
		
		this.prefStore.setValue( PreferenceConstants.VISITED_CONTROL_STYLE ,
				visitedControlStyle );
		
	}

	/**
	 * {@inheritDoc}
	 */
	protected void performDefaults() {
		this.prefStore.setValue( PreferenceConstants.EXPLORER_PATH, "" );
		
		this.prefStore.setValue( PreferenceConstants.EDITOR_PATH, "" );
		
		this.prefStore.setValue( PreferenceConstants.GETTER_SETTER_STYLE, PreferenceConstants.STR_STYLE_BY_CONTENT );
		
		this.prefStore.setValue( PreferenceConstants.VISITED_CONTROL_STYLE , PreferenceConstants.VISITED_CONTROL_ALL_TYPE5 );
		
	}

}
