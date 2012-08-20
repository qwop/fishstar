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
public class GenTableVAD extends AbstractGenVAD{
	
	private String theadDoc, tbodyDoc, tfootDoc ;
	
	/*
        <table class="tdata">
          <thead>
            <tr>
              <th>能力编码</th>
              <th>能力名称</th>
              <th>应用编码</th>
              <th>应用名称</th>
              <th>订购状态</th>
              <th>创建日期</th>
            </tr>
          </thead>
          <tbody>
<c:forEach var="sub" items="${data.dataset}" varStatus="status">
			  <tr>
              	<td>${sub.service_code}</td>
              	<td>${sub.service_name}</td>
              	<td>${sub.app_id}</td>
              	<td>${sub.strname}</td>
              	<td>
					<c:if test="${sub.application_result == 0 }">未审批</c:if>
					<c:if test="${sub.application_result == 1 }">订购成功</c:if>
					<c:if test="${sub.application_result == 2 }">订购失败</c:if>
				</td>
              	<td>${sub.application_date}</td>
              </tr>
</c:forEach>
							</tbody>
          <tfoot>
            <tr>
              <td colspan="8"><jsp:include page="/page.jsp" /></td>
            </tr>
          </tfoot>
        </table>
	 * */
	
	private StringBuffer  thead, tbody, tfoot;
	
	@Override
	protected void getDoc() {
		theadDoc = PREF_STORE.getString(PreferenceConstants.SELF_DEFINE_THEAD_KEY);
		tbodyDoc = PREF_STORE.getString(PreferenceConstants.SELF_DEFINE_TBODY_KEY);
		tfootDoc = PREF_STORE.getString(PreferenceConstants.SELF_DEFINE_TFOOT_KEY);
		
		
		if ( Util.isNullString( theadDoc ) ) {
			theadDoc = Util.getDefaultSelfDefineTHead();
		}
		if ( Util.isNullString( tbodyDoc ) ) {
			tbodyDoc = Util.getDefaultSelfDefineTBody();
		}
		if ( Util.isNullString( tfootDoc ) ) {
			tfootDoc = Util.getDefaultSelfDefineTFoot();
		}
	}

	@Override
	protected void head() {
		
		thead = new StringBuffer();
		tbody= new StringBuffer();
		tfoot= new StringBuffer( tfootDoc );
		
	}

	@Override
	protected void middle() {
		Set<String> keys = middleMap.keySet();
		
		if ( null != keys && keys.size() > 0 ) {
			String key, value,
			theadHtml = theadDoc,
			tbodyHtml = tbodyDoc
			;
			
			boolean changed = false;
			for ( Iterator<String> iter = keys.iterator(); iter.hasNext(); ) {
				key = iter.next();
				
				if ( null != key ) {
					value = middleMap.get( key );
					
					theadHtml = theadHtml.replaceAll( key, value );
					tbodyHtml = tbodyHtml.replaceAll( key, value );
//					tfootHtml = tfootHtml.replaceAll( key, value );
					
					changed = true;
				}
			}
			
			if ( changed )  {
				thead.append( theadHtml );
				tbody.append( tbodyHtml );
			}
		}
		
	}

	@Override
	protected void tail() {
		content
		.append( "<table class=\"\">" ).append( StringUtil.LN )
		
		.append( StringUtil.INDENT1 ).append( "<thead>" ).append( StringUtil.LN )
		.append( StringUtil.INDENT2 ).append( "<tr>" ).append( StringUtil.LN )
		.append(  thead.toString().replaceAll( StringUtil.INDENT_REG, StringUtil.INDENT3 ) ).append( StringUtil.LN )
		.append( StringUtil.INDENT2 ).append( "</tr>" ).append( StringUtil.LN )
		.append( StringUtil.INDENT1 ).append( "</thead>" ).append( StringUtil.LN )
		
		.append( StringUtil.INDENT1 ).append( "<tbody>" ).append( StringUtil.LN )
		.append( StringUtil.INDENT2 ).append( "<tr>" ).append( StringUtil.LN )
		.append(  tbody.toString().replaceAll( StringUtil.INDENT_REG, StringUtil.INDENT3 ) ).append( StringUtil.LN )
		.append( StringUtil.INDENT2 ).append( "</tr>" ).append( StringUtil.LN )
		.append( StringUtil.INDENT1 ).append( "</tbody>" ).append( StringUtil.LN )
		
		
		.append( StringUtil.INDENT1 ).append( "<tfoot>" ).append( StringUtil.LN )
		.append( StringUtil.INDENT2 ).append( "<tr>" ).append( StringUtil.LN )
		.append(  tfoot.toString().replaceAll( StringUtil.INDENT_REG, StringUtil.INDENT3 ) ).append( StringUtil.LN )
		.append( StringUtil.INDENT2 ).append( "</tr>" ).append( StringUtil.LN )
		.append( StringUtil.INDENT1 ).append( "</tfoot>" ).append( StringUtil.LN )
		
		.append( "</table>" ).append( StringUtil.LN )
		;
	}

}
