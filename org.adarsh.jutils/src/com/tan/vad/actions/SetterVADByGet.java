package com.tan.vad.actions;

import org.adarsh.jutils.preferences.PreferenceConstants;

import com.tan.vad.AbstractSetterVAD;

public class SetterVADByGet extends AbstractSetterVAD {

	/**
	 * *
	 * <ul>
	 * <li>PreferenceConstants.STR_STYLE_BY_CONTENT 样式1 vo.setAge( 2 );</li>
	 * <li>PreferenceConstants.STR_STYLE_BY_GET 样式2 vo.setAge( po.getAge() );</li>
	 * <li>PreferenceConstants.STR_STYLE3 样式3 待设定</li>
	 * </ul>
	 */
	@Override
	public void setStyle() {
		this.style = PreferenceConstants.STR_STYLE_BY_GET;
	}

}
