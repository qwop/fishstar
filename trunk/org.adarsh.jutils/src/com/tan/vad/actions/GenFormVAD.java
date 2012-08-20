package com.tan.vad.actions;

import java.util.Iterator;
import java.util.Set;

import org.adarsh.jutils.internal.Util;
import org.adarsh.jutils.preferences.PreferenceConstants;

import com.tan.util.StringUtil;
import com.tan.vad.AbstractGenVAD;

/**
 * 
 * @author qwop
 * @date 2012.08.20 10.55
 */
public class GenFormVAD extends AbstractGenVAD{
	
	private String form ;
	
	@Override
	protected void getDoc() {
		form = PREF_STORE.getString( PreferenceConstants.SELF_DEFINE_FORM_KEY );
		
		if ( Util.isNullString( form ) ) {
			form = Util.getDefaultSelfDefineForm();
		}
	}

	@Override
	protected void head() {
		content
		.append(  "<form action=\"\" method=\"post\" onsubmit=\"return check();\">" )
		.append( StringUtil.LN 	)
		;
	}

	@Override
	protected void middle() {
		Set<String> keys = middleMap.keySet();
		
		if ( null != keys && keys.size() > 0 ) {
			String key, value,
			formHtml = form
			;
			
			boolean changed = false;
			for ( Iterator<String> iter = keys.iterator(); iter.hasNext(); ) {
				key = iter.next();
				
				if ( null != key ) {
					formHtml = formHtml.replaceAll( key, middleMap.get( key ) );
					changed = true;
				}
			}
			
			if ( changed /*!formItem.equals( form )*/ )
				content
				.append(   formHtml.replaceAll( StringUtil.INDENT_REG, StringUtil.INDENT2 )  )
				.append( StringUtil.LN )
				;
		}
		
	}

	@Override
	protected void tail() {
		content
		.append(  "</form>" )
		.append( StringUtil.LN 	)
		;
	}

}
