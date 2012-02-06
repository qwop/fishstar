package util;

/**
 * 二叉树接口 二叉树: 
 * 	二叉树或是一棵空树，
 *  或者是一棵由根节点和两棵互不相交 的分别
 *  称为根的左子树和右子树的子树所组成的非空树
 * 
 * 每个结点的孩子数只能 0、1、2个。
 * 	并且每个孩子都有左右之分。。
 *  位于左边的孩子称为左孩子
 *  位于右边的孩子成为右孩子
 * 
 * 左孩子为根的子树为左子树 右孩子为根的子树为右子树
 * 性质
 * @author dolphin
 * 
 *         2012-2-2 下午5:19:29
 */
public interface BinaryTree {
	/**
	 * 返回二叉树的结点数
	 * 
	 * @return
	 */
	int size();

	/**
	 * 判断二叉树是否为空
	 * 
	 * @return
	 */
	boolean isEmpty();

	/**
	 * 获取根节点
	 * 
	 * @return
	 */
	TreeNode getRoot();

	/**
	 * 返回二叉树的高度
	 * 
	 * @return
	 */
	int getHeight();

	/**
	 * 找到数据元素，所在的结点，若e不存在，则返回为空
	 * 
	 * @param data
	 * @return
	 */
	TreeNode find(Object e);

	// 先序遍历二叉树
	Iterator preOrder();

	// 中序遍历二叉树
	Iterator inOrder();

	// 后序遍历二叉树
	Iterator postOrder();

	// 按层遍历二叉树
	Iterator levelOrder();
}
