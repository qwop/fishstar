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
import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

/**
 * The preference page handler for <tt>JUtils -> Getter Setter Style</tt>.
 * 
 * @author Dolphin
 * 
 */
public class GenPreferencesPage extends FieldEditorPreferencePage
		implements IWorkbenchPreferencePage {

	/**
	 * The lone instance of <tt>JUtilsPlugin</tt>.
	 */
	private final JUtilsPlugin jUtilsPlugin = JUtilsPlugin.getDefault();
	
	
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
	

	public GenPreferencesPage() {
		super(GRID);
		super.setPreferenceStore(this.prefStore);
	}

	/**
	 * {@inheritDoc}
	 */
	public void init(IWorkbench workbench) {
		
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
		super.setDescription(PreferenceConstants.SELF_DEFINE_GEN_DESC);
	}

	public static void main(String[] args) throws Exception {
		System.out.println(	Util.getDefaultSelfDefineForm() );
		System.out.println(	Util.getDefaultSelfDefineTHead() );
		System.out.println(	Util.getDefaultSelfDefineTBody() );
		System.out.println(	Util.getDefaultSelfDefineTFoot() );
	}
	/**
	 * {@inheritDoc}
	 */
	protected void createFieldEditors() {
		Composite parent = super.getFieldEditorParent();

		GridData data = new GridData(GridData.FILL_BOTH);
		parent.setLayout(new GridLayout( 3, true));
		parent.setLayoutData(data);
		
		
		BooleanFieldEditor autoSaveCheckbox = new BooleanFieldEditor(
				PreferenceConstants.COPYCON_AUTOSAVE,
				PreferenceConstants.COPYCON_AUTOSAVE_LABEL, parent);
		super.addField(autoSaveCheckbox);
		
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
//			if ( PreferenceConstants.STR_STYLE_BY_CONTENT.equals(event.getNewValue()) ) {
//				getterSetterStyle = PreferenceConstants.STR_STYLE_BY_CONTENT;
//			} else if ( PreferenceConstants.STR_STYLE_BY_GET.equals(event.getNewValue()) ) {
//				getterSetterStyle = PreferenceConstants.STR_STYLE_BY_GET;
//			} else if ( PreferenceConstants.STR_STYLE3.equals(event.getNewValue()) ) {
//				getterSetterStyle = PreferenceConstants.STR_STYLE3;
//			}
//			
//			
//			visitedControlStyle = String.valueOf( event.getNewValue() );
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
		this.prefStore.setValue(PreferenceConstants.SELF_DEFINE_FORM_KEY, this.formDoc.get());
		this.prefStore.setValue(PreferenceConstants.SELF_DEFINE_THEAD_KEY, this.theadDoc.get());
		this.prefStore.setValue(PreferenceConstants.SELF_DEFINE_TBODY_KEY, this.tbodyDoc.get());
		this.prefStore.setValue(PreferenceConstants.SELF_DEFINE_TFOOT_KEY, this.tfootDoc.get());
	}

	/**
	 * {@inheritDoc}
	 */
	protected void performDefaults() {
		
		String form = Util.getDefaultSelfDefineForm();
		String thead = Util.getDefaultSelfDefineTHead();
		String tbody = Util.getDefaultSelfDefineTBody();
		String tfoot = Util.getDefaultSelfDefineTFoot();

		this.formDoc.set( form );
		this.theadDoc.set( thead );
		this.tbodyDoc.set( tbody );
		this.tfootDoc.set( tfoot );

		
		this.prefStore.setValue(PreferenceConstants.SELF_DEFINE_FORM_KEY, form);
		this.prefStore.setValue(PreferenceConstants.SELF_DEFINE_THEAD_KEY, thead);
		this.prefStore.setValue(PreferenceConstants.SELF_DEFINE_TBODY_KEY, tbody);
		this.prefStore.setValue(PreferenceConstants.SELF_DEFINE_TFOOT_KEY, tfoot);
		
	}

}
