package cn.nsccsz;

import static org.junit.Assert.*;

import org.junit.Test;

public class StringUtilTest
{

    @Test(expected = IllegalArgumentException.class)
    public void testTrim() throws Exception
    {
        assertEquals("", StringUtil.trim("         "));

        assertEquals("你好", StringUtil.trim("  	你好"));
        assertEquals("你好", StringUtil.trim("你好			      "));
        assertEquals("你好", StringUtil.trim("			你好			      "));

        StringUtil.trim(null);

    }

}
