package com.tan.util;

import junit.framework.TestCase;

public class StringUtilTest extends TestCase {

	public void testGreet() {
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

}
