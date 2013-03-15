package com.tan.vad.actions;

import org.eclipse.jdt.core.IField;
import org.eclipse.jdt.core.JavaModelException;

import com.tan.util.Generate;
import com.tan.util.StringUtil;
import com.tan.vad.AbstractGenCodeVAD;

public class GenDestoryByNull extends AbstractGenCodeVAD {
	/**

	 生成如下代码
	// 3 wrap method.
	public static void main( String[] args ) {
	 	// 1. define
		Vo dest = new Vo();
		Vo src = new Vo();
		
		// 2. variables
		// 获取姓名.
		dest.getName(  );
	}
	 */
	
	@Override
	public void define(String javaName) {
		
	}

	@Override
	public void variable(IField field, String comment) {
		
		String suffix = "";
		
//		if ( field.getDeclaringType().)
		
		if ( null != field ) {
			String signature = null;
			try {
				signature = field.getTypeSignature();
			} catch (JavaModelException e) {
				e.printStackTrace();
			}
			
			String dummy = StringUtil.getDummyNull( signature );
			if ( null == dummy ) return;
			
			suffix = "\t=\t" + dummy + ";";
			
			StringUtil.append(
					code,
					Generate.INDENT , "// 设置" , comment ,  ".", Generate.N,
					Generate.INDENT , field.getElementName(), suffix  , Generate.N
			);
		}
	}

	@Override
	public void wrap() {
		code.insert(0, "public void dummyDestory() {" + Generate.N  );
				  // code 
		code.append( "}" + Generate.N );
	}

	
	public void testGenMethodCode() {
		code = new StringBuffer();
		
		define( "JavaName" );
		
		variable( null, "comment" );
		
		wrap();
		
		System.out.println( code );
	}
	
	public static void main(String[] args) {
		GenDestoryByNull gen = new GenDestoryByNull();
		
		gen.testGenMethodCode();
	}

}
