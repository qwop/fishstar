package util;

public class TreeNode {
	private Object data;
	/**
	 * @return the data
	 */
	public Object getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(Object data) {
		this.data = data;
	}

	/* 父结点 */
	private TreeNode parent;
	/* 左孩子 */
	private TreeNode lChild;
	/* 右孩子 */
	private TreeNode rChild;
	/* 以该结点为根的子树的高度 */
	private int height;
	/* 该结点子孙数（包括结点本身） */
	private int size;
	
	public TreeNode() {
		this( null );
	}
	
	public TreeNode( final Object e ) {
		data = e;
		parent = lChild = rChild = null;
		height = 0;
		size = 1;
	}
	
	
	/******辅助方法,判断当前结点位置情况******/
	//判断是否有父亲
	public boolean hasParent() { return null != parent; }
	//判断是否有左孩子
	public boolean hasLChild() { return null != lChild; }
	//判断是否有右孩子
	public boolean hasRChild() { return null != rChild; }
	//判断是否为叶子结点
	public boolean isLeaf() { return !hasLChild() && !hasRChild() ; }
	//判断是否为某结点的左孩子
	public boolean isLChild() { return hasParent() && this == parent.lChild ; }
	//判断是否为某结点的右孩子
	public boolean isRChild() { return hasParent() && this == parent.rChild; }

	/******Getter/ Setter******/
	/**
	 * @return the parent
	 */
	public TreeNode getParent() {
		return parent;
	}

	/**
	 * @return the lChild
	 */
	public TreeNode getlChild() {
		return lChild;
	}

	/**
	 * @return the rChild
	 */
	public TreeNode getrChild() {
		return rChild;
	}

	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @return the size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * @param parent the parent to set
	 */
	public void setParent(TreeNode parent) {
		this.parent = parent;
	}

	/**
	 * @param lChild the lChild to set
	 */
	public void setlChild(TreeNode lChild) {
		this.lChild = lChild;
	}

	/**
	 * @param rChild the rChild to set
	 */
	public void setrChild(TreeNode rChild) {
		this.rChild = rChild;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * @param size the size to set
	 */
	public void setSize(int size) {
		this.size = size;
	}
}
