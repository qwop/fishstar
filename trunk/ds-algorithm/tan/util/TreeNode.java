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

	/* ����� */
	private TreeNode parent;
	/* ���� */
	private TreeNode lChild;
	/* �Һ��� */
	private TreeNode rChild;
	/* �Ըý��Ϊ���������ĸ߶� */
	private int height;
	/* �ý����������������㱾�� */
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
	
	
	/******��������,�жϵ�ǰ���λ�����******/
	//�ж��Ƿ��и���
	public boolean hasParent() { return null != parent; }
	//�ж��Ƿ�������
	public boolean hasLChild() { return null != lChild; }
	//�ж��Ƿ����Һ���
	public boolean hasRChild() { return null != rChild; }
	//�ж��Ƿ�ΪҶ�ӽ��
	public boolean isLeaf() { return !hasLChild() && !hasRChild() ; }
	//�ж��Ƿ�Ϊĳ��������
	public boolean isLChild() { return hasParent() && this == parent.lChild ; }
	//�ж��Ƿ�Ϊĳ�����Һ���
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
