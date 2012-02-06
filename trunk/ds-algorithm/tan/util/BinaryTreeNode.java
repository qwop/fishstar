package util;

public class BinaryTreeNode {
	private Object data; // Ԫ������
	private BinaryTreeNode parent /*�ý��ĸ����*/, lChild /*�ý�������*/, rChild/*�ý����Һ���*/;




	private int height /* �Ըý��Ϊ�������ĸ߶�*/, size /*�ý��������� (������㱾��)*/;
	
	public BinaryTreeNode() {
		this( null );
	}
	
	public BinaryTreeNode( final Object e ) {
		data = e;
		parent = lChild = rChild = null;
		height = 0 ; 
		size = 1;
	}
	
	public boolean hasParent() { return null != parent; }
	public boolean hasLChild() { return null != lChild; }
	public boolean hasRChild() { return null != rChild; }
	
	public boolean isLeaf() { return !hasLChild() && !hasRChild(); }
	
	public boolean isLChild() { return hasParent() && this == parent.lChild; }
	public boolean isRChild() { return hasParent() && this == parent.rChild; }
	
	
	public int getHeight() { return height; }
	public int getSize() { return size; }
	
	/**
	 * ����ǰ�Ľ�� v ���ӷ����仯������Ҫ updateHeight �������µ�ǰ����Լ����ȵĽ��߶ȡ�
	 * һ�����ĸ߶ȷ����仯��Ӱ�쵽���ȵĽ��ĸ߶�
	 * ���������κ�һ�����ĸ߶ȶ��������������ĸ߶��д��߼�1
	 * �������ĸ߶�ֵ��Ҫ��ȡ�ý������Һ��ӵĸ߶ȼ��ɻ�á���
	 */
	public void updateHeight() {
		int newHeight = 0; // 
		if ( hasLChild() ) {
			newHeight = Math.max( newHeight, 1 + lChild.height );
		}
		
		if ( hasRChild() ) {
			newHeight = Math.max( newHeight, 1 + rChild.height );
		}
		
		if ( newHeight == height ) { return; }
		
		height = newHeight;
		
		if ( hasParent() ) parent.updateHeight();
	}
	
	/**
	 * ������ node �ĺ��ӷ����˱仯�� Ӧ�ø��µ�ǰ����Լ����ȵĹ�ģ��
	 * ���������κ� һ�����Ĺ�ģ���������������Ĺ�ģ֮�ͼ��Ͻ������
	 */
	public void updateSize() {
		size = 1;
		if ( hasLChild() ) {
			size += lChild.size;
		}
		
		if ( hasRChild() ) {
			size += rChild.size;
		}
		
		if ( hasParent() ) parent.updateSize();
	}
	
	/**
	 * �жϵ�ǰ�Ľ�� node �� ����� parent ֮��Ĺ�ϵ
	 * �޸�  node �� parent ����ָ���������Ҫ����ʱ�䡣
	 * 
	 * ���ڽ��ĺ��ӷ����˱仯�������Ҫ���� updateHeight �� updateSize
	 * ���¸���� parent �Լ����ȵĸ߶����ģ
	 */
	public void sever() {
		if ( !hasParent() ) { return; }
		
		if ( isLChild() ) {
			parent.lChild = null;
		} else if ( isRChild() ) {
			parent.rChild = null;
		}
		
		parent.updateHeight();
		parent.updateSize();
		
		parent = null;
	}
	
	/**
	 * ��� ��ǰ�Ľ�������ӣ��� �ϵ������븸���Ĺ�ϵ.
	 * ���� node ���
	 * @param node
	 * @return
	 */
	public BinaryTreeNode setLChild( final BinaryTreeNode node ) {
		BinaryTreeNode oldLC = lChild;
		if ( hasLChild() ) {
			lChild.sever();
		}
		
		if ( node != null ) {
			node.sever();
			
			lChild = node;
			node.parent = this;
			updateHeight();
			updateSize();
		}
		
		return oldLC;
	}
	
	
	
	public BinaryTreeNode setRChild( final BinaryTreeNode node )  {
		BinaryTreeNode oldRC = rChild;
		
		if ( hasRChild() ) {
			rChild.sever();
		}
		
		if ( null != node ) {
			node.sever();
			
			rChild = node;
			node.parent = this;
			updateHeight();
			updateSize();
		}
		
		return oldRC;
	}
	
	
	/**
	 * @return the parent
	 */
	public BinaryTreeNode getParent() {
		return parent;
	}

	/**
	 * @return the lChild
	 */
	public BinaryTreeNode getLChild() {
		return lChild;
	}

	/**
	 * @return the rChild
	 */
	public BinaryTreeNode getRChild() {
		return rChild;
	}
}
