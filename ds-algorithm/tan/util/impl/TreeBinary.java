/**
 * 
 */
package util.impl;

import java.util.List;

import util.BinaryTreeNode;
import util.Iterator;
import util.Stack;
import util.Tree;
import util.TreeNode;

/**
 * @author dolphin
 *
 * 2012-2-6 下午3:57:10
 */
public class TreeBinary implements Tree {
	private BinaryTreeNode root;
	/* 
	 * @see util.Tree#size()
	 */
	@Override
	public int size() {
		
		return 0;
	}

	/* 
	 * @see util.Tree#getRoot()
	 */
	@Override
	public TreeNode getRoot() {
		
		return null;
	}

	/* 
	 * @see util.Tree#getParent(util.TreeNode)
	 */
	@Override
	public TreeNode getParent(TreeNode node) {
		
		return null;
	}

	/* 
	 * @see util.Tree#getFirstChild(util.TreeNode)
	 */
	@Override
	public TreeNode getFirstChild(TreeNode node) {
		
		return null;
	}

	/* 
	 * @see util.Tree#getNextSibling(util.TreeNode)
	 */
	@Override
	public TreeNode getNextSibling(TreeNode node) {
		
		return null;
	}

	/* 
	 * @see util.Tree#getHeight(util.TreeNode)
	 */
	@Override
	public int getHeight(TreeNode node) {
		
		return 0;
	}

	/* 
	 * @see util.Tree#insertChild(util.TreeNode, util.TreeNode)
	 */
	@Override
	public void insertChild(TreeNode node, TreeNode child) {
		

	}

	/* 
	 * @see util.Tree#deleteChild(util.TreeNode, int)
	 */
	@Override
	public void deleteChild(TreeNode node, int i) {
		

	}
	////////////////// 先序遍历,递归实现 //////////////////
	/* 
	 * @see util.Tree#preOrder()
	 */
	@Override
	public Iterator preOrder() {
		List list = new ListDoubleLinked();
		
		preOrderResursion( this.root, list );
		
		return null;
	}

	private void preOrderResursion(BinaryTreeNode root, List list) {
		if ( root == null ) { return ; }
		
		list.add( root );
		
		preOrderResursion( root.getLChild(), list );
		preOrderResursion( root.getRChild(), list );
	}
	////////////////// 先序遍历,递归实现 //////////////////
	
	////////////////// 先序遍历,非递归实现 //////////////////
	private List preOrderTraverse( BinaryTreeNode root ) {
		if ( root == null ) { return null; }
		List list = new ListDoubleLinked();
		BinaryTreeNode parent = root;
		Stack s = new StackSingleLinked();
		while ( parent != null ) {
			while ( null != parent ) {
				
				list.add( parent );
				
				if ( parent.hasRChild() ) { s.push( parent.getRChild() ); }
				
				parent = parent.getLChild();
			}
			
			if ( !s.isEmpty() ) {
				parent = ( BinaryTreeNode ) s.pop(); // 右子树
			}
		}
		
		return list;
	}
	
	////////////////// 先序遍历,非递归实现 //////////////////
	

	/* 
	 * @see util.Tree#postOrder()
	 */
	@Override
	public Iterator postOrder() {
		
		return null;
	}

	/* 
	 * @see util.Tree#levelOrder()
	 */
	@Override
	public Iterator levelOrder() {
		
		return null;
	}

}
