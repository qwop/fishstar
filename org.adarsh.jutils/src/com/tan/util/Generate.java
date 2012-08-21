package com.tan.util;

import org.adarsh.jutils.preferences.PreferenceConstants;
import org.eclipse.jdt.core.IField;
import org.eclipse.jdt.core.JavaModelException;


/**
 * 生成伪代码.
 * @author TanYuanji
 *
 */
public final class Generate {
	private final static String INDENT = "\t";
	private final static String N = System.getProperty("line.separator", "\r\n");
	
	/**
	 * 根据Field类型生成对应的伪代码.		
	 * @param b 字符串 string buffer
	 * @param field Eclipse SDK 中对应的 Field 类型
	 * @param comment 对应的Field的注释
	 * @param style 样式类型 
	 * <ul>
	 * 	<li>PreferenceConstants.STR_STYLE_BY_CONTENT 样式1 vo.setAge( 2 );</li>
	 * 	<li>PreferenceConstants.STR_STYLE_BY_GET 样式2 vo.setAge( po.getAge() );</li>
	 * 	<li>PreferenceConstants.STR_STYLE3 样式3 待设定</li>
	 * </ul>
	 */
	public final static void generateDummyCode(
			final StringBuffer b, 
			final IField field,
			final String comment,
			final String style ) {
		final String name = field.getElementName();
		if (name == null || "serialVersionUID".equals(name)){
			return;
		}
		
		String typeSignature = null;
		try {
			typeSignature = field.getTypeSignature();
		} catch (JavaModelException e) {
			e.printStackTrace();
		}
		
		if (  PreferenceConstants.STR_STYLE_BY_GET.equals( style )  ) {
			generateDummyCode( b,  name, comment );
		}  else if (  PreferenceConstants.STR_STYLE_SYSOUT.equals( style )  ) {
			generateDummyCode( b,  name, comment );
		} else if ( null != typeSignature ) {
			// 样式1 根据类型创建假数据
			generateDummyCode( b,  name, comment , StringUtil.getDummyField( typeSignature , comment, name ));
		} else {
			generateDummyCode( b,  name, comment );
		}
		
	}
	
	
	private final static void generateDummyCode(
			final StringBuffer b, 
			final String name,
			final String comment,
			final String ... dummys) {
		if (name == null || "serialVersionUID".equals(name)){
			return;
		}
		final String methodSuffix =
				Character.toUpperCase(name.charAt(0)) + 
				name.substring(1), dummy;
		
		if ( null == dummys || dummys.length == 0  || dummys[0] == null ) {
			dummy = "src.get" + methodSuffix + "()";
		} else {
			dummy = dummys[0];
		}
		
		StringUtil.append(b,
				INDENT, "// 设置", comment , N,
				INDENT,"dest.set" , methodSuffix,'(',dummy,");", N
		);
	}
	
	public final static void generateDummyGetter(final StringBuffer b, final String name,
			final String comment, final String style ) {
		if (name == null || "serialVersionUID".equals(name)){
			return;
		}
		String methodSuffix = Character.toUpperCase(name.charAt(0)) +  name.substring(1);
		
		if (  PreferenceConstants.STR_STYLE_SYSOUT.equals( style )  ) { // sysout
			StringUtil.append(b,
					INDENT , "// 获取" , comment , ".",  N  ,
					INDENT , "System.out.printf( \"%-20s %-10s\\n\", \"", 
						comment, "\\t\" , src.get" , methodSuffix, "()" +
							" );"  , N
					);
		} else {
			StringUtil.append(b,
					INDENT , "// 获取" , comment ,  ".", N  ,
					INDENT , "src.get" , methodSuffix  , "();"  , N
					);
		}
	}

	public final static void generateGetterSetter(final StringBuffer b, final String name,
			final String comment) {
//		String name = field.getElementName();
//		int type = field.getElementType();
		if (name == null || "serialVersionUID".equals(name)){
			return;
		}
		String methodSuffix = Character.toUpperCase(name.charAt(0)) +  name.substring(1);
		StringUtil.append(b,
				 INDENT + "// 获取" + comment + N + 
				 INDENT + "public Object get" + methodSuffix + "() {" + N + 
				 INDENT + INDENT + "return this." + name + ";" + N + 
				 INDENT + "}"+ N + N
		);
		
//		String methodSuffix = Character.toUpperCase(name.charAt(0)) +  name.substring(1);
		b.append(INDENT + "// 设置" + comment + N + 
				INDENT + "public Object set" + methodSuffix + "(final Object " + name + ") {" + N + 
				 INDENT + INDENT + "this." + name + "=" + name + ";" + N + 
				 INDENT + "}"+ N 
		);
	}
	
	/**
	 * 构造对象
	 * @param b
	 * @param javaName
	 */
	public final static void generateDummyObjects(final StringBuffer b, final String javaName) {
		b.append(INDENT + javaName  + " dest = " + "new " + javaName + "();" + N);
		b.append(INDENT + javaName  + " src = " + "new " + javaName + "();" + N);
	}
	
	
	public static void main(String[] args) {
		StringBuffer b = new StringBuffer();
//		generateDummyGetter( b, "name", "姓名" );
		generateDummyCode( b, "name", "姓名" , "张三" );
		System.out.println(b);
	}
}
