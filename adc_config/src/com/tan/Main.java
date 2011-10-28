package com.tan;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
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
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import com.tan.swing.FileButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

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
	private int x = 300,y = 200, width = 800, height = 500;
	
	private JLabel l1,l2,l3,l4,l5,l6,l7,l8;
	private JTextField ftpIp,ftpPort,ftpUser,ftpPass,ftpPath,frontIp,frontPort,t8;
	
	private boolean found;
	private JCheckBox replaceMode ;
	private JTextArea textPane;
	public Main() {
		init();
		oldPath = CUR_DIR;
	}
	
	private void init() {
		setTitle("");
		components();
		
		setVisible( true );
		setDefaultCloseOperation( EXIT_ON_CLOSE );
		
		addListeners();
	}

	private void components() {
		setBounds( x, y , width , height);
		getContentPane().setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(10, 10, 772, 220);
		panel.setBorder(new TitledBorder(null, "配置区", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(panel);
		panel.setLayout(null);
		l1 = new JLabel("FTP服务器的IP地址");
		l1.setHorizontalAlignment(SwingConstants.LEFT);
		l1.setBounds(84, 23, 160, 15);
		panel.add(l1);
		ftpIp = new JTextField("10.16.19.219");
		ftpIp.setBackground(new Color(204, 255, 204));
		ftpIp.setForeground(Color.BLUE);
		ftpIp.setBounds(276, 23, 78, 21);
		panel.add(ftpIp);
		
		l2 = new JLabel("FTP服务器端口");
		l2.setHorizontalAlignment(SwingConstants.LEFT);
		l2.setBounds(109, 47, 135, 15);
		panel.add(l2);
		ftpPort = new JTextField("21");
		ftpPort.setBackground(new Color(204, 255, 204));
		ftpPort.setForeground(Color.BLUE);
		ftpPort.setBounds(276, 47, 18, 21);
		panel.add(ftpPort);
		
		l3 = new JLabel("登录FTP服务器的用户名");
		l3.setHorizontalAlignment(SwingConstants.LEFT);
		l3.setBounds(61, 74, 183, 15);
		panel.add(l3);
		ftpUser = new JTextField("admin");
		ftpUser.setBackground(new Color(204, 255, 204));
		ftpUser.setForeground(Color.BLUE);
		ftpUser.setBounds(276, 71, 36, 21);
		panel.add(ftpUser);
		
		l4 = new JLabel("登录FTP服务器的密码");
		l4.setHorizontalAlignment(SwingConstants.LEFT);
		l4.setBounds(73, 95, 171, 15);
		panel.add(l4);
		ftpPass = new JTextField("admin");
		ftpPass.setBackground(new Color(204, 255, 204));
		ftpPass.setForeground(Color.BLUE);
		ftpPass.setBounds(276, 95, 36, 21);
		panel.add(ftpPass);
		
		l5 = new JLabel("FTP服务器上的保存文件的路径");
		l5.setHorizontalAlignment(SwingConstants.LEFT);
		l5.setBounds(25, 119, 219, 15);
		panel.add(l5);
		ftpPath = new JTextField("adc/file");
		ftpPath.setBackground(new Color(204, 255, 204));
		ftpPath.setForeground(Color.BLUE);
		ftpPath.setBounds(276, 119, 54, 21);
		panel.add(ftpPath);
		
		l6 = new JLabel("首页IP/主机名");
		l6.setHorizontalAlignment(SwingConstants.LEFT);
		l6.setBounds(109, 143, 135, 15);
		panel.add(l6);
		frontIp = new JTextField("192.168.1.34");
		frontIp.setBackground(new Color(204, 255, 204));
		frontIp.setForeground(Color.BLUE);
		frontIp.setBounds(276, 143, 78, 21);
		panel.add(frontIp);
		
		l7 = new JLabel("首页端口");
		l7.setHorizontalAlignment(SwingConstants.LEFT);
		l7.setBounds(139, 167, 105, 15);
		panel.add(l7);
		frontPort = new JTextField("7001");
		frontPort.setBackground(new Color(204, 255, 204));
		frontPort.setForeground(Color.BLUE);
		frontPort.setBounds(276, 167, 30, 21);
		panel.add(frontPort);
		
		replaceMode = new JCheckBox( "替换本身(不选创建测试文件)", false );
		replaceMode.setBounds(110, 188, 244, 23);
		panel.add(replaceMode);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(388, 10, 374, 200);
		panel.add(scrollPane);
		
		textPane = new JTextArea();
		scrollPane.setViewportView(textPane);
		textPane.setBackground(new Color(204, 255, 204));
		textPane.setForeground(Color.BLUE);
		
		panel_1 = new JPanel();
		panel_1.setBounds(10, 252, 772, 211);
		getContentPane().add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 5));
		btn1 = new JButton("搜寻配置文件properties");
		panel_1.add(btn1);
		
		JButton btnNewButton = new JButton("清空");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textPane.setText( "" );
			}
		});
		panel_1.add(btnNewButton);
		
		btnNewButton_1 = new JButton("退出");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit( 1 );
			}
		});
		panel_1.add(btnNewButton_1);
	}
	
	private static String suffix = "WEB-INF" + File.separator + "classes" + File.separator + "adc_web_config.properties";
	private JPanel panel;
	private JPanel panel_1;
	private JButton btnNewButton_1;

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
						
						
						for ( int i = 3; i < panel_1.getComponents().length; i++ ) {
								panel_1.remove( i );
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
			fb = new FileButton( this, f );
			fb.setMain( this );
//			scrollPane.setColumnHeaderView(fb);
			panel_1.add(fb);
		}
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
	
	public void dis( final Object msg ) {
		textPane.setText( textPane.getText() + (textPane.getText().length() == 0 ? "" : "\r\n") + String.valueOf( msg ) );
	}
}
