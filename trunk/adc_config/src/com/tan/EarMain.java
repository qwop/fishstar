package com.tan;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Properties;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.JPanel;
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
	private JTextField silog;
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
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle( "adc.ear" );
		frame.setBounds(100, 100, 732, 581);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			SwingUtilities.updateComponentTreeUI( frame );
			
			btnLoadEarFiles = new JButton("Load Properties");
			btnLoadEarFiles.setBounds(0, 0, 710, 25);
			btnLoadEarFiles.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//temp();
					final File root = new File( Main.CUR_DIR );
					
					if ( root.exists() && root.isDirectory() ) {
						final File f1 = new File( root , "adc_ejb_config.properties" );
						final File f2 = new File( root , "ssconfig.properties" );
						Properties p1 = IOUtil.load( f1 );
						Properties p2 = IOUtil.load( f2 );
						
						
						System.out.println( ejbPanel.getComponents().length );;
						System.out.println( ssPanel.getComponents().length );;
						billPath.setText( p1.getProperty( billPath.getText().trim() )); 
						silog.setText( p1.getProperty( silog.getText().trim() )); 
						workflowPath.setText( p2.getProperty( workflowPath.getText().trim() )); 
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
										silog.setText( p.getProperty( silog.getText().trim() )); 
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
			ejbPanel.setBounds(0, 392, 714, 152);
			frame.getContentPane().add(ejbPanel);
			ejbPanel.setLayout(null);
			
			JLabel lblNewLabel_2 = new JLabel("工作流配置目录 workflow.path");
			lblNewLabel_2.setBounds(35, 50, 133, 25);
			ejbPanel.add(lblNewLabel_2);
			lblNewLabel_2.setToolTipText("工作流配置目录 workflow.path");
			
			workflowPath = new JTextField();
			workflowPath.setBounds(178, 47, 520, 31);
			ejbPanel.add(workflowPath);
			workflowPath.setText("workflow.path");
			workflowPath.setColumns(10);
			
			ssPanel = new JPanel();
			ssPanel.setBorder(new TitledBorder(null, "adc_ejb_config.properties", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			ssPanel.setBounds(0, 23, 714, 344);
			frame.getContentPane().add(ssPanel);
			ssPanel.setLayout(null);
			
			JLabel lblNewLabel = new JLabel("ADC SI访问业务日志文件存放路径");
			lblNewLabel.setBounds(10, 52, 164, 42);
			ssPanel.add(lblNewLabel);
			lblNewLabel.setToolTipText("ADC SI访问业务日志文件存放路径");
			
			JLabel lblNewLabel_1 = new JLabel("ADC 话单文件存放路径");
			lblNewLabel_1.setBounds(16, 142, 158, 15);
			ssPanel.add(lblNewLabel_1);
			lblNewLabel_1.setToolTipText("ADC 话单文件存放路径");
			
			lblAdcOpxml = new JLabel("ADC OP应用申请评估项配置XML文件存放路径(带文件名)");
			lblAdcOpxml.setBounds(16, 177, 158, 15);
			ssPanel.add(lblAdcOpxml);
			lblAdcOpxml.setToolTipText("ADC OP应用申请评估项配置XML文件存放路径(带文件名)");
			
			lblAdcOp = new JLabel("ADC OP应用申请流程 权限点－－工作流角色映射 XML文件存放路径(带文件名)");
			lblAdcOp.setBounds(16, 211, 158, 15);
			ssPanel.add(lblAdcOp);
			lblAdcOp.setToolTipText("ADC OP应用申请流程 权限点－－工作流角色映射 XML文件存放路径(带文件名)");
			
			lblAdc = new JLabel("ADC门户版本标识");
			lblAdc.setBounds(16, 249, 158, 15);
			ssPanel.add(lblAdc);
			lblAdc.setToolTipText("ADC门户版本标识");
			
			silog = new JTextField();
			silog.setBounds(184, 47, 520, 31);
			ssPanel.add(silog);
			silog.setText("adc.si.logpath");
			silog.setColumns(10);
			billPath.setBounds(184, 126, 520, 31);
			ssPanel.add(billPath);
			billPath.setText("adc.bill.filepath");
			billPath.setColumns(10);
			
			opgradepath = new JTextField();
			opgradepath.setBounds(184, 161, 520, 31);
			ssPanel.add(opgradepath);
			opgradepath.setText("adc.op.opgradepath");
			opgradepath.setColumns(10);
			
			opworkflowrolepath = new JTextField();
			opworkflowrolepath.setBounds(184, 195, 520, 31);
			ssPanel.add(opworkflowrolepath);
			opworkflowrolepath.setText("adc.op.opworkflowrolepath");
			opworkflowrolepath.setColumns(10);
			
			version = new JTextField();
			version.setBounds(184, 233, 520, 31);
			ssPanel.add(version);
			version.setText("adc.version.info");
			version.setColumns(10);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void resetTxt() {
		billPath.setText("adc.bill.filepath");
		silog.setText("adc.si.logpath");
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
}
