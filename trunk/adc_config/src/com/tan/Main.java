package com.tan;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import reg.impl.AdcRegImpl;

import com.tan.swing.FileButton;

/**
 * 
 * @author dolphin
 *
 * 2011-11-11 上午9:39:09
 */
public class Main extends JFrame {
	private static final long serialVersionUID = 1L;
	public  static final String CUR_DIR = System.getProperty("user.dir");
	private static String ADC_WEB_CONFIG = "adc_web_config.properties";
	private static String WEBINF_ADC_WEB_CONFIG = "WEB-INF" + File.separator + "classes" + File.separator + ADC_WEB_CONFIG;
	private static String WEBXML = "web.xml";
	private static String OWS_INIT_PROPERTIES = "init.properties";
	private static String WEBINF_WEBXML = "WEB-INF" + File.separator  + WEBXML;
	private static String WEBINF_CLASSES_OWS_INIT_PROPERTIES = "WEB-INF" + File.separator + "classes" + File.separator +OWS_INIT_PROPERTIES;
	private static String SQL_MAP_CONFIG =  "SqlMapConfig.xml";
	private static String SQL_MAP_CONFIG_ADC =  "SqlMapConfig_adc.xml";
	private static String WEBINF_SQL_MAP_CONFIG = "WEB-INF" + File.separator + "classes" + File.separator  + "res" + File.separator + SQL_MAP_CONFIG;
	private static String WEBINF_SQL_MAP_CONFIG_ADC = "WEB-INF" + File.separator + "classes" + File.separator  + "res" + File.separator + SQL_MAP_CONFIG_ADC;
	
	private transient JPanel panel;
	private transient JPanel btnPanel;
	private transient JButton btnNewButton_1;
	private transient Toolkit 		kit 						= Toolkit.getDefaultToolkit();
	private transient JButton btn1;
	private transient List<File> pfs;
	private transient String oldPath = "E:\\adc\\LN_Version2\\build1027";
	private transient int x = 300,y = 100, width = 800, height = 600;
	
	private transient JLabel l1,l2,l3,l4,l5,l6,l7,l8;
	private transient JTextField ftpIp,ftpPort,ftpUser,ftpPass,ftpPath,frontIp,frontPort,t8;
	
	private transient boolean found;
	private transient JCheckBox replaceMode ;
	private transient JTextArea textPane;
	private JLabel lblip;
	private JTextField dbIP;
	private JLabel lblSid;
	private JTextField sid;
	private JLabel label_2;
	private JTextField dbPort;
	private JLabel lblDb;
	private JTextField reportDbUser;
	private JTextField reportDbPass;
	private JLabel lblDb_1;
	private JLabel label;
	private JLabel label_1;
	private JTextField dbUser;
	private JTextField dbPass;
	private JButton btnOws;
	private JButton button_1;
	
	private Replacer owsReplacerWebXml, owsReplaceProp,sqlMapReaplcer,sqlMapReportReaplcer;
	private JButton btnEjb;
	
	private AdcRegImpl adcReg;
	
	public Main() {
		oldPath = CUR_DIR;
		adcReg = new AdcRegImpl();
		
		adcReg.setDebug( true );
		init();
	}
	
