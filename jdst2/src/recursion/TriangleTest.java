package recursion;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 12-3-28
 * Time: 下午3:37
 * To change this template use File | Settings | File Templates.
 */
public class TriangleTest {
    public static void main(String[] args) {
        TriangleTest test = new TriangleTest();

/*
       for ( int i = 1; i <= 9 ; i++ ) {
           test.triangle( i );
       }
*/

        int ret = test.triangle( 9 );
        System.out.println( ret );

        ret = test.recurisonTriangle( 9 );
        System.out.println();
        System.out.println( ret );

        
    }
    

    private int recurisonTriangle(int n) {
        if ( n == 1 ) {
            System.out.print( 1 );
            return 1;
        }
        
        System.out.print( "fn(" + n + ")=" + n + "+ ");
//        System.out.print( "fn(" + n + ")=" + n + " + fn(" + ( n - 1 ) + ") ");
        return n + recurisonTriangle( n - 1 );
    }

    private int triangle(int n) {
        int total = 0 ;
         // n + value( n - 1 )
        
        //   fn( n ) = fn( n - 1 ) + n
        while ( n > 0 ) {
            total = total + n;
            --n;
        }
        
       return total;
    }

}
