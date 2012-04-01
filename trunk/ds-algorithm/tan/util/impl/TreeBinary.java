/**
 * 
 */
package util.impl;

import java.util.List;

import util.BinaryTreeNode;
import util.Iterator;
import util.Queue;
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
	/**
	 * 以一棵树根节点 root 为参数
	 * 如果 root 为空 则直接返回
	 * 否则 parent 指向 root， 先序遍历以 parent 为根的树
	 * 
	 * 内层循环中，沿着结点 parent 一直向左走， 沿途访问经过的根节点，
	 * 并且将根节点的非空右子树入栈。直到 parent 为空。
	 * 
	 * 在外层循环中，此时应当取出沿途最后碰到的非空右子树根，栈顶结点 ( 以 parent 指向 )
	 * 继续遍历这棵 parent 指向的子树。如果堆栈为空，则表示再没有右子树需要遍历
	 * 此时结束外层循环，完成了整棵树的先序遍历
	 * 
	 * @param root
	 * @return
	 */
	private List preOrderTraverse( BinaryTreeNode root ) {
		if ( root == null ) { return null; }
		List list = new ListDoubleLinked();
		BinaryTreeNode parent = root;
		Stack s = new StackSingleLinked();
		while ( null != parent ) {
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
	

	////////////////// 中序遍历,非递归实现 //////////////////
	/**
	 * 如果为根节点空值 则返回
	 * 否则 parent 结点指向 root, 以终须遍历 parent 为根的树。
	 * 内层循环中，沿着根节点沿途将根节点入栈，直到 parent 为空。
	 * 此时应当取出上一层跟几点访问之，然后转向该根节点的右子树进行中序遍历。
	 * 如果堆栈和parent 都为空，则说明没有更多的子树需要遍历，此时结束外层循环
	 * @param root
	 * @return
	 */
	public List inOrder( BinaryTreeNode root ) {
		if ( null == root ) { return null; }
		List list = new ListDoubleLinked();
		Stack stack = new StackSingleLinked();
		BinaryTreeNode parent = root;
		while ( null != parent || !stack.isEmpty() ) {
			while ( null != parent ) {
				stack.push( parent );
				parent = parent.getLChild();
			}
			
			if ( !stack.isEmpty() ) {
				parent = ( BinaryTreeNode ) stack.pop();
				list.add( parent );
				parent = parent.getRChild();
			}
		}
		return list;
	}
	////////////////// 中序遍历,非递归实现 //////////////////
	
	
	////////////////// 后序遍历,非递归实现 //////////////////
	/**
	 * 以root根节点作为参数，如果root为空则返回null
	 * 否则将parent指向root，
	 * 后续遍历以parent为根的树
	 * 
	 * 第一个while循环中，沿着根节点parent向左子树深入,如果左子树为空则向右子树深入
	 * 栈顶结点为叶子或者无右子树的单分支节点。
	 * 
	 * 访问parent后，说迷你个以parent为根的子树访问完毕，判断是否为其父结点的右孩子（当前栈顶为其父结点）
	 * 如果是，则说明只要访问其父亲就可以完成对  parent 的父亲为根的子树的遍历
	 * 内层第二个while循环完成；如果不是，则转向父结点的右子树继续后序遍历。
	 * 
	 * 
	 * @param root
	 * @return
	 */
	public List postOrder( BinaryTreeNode root ) {
		if ( null == root ) { return null; }
		List list = new ListSingleLinked();
		Stack s = new StackSingleLinked();
		BinaryTreeNode parent = root;
		while ( null != parent || !s.isEmpty() ) {
			while ( null != parent ) { // 先左后右不断深入
				s.push( parent );		// 将根节点入栈
				if ( parent.hasLChild() ) { parent = parent.getLChild(); }
				else { parent = parent.getRChild(); }
			}
			
			if ( !s.isEmpty() ) {
				parent = ( BinaryTreeNode ) s.pop();  // 取出栈顶 根节点访问
				list.add( parent );
			}
			
			// 满足条件 说明镇定根节点右子树已访问，应出栈访问
			while ( !s.isEmpty() && ( ( BinaryTreeNode ) s.peek() ).getRChild() == parent ) {
				parent = ( BinaryTreeNode ) s.pop();
				list.add( parent );
			}
			
			// 转向栈顶根节点右子树访问，继续后续遍历
			if ( !s.isEmpty() ) { parent = ( ( BinaryTreeNode ) s.peek() ).getRChild(); }
			else { parent = null; }
		}
		return list;
	}
	////////////////// 后序遍历,非递归实现 //////////////////
	

	////////////////// 层次遍历,非递归实现 //////////////////
	public List levelOrder( BinaryTreeNode root ) {
		if ( null == root ) { return null; }
		List list = new ListSingleLinked();
		Queue q = new QueueArray();
		q.enqueue( root );
		
		BinaryTreeNode parent;
		while ( !q.isEmpty() ) {
			parent = ( BinaryTreeNode ) q.dequeue();
			list.add( parent );
			
			if ( parent.hasLChild() ) {
				q.enqueue( parent.getLChild() );
			}
			
			if ( parent.hasRChild() ) {
				q.enqueue( parent.getRChild() );
			}
		}
		return null;
	}
	////////////////// 层次遍历,非递归实现 //////////////////

    ////////////////// 查找元素，返回结点 //////////////////
    public BinaryTreeNode find( Object e ) {
        return search( root, e );
    }

    private BinaryTreeNode search(BinaryTreeNode root, Object e) {
        if ( root == null ) return null;
        if ( root.equals( e ) ) { return root;}
        BinaryTreeNode v = search( root.getLChild(), e ); // 否者在左子树中找
        if ( v == null ) 
            v = search( root.getRChild(), e); // 没找到，在右子树中找
        return v;
    }


    ////////////////// 查找元素，返回结点 //////////////////
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
