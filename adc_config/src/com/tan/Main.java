package com.tan;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.tan.swing.FileButton;

/**
 * 
 * @author tyj
 *
 */
public class Main extends JFrame {
	private static final long serialVersionUID = 1L;
	public static final String CUR_DIR = System.getProperty("user.dir");
	private JButton btn1;
	private List<File> pfs;
	private String oldPath = "E:\\adc\\LN_Version2\\build1027";
	private int x = 300,y = 200, width = 500, height = 600;
	
	private JLabel l1,l2,l3,l4,l5,l6,l7,l8;
	private JTextField ftpIp,ftpPort,ftpUser,ftpPass,ftpPath,frontIp,frontPort,t8;
	
	private boolean found;
	private JCheckBox replaceMode ;
	
	
	public Main() {
		init();
		oldPath = CUR_DIR;
	}
	
	private void init() {
		getContentPane().setLayout( null );
		btn1 = new JButton("adc_web_config");
		btn1.setBounds(20, 10, 119, 25);
		
		getContentPane().add( btn1 );
		setBounds( x, y , width , height);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 57, 288, 453);
		getContentPane().add(scrollPane);
		
		panel = new JPanel();
		panel1 = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(null);
		l1 = new JLabel("FTP服务器的IP地址");
		l1.setBounds(70, 36, 102, 15);
		panel.add(l1);
		ftpIp = new JTextField("10.16.19.219");
		ftpIp.setBounds(182, 36, 78, 21);
		panel.add(ftpIp);
		
		l2 = new JLabel("FTP服务器端口");
		l2.setBounds(94, 94, 78, 15);
		panel.add(l2);
		ftpPort = new JTextField("21");
		ftpPort.setBounds(182, 94, 18, 21);
		panel.add(ftpPort);
		
		l3 = new JLabel("登录FTP服务器的用户名");
		l3.setBounds(46, 152, 126, 15);
		panel.add(l3);
		ftpUser = new JTextField("admin");
		ftpUser.setBounds(182, 152, 36, 21);
		panel.add(ftpUser);
		
		l4 = new JLabel("登录FTP服务器的密码");
		l4.setBounds(58, 210, 114, 15);
		panel.add(l4);
		ftpPass = new JTextField("admin");
		ftpPass.setBounds(182, 210, 36, 21);
		panel.add(ftpPass);
		
		l5 = new JLabel("FTP服务器上的保存文件的路径");
		l5.setBounds(10, 268, 162, 15);
		panel.add(l5);
		ftpPath = new JTextField("adc/file");
		ftpPath.setBounds(182, 268, 54, 21);
		panel.add(ftpPath);
		
		l6 = new JLabel("首页IP/主机名");
		l6.setBounds(94, 326, 78, 15);
		panel.add(l6);
		frontIp = new JTextField("192.168.1.34");
		frontIp.setBounds(182, 326, 78, 21);
		panel.add(frontIp);
		
		l7 = new JLabel("首页端口");
		l7.setBounds(124, 384, 48, 15);
		panel.add(l7);
		frontPort = new JTextField("7001");
		frontPort.setBounds(182, 384, 30, 21);
		panel.add(frontPort);
		
		panel_1 = new JPanel();
		panel_1.setBounds(319, 57, 175, 406);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		replaceMode = new JCheckBox( "替换本身/不选创建测试文件", false );
		replaceMode.setBounds(6, 18, 175, 23);
		panel_1.add(replaceMode);
		
		
		setVisible( true );
		setDefaultCloseOperation( EXIT_ON_CLOSE );
		
		addListeners();
	}
	
	private static String suffix = "WEB-INF" + File.separator + "classes" + File.separator + "adc_web_config.properties";
	private JScrollPane scrollPane;
	private JPanel panel, panel1;
	private JPanel panel_1;

	private void addListeners() {
		btn1.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				List<File> pfs = listProps(CUR_DIR);
				
				if ( !found ) {
					procPfs(pfs);
				} else {
					JOptionPane.showMessageDialog( null, "已经搜寻到adc_web_config.properties文件！");
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
				 
				 File pf;
				 List<File> pfs = new ArrayList<File>();
				 if ( null != files && files.length > 0 ) {
					 for ( final File dir : files ) {
						 if ( dir.isDirectory() ) {
							 pf = new File( dir, suffix );
							 
							 if ( pf.exists() && pf.isFile() ) {
								 pfs.add( pf );
							 }
							 
							 pf = new File( dir, "WebContent" + File.separator + suffix );
							 
							 if ( pf.exists() && pf.isFile() ) {
								 pfs.add( pf );
							 }
						 }
					 }
				 }
				return pfs;
			}
		});
	}

	
	protected void notifyPfs() {
		FileButton fb;
		for ( final File f : pfs ) {
			fb = new FileButton( f );
			panel_1.add( fb );
			fb.setMain( this );
		}
		
		getContentPane().add( panel_1 );
		setVisible( true );
		//resize( width, height );
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

}
