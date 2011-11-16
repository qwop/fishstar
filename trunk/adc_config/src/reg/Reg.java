package reg;
/**
 * 注册表操作
 * @author dolphin
 *
 * 2011-11-14 上午10:43:06
 */
public interface Reg {
	int HKLM = 0x11, HKCU = 0x22,  HKCR = 0x33, HKU = 0x44, HKCC = 0x55;
	
	/**
	 * if not exists then add value , else then update the keyName's value.
	 * @param keyName
	 */
	void updateValue( final String keyName, final Object value );
	
	/**
	 * if not exists then add value , else then update the keyName's value.
	 * @param keyName
	 */
	void delete( final String ... keyName );
	
	void dispose(); 
	
	String get( final String keyName );
}
