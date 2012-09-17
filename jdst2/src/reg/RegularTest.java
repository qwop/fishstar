package reg;
/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 12-3-27
 * Time: 下午4:12
 * To change this template use File | Settings | File Templates.
 */
public class RegularTest {
    public static void main( String[] args) {
        String str = "[  32]\t  45*\t  32*\t   4\t   5*\t   6\t 776*\t  76\t  43\t  32*\t  43\t  65\t 709\t";

        String regStr = "(.+\\*.+){2,}";
       
        while ( str.matches( regStr ) ) {
            str = str.replaceFirst( "\\*", "" );
        }
        
        System.out.println( str );
    }
}
