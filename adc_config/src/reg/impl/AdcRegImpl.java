package reg.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Logger;

import reg.Reg;

public class AdcRegImpl implements Reg {
	private Runtime runtime;
	
	public AdcRegImpl() {
		runtime = Runtime.getRuntime();
	}
	
	private boolean debug;
	private static Logger logger = Logger.getLogger( AdcRegImpl.class.getSimpleName() );
	
	public void setDebug( boolean debug ) {
		this.debug = debug;
	}
	
	private void exec( final String command ) {
		if ( null != command ) {
			
			log( "The command: " + command);
			
			try {
				runtime.exec( command );
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void log( final Object msg ) {
		if ( debug ) {
			logger.info( String.valueOf( msg) );
		}
	}
	
//
//	/* (non-Javadoc)
//	 * @see reg.Reg#delKey(java.lang.String)
//	 */
//	@Override
//	public void delKey(String keyName) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	/* (non-Javadoc)
//	 * @see reg.Reg#delValue(java.lang.String)
//	 */
//	@Override
//	public void delValue(String keyName) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	/* (non-Javadoc)
//	 * @see reg.Reg#updateValue(java.lang.String)
//	 */
//	@Override
//	public void updateValue(String keyName) {
//		
//	}
//
//	/* HKCR	HKEY_CLASSES_ROOT
//	 * HKCU	HKEY_CURRENT_USER
//	 * HKLM	HKEY_LOCAL_MACHINE
//	 * HKU	HKEY_USERS
//	 * HKCC	HKEY_CURRENT_CONFIG
//	 */
//	@Override
//	public void addKey(String subKey, int keyType) {
//		String rootKey = "";
//		switch( keyType ) { 
//			case Reg.HKLM : {
//				rootKey = "HKLM\\";
//				break;
//			}
//			case Reg.HKCU : {
//				rootKey = "HKCU\\";
//				break;
//			}
//			case Reg.HKCR : {
//				rootKey = "HKCR\\";
//				break;
//			}
//			case Reg.HKU : {
//				rootKey = "HKU\\";
//				break;
//			}
//			case Reg.HKCC : {
//				rootKey = "HKCC\\";
//				break;
//			}
//			default : {
//				
//			}
//		}
//		String command = "reg add " ;
//		exec( command + rootKey  + subKey );
//	}
//
//	@Override
//	public void addValue(String fullKey , Object value, int type) {
//		
//	}
//
//	@Override
//	public void dispose() {
//		runtime = null;
//	}

	private String delCmd = "cmd /c echo Y | REG DELETE HKLM\\SOFTWARE\\adc_config";
	@Override
	public void delete( String ... keyNames ) {
		if ( null != keyNames && keyNames.length > 0 ) {
			for ( final String keyName : keyNames ) {
				String command = delCmd + " /v " + keyName;
				exec( command );
			}
		} else {
			exec( delCmd );
		}
	}

	@Override
	public void updateValue(String keyName, Object value) {
		//  REG ADD HKLM\Software\MyCo /v Data /t REG_BINARY /d fe340ead
		//    添加一个值(名称: Data，类型: REG_BINARY，数据: fe340ead)
		// /f 强制覆盖值
		String command = "REG ADD HKLM\\SOFTWARE\\adc_config /v " + keyName + " /t REG_SZ /d " + value + " /f";
		exec( command );
	}

	@Override
	public void dispose() {
		exec( "taskkill /f /im Reg.exe" ) ;
		runtime = null;
	}

	@Override
	public String get(String keyName) {
//		final String queryCmd = "cmd /c for /f \"skip=2 tokens=2,3* delims=REG_SZ\" %a in ('reg query \"HKEY_LOCAL_MACHINE\\SOFTWARE\\adc_config\" /v " + keyName + "') do @echo %a";	
		final String queryCmd = "cmd /c for /f \"skip=2 tokens=2,3*\" %a in ('reg query \"HKEY_LOCAL_MACHINE\\SOFTWARE\\adc_config\" /v " + keyName + "') do @echo %b";	
		return readCmd( queryCmd );
	}
	
	
	private String readCmd(final String command) {
		Process p = null;
		log( command );
		try {
			p = runtime.exec(command);
		} catch (IOException e) {
			e.printStackTrace();
		}
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				p.getInputStream()));
		String line;
		StringBuffer buf = new StringBuffer();
		try {
			while (null != (line = reader.readLine())) {
				buf.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != reader) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return buf.toString().trim();
	}

}
