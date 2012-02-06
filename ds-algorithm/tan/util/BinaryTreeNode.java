package util;

public class BinaryTreeNode {
	private Object data; // 元素数据
	private BinaryTreeNode parent /*该结点的父结点*/, lChild /*该结点的左孩子*/, rChild/*该结点的右孩子*/;




	private int height /* 以该结点为根的树的高度*/, size /*该结点的子孙树 (包括结点本身)*/;
	
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
	 * 若当前的结点 v 孩子发生变化，则需要 updateHeight 方法更新当前结点以及祖先的结点高度。
	 * 一个结点的高度发生变化，影响到祖先的结点的高度
	 * 二叉树中任何一个结点的高度都等于左右子树的高度中大者加1
	 * 左右树的高度值需要获取该结点的左右孩子的高度即可获得。。
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
	 * 如果结点 node 的孩子发生了变化， 应该更新当前结点以及祖先的规模！
	 * 二叉树中任何 一个结点的规模，等于左右子树的规模之和加上结点自身！
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
	 * 切断当前的结点 node 与 父结点 parent 之间的关系
	 * 修改  node 与 parent 结点的指针域，这个需要常数时间。
	 * 
	 * 由于结点的孩子发生了变化，因此需要调用 updateHeight 和 updateSize
	 * 更新父结点 parent 以及祖先的高度与规模
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
	 * 如果 当前的结点有左孩子，则 断掉左孩子与父结点的关系.
	 * 建立 node 结点
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
