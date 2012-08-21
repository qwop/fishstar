package com.tan.util;

import java.text.MessageFormat;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import org.eclipse.jdt.core.Signature;

public final class StringUtil {
	static Pattern singleCommentPattern = Pattern.compile(
			"//([ \\t\\f]*)\\w+\\.release\\s*\\(\\s*\\)", Pattern.DOTALL),
			multipleCommentPattern = Pattern.compile(
					"/\\*\\s*\\w+\\.release\\s*\\(\\s*\\).+\\*/",
					Pattern.DOTALL);
	public static final Object LN = System.getProperty( "line.separator", "\r\n" );
	
	public static final String INDENT_REG = "(?m)^";
	
	public static final String INDENT1 = "	";
	public static final String INDENT2 = "		";
	public static final String INDENT3 = "			";
	public static final String INDENT4 = "				";
	
	public static boolean isRightReleaseCode(final String sourceCode) {
		boolean isSingle = singleCommentPattern.matcher(sourceCode).find(), isMultiple = multipleCommentPattern
				.matcher(sourceCode).find();
		return !isSingle && !isMultiple;
	}
	
	public static void appendln( StringBuffer buf, Object[] args ) {
		for ( int i = 0; i < args.length; i++) {
			buf.append( args[i] ).append( LN );
		}
	}
	/**
	 * 判断字符串是否为空.
	 * 
	 * @param v
	 * @return
	 */
	public final static boolean isEmpty( final String v ) {
		return v == null || v.trim().length() == 0;
	}

	public final static void append(final StringBuffer b, final Object[] args) {
		if (null != args && args.length > 0) {
			for (int i = 0; i < args.length; i++) {
				b.append(args[i]);
			}
		}
	}

	/**
	 * Not null.
	 * 
	 * @param v
	 * @return
	 */
	public final static boolean isNotNull( final String v) {
		return v != null && v.trim().length() != 0
				&& !"null".equals(v.trim().toLowerCase());
	}

	/**
	 * 判断字符串是否不为空.
	 * 
	 * @param v
	 * @return
	 */
	public final static boolean isNotEmpty(String v) {
		return v != null && v.trim().length() != 0;
	}

	/**
	 * 判断是否为数字.
	 * 
	 * @param v
	 * @return
	 */
	public static boolean isNumber(String v) {
		if (isEmpty(v))
			return false;
		char[] vs = v.toCharArray();
		for (int i = 0; i < vs.length; i++)
			if (vs[i] < 48 || vs[i] > 57)
				return false;
		return true;
	}

	/**
	 * 判断是否为空白字符. 包括 { '\u0009'，水平制表符。 ' '，换行。 '\u000B'，纵向制表符。 '\u000C'，换页。 '
	 * '，回车。 '\u001C'，文件分隔符。 '\u001D'，组分隔符。 '\u001E'，记录分隔符。 '\u001F'，单元分隔符。 }
	 * 
	 * @param v
	 * @return
	 */
	public static boolean isWhitespace(String v) {
		if (v == null)
			return false;
		char[] vs = v.toCharArray();
		for (int i = 0; i < vs.length; i++)
			if (!Character.isWhitespace(vs[i]))
				return false;
		return true;
	}

