package recursion;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RecursionTest {
	Recursion recursion;
	
	@Before
	public void setUp() throws Exception {
		recursion = new Recursion();
	}

	@After
	public void tearDown() throws Exception {
		recursion = null;
	}

	@Test
	public void testFactorial() {
		assertEquals( "", 1 , recursion.factorial( 1 ) ); // 1
		assertEquals( "", 2 , recursion.factorial( 2 ) ); // 2 * 1 
		assertEquals( "", 6 , recursion.factorial( 3 ) ); // 3 * 2 * 1
		assertEquals( "", 24 , recursion.factorial( 4 ) ); // 4 * 3 * 2 * 1
		assertEquals( "", 120 , recursion.factorial( 5 ) ); // 5 * 4 * 3 * 2 * 1
	}
	@Test
	public void testFactorial1() {
		assertEquals( "", 1 , recursion.factorial1( 1 ) ); // 1
		assertEquals( "", 2 , recursion.factorial1( 2 ) ); // 2 * 1 
		assertEquals( "", 6 , recursion.factorial1( 3 ) ); // 3 * 2 * 1
		assertEquals( "", 24 , recursion.factorial1( 4 ) ); // 4 * 3 * 2 * 1
		assertEquals( "", 120 , recursion.factorial1( 5 ) ); // 5 * 4 * 3 * 2 * 1
	}
	
	@Test
	public void testPower() {
//		assertEquals( "", 1 , recursion.power( 1, 0 ) ); // 1
//		assertEquals( "", 4 , recursion.power( 2, 2 ) ); // 1
//		assertEquals( "", 512 , recursion.power( 8, 3 ) ); // 1
		
		recursion.power( 8, 8 );
	}
}
