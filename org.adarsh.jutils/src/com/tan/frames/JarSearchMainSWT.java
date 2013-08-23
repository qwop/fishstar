package com.tan.frames;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Properties;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import com.tan.util.JarSearcher;
import com.tan.util.StringUtil;

public class JarSearchMainSWT {
	public static int FRAME_WIDTH = 681 , FRAME_HEIGHT = 706;
	protected Shell shlJarCreateBy;
	private Text inputText;

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
	private List<Button> CheckButtons;
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
		
		shlJarCreateBy.setSize( FRAME_WIDTH, FRAME_HEIGHT );
		shlJarCreateBy.setText("Jar文件搜寻器 create by tyj");
		shlJarCreateBy.setLayout(new FormLayout());
		
		comboBox = new Combo(shlJarCreateBy, SWT.NONE);
		FormData fd_comboBox = new FormData();
		fd_comboBox.left = new FormAttachment(0, 30);
		fd_comboBox.right = new FormAttachment(100, -30);
		fd_comboBox.top = new FormAttachment(0, 10);
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
		
		CheckButtons = new ArrayList<Button>();
		for ( final String name : EXT_NAMES ) {
			Button btnCheckButton = new Button(composite, SWT.CHECK);
			btnCheckButton.setText( name );
			btnCheckButton.setSelection( true );
			btnCheckButton.addMouseListener( adapter );
			CheckButtons.add( btnCheckButton );
//			FileNameCheckBox box = new FileNameCheckBox(name, composite, SWT.CHECK); 
		}
		
		inputText = new Text(shlJarCreateBy, SWT.BORDER);
		FormData fd_inptText = new FormData();
		fd_inptText.right = new FormAttachment(comboBox, 0, SWT.RIGHT);
		fd_inptText.left = new FormAttachment(comboBox, 0, SWT.LEFT);
//		fd_inptText.bottom = new FormAttachment(100, -378);
//		fd_inptText.left = new FormAttachment(0, 30);
//		fd_inptText.right = new FormAttachment(0, 605);
		inputText.setLayoutData(fd_inptText);
		
		Button btnNewButton = new Button(shlJarCreateBy, SWT.NONE);
		FormData fd_btnNewButton = new FormData();
		fd_btnNewButton.left = new FormAttachment(0, 30);
		fd_btnNewButton.right = new FormAttachment(100, -30);
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
	    					
	    					String iText = inputText.getText() ;
	    					if ( StringUtil.isEmpty( iText ) ) {
	    						resultText.setText( "请输入关键字" );
	    						inputText.forceFocus();
	    						return;
	    					}
	    					String[] keywords = iText.split( "\\s+" );
	    					
	    					
	    					if ( nameRadio.getSelection() ) {
		    					searcher.searchfilename( 
		    							keywords
		    					);
	    					} else if ( contentRadio.getSelection() ) {
	    						boolean searchClass = false;
//	    						searcher.setKeyWord( keywords );
	    						searcher.setKeyWords(
	    								keywords
	    						);
	    						searcher.processContent(searchClass);
	    					}
	    					