	private void init() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			SwingUtilities.updateComponentTreeUI(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		setTitle("adc一键配置");
		components();
		
		setBounds( x, y , width , height);
		
		setVisible( true );
		setDefaultCloseOperation( EXIT_ON_CLOSE );
		addWindowListener( new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				exit();
				super.windowClosing(e);
			}
		});
		addListeners();
		
		resetText();
	}
	
	private void exit() {
		adcReg.updateValue( "ftpIp", ftpIp.getText() );
		
		adcReg.updateValue( "frontIp", frontIp.getText() );
		
		adcReg.updateValue( "ftpPort", ftpPort.getText() );
		
		adcReg.updateValue( "ftpUser", ftpUser.getText() );
		
		adcReg.updateValue( "ftpPass", ftpPass.getText() );
		
		adcReg.updateValue( "ftpPath", ftpPath.getText() );
		
		adcReg.updateValue( "frontPort", frontPort.getText() );
		
		adcReg.updateValue( "dbIP", dbIP.getText() );
		
		adcReg.updateValue( "sid", sid.getText() );
		
		adcReg.updateValue( "dbPort", dbPort.getText() );
		
		adcReg.updateValue( "reportDbUser", reportDbUser.getText() );
		
		adcReg.updateValue( "reportDbPass", reportDbPass.getText() );
		
		adcReg.updateValue( "dbUser", dbUser.getText() );
		
		adcReg.updateValue( "dbPass", dbPass.getText() );			
		
		adcReg.dispose();
		
		System.exit( 1 );
	}
	private void resetText() {
		String value = adcReg.get("ftpIp");
		ftpIp.setText(StringUtil.isEmpty(value) ? "10.16.19.219" : value);
		value = adcReg.get("frontIp");
		frontIp.setText(StringUtil.isEmpty(value) ? "192.168.1.34" : value);
		value = adcReg.get("ftpPort");
		ftpPort.setText(StringUtil.isEmpty(value) ? "21" : value);
		value = adcReg.get("ftpUser");
		ftpUser.setText(StringUtil.isEmpty(value) ? "admin" : value);
		value = adcReg.get("ftpPass");
		ftpPass.setText(StringUtil.isEmpty(value) ? "admin" : value);
		value = adcReg.get("ftpPath");
		ftpPath.setText(StringUtil.isEmpty(value) ? "adc/file" : value);
		value = adcReg.get("frontPort");
		frontPort.setText(StringUtil.isEmpty(value) ? "7001" : value);
		value = adcReg.get("dbIP");
		dbIP.setText(StringUtil.isEmpty(value) ? "10.16.69.103" : value);
		value = adcReg.get("sid");
		sid.setText(StringUtil.isEmpty(value) ? "orcl" : value);
		value = adcReg.get("dbPort");
		dbPort.setText(StringUtil.isEmpty(value) ? "1521" : value);
		value = adcReg.get("reportDbUser");
		reportDbUser.setText(StringUtil.isEmpty(value) ? "lnyd_adc2" : value);
		value = adcReg.get("reportDbPass");
		reportDbPass.setText(StringUtil.isEmpty(value) ? "lnyd_adc2" : value);
		value = adcReg.get("dbUser");
		dbUser.setText(StringUtil.isEmpty(value) ? "lnyd_adc" : value);
		value = adcReg.get("dbPass");
		dbPass.setText(StringUtil.isEmpty(value) ? "lnyd_adc" : value);
	}

	private void components() {
		getContentPane().setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(10, 10, 772, 344);
		panel.setBorder(new TitledBorder(null, "配置区", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(panel);
		panel.setLayout(null);
		l1 = new JLabel("FTP服务器的IP地址");
		l1.setHorizontalAlignment(SwingConstants.LEFT);
		l1.setBounds(69, 23, 111, 15);
		panel.add(l1);
		ftpIp = new JTextField("10.16.19.219");
		ftpIp.setBackground(new Color(204, 255, 204));
		ftpIp.setForeground(Color.BLUE);
		ftpIp.setBounds(190, 23, 173, 21);
		panel.add(ftpIp);
		
		l2 = new JLabel("FTP服务器端口");
		l2.setHorizontalAlignment(SwingConstants.LEFT);
		l2.setBounds(94, 47, 86, 15);
		panel.add(l2);
		ftpPort = new JTextField("21");
		ftpPort.setBackground(new Color(204, 255, 204));
		ftpPort.setForeground(Color.BLUE);
		ftpPort.setBounds(190, 47, 173, 21);
		panel.add(ftpPort);
		
		l3 = new JLabel("登录FTP服务器的用户名");
		l3.setHorizontalAlignment(SwingConstants.LEFT);
		l3.setBounds(46, 74, 134, 15);
		panel.add(l3);
		ftpUser = new JTextField("admin");
		ftpUser.setBackground(new Color(204, 255, 204));
		ftpUser.setForeground(Color.BLUE);
		ftpUser.setBounds(190, 71, 173, 21);
		panel.add(ftpUser);
		
		l4 = new JLabel("登录FTP服务器的密码");
		l4.setHorizontalAlignment(SwingConstants.LEFT);
		l4.setBounds(58, 95, 122, 15);
		panel.add(l4);
		ftpPass = new JTextField("admin");
		ftpPass.setBackground(new Color(204, 255, 204));
		ftpPass.setForeground(Color.BLUE);
		ftpPass.setBounds(190, 95, 173, 21);
		panel.add(ftpPass);
		
		l5 = new JLabel("FTP服务器上的保存文件的路径");
		l5.setHorizontalAlignment(SwingConstants.LEFT);
		l5.setBounds(10, 119, 170, 15);
		panel.add(l5);
		ftpPath = new JTextField("adc/file");
		ftpPath.setBackground(new Color(204, 255, 204));
		ftpPath.setForeground(Color.BLUE);
		ftpPath.setBounds(190, 119, 173, 21);
		panel.add(ftpPath);
		
		l6 = new JLabel("首页IP/主机名");
		l6.setHorizontalAlignment(SwingConstants.LEFT);
		l6.setBounds(94, 143, 86, 15);
		panel.add(l6);
		frontIp = new JTextField("192.168.1.34");
		frontIp.setBackground(new Color(204, 255, 204));
		frontIp.setForeground(Color.BLUE);
		frontIp.setBounds(190, 143, 173, 21);
		panel.add(frontIp);
		
		l7 = new JLabel("首页端口");
		l7.setHorizontalAlignment(SwingConstants.LEFT);
		l7.setBounds(124, 167, 56, 15);
		panel.add(l7);
		frontPort = new JTextField("7001");
		frontPort.setBackground(new Color(204, 255, 204));
		frontPort.setForeground(Color.BLUE);
		frontPort.setBounds(190, 167, 173, 21);
		panel.add(frontPort);
		
		replaceMode = new JCheckBox( "替换本身(不选创建测试文件)", false );
		replaceMode.setBounds(46, 315, 229, 23);
		panel.add(replaceMode);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(373, 18, 389, 316);
		panel.add(scrollPane);
		
		textPane = new JTextArea();
		scrollPane.setViewportView(textPane);
		textPane.setBackground(new Color(204, 255, 204));
		textPane.setForeground(Color.BLUE);
		
		lblip = new JLabel("数据库IP");
		lblip.setHorizontalAlignment(SwingConstants.LEFT);
		lblip.setBounds(124, 192, 56, 15);
		panel.add(lblip);
		
		dbIP = new JTextField("10.16.69.103");
		dbIP.setForeground(Color.BLUE);
		dbIP.setBackground(new Color(204, 255, 204));
		dbIP.setBounds(190, 192, 173, 21);
		panel.add(dbIP);
		
		lblSid = new JLabel("SID");
		lblSid.setHorizontalAlignment(SwingConstants.LEFT);
		lblSid.setBounds(145, 220, 26, 15);
		panel.add(lblSid);
		
		sid = new JTextField("orcl");
		sid.setForeground(Color.BLUE);
		sid.setBackground(new Color(204, 255, 204));
		sid.setBounds(190, 217, 173, 21);
		panel.add(sid);
		
		label_2 = new JLabel("数据库端口");
		label_2.setHorizontalAlignment(SwingConstants.LEFT);
		label_2.setBounds(119, 245, 61, 15);
		panel.add(label_2);
		
		dbPort = new JTextField("1521");
		dbPort.setForeground(Color.BLUE);
		dbPort.setBackground(new Color(204, 255, 204));
		dbPort.setBounds(190, 242, 173, 21);
		panel.add(dbPort);
		
		lblDb = new JLabel("DB用户名(报表)");
		lblDb.setHorizontalAlignment(SwingConstants.LEFT);
		lblDb.setBounds(190, 273, 86, 15);
		panel.add(lblDb);
		
		reportDbUser = new JTextField("lnyd_adc2");
		reportDbUser.setForeground(Color.BLUE);
		reportDbUser.setBackground(new Color(204, 255, 204));
		reportDbUser.setBounds(288, 270, 75, 21);
		panel.add(reportDbUser);
		
		reportDbPass = new JTextField("lnyd_adc2");
		reportDbPass.setForeground(Color.BLUE);
		reportDbPass.setBackground(new Color(204, 255, 204));
		reportDbPass.setBounds(288, 295, 75, 21);
		panel.add(reportDbPass);
		
		lblDb_1 = new JLabel("DB密码(报表)");
		lblDb_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblDb_1.setBounds(190, 298, 86, 15);
		panel.add(lblDb_1);
		
		label = new JLabel("DB用户名");
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setBounds(46, 273, 56, 15);
		panel.add(label);
		
		label_1 = new JLabel("DB密码");
		label_1.setHorizontalAlignment(SwingConstants.LEFT);
		label_1.setBounds(46, 298, 47, 15);
		panel.add(label_1);
		
		dbUser = new JTextField("lnyd_adc");
		dbUser.setForeground(Color.BLUE);
		dbUser.setBackground(new Color(204, 255, 204));
		dbUser.setBounds(110, 270, 61, 21);
		panel.add(dbUser);
		
		dbPass = new JTextField("lnyd_adc");
		dbPass.setForeground(Color.BLUE);
		dbPass.setBackground(new Color(204, 255, 204));
		dbPass.setBounds(110, 295, 61, 21);
		panel.add(dbPass);
		
		btnPanel = new JPanel();
		btnPanel.setBounds(10, 364, 772, 211);
		getContentPane().add(btnPanel);
		btnPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 5));
		btn1 = new JButton("搜寻配置文件properties");
		btnPanel.add(btn1);
		
		btnOws = new JButton("OWS");
		btnOws.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ( owsReplacerWebXml != null ) {
					owsReplacerWebXml.replace( null, null, replaceMode.isSelected() );
					Main.this.appendToTextPane("ows xml 变更！");
				}else {
					appendToTextPane( "ows web.xml配置文件未找到!" );
				}	
				
				if ( owsReplaceProp != null ) {
					owsReplaceProp.replace( "ows.serviceUrl", StringUtil.concatHttpUrl( Main.this.getFrontIp(), Main.this.getFrontPort(), "/adc_ws/Service.jws" )
							,replaceMode.isSelected() );
					Main.this.appendToTextPane("ows prop 变更！");
				}else {
					appendToTextPane( "ows  init.properties 文件未找到!" );
				}
			}
		});
		btnPanel.add(btnOws);
		
		button_1 = new JButton("报表");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ( sqlMapReaplcer != null ) {
					sqlMapReaplcer.replace( null, null, replaceMode.isSelected() );
					Main.this.appendToTextPane("sql map 变更！");
				} else {
					appendToTextPane( "报表SqlMapConfig.xml配置文件未找到!" );
				}
				if ( sqlMapReportReaplcer != null ) {
					sqlMapReportReaplcer.replace( null, null, replaceMode.isSelected() );
					Main.this.appendToTextPane("sql map report 变更！");
				} else {
					appendToTextPane( "报表SqlMapConfig_adc.xml配置文件未找到!" );
				}
			}
		});
		btnPanel.add(button_1);
		
		btnNewButton_1 = new JButton("退出");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exit();
			}
		});
		
		JButton btnNewButton = new JButton("清空");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textPane.setText( "" );
			}
		});
		
		btnEjb = new JButton("EJB");
		btnEjb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EarMain.main( null );
			}
		});
		btnPanel.add(btnEjb);
		
		btnPanel.add(btnNewButton);
		btnPanel.add(btnNewButton_1);
	}
	


	private void addListeners() {
		btn1.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				search( Main.this.oldPath );
			}

			private void search( final String dir ) {
				List<File> pfs = listProps( dir );
				if ( !found ) {
					procPfs(pfs);
				} else {
					int result = JOptionPane.showConfirmDialog(null, "已经搜寻到"
							+ pfs.size()
							+ "个！，是否新目录搜索？");
					
					// 是否重新搜索
					if (result == JOptionPane.YES_OPTION) {
						found = false; // 未找到标识
						
						if ( Main.this.pfs != null ) {
							Main.this.pfs.clear();  // 清除
						}
						
						int len = btnPanel.getComponents().length - 6;
						for ( int i = 0; i < len; i++ ) {
								btnPanel.remove( 6 );
						}
						procPfs( null );
					}
				}
			}

			private boolean procPfs(List<File> pfs) {
				// check the find the files ?
				 if ( null != pfs && pfs.size() > 0 ) {
					 // find
					 found = true;
					 if ( found && CUR_DIR.equals( oldPath )) {
						 JOptionPane.showMessageDialog( null, "当前目录搜寻到" + pfs.size() + "个adc_web_config.properties文件！");
					 }
					 
					 Main.this.pfs = pfs;
					 
					 Main.this.notifyPfs();
					 
					 Main.this.appendToTextPane( "搜索到 "  + pfs.size() + " 个 properties 文件！");
					 return true;
				 } else {
					 // not find.
					 JOptionPane.showMessageDialog( null, "未找到adc_web_config.properties文件！");
					 String dir = (String) JOptionPane.showInputDialog(null, "请输入文件的路径", "输入",
                             JOptionPane.QUESTION_MESSAGE, null, null, oldPath);
					// String dir = JOptionPane.showInputDialog("请输入文件的路径");
					 if ( dir == null ) { // cancel the input dialog.
						 return false;
					 }
					 oldPath = dir;
					 return procPfs( listProps( dir ) );
				 }
			}


			private List<File> listProps( final String path ) {
				 if ( StringUtil.isEmpty( path ) ) {
					 JOptionPane.showMessageDialog( null, "路径为空！");
					 return null;
				 }
				 final File root = new File( path );
				 if ( !root.exists() ) {
					 JOptionPane.showMessageDialog( null, "未找到adc_web_config.properties文件！");
					 return null;
				 }
				 if ( root.isFile() ) {
					 JOptionPane.showMessageDialog( null, "请输入一个目录地址！");
					 return null;
				 }
				 
				 final File[] files = root.listFiles();
				 
				 List<File> pfs = new ArrayList<File>();
				 if ( null != files && files.length > 0 ) {
					 for ( final File dir : files ) {
						 if ( dir.isDirectory() ) {
							 searchAdcConfig( pfs, dir );
							 seachOther( pfs, dir );
						 }
					 }
				 }
				return pfs;
			}
			
			private void searchAdcConfig(List<File> pfs, final File dir) {
				search( pfs, dir, 
						WEBINF_ADC_WEB_CONFIG,
						"WebContent" + File.separator + WEBINF_ADC_WEB_CONFIG,
						"src" + File.separator + ADC_WEB_CONFIG
				);
			}
			
			
			private final void search(List<File> pfs, final File dir, final String ... suffixs ) {
				File pf ;
				for ( final String suffix : suffixs ) {
					pf = new File( dir, suffix );
					
					if ( pf.exists() && pf.isFile() ) {
						pfs.add( pf );
					}
				}
			}
			
			private final Replacer getReplacer(List<File> pfs, final File dir, final String ... suffixs ) {
				File pf ;
				for ( final String suffix : suffixs ) {
					pf = new File( dir, suffix );
					
					if ( pf.exists() && pf.isFile() ) {
						return new Replacer( pf, Main.this );
					}
				}
				return null;
			}
			
			private void seachOther(List<File> pfs,File dir) {
				if (dir.getAbsolutePath().indexOf("adc_ows") >= 0) {
					// search web xml
					owsReplacerWebXml = getReplacer( pfs, dir, 
							WEBINF_WEBXML,
							"WebContent" + File.separator + WEBINF_WEBXML
					);
					
					
					// search web properties
					owsReplaceProp = getReplacer( pfs, dir, 
							"src" + File.separator + "init.properties",
							"WebContent" + File.separator + WEBINF_CLASSES_OWS_INIT_PROPERTIES,
							WEBINF_CLASSES_OWS_INIT_PROPERTIES
					);
					
				}
				if (dir.getAbsolutePath().indexOf("adc_report") >= 0) {
					// search web xml
					sqlMapReaplcer = getReplacer( pfs, dir, 
							"src" + File.separator + "res" + File.separator  + SQL_MAP_CONFIG,
							"WebRoot" + File.separator + WEBINF_SQL_MAP_CONFIG,
							WEBINF_SQL_MAP_CONFIG
					);
					
					
					sqlMapReportReaplcer = getReplacer( pfs, dir, 
							"src" + File.separator + "res" + File.separator + SQL_MAP_CONFIG_ADC,
							"WebRoot" + File.separator +  "res" + File.separator + WEBINF_SQL_MAP_CONFIG_ADC,
							WEBINF_SQL_MAP_CONFIG_ADC
					);
				}

			}
		});
	}

	protected void notifyPfs() {
		FileButton fb;
		filter(  );
		for ( final File f : pfs ) {
			fb = new FileButton( this, f );
			fb.setMain( this );
//			scrollPane.setColumnHeaderView(fb);
			loadPropsByOp( f );
			
			if ( f.getName().indexOf( ADC_WEB_CONFIG ) >= 0 ) {
				btnPanel.add(fb);
			}
		}
		
		setVisible( true );
		//resize( width, height );
	}

	private void filter() {
		// 如果有 src 和 web classes 同时存在则将  class 除去
		boolean hadSrc = false, hadClasses = false;
		List<File> tofilters = new ArrayList<File>();
		for ( final File f : pfs ) {
			if ( f.getAbsolutePath().indexOf( "src" ) >= 0 ) {
				hadSrc = true;
			} else if ( f.getAbsolutePath().indexOf( "classes" ) >= 0 ) {
				hadClasses = true;
				tofilters.add( f );
			}
		}
		
		if ( hadSrc && hadClasses ) {
			for ( final File f : tofilters ) {
				pfs.remove( f );
			}
		}
	}

	private void loadPropsByOp(File f) {
		// loading the ftp and front page's information by the adc_op's web config properties
		if ( f.getAbsolutePath().indexOf( "adc_op" ) >= 0  ) {
			appendToTextPane( " Loading by the file:  " + f + "\r\n" );
			Properties p = IOUtil.load( f );
			
			ftpPort.setText( p.getProperty( "ftp.port" ) );
			ftpUser.setText( p.getProperty( "ftp.user" ) );
			ftpPass.setText( p.getProperty( "ftp.password" ) );
			ftpPath.setText( p.getProperty( "ftp.path" ) );
			String frontPageUrl =  p.getProperty( "front.page.url" ) ;
			frontIp.setText( StringUtil.getIP( p.getProperty( frontPageUrl ) ) );
			frontPort.setText( StringUtil.getPort( p.getProperty( frontPageUrl ) ) );

//			dbIP.setText( p.getProperty( "front.page.url" ) );
//			sid.setText( p.getProperty( "front.page.url" ) );
//			
//			dbPort.setText( p.getProperty( "front.page.url" ) );
//			
//			reportDbUser.setText( p.getProperty( "front.page.url" ) );
//			reportDbPass.setText( p.getProperty( "front.page.url" ) );
//			
//			dbUser.setText( p.getProperty( "front.page.url" ) );
//			dbPass.setText( p.getProperty( "front.page.url" ) );
		}
		// loading the report information by the 
		else if ( f.getAbsolutePath().indexOf( "adc_report" ) >= 0 ) {
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Main();
	}

	public String getFtpIp() {
		return ftpIp.getText();
	}

	public String getFtpPort() {
		return ftpPort.getText();
	}

	public String getFtpUser() {
		return ftpUser.getText();
	}

	public String getFtpPass() {
		return ftpPass.getText();
	}

	public String getFtpPath() {
		return ftpPath.getText();
	}

	public String getFrontIp() {
		return frontIp.getText();
	}

	public String getFrontPort() {
		return frontPort.getText();
	}

	public boolean isReplaceModeSelected() {
		return replaceMode.isSelected();
	}
	/**
	 * @return the dbIP
	 */
	public String getDbIP() {
		return dbIP.getText();
	}

	/**
	 * @return the sid
	 */
	public String getSid() {
		return sid.getText();
	}

	/**
	 * @return the dbPort
	 */
	public String getDbPort() {
		return dbPort.getText();
	}

	/**
	 * @return the dbUser
	 */
	public String getDbUser() {
		return dbUser.getText();
	}

	/**
	 * @return the dbPass
	 */
	public String getDbPass() {
		return dbPass.getText();
	}	


	/**
	 * @return the reportDbUser
	 */
	public String getReportDbUser() {
		return reportDbUser.getText();
	}

	/**
	 * @return the reportDbPass
	 */
	public String getReportDbPass() {
		return reportDbPass.getText();
	}
	
	
	public void appendToTextPane( final Object msg ) {
		textPane.setText( textPane.getText() + (textPane.getText().length() == 0 ? "" : "\r\n") + String.valueOf( msg ) );
	}
	
}
