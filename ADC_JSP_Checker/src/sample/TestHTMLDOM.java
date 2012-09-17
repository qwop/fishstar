package sample;

import org.cyberneko.html.parsers.DOMParser;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class TestHTMLDOM {
	public static void main(String[] argv) throws Exception {
		
		argv = new String[] {
//				"D:\\bea\\user_projects\\workspaces\\default\\adc_op\\WebContent\\dd\\ectype_yearincome_add.jsp",
//				"D:\\bea\\user_projects\\workspaces\\default\\adc_op\\WebContent\\dd\\othertype_area.jsp",
//				"D:\\bea\\user_projects\\workspaces\\default\\adc_op\\WebContent\\dd\\marketconditiontypemanage.jsp",
//				"D:\\bea\\user_projects\\workspaces\\default\\adc_op\\WebContent\\dd\\marketconditiontypeedit.jsp",
				"D:\\bea\\user_projects\\workspaces\\default\\adc_op\\WebContent\\dd\\ectype_yearincome_modify.jsp"	
		};
		
		DOMParser parser = new DOMParser();
		for (int i = 0; i < argv.length; i++) {
			parser.parse(argv[i]);
			print("", parser.getDocument());
		}
	}

	public static void print( String indent, Node node ) {
		
		if ( node instanceof Element) {
			Element element = ( Element ) node;
			
			System.out.println( element.getAttribute("class"));
		}
		
		Node child = node.getFirstChild();
		while (child != null) {
			print( indent + " ", child );
			child = child.getNextSibling();
		}
	}
}