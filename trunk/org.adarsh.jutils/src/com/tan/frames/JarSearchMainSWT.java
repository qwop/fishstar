package com.tan.frames;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import com.tan.util.JarSearcher;
import com.tan.util.StringUtil;
import org.eclipse.swt.widgets.Group;

public class JarSearchMainSWT {

	protected Shell shlJarCreateBy;
	private Text inptText;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			JarSearchMainSWT window = new JarSearchMainSWT();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlJarCreateBy.open();
		shlJarCreateBy.layout();
		while (!shlJarCreateBy.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
	
	private Properties prop;
	private Combo comboBox;
	private File file;
	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlJarCreateBy = new Shell();
		shlJarCreateBy.addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent arg0) {
				save();
			}

		});
		
		shlJarCreateBy.setSize(643, 620);
		shlJarCreateBy.setText("Jar文件搜寻器 create by tyj");
		shlJarCreateBy.setLayout(new FormLayout());
		
		comboBox = new Combo(shlJarCreateBy, SWT.NONE);
		FormData fd_comboBox = new FormData();
		fd_comboBox.right = new FormAttachment(0, 605);
		fd_comboBox.top = new FormAttachment(0, 25);
		fd_comboBox.left = new FormAttachment(0, 30);
		comboBox.setLayoutData(fd_comboBox);
		comboBox.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				int selectionIndex = comboBox.getSelectionIndex();
				String item = comboBox.getItem( selectionIndex );
				prop.put( "selected", item );
			}
		});
		
		if ( null == prop ) {
			loadProp();
		}
		
		
		comboBox.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				DirectoryDialog dialog = new DirectoryDialog( shlJarCreateBy, SWT.NONE );
				final String value = dialog.open();
				if ( value != null ) {
					add( value ) ;
				}
			}
		});
		
		Composite composite = new Composite(shlJarCreateBy, SWT.NONE);
		FormData fd_composite = new FormData();
		fd_composite.right = new FormAttachment(comboBox, 0, SWT.RIGHT);
		fd_composite.left = new FormAttachment(0, 30);
		composite.setLayoutData(fd_composite);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));
		// qwop
		
		NameMouseAdapter adapter = new NameMouseAdapter();
		for ( final String name : endsName ) {
			Button btnCheckButton = new Button(composite, SWT.CHECK);
			btnCheckButton.setText( name );
			btnCheckButton.addMouseListener( adapter );
//			FileNameCheckBox box = new FileNameCheckBox(name, composite, SWT.CHECK); 
		}
		
		inptText = new Text(shlJarCreateBy, SWT.BORDER);
		FormData fd_inptText = new FormData();
		fd_inptText.left = new FormAttachment(0, 30);
		fd_inptText.right = new FormAttachment(0, 605);
		inptText.setLayoutData(fd_inptText);
		
		Button btnNewButton = new Button(shlJarCreateBy, SWT.NONE);
		FormData fd_btnNewButton = new FormData();
		fd_btnNewButton.top = new FormAttachment(inptText, 6);
		fd_btnNewButton.left = new FormAttachment(comboBox, 0, SWT.LEFT);
		fd_btnNewButton.right = new FormAttachment(0, 605);
		btnNewButton.setLayoutData(fd_btnNewButton);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				final Object item = comboBox.getText();
	    		 if ( null != item ) {
	    			 File dir = new File( String.valueOf( item ) );
	    			 if ( dir.exists() && dir.isDirectory() ) {
	    				 	resultText.setText( "" );
	    					JarSearcher searcher = new JarSearcher( dir );
	    					
	    					searcher.setFileSuffixs( names.toArray( new String[] {} ) );
	    					
	    					if ( nameRadio.getSelection() ) {
	    						String text = inptText.getText() ;
	    						if ( StringUtil.isEmpty( text ) ) {
	    							resultText.setText( "请输入关键字" );
	    							return;
	    						}
	    						String[] spaces = text.split( "\\s+" );
		    					searcher.searchfilename( 
		    							spaces
		    					);
	    					} else if ( contentRadio.getSelection() ) {
	    						boolean searchClass = false;
	    						searcher.setKeyWord( inptText.getText() );
	    						searcher.processContent(searchClass);
	    					}
	    					resultText.setText( searcher.getResult());
	    			 }
	    		 } else {
	    			 resultText.setText( "请选择或者添加文件夹，双击下拉框！" );
	    		 }
			}
		});
		btnNewButton.setText("处理");
		
		ScrolledComposite scrolledComposite = new ScrolledComposite(shlJarCreateBy, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		FormData fd_scrolledComposite = new FormData();
		fd_scrolledComposite.top = new FormAttachment(btnNewButton, 6);
		fd_scrolledComposite.left = new FormAttachment(0, 30);
		fd_scrolledComposite.bottom = new FormAttachment(0, 568);
		fd_scrolledComposite.right = new FormAttachment(0, 605);
		scrolledComposite.setLayoutData(fd_scrolledComposite);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		
		resultText = new StyledText(scrolledComposite, SWT.BORDER | SWT.FULL_SELECTION | SWT.READ_ONLY);
		resultText.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		resultText.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_FOREGROUND));
		scrolledComposite.setContent(resultText);
		scrolledComposite.setMinSize(resultText.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		
		Label lblNewLabel = new Label(shlJarCreateBy, SWT.NONE);
		fd_inptText.bottom = new FormAttachment(lblNewLabel, 27, SWT.BOTTOM);
		fd_inptText.top = new FormAttachment(lblNewLabel, 6);
		fd_composite.bottom = new FormAttachment(100, -405);
		FormData fd_lblNewLabel = new FormData();
		fd_lblNewLabel.top = new FormAttachment(composite, 30);
		fd_lblNewLabel.left = new FormAttachment(comboBox, 0, SWT.LEFT);
		fd_lblNewLabel.bottom = new FormAttachment(0, 223);
		fd_lblNewLabel.right = new FormAttachment(0, 205);
		lblNewLabel.setLayoutData(fd_lblNewLabel);
		lblNewLabel.setText("请输入关键字：");
		
		Group group = new Group(shlJarCreateBy, SWT.NONE);
		fd_composite.top = new FormAttachment(group, 6);
		group.setText("查找类型");
		FormData fd_group = new FormData();
		fd_group.bottom = new FormAttachment(100, -452);
		fd_group.top = new FormAttachment(comboBox, 28);
		fd_group.left = new FormAttachment(0, 30);
		fd_group.right = new FormAttachment(0, 590);
		group.setLayoutData(fd_group);
		
		nameRadio = new Button(group, SWT.RADIO);
		nameRadio.setBounds(106, 15, 120, 19);
		nameRadio.setText("文件名查找");
		nameRadio.setSelection( true );
		
		contentRadio = new Button(group, SWT.RADIO);
		contentRadio.setBounds(332, 15, 120, 19);
		contentRadio.setText("文件内容查找");
	}
	
	
	static String[] endsName =  {
		"properties",
		"classes",
		"txt",
		"xml",
		"ini" 
	};
	 static List<String> names = new ArrayList<String>();
	private StyledText resultText;
	private Button nameRadio;
	private Button contentRadio;

	static class NameMouseAdapter extends MouseAdapter {
		@Override
		public void mouseUp(MouseEvent event) {
			Button comp = (Button) event.getSource();

			if (comp.isEnabled()) {
				names.add(comp.getText());
			} else {
				names.remove(comp.getText());
			}
		}
	}
	

	protected void save() {
		if ( null != file && file.exists()  && file.isFile() ) 
			try {
				prop.store( new FileOutputStream( file ) , "" );
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

	protected void add(String value) {
		if ( prop.contains( value ) ) { return ; }
		int selectionIndex =  comboBox.getItemCount(); //  - 1;
		String sIndex = "" + selectionIndex;
		System.out.println( sIndex );
		prop.put( sIndex, value );
		comboBox.add( value );
		comboBox.select( selectionIndex );
		prop.put( "selected", value );
	}

	protected boolean loadProp() {
		if ( prop == null ) {
			prop = new Properties();
			file = new File("combo.properties");
			try {
				boolean right = false;
				if ( file.isDirectory() ) {
					right = file.delete();
					right = file.createNewFile();
				} else if ( !file.exists() ) {
					file.createNewFile();
				}
				right = file.isFile() && file.exists();
				if ( right ) {
					prop.load( new FileInputStream( file ) );
					if ( !prop.isEmpty() && null != comboBox) {
						Collection<Object> objs = prop.values();
						Object sObj = prop.get( "selected" );
						int i = 0;
						for ( final Object obj : objs ) {
							comboBox.add( String.valueOf( obj ) );
							if ( null != sObj &&  sObj.equals(  obj ) ) {
								comboBox.select( i );
							}
							i++ ;
						}
					}
					return true;
				} else {
					return false;
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
}
