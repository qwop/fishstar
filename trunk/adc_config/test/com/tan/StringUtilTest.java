package com.tan;

import static org.junit.Assert.*;

import org.junit.Test;

public class StringUtilTest {

	@Test
	public void testGetAdcProjectKeyWord() {
		assertEquals( "通过adc_关键字获取当前项目的名称", "adc_config", StringUtil.getAdcProjectKeyWord( "D:\\bea\\user_projects\\workspaces\\default\\adc_config" ) );
		assertEquals( "通过adc_关键字获取当前项目的名称", "adc_op", StringUtil.getAdcProjectKeyWord( "D:\\bea\\user_projects\\workspaces\\default\\adc_op\\suffix" ) );
	}

}
