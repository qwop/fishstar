package sample;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.cyberneko.html.parsers.DOMParser;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public class StyleChecker {
	private String path;
	private boolean right;
	private DOMParser parser;
	private List<String> classNameList;
	private boolean error;
	
	public void display() {
		
		if ( error ) {
			System.err.println( classNameList + "\t" +  path );
		} else {
			System.out.println( classNameList + "\t" +  path );
		}
	}
	
	public boolean isRight() {
		right = true;
		String[] arrays = classNameList.toArray( new String[]{} );
		// c_table before the [ c_title, c_name ]
	
		int size = classNameList.size();
		// "c_table", "c_title", "c_name", "c_content"
		int tableIdx = classNameList.indexOf( "c_table" ),
		titleIdx = classNameList.lastIndexOf( "c_title" ),
		nameIdx = classNameList.lastIndexOf( "c_name" ),
		contentIdx = classNameList.indexOf( "c_content" );
		
		if ( contentIdx >= 0 ) {
			// right 
			right =  
			contentIdx > nameIdx  &&
			nameIdx > titleIdx &&
			titleIdx > tableIdx;
			
			if ( nameIdx > contentIdx ||
					titleIdx > contentIdx ) {
				
				error = true; 
			}
			
		} 
			
		
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public StyleChecker(String path) {
		super();
		this.path = path;
		init(path);
	}

	private void init(String path) {
		classNameList = new ArrayList<String>();
		parser = new DOMParser();
		try {
			parser.parse( path );
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		handler( parser.getDocument() );
	}
	


	public void handler( Node node ) {
		String name = node.getNodeName();
		if ( node instanceof Element
				&& "DIV".equals( name )) {
			Element element = ( Element ) node;
			String className =  element.getAttribute("class");
			
			if ( null != className && className.trim().length() > 0 ) {
				classNameList.add( className );
			}
		}
		
		Node child = node.getFirstChild();
		while (child != null) {
			handler( child );
			child = child.getNextSibling();
		}
	}


	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
}
