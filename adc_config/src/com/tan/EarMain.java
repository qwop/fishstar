package com.tan;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

/**
 * for the adc.ear file 
 * @author dolphin
 *
 * 2011-11-2 上午9:55:28
 */
public class EarMain {

	private JFrame frame;
	private JButton btnLoadEarFiles ;
	private JTextField siLog;
	private final JTextField billPath = new JTextField();
	private JTextField workflowPath;
	private JLabel lblAdcOpxml;
	private JTextField opgradepath;
	private JLabel lblAdcOp;
	private JTextField opworkflowrolepath;
	private JLabel lblAdc;
	private JTextField version;
	private JPanel ejbPanel ;
	private JPanel ssPanel ;
	
	private File adcEjb, ssConfig;
	private List<String> adcEjbReplaceMents, ssConfigReplaceMents;
	private JTextArea editorPane ;
	private JCheckBox chkPath ;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EarMain window = new EarMain();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public EarMain() {
		initialize();
		adcEjbReplaceMents = new ArrayList<String>();
		ssConfigReplaceMents = new ArrayList<String>();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle( "adc.ear" );
		frame.setBounds(100, 100, 732, 581);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			SwingUtilities.updateComponentTreeUI( frame );
			
			btnLoadEarFiles = new JButton("Load Properties");
			btnLoadEarFiles.setBounds(10, 7, 136, 32);
			btnLoadEarFiles.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//temp();
					
					resetTxt();
					final File root = new File( Main.CUR_DIR );
					
					search( root );
				}

				private void search(File root) {
					if ( root.exists() && root.isDirectory() ) {
						File f1 = new File( root , "adc_ejb_config.properties" );
						File f2 = new File( root , "ssconfig.properties" );
						if ( !f1.exists() ) {
							f1 = new File( root, "adc_ejb" + File.separator + "ejbModule" + File.separator + "adc_ejb_config.properties" );
							
							if ( f1.exists() ) {
								f2 = new File( root, "adc_ejb" + File.separator + "ejbModule" + File.separator + "ssconfig.properties" );
							}
						}
						
						if ( f1.exists() && f2.exists()  && f1.isFile() && f2.isFile() ) { // 文件存在并且是一个文件类型
							adcEjb = f1;
							ssConfig = f2;
							
							EarMain.this.appendToTextPane( "Loading file : " + f1  );
							EarMain.this.appendToTextPane( "Loading file : " + ssConfig  );
							
							Properties p1 = IOUtil.load( f1 );
							Properties p2 = IOUtil.load( f2 );
							
							System.out.println( ejbPanel.getComponents().length );;
							System.out.println( ssPanel.getComponents().length );;
//							billPath.setText( p1.getProperty( billPath.getText().trim() )); 
//							siLog.setText( p1.getProperty( siLog.getText().trim() )); 
//							opgradepath.setText( p1.getProperty( opgradepath.getText().trim() )); 
//							opworkflowrolepath.setText( p1.getProperty( opworkflowrolepath.getText().trim() )); 
//							version.setText( p1.getProperty( version.getText().trim() )); 
//							workflowPath.setText( p2.getProperty( workflowPath.getText().trim() ));
							
							
							loadEjbProp( p1, billPath, siLog, opgradepath, opworkflowrolepath, version );
							loadEjbProp( p2, workflowPath );
						} else {
							 String dir = (String) JOptionPane.showInputDialog(null, f1 + "不存在！", "输入文件的根目录",
		                             JOptionPane.QUESTION_MESSAGE, null, null, root.getAbsolutePath());
							 
							 search( new File( dir ) );
						}
					} else {
						 // not find.
						String dir = (String) JOptionPane.showInputDialog(null, root + "不存在！", "输入文件的根目录",
	                             JOptionPane.QUESTION_MESSAGE, null, null, root.getAbsolutePath());
						 
						search( new File( dir ) );
					}
				}

