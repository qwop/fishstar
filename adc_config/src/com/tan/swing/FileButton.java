package com.tan.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.swing.JButton;

import com.tan.IOUtil;
import com.tan.Main;
import com.tan.StringUtil;

/**
 * 
 * @author dolphin
 *
 * 2011-11-11 上午9:38:51
 */
public class FileButton extends JButton{

	private static final long serialVersionUID = 1L;
	
	final static String RN = System.getProperty( "line.separator", "\r\n" );
	
	/** store the file which like d:\default\adc_op\src\adc_web_config.properties*/
	private File file;
	/** the properties file's reference */
	private Properties prop;
	/** the reference of the main for get the textfield value*/
	private Main main;
	/** the adc keyword which like 'adc_op, adc_ec'*/
	private String adc;
	
	/** the value of the properties like {key=value} to replace  */
	private List<String> replacements = new ArrayList<String>();
	
	public FileButton( final Main main, final File file ) {
		super( file.getAbsolutePath() );
		this.file = file;
		addListener() ;
		
		try {
			adc = StringUtil.getAdcProjectKeyWord( file.getAbsolutePath() );
		} catch( Exception e ) {
			adc = null;
		}
		this.main = main;
		if ( null == adc || adc.indexOf( "adc_") < 0 ) {
			System.err.println( file +  " \tnot contains the adc keyword !");
			
			main.appendToTextPane( file +  " \tnot contains the adc keyword !" );
		}
		
		main.appendToTextPane( adc );
	}

	private void addListener() {
		this.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// load the properties 
				prop = IOUtil.load( FileButton.this.file );
				
				// to replace the 'key word'
				replace();
			}
		});
	}
	
	protected void replace() {
		replacements.clear();
		
//		#论坛的url
//		forum.server.url=http://localhost:9999/adc_bbs/SSO/Login
		//check( "forum.server.url", "http://" + main.getFrontIp() + "/adc_bbs/SSO/Login" );
		
		check ( "forum.server.url", StringUtil.concatHttpUrl( main.getFrontIp(), main.getFrontPort(), "/adc_bbs/SSO/Login" ) );
		
//		# FTP服务器的IP地址
//		ftp.server=dolphin-3bce011
		check( "ftp.server", main.getFtpIp() );
//		# FTP服务器端口
//		ftp.port=21
		check( "ftp.port", main.getFtpPort() );
//		# 登录FTP服务器的用户名
//		ftp.user=admin
		check( "ftp.user", main.getFtpUser() );
//		# 登录FTP服务器的密码
//		ftp.password=admin
		check( "ftp.password", main.getFtpPass() );
//		# FTP服务器上的保存文件的路径
//		ftp.path=adc/file
		check( "ftp.path", main.getFtpPath() );
//		# 门户首页地址
//		front.page.url=http://qwop:7001/adc_area/login.jsp
		check( "front.page.url",  StringUtil.concatHttpUrl( main.getFrontIp(), main.getFrontPort(), "/" + (adc != null ? adc : "adc_op" )+ "/login.jsp" ) );
		
//		# 文件服务根地址
//		file.root.url=http://qwop:7001/adc_op/file
		check( "file.root.url",  StringUtil.concatHttpUrl( main.getFrontIp(), main.getFrontPort(), "/" + (adc != null ? adc : "adc_op" )+ "/file" ) );
		
//		# 单点登录Web Service接口地址
//		sso.server.url=http://qwop:7001/adc_ws/Service.jws
		check( "sso.server.url",  StringUtil.concatHttpUrl( main.getFrontIp(), main.getFrontPort(), "/adc_ws/Service.jws" ) );
		
//		# 统计服务器地址
//		report.server.url=http://qwop:7001/ADCReport/report
		check( "report.server.url",  StringUtil.concatHttpUrl( main.getFrontIp(), main.getFrontPort(), "/adc_report/report" ) );
		
//		# EC门户首页地址
//		ec.front.page.url=http://qwop:7001/adc_ec
		check( "ec.front.page.url",  StringUtil.concatHttpUrl( main.getFrontIp(), main.getFrontPort(), "/adc_ec" ) );
		
		replaceWrite() ;
	}
	
	private void replaceWrite() {
		if ( null == replacements || replacements.size() == 0 ) {
			main.appendToTextPane( adc + " 无可替换的内容！" );
			return ;
		}
		RandomAccessFile raf = null;
		StringBuffer buf = new StringBuffer();
		String prefix;
		String line;
		String info = null;
		File outputFile;
		try {
			raf = new RandomAccessFile( file, "rw" );
			while ( null != ( line = raf.readLine() )) {
				for ( final String newValue : replacements ) {
					prefix = newValue.substring( 0, newValue.indexOf('=') ); // get the key word by the replacements.
					
					if ( line.indexOf( prefix ) >= 0) { // which line  had the key, then replace the line
						line = newValue;
					}
				}
				buf.append( line  + RN);
			}
			raf.close();
			raf = null;
			if ( main.isReplaceModeSelected() ) {
				info = "本文件变更" + file.getName();
				raf = new RandomAccessFile( file, "rw");
			} else {
				outputFile = new File( Main.CUR_DIR, "adc_web_config_" + adc + ".properties" );
				info = "创建测试文件:" + outputFile.getName();
				raf = new RandomAccessFile( outputFile, "rw");
			}
			
			raf.write( buf.toString().getBytes() );
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				raf.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			raf = null;
			System.out.println( info );
			main.appendToTextPane(  info );
		}
	}

	protected void check( final String key, final String newValue ) {
		if ( prop.containsKey( key) ) { // the properties contains the key value.
			final String oldValue = prop.getProperty( key ); // get the key -> value
			if ( null != newValue ) { // new value can not be null.
				if ( newValue.equals( oldValue ) ) { // new value can not equals the old value.
					return;
				} else if ( !replacements.contains( key + '=' + newValue ) ){
					//System.out.println( key + '=' + oldValue + " -> " + key + '=' + newValue );
					replacements.add( key + '=' + newValue );
				}
			}
			
		}
	}

	public void setMain(Main main) {
		this.main = main;
	}
}
