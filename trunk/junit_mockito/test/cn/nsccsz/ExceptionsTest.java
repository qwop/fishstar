package cn.nsccsz;

import static org.junit.Assert.*;

import org.junit.Test;

public class ExceptionsTest
{
    @Test
    public void testExceptions()
    {
        try
        {
            StringUtil.mustThrowException();
            fail();
        }
        catch (Exception e)
        {
            // expected.
        }

        try
        {
            StringUtil.mustThrowException();
            fail();
        }
        catch (Exception e)
        {
            // expected.
        }
    }
}