				private void temp() {
					final File file = new File( Main.CUR_DIR );
					File earFile = null;
					if ( file.isDirectory() && file.exists() ) {
						File[] files = file.listFiles();
						
						for ( final File f : files ) {
							if ( f.getName().toLowerCase().endsWith( ".ear" ) ) {
								earFile = f;
								break;
							}
						}
					}
					
					if ( null != earFile && earFile.exists() && earFile.isFile() ) {
						// find the entry which name is "adc_ejb.jar"
						resetTxt();
						ZipEntry entry;
						InputStream in= null;
						JarInputStream jarS = null;
						try {
							ZipFile ear = new ZipFile( earFile );
							Enumeration es = ear.entries();
							while ( es.hasMoreElements() ) {
								entry = ( ZipEntry ) es.nextElement();
								if ( "adc_ejb.jar".equals( entry.getName().toLowerCase() ) ) {
									in = ear.getInputStream( entry ) ;
									jarS = new JarInputStream( in );
								}
							}
						} catch (ZipException e1) {
							e1.printStackTrace();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						
						
						if ( jarS != null ) {
							JarEntry jarEntry;
							try {
								Properties p ;
								while ( null != ( jarEntry = jarS.getNextJarEntry() ) ) {
									if (  jarEntry.getName().toLowerCase().endsWith( "adc_ejb_config.properties" )  ) {
										p = loadP( jarS , jarEntry.getSize(), jarEntry.getName() );
//										System.out.println( p.getProperty( "adc.bill.filepath" ) );;
										
										billPath.setText( p.getProperty( billPath.getText().trim() )); 
										siLog.setText( p.getProperty( siLog.getText().trim() )); 
									} else if (  jarEntry.getName().toLowerCase().endsWith( "ssconfig.properties" )  ) {
										p = loadP( jarS , jarEntry.getSize(), jarEntry.getName() );
//										System.out.println( p.getProperty( "workflow.path" ) );;
										workflowPath.setText( p.getProperty( workflowPath.getText().trim() )); 
									}
								}
							} catch (IOException e1) {
								e1.printStackTrace();
							} finally {
								if ( jarS != null ) { 
									try {
										jarS.close();
									} catch (IOException e1) {
										e1.printStackTrace();
									}
								}
							}
						}
					}
				}
			});
			frame.getContentPane().setLayout(null);
			frame.getContentPane().add(btnLoadEarFiles);
			
			ejbPanel = new JPanel();
			ejbPanel.setBorder(new TitledBorder(null, "ssconfig.properties", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			ejbPanel.setBounds(0, 265, 714, 60);
			frame.getContentPane().add(ejbPanel);
			ejbPanel.setLayout(null);
			
			JLabel lblNewLabel_2 = new JLabel("工作流配置目录 workflow.path");
			lblNewLabel_2.setBounds(10, 20, 133, 25);
			ejbPanel.add(lblNewLabel_2);
			lblNewLabel_2.setToolTipText("工作流配置目录 workflow.path");
			
			workflowPath = new JTextField();
			workflowPath.setBounds(184, 17, 520, 31);
			ejbPanel.add(workflowPath);
			workflowPath.setText("workflow.path");
			workflowPath.setColumns(10);
			
			ssPanel = new JPanel();
			ssPanel.setBorder(new TitledBorder(null, "adc_ejb_config.properties", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			ssPanel.setBounds(0, 49, 714, 216);
			frame.getContentPane().add(ssPanel);
			ssPanel.setLayout(null);
			
			JLabel lblNewLabel = new JLabel("ADC SI访问业务日志文件存放路径");
			lblNewLabel.setBounds(10, 20, 164, 42);
			ssPanel.add(lblNewLabel);
			lblNewLabel.setToolTipText("ADC SI访问业务日志文件存放路径");
			
			JLabel lblNewLabel_1 = new JLabel("ADC 话单文件存放路径");
			lblNewLabel_1.setBounds(10, 62, 158, 15);
			ssPanel.add(lblNewLabel_1);
			lblNewLabel_1.setToolTipText("ADC 话单文件存放路径");
			
			lblAdcOpxml = new JLabel("ADC OP应用申请评估项配置XML文件存放路径(带文件名)");
			lblAdcOpxml.setBounds(10, 97, 158, 15);
			ssPanel.add(lblAdcOpxml);
			lblAdcOpxml.setToolTipText("ADC OP应用申请评估项配置XML文件存放路径(带文件名)");
			
			lblAdcOp = new JLabel("ADC OP应用申请流程 权限点－－工作流角色映射 XML文件存放路径(带文件名)");
			lblAdcOp.setBounds(10, 131, 158, 15);
			ssPanel.add(lblAdcOp);
			lblAdcOp.setToolTipText("ADC OP应用申请流程 权限点－－工作流角色映射 XML文件存放路径(带文件名)");
			
			lblAdc = new JLabel("ADC门户版本标识");
			lblAdc.setBounds(10, 169, 158, 15);
			ssPanel.add(lblAdc);
			lblAdc.setToolTipText("ADC门户版本标识");
			
			siLog = new JTextField();
			siLog.setBounds(184, 26, 520, 31);
			ssPanel.add(siLog);
			siLog.setText("adc.si.logpath");
			siLog.setColumns(10);
			billPath.setBounds(184, 62, 520, 31);
			ssPanel.add(billPath);
			billPath.setText("adc.bill.filepath");
			billPath.setColumns(10);
			
			opgradepath = new JTextField();
			opgradepath.setBounds(184, 97, 520, 31);
			ssPanel.add(opgradepath);
			opgradepath.setText("adc.op.opgradepath");
			opgradepath.setColumns(10);
			
			opworkflowrolepath = new JTextField();
			opworkflowrolepath.setBounds(184, 131, 520, 31);
			ssPanel.add(opworkflowrolepath);
			opworkflowrolepath.setText("adc.op.opworkflowrolepath");
			opworkflowrolepath.setColumns(10);
			
			version = new JTextField();
			version.setBounds(184, 169, 520, 31);
			ssPanel.add(version);
			version.setText("adc.version.info");
			version.setColumns(10);
			
			JButton saveBtn = new JButton("保存");
			saveBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					if (null != adcEjb && null != ssConfig ) {
						Properties p1 = IOUtil.load( adcEjb );
						Properties p2 = IOUtil.load( ssConfig );
						
						boolean b1 = checkPath( p1 , "adc.si.logpath", siLog );
						boolean b2 = checkPath( p1 , "adc.bill.filepath", billPath);
						boolean b3 = checkPath( p1 , "adc.op.opgradepath", opgradepath);
						boolean b4 = checkPath( p1 , "adc.op.opworkflowrolepath", opworkflowrolepath);
						boolean b5 = checkVersion( p1 , "adc.version.info", version);
						
						if ( b1 || b2 || b3  || b4 || b5 ) {
							try {
//								p1.store(new FileOutputStream( "delete.adc_ejb_config.properties" ) , "modify by tyj!");
								p1.store(new FileOutputStream( adcEjb ) , "modify by tyj!");
							} catch (FileNotFoundException e1) {
								e1.printStackTrace();
							} catch (IOException e1) {
								e1.printStackTrace();
							}
							
							appendToTextPane( "EJB CONFIG 变更！" );
						}
						

						boolean changed = checkPath( p2 , "adc.version.info", workflowPath);
						
						if ( changed ) {
							try {
//								p2.store(new FileOutputStream( "delete.ssconfig.properties" ) , "modify by tyj!");
								p2.store(new FileOutputStream( ssConfig ) , "modify by tyj!");
							} catch (FileNotFoundException e1) {
								e1.printStackTrace();
							} catch (IOException e1) {
								e1.printStackTrace();
							}
							appendToTextPane( "SS CONFIG 变更！" );
						}
						
					}
					
				}

				private boolean checkVersion(Properties p, String key,
						JTextField t) {
					String oldValue = p.getProperty( key );
					String newValue = t.getText();
					if ( !newValue.equals( oldValue ) ) {
						p.setProperty( key, newValue );
						return true;
					}
					return false;
				}

				private boolean checkPath(Properties p, String key,
						JTextField tPath) {
					String oldValue = p.getProperty( key );
					String newValue = tPath.getText();
					if ( !newValue.equals( oldValue ) ) {
						if ( chkPath.isSelected()  ) {
							File f  =  new File( newValue ) ;
							if ( !f.exists() ) {
								System.err.println( f  + "不是一个有效路径" );
								appendToTextPane( f  + " 不是一个有效路径"  );
							} else {
								p.setProperty( key, newValue );
								return true;
							}
						} else {
							p.setProperty( key, newValue );
							return true;
						}
					}
					return false;
				}
			});
			saveBtn.setBounds(174, 7, 136, 32);
			frame.getContentPane().add(saveBtn);
			
			editorPane = new JTextArea();
			editorPane.setFont(new Font("Monospaced", Font.PLAIN, 12));
			editorPane.setForeground(new Color(255, 0, 0));
			editorPane.setBackground(new Color(204, 255, 204));
			editorPane.setBounds(0, 335, 714, 209);
			frame.getContentPane().add(editorPane);
			
			chkPath = new JCheckBox("验证路径");
			chkPath.setBounds(351, 11, 103, 23);
			frame.getContentPane().add(chkPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void loadEjbProp(Properties p, JTextField ...   fields) {
		String key,value;
		for ( final JTextField f : fields ) {
			key = f.getText();
			value = p.getProperty( key );
			f.setText( value );
			adcEjbReplaceMents.add( key );
		}
	}
	protected void loadSSConfigProp(Properties p, JTextField ...   fields) {
		String key,value;
		for ( final JTextField f : fields ) {
			key = f.getText();
			value = p.getProperty( key );
			f.setText( value );
			ssConfigReplaceMents.add( key );
		}
	}
	
	private void resetTxt() {
		siLog.setText("adc.si.logpath");
		billPath.setText("adc.bill.filepath");
		opgradepath.setText("adc.op.opgradepath");
		opworkflowrolepath.setText("adc.op.opworkflowrolepath");
		version.setText("adc.version.info");
		workflowPath.setText("workflow.path");
	}

	protected Properties loadP(JarInputStream jarS, long size, String name) {
		Properties p = new Properties();
		byte[] b = new byte[(int) size];
		int len = -1 ;
		try {
			len = jarS.read(b);
			save( b,  name );
			if ( len > 0  ){
				p.load( new ByteArrayInputStream(b, 0, len) );
				System.out.println( "size: " + size + ", length " + len + ", bytes.length : " + b.length );
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return p;
	}

	private void save(byte[] b, String name) {
		try {
			OutputStream os = new FileOutputStream( name );
			os.write( b, 0, b.length  );
			os.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void appendToTextPane( final Object msg ) {
		editorPane.setText( editorPane.getText() + (editorPane.getText().length() == 0 ? "" : "\r\n") + String.valueOf( msg ) );
	}
}