	    					resultText.setText( searcher.getResult());
	    					
	    					
	    					///////////// set style rang  start.
	    					String text = resultText.getText();
							if (text.length() > 0) {
								StyleRange[] ranges = createStyleRanges( keywords, text );
								if ( null != ranges && ranges.length > 0  ) 
									resultText.setStyleRanges(ranges);
							}
							///////////// set style rang  end.
	    			 }
	    		 } else {
	    			 resultText.setText( "请选择或者添加文件夹，双击下拉框！" );
	    		 }
			}
		});
		btnNewButton.setText("处理");
		
		ScrolledComposite scrolledComposite = new ScrolledComposite(shlJarCreateBy, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		fd_btnNewButton.bottom = new FormAttachment(100, -383);
		FormData fd_scrolledComposite = new FormData();
		fd_scrolledComposite.top = new FormAttachment(btnNewButton);
		fd_scrolledComposite.bottom = new FormAttachment(100);
		fd_scrolledComposite.left = new FormAttachment(0, 30);
		fd_scrolledComposite.right = new FormAttachment(100, -30);
		scrolledComposite.setLayoutData(fd_scrolledComposite);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		
		resultText = new StyledText(scrolledComposite, SWT.BORDER | SWT.FULL_SELECTION | SWT.READ_ONLY);
		resultText.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		resultText.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_FOREGROUND));
		scrolledComposite.setContent(resultText);
		scrolledComposite.setMinSize(resultText.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		
		Label lblNewLabel = new Label(shlJarCreateBy, SWT.NONE);
		fd_inptText.top = new FormAttachment(lblNewLabel, 6);
		fd_composite.bottom = new FormAttachment(100, -495);
		FormData fd_lblNewLabel = new FormData();
		fd_lblNewLabel.top = new FormAttachment(composite, 30);
		fd_lblNewLabel.left = new FormAttachment(comboBox, 0, SWT.LEFT);
		fd_lblNewLabel.bottom = new FormAttachment(100, -442);
		fd_lblNewLabel.right = new FormAttachment(100, -214);
		lblNewLabel.setLayoutData(fd_lblNewLabel);
		lblNewLabel.setText("请输入关键字(例 button canvas form grid)：");
		
		Group group = new Group(shlJarCreateBy, SWT.NONE);
		fd_composite.top = new FormAttachment(group, 6);
		group.setText("查找类型");
		FormData fd_group = new FormData();
		fd_group.top = new FormAttachment(comboBox, 6);
		fd_group.left = new FormAttachment(comboBox, 0, SWT.LEFT);
		fd_group.bottom = new FormAttachment(100, -588);
		fd_group.right = new FormAttachment(100, -30);
		group.setLayoutData(fd_group);
		
		nameRadio = new Button(group, SWT.RADIO);
		nameRadio.setBounds(106, 15, 120, 19);
		nameRadio.setText("文件名查找");
		nameRadio.setSelection( true );
		
		contentRadio = new Button(group, SWT.RADIO);
		contentRadio.setBounds(332, 15, 120, 19);
		contentRadio.setText("文件内容查找");
		
		final Button allSelBtn = new Button(shlJarCreateBy, SWT.CHECK);
		allSelBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				for ( final Button btn : CheckButtons ) {
					btn.setSelection( allSelBtn.getSelection() );
				}
			}
		});
		FormData fd_btnCheckButton_1 = new FormData();
		fd_btnCheckButton_1.top = new FormAttachment(composite, 5);
		fd_btnCheckButton_1.left = new FormAttachment(0, 309);
		allSelBtn.setLayoutData(fd_btnCheckButton_1);
		allSelBtn.setText("全选");
		allSelBtn.setSelection( true );
	}
	
	
	protected StyleRange[] createStyleRanges(String[] keywords, String text) {
		if ( null != keywords && keywords.length > 0 && !StringUtil.isEmpty( text ) ) {
			text = text.toLowerCase();
			Color red = Display.getDefault().getSystemColor(SWT.COLOR_RED);
			List<StyleRange> list = new ArrayList<StyleRange>();
			for ( int i = 0; i < keywords.length; i++ ) {
				keywords[i] = keywords[i].toLowerCase();
				int len = text.length();
				int fromIndex = 0;
				while ( fromIndex >= 0 && fromIndex < len - 1 ) {
					int idx = text.indexOf( keywords[i], fromIndex );
					if ( idx < 0 ) {
						break;
					}
					StyleRange sr = new StyleRange( idx,  keywords[i].length(), red, null);
//					System.out.println( idx  + "\t" +  keywords[i].length() );
					sr.fontStyle=sr.fontStyle|SWT.BOLD;
					list.add( sr );
					fromIndex = idx + keywords[i].length() ;
				}
			}
			
			Collections.sort(list, new Comparator<StyleRange> () {
				@Override
				public int compare(StyleRange r1, StyleRange r2) {
					return r1.start - r2.start;
				}
			});
			
			///////////// test error invalid arguments start.
//			list = new ArrayList<StyleRange>();
//			// 1234
//			// 1 - 4
//			// 2 - 2
//			list.add( new StyleRange( 10,  2, red, null) );
//			list.add( new StyleRange( 5,  2, red, null) );
			///////////// test error invalid arguments start.
			
			StyleRange[] array = list.toArray( new StyleRange[0] );
			return array;
		}
		return null;
	}
	
	

	static String[] EXT_NAMES =  {
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
