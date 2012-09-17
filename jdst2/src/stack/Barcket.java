package stack;

import java.util.Stack;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 12-3-27
 * Time: 下午5:59
 * To change this template use File | Settings | File Templates.
 */
public class Barcket {


    public static void main(String[] args) {
        String str = "[0)(}[)";
        // the error
        check(str);
        // miss right
        str = "("    ;
        check( str );
        // the right
        str = "[0](1){2}";
        
        check( str );
    }

    private static void check(String str) {
        if (str != null && str.trim().length() > 0) {
            int size = str.length();
            Stack stack = new Stack();
            char[] cs = str.toCharArray();

            for (int i = 0; i < size; i++) {
                switch (cs[i]) {
                    case '{':
                    case '(':
                    case '[':
                        stack.push(cs[i]);         break;
                    case '}':
                    case ')':
                    case ']':
                        if ( !stack.isEmpty() ) {
                            Character c = ( Character ) stack.pop();
                            if (
                                    ( c == '{' && cs[i] != '}') ||
                                    ( c == '[' && cs[i] != ']') ||
                                    ( c == '(' && cs[i] != ')')
                                ){
                                System.out.println();
                                System.out.println("Barcket is error, the error!");
                                System.out.println(str);
                                for( int j = 0; j < i ; j++) {
                                    System.out.print(' ');
                                }
                                System.out.print('^');
                            }

                        }   else {
                            System.out.println( "There is no matched barcket" );
                        }

                }
            }


            if ( !stack.isEmpty() ) {
                System.out.println( "Error miss the right !" );
            }
        }
    }
}
