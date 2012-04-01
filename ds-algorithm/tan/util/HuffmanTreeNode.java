package util;

/**
 * @author dolphin
 *
 * Mar 22, 2012 11:00:03 AM
 */
public class HuffmanTreeNode extends BinaryTreeNode {
	

	private int		weight;		// È¨Öµ
	private String	coding	= "";	// ±àÂë
	
	public HuffmanTreeNode( int weight ) { this( weight, null ); }

	public HuffmanTreeNode( int weight, Object e ) {
		super( e );
		this.weight = weight;
	}
	
	
	/**
	 * @return the weight
	 */
	public int getWeight() {
		return weight;
	}

	/**
	 * @return the coding
	 */
	public String getCoding() {
		return coding;
	}

	/**
	 * @param weight the weight to set
	 */
	public void setWeight( int weight ) {
		this.weight = weight;
	}

	/**
	 * @param coding the coding to set
	 */
	public void setCoding( String coding ) {
		this.coding = coding;
	}
}
