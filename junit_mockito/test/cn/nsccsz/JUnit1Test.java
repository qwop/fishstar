package cn.nsccsz;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Array;

import org.junit.Test;

public class JUnit1Test
{
    static class MyClass
    {
        public int multiply(int a, int b)
        {
            return a * b;
        }
    }

    @Test
    public void testMultiply()
    {
        MyClass tester = new MyClass();

        assertEquals("10 x 5 must be 50 ", 50, tester.multiply(10, 5));
    }

    @Test(timeout = 10000)
    public void testTime()
    {
        for (int i = 0; i < 1000000; i++)
        {
            // System.out.println("1");
        }
    }

    @Test
    public void testAssertEquals()
    {
        int[] intArr = new int[]
        { 1, 2 };
        int[] intArr2 = new int[]
        { 1, 2 };
        // assertEquals( intArr, intArr );
        assertEquals(intArr, intArr);

        assertEquals(1.1f, 1.1111d, 1);
    }

}
