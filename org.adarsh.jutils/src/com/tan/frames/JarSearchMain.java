package com.tan.frames;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import com.tan.util.JarSearcher;
import com.tan.util.StringUtil;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JScrollPane;


@SuppressWarnings("serial")
public class JarSearchMain extends JFrame {
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JarSearchMain frame = new JarSearchMain();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	DefaultComboBoxModel aModel = new DefaultComboBoxModel();
	/**
	 * Create the frame.
	 */
	public JarSearchMain() {
		setTitle("Jar文件搜寻器 create by tyj");
		try {
			UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName() );
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		} catch (UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}
		SwingUtilities.updateComponentTreeUI( this 	);
		
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 681, 706);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		final JComboBox comboBox = new JComboBox();
		comboBox.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if ( e.getClickCount() == 2 ) {
					JFileChooser chooser = new JFileChooser();
					chooser.setCurrentDirectory(new java.io.File("."));
					chooser.setDialogTitle("choosertitle");
					chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					chooser.setAcceptAllFileFilterUsed(false);
					
					if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
						myadd( chooser.getSelectedFile() );
						comboBox.setModel(aModel);
					} else {
						System.out.println("No Selection ");
					}
				}
			}

			private void myadd(File selectedFile) {
				int size = aModel.getSize();
				if ( size == 0 ) {
					aModel.addElement( selectedFile );
				} else {
					for ( int i = 0; i < size; i++ ) {
						Object file = aModel.getElementAt( i ) ;
						if ( file instanceof File ) {
							File aFile = ( File ) file;
							if ( aFile.equals( selectedFile ) ) {
								return;
							}
						}
					}// end for.
					aModel.addElement( selectedFile );
				}
			}
		});
		
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {}
			
			
			
		});
		comboBox.setBounds(69, 13, 534, 26);
		contentPane.add(comboBox);
		
		final JRadioButton nameRadio = new JRadioButton("文件名查找");
		nameRadio.setBounds(173, 47, 153, 27);
		nameRadio.setSelected( true );
		contentPane.add(nameRadio);
		
		final JRadioButton contentRadio = new JRadioButton("文件内容查找");
		contentRadio.setBounds(335, 48, 153, 27);
		contentPane.add(contentRadio);
		
		ButtonGroup group = new ButtonGroup();
	    group.add(nameRadio);
	    group.add(contentRadio);
	    
	    JPanel panel = new JPanel();
	    panel.setBounds(69, 71, 534, 90);
	    contentPane.add(panel);
	    panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
	    
/*	    JCheckBox chckbxNewCheckBox = new JCheckBox("New check box");
	    chckbxNewCheckBox.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		System.out.println("FUCK");
	    	}
	    });
	    panel.add(chckbxNewCheckBox);*/
	    
	    loadMyBoxs(panel);
	    
	    
	    textField = new JTextField();
	    textField.setBounds(69, 218, 534, 49);
	    contentPane.add(textField);
	    textField.setColumns(10);
	    
	    JLabel label = new JLabel("请输入关键字：");
	    label.setBounds(70, 187, 266, 18);
	    contentPane.add(label);
	    
	    JScrollPane scrollPane = new JScrollPane();
	    scrollPane.setBounds(14, 347, 645, 317);
	    contentPane.add(scrollPane);
	    
	    final JTextPane textPane = new JTextPane();
	    scrollPane.setViewportView(textPane);
	    textPane.setForeground(Color.BLUE);
	    textPane.setBackground(Color.LIGHT_GRAY);
	    textPane.setFont(new Font("宋体", Font.PLAIN, 12));
	    
	    
	    JButton btnNewButton = new JButton("处理");
	    btnNewButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		 final Object item = comboBox.getSelectedItem() ;
	    		 if ( null != item ) {
	    			 File dir = new File( String.valueOf( item ) );
	    			 if ( dir.exists() && dir.isDirectory() ) {
	    				    textPane.setText( "" );
	    					JarSearcher searcher = new JarSearcher( dir );
	    					
	    					searcher.setFileSuffixs( names.toArray( new String[] {} ) );
	    					
	    					if ( nameRadio.isSelected() ) {
	    						String text = textField.getText() ;
	    						if ( StringUtil.isEmpty( text ) ) {
	    							textPane.setText( "请输入关键字" );
	    							return;
	    						}
	    						String[] spaces = text.split( "\\s+" );
		    					searcher.searchfilename( 
		    							spaces
		    					);
	    					} else if ( contentRadio.isSelected() ) {
	    						boolean searchClass = false;
	    						searcher.setKeyWord( textField.getText() );
	    						searcher.process(searchClass);
	    					}
	    					textPane.setText( searcher.getResult());
	    			 }
	    		 } else {
	    			 textPane.setText( "请选择或者添加文件夹，双击下拉框！" );
	    		 }
	    	}
	    });
	    btnNewButton.setBounds(130, 280, 386, 54);
	    contentPane.add(btnNewButton);
	}

	private void loadMyBoxs(JPanel panel) {
		for ( String str : endsName ) {
	    	FileNameCheckBox chckbxNewCheckBox_1 = new FileNameCheckBox( str );
	    	panel.add(chckbxNewCheckBox_1);
	    }
	}
	
	static String[] endsName =  {
		"properties",
		"classes",
		"txt",
		"xml",
		"ini" 
	};
	
	static List<String> names = new ArrayList<String>();
	private JTextField textField;
	
	static class FileNameCheckBox extends JCheckBox {
		private static final long serialVersionUID = -7154674746536282465L;
		public FileNameCheckBox(String str) {
			super( str );
			
			addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent e) {
		    		if ( FileNameCheckBox.this.isSelected() ) {
		    			names.add(FileNameCheckBox.this.getText());
		    		} else {
		    			names.remove( FileNameCheckBox.this.getText() );
		    		}
		    	}
		    });
		}
	}
}
