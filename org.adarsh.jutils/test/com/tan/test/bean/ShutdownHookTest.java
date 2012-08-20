package com.tan.test.bean;

public class ShutdownHookTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				System.out.println( "add shut down hoo !" );
			}
		});
		
		
		System.out.println( "one" );
		System.out.println( "two" );
		System.out.println( "three" );
		System.out.println( "1\n2".replaceAll( "(?m)^", "\t" ) );
	}

}
