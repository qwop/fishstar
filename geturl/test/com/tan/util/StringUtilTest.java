package com.tan.util;

import junit.framework.TestCase;

import org.junit.Test;

public class StringUtilTest extends TestCase {

	private void testGreet() {
		System.out.println( StringUtil.greetsLen );
		for ( int i = 0; i < 1000; i ++ ) {
			try {
				Thread.sleep( 50 );
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.print( " " + StringUtil.getGreetLenRandom() );
			if ( i % 5 == 0 ) {
				System.out.println( );
			}
		}
	}
	
	
	@Test
	public void testFuck() {
		String sql = "select distinct p.strurl"
			+ " from nn_oprolepriv rp,nn_opprivinfo p"
			+ " where rp.intpriv = p.intpriv and rp.introle = ?";
		
		System.out.println( sql );
	}
}