	/**
	 * 过滤字符. Example:<br>
	 * 
	 * <pre>
	 * 	value :"a", "b"
	 *  oldChar: "
	 *  newChar: '
	 *  return : 'a', 'b'
	 * </pre>
	 * 
	 * @param value
	 *            要过滤的值.
	 * @param oldChar
	 *            需要过滤的字符.
	 * @param newChar
	 *            将要替换的字符.
	 * @return
	 */
	public static String filter(String value, char oldChar, char newChar) {
		char[] chars = value.toCharArray();
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < chars.length; i++) {
			if (chars[i] == oldChar)
				buf.append(newChar);
			else
				buf.append(chars[i]);
		}
		return buf.toString();
	}

	/**
	 * 过滤字符. "A".replace('A', 'B');
	 * 
	 * @param value
	 * @param oldChar
	 * @param newChar
	 * @return
	 */
	public static String filter0(String value, char oldChar, char newChar) {
		char[] chars = value.toCharArray();
		char[] results = new char[chars.length];
		for (int i = 0; i < chars.length; i++) {
			if (chars[i] == oldChar)
				results[i] = newChar;
			else
				results[i] = chars[i];
		}
		return new String(results, 0, results.length);
	}

	/**
	 * 过滤字符串. 类似 String.replaceAll("oldString", "newString");
	 * 
	 * @param value
	 *            .
	 * @param oldStr
	 *            .
	 * @param newStr
	 *            .
	 * @return
	 */
	public static String filter(String value, String oldStr, String newStr) {
		StringBuffer buf = new StringBuffer();
		int start = 0;
		int index = value.indexOf(oldStr);
		int length = oldStr.length();
		int lastIndex = value.lastIndexOf(oldStr);
		String last = value.substring(lastIndex + length);
		while (index >= 0) {
			buf.append(value.substring(start, index)).append(newStr);
			value = value.substring(start + index + length);
			index = value.indexOf(oldStr);
		}
		buf.append(last);
		return buf.toString();
	}

	public static String getString(String basename, String key) {
		ResourceBundle bundle = ResourceBundle.getBundle(basename);
		return bundle.getString(key);
	}

	public static String getString(String basename, String key, Object[] args) {
		ResourceBundle bundle = ResourceBundle.getBundle(basename);
		String message = bundle.getString(key);
		return MessageFormat.format(message, args);
	}

	public final static Object getInstanceSentence(final String owner) {
		return owner + " start = new " + owner + "();";
		// return owner + ' ' + owner.toLowerCase() + " = new " + owner + "();";
	}
	
	/**
	 * 获取指定字段的注释内容， 过滤掉相关注释符号.
	 * @param fieldName		字段名称.
	 * @param string		包含注释的java字段注释.
	 * @param isJavaDoc		是否为文档注释
	 * @return
	 */
	public static String getComment(final String fieldName, String string,
			boolean isJavaDoc) {
		if (isEmpty(string)) {
			return null;
		}
		string = string.trim();
		if (isJavaDoc) { // JavaDoc注释
			if (string.startsWith("/*")) {
				string = string.substring(2);
			} else if (string.startsWith("/**")) {
				string = string.substring(3);
			}// 删除起始标记. /* 或者 /**

			if (string.endsWith("*/")) {
				string = string.substring(0, string.length() - 2);
			} else if (string.endsWith("**/")) {
				string = string.substring(0, string.length() - 3);
			}// 删除结束标记 */ 或者 **/
			string =  string.replaceAll("\\*", "").trim(); // 过滤掉 * 字符.
			
		}
		else {
			int lineIdx = string.indexOf("\r\n");
			if (lineIdx < 0) {
				lineIdx = string.indexOf("\n");
			}// 获取回车换行位置。
			int commentStart = string.indexOf("//"); // 是否有// 标记
			
			if ( commentStart == 0 ) {// 以//开头.
				if (lineIdx > 0) {
					string = string.substring(2, lineIdx).trim();
				} else {
					string = string.substring(2).trim();
				}
			} else if ( commentStart > 0 ) { // 注释// 不是处于标记开头
				string = string.substring(commentStart + 2).trim();
			}  else if ( commentStart < 0 ) {
				// not found the comment //
				string = fieldName;
			}
		}
		
		string = string.trim();
		
		if ( string.length() > 1 ) {
			
			string = filterJavaDoc( string );
			
			string = trimQuot( string );
			
			string = delLastSymbol( string );
			
		}
		
		
		if ( isEmpty( string ) ) {
			// 未找到 注释//标记.
			if (!isEmpty(fieldName)) {
				return fieldName;
			}
			return "**未找到注释/字段**";
			
		}
		
		return string;
	}
	
	/**
	 * 删除最后一个符号
	 * @param string
	 * @return
	 */
	public static String delLastSymbol(String string) {
		if ( null != string && string.length() > 1) {
			int startIdx = 0, endIdx = string.length() - 1;
			char lstChar = string.charAt( endIdx );
			switch (lstChar) {
			case '.':
			case '，':
			case '、':
			case '；':
			case '：':
			case '·':
			case 'ˉ':
			case 'ˇ':
			case '‘':
			case '’':
			case '“':
			case '”':
			case '＂':
			case '＇':
			case '｀':
			case '〃':
			case '。': {
				return string.substring(startIdx, endIdx);
				}
			}
			
		}
		return string;
	}

	/**
	 * 过滤字符串. 类似 String.replace("replaceMent", "content");
	 * 
	 * @param value
	 *            .
	 * @param replaceMent
	 *            .
	 * @param content
	 *            .
	 * @return
	 */
	public static String replace(String value, String replaceMent,
			String content) {
		if (isEmpty(value)) {
			return "";
		}
		int idx = value.indexOf(replaceMent);
		if (idx >= 0) {
			return value.substring(0, idx)
					+ value.substring(idx + replaceMent.length());
		}
		return "";
	}

	/**
	 * 根据 Field 的 type 签名生成对应的伪代码.
	 * 
	 * @param typeSignature
	 * @param strings
	 * @return
	 */
	public static String getDummyField( String typeSignature, String[] strings ) {
		if ( isEmpty( typeSignature ) ) {
			return null;
		}
		if ( typeSignature.length() == 1 ) { // 原生类型  primitive type.
			char c = typeSignature.charAt( 0 );
			
			switch (c) {
			case Signature.C_BOOLEAN: {
				return " false ";
			}
			case Signature.C_BYTE: {
				return " (byte) 1 ";
			}
			case Signature.C_CHAR: {
				return " \'A\' ";
			}
			case Signature.C_DOUBLE: {
				return " 1d ";
			}
			case Signature.C_FLOAT: {
				return " 1f ";
			}
			case Signature.C_INT: {
				return " 1 ";
			}
			case Signature.C_LONG: {
				return "1L";
			}
			case Signature.C_SHORT: {
				return " (short) 2 ";
			}
			case Signature.C_COLON: {
				return " : ";
			}
			case Signature.C_VOID: {
				return "";
			}
			case Signature.C_TYPE_VARIABLE: {
				return "";
			}
			case Signature.C_STAR: {
				return "";
			}
			case Signature.C_EXCEPTION_START: {
				return "";
			}
			case Signature.C_EXTENDS: {
				return "";
			}
			case Signature.C_SUPER: {
				return "";
			}
			case Signature.C_DOT: {
				return "";
			}
			case Signature.C_DOLLAR: {
				return "";
			}
			case Signature.C_ARRAY: {
				return "";
			}
			case Signature.C_RESOLVED: {
				return "";
			}
			case Signature.C_UNRESOLVED: {
				return "";
			}
			case Signature.C_NAME_END: {
				return "";
			}
			case Signature.C_PARAM_START: {
				return "";
			}
			case Signature.C_PARAM_END: {
				return "";
			}
			case Signature.C_GENERIC_START: {
				return "";
			}
			case Signature.C_GENERIC_END: {
				return "";
			}
			case Signature.C_CAPTURE: {
				return "";
			}
			default:
				return "";
			}
		} else if (typeSignature.charAt(0) == 'Q') { // 对象类型
			typeSignature = typeSignature.replaceAll(";", "").replaceAll("<Q",
					"<");
			String type = typeSignature.substring(1);
			if ("String".equals(type)) {
				if (null != strings && strings.length > 0) {
					if (!isEmpty(strings[0])) {
						return " \"" + strings[0] + "\" ";
					} else if (strings.length > 1 && !isEmpty(strings[1])) {
						return " \"" + strings[1] + "\" ";
					}
				}
				return " \"string\" ";
			} else if ( "Integer".equals( type ) ) {
				return " Integer.valueOf(1) ";
			} else if ( "Double".equals( type ) ) {
				return " Double.valueOf( 1d ) ";
			} else if ( "Float".equals( type ) ) {
				return " Float.valueOf( 1f ) ";
			} else if ( "Short".equals( type ) ) {
				return " Short.valueOf( (short) 1 ) ";
			} else if ( "Boolean".equals( type ) ) {
				return " Boolean.valueOf( false ) ";
			} else if ( "Long".equals( type ) ) {
				return " Long.valueOf( 1L ) ";
			} else if ( "Character".equals( type ) ) {
				return " Character.valueOf( 'A' ) ";
			} else if ( "Byte".equals( type ) ) {
				return " Byte.valueOf( (byte) 1 ) ";
			} else if ("BigInteger".equals(type)) {
				return " new BigInteger( \"100\" ) ";
			}else if ( "BigDecimal".equals( type ) ) {
				return " new BigDecimal( \"100\" ) ";
			} else if (type.indexOf('<') >= 0 && type.indexOf('>') >= 0) {
				int idx1 = type.indexOf('<'), idx2 = type.indexOf('>');

				if (type.indexOf("List<") >= 0) {
					return " new ArrayList" + type.substring(idx1, idx2 + 1)
							+ "() ";
				} else if (type.indexOf("Set<") >= 0) {
					return " new HashSet" + type.substring(idx1, idx2 + 1)
							+ "() ";
				} else if (type.indexOf("Map<") >= 0) {
					String str = type.substring(idx1, idx2 + 1).replace('Q',
							',');
					return " new HashMap" + str + "() ";
				}

			}
			return " new " + type + "() ";
		} else if (typeSignature.charAt(0) == '[') { // primitive 数组类型
			switch (typeSignature.charAt(1)) {
			case Signature.C_BOOLEAN: {
				return " new boolean[]{ true, false } ";
			}
			case Signature.C_BYTE: {
				return " new byte[]{ 1, 2 } ";
			}
			case Signature.C_CHAR: {
				return " new char[]{ 1, 2 } ";
			}
			case Signature.C_DOUBLE: {
				return " new double[]{ 1d,2d } ";
			}
			case Signature.C_FLOAT: {
				return " new float[]{ 1f,2f }  ";
			}
			case Signature.C_INT: {
				return " new int[]{ 1,2 }  ";
			}
			case Signature.C_SHORT: {
				return " new short[]{1,2}  ";
			}
			}
		}
		return null;
	}

	public static String trimQuot(String value) {
		if ( isEmpty( value ) ) {
			return value;
		}
		char c = value.charAt( 0 ) ;
		int len = value.length();
		int beginIndex = 0, endIndex = len ;
		if ( isQuot( c ) ) {
			beginIndex = 1;
		}
		
		c = value.charAt( len - 1 ) ;
		if ( isQuot( c ) ) {
			endIndex = len - 1;
		}
		return value.substring( beginIndex, endIndex );
	}

	static char[] QUOTS = { 
		'\'', '\"', '“',
		'”', '‘', '’',
		'〝', '〞', '＂'
	};
	
	private static boolean isQuot(char c) {
		for ( int i = 0; i < QUOTS.length; i++ ) {
			if ( c == QUOTS[i] ) {
				return true;
			}
		}
		return false;
	}

	public static String filterJavaDoc( String comment ) {
		if ( !isEmpty( comment ) ) {
			String replaced = null;
			try {
				replaced = comment.replaceAll( "@(\\w+)([@\\w\\s])+", "" );
			} catch ( Throwable e ) {
			}
			if ( null != replaced ) {
				return replaced.trim() ;
			}
			return comment;
		}
		return null;
	}
	
	public static void main( String[] args ) {
		System.out.println( 
				filterJavaDoc( "fuck\n" +
						"\t  @param args\n" + 
						"\t  @throws Exception" )
 ) ;
	}

	public static void appendln(StringBuffer buf, String string) {
		buf.append( string );
	}

}
