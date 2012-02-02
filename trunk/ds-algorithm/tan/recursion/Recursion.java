package recursion;

public class Recursion {
	public int factorial( final int n ) {
		if ( n == 0 ) {
			return 1;
		} else {
			return n * factorial( n - 1 );
		}
	}
	
	
	public int factorial1 ( int n ) {
		if ( n == 0 || n == 1 ) {
			return 1;
		} else if ( n == 2 ) {
			return 2;
		} else {
			int ret = 1 ;
			
			while ( n != 1 ) {
//				System.out.print( ( ret * n ) + " = " + ret + "*" + n + " ");
				System.out.print( "*" + n + " ");
				ret  = ret * n ;
				n = n - 1;
			}
			
			System.out.println();
			return ret;
		}
		
	}
	
	
	public int power( int x , int n ) {
		int y = 0 ;
		
		if ( n == 0 ) {
			y = 1;
		} else {
			System.out.println( " y = power( " + x + ", " + ( n / 2 ) + " );");
			y = power( x, n / 2 );
			y = y * y;
			if ( n % 2 == 1 ) { 
				y = y * x;
			}
		}
		
		return y;
	}
}
