package reg.impl;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RegImplTest {
	private   AdcRegImpl adcReg;
	
	@Before
	public void setUp() throws Exception {
		adcReg = new AdcRegImpl();
		
		adcReg.setDebug( true );
	}

	@After
	public void tearDown() throws Exception {
		adcReg.dispose();
	}
	
	@Test
	public void testUpdateValue() {
		adcReg.updateValue( "test", "Test" );
		adcReg.updateValue( "reportDbPass", "lnyd_adc2" );
		adcReg.updateValue( "name", "谭元吉" );
	}

	@Test
	public void testDelete() {
		adcReg.delete( "name" );
	}

	@Test
	public void testGet() {
		assertEquals( "Test", adcReg.get( "test" ) );
		
		assertEquals( "lnyd_adc2", adcReg.get( "reportDbPass" ) );
	}
}
