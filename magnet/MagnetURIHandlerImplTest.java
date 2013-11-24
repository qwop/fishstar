package com.aelitis.net.magneturi.impl;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;

import org.junit.Before;
import org.junit.Test;

/** 
 * <p>Description: [描述该类概要功能介绍]</p>
 * @author  <a href="mailto: xxx@neusoft.com">作者中文名</a>
 * @version $Revision$ 
 */
public class MagnetURIHandlerImplTest
{

    /**
     * <p>Discription:[方法功能中文描述]</p>
     * @throws java.lang.Exception
     * @author:[创建者中文名字]
     * @update:[日期YYYY-MM-DD] [更改人姓名][变更描述]
     */
    @Before
    public void setUp()
        throws Exception
    {
    }

    /**
     * {@link com.aelitis.net.magneturi.impl.MagnetURIHandlerImpl#process(java.lang.String, java.io.InputStream, java.io.OutputStream)} 的测试方法。
     */
    @Test
    public void testProcessStringInputStreamOutputStream()
    {
        // fail( "尚未实现" );
        MagnetURIHandlerImpl handler = new MagnetURIHandlerImpl();
        /*
String          get,
        BufferedReader  is,
        OutputStream    os
                 */
        BufferedReader  is = null;
        OutputStream    os = null;
        try
        {
            handler.process( "magnet:?xt=urn:sha1:YNCKHTQCWBTRNJIV4WNAE52SJUQCZO5C", is, os );
        }
        catch ( IOException e )
        {
            e.printStackTrace();
        }
    }

}
