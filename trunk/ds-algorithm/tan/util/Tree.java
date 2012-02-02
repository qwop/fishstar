package util;


/**
 * 树 ( Tree ) : 树一个集合以及在该集合上定义的一种关系构成
 * 结点 ( Node ) : 集合中的元素为结点
 * 父子关系 ( Relation ) : 树的定义关系,在 树的结点之间建立一个层次结构。
 * 根节点( Root ) : 具有特殊地位的结点
 * 
 * 树 ( Tree ) 是  n ( n >= 0 ) 个结点的有限集合
 * 1) 一棵空树 ( n === 0 ) ， 空树中不包含任何的结点
 * 2) 非空树 ( n > 0 ), 此时又且仅有一个特定的称为根 ( root ) 的结点
 *    当 n > 1, 其余的结点可分为 m ( m > 0 ) 个互不相交的有限集
 *    每一个本身又是一棵树，为 根的子树 ( sub tree )
 *    
 * 层次 ( level ): 根开始定义
 * 层次为 0 的结点 为根节点。
 * 子树的根的层次 为 1
 * 若结点在 L 层,其子树的根节点层次为 ( L + 1 ) 层   
 * 
 * 对于层次为 k ( k > 0 ) 的每个结点 c， 都有且仅有一个层次为  (k - 1) 的结点 p 对应
 * 父结点 ( parent ) : p 为  c 的父结点 
 * 孩子 ( child ) : c 为  p 的孩子结点
 * 根结点没有父亲，其余的结点只有一个父结点，但是却可能有多个孩子，同一结点的孩子相互称为 兄弟 ( sibling ) 
 * 深度 ( Depth ) : 树中结点中最大层次数。
 * 高度 ( Height ) : 
 * 度  ( Degree ) : 结点 拥有的子树的数目 
 * 叶子 ( Leaf ) : 度 为 0 的结点 或者 是终端结点
 * 分支结点 / 非终端结点: 度不为0 的结点
 * 除根之外的分支结点内部结点
 * 
 * 树中的结点数目 == 树的边数之和 + 1 == 结点的度数之和 + 1
 * 
 * 祖	先：	从根到该结点路径上的所有结点
 * 子	孙：	某结点为根的树中任一结点都为该结点的子孙
 * 堂兄弟： 	父亲在同一层次的结点互为堂兄弟
 * 
 * 有序树:树中结点的各子树看成从左至右是有次序的,若不考虑子树的顺序为无序树
 * m叉树:树中所有的结点中最大度数为m的有序树为m叉树
 * 森林 (forest) : m ( m >= 0 ) 棵互不相交的树的集合 
 * 子树的集合即为森林.
 * 树和森林的概念相近, 删去一棵树的根得到一个森林.
 * 加上一个结点作为树根，森林变成一棵树
 * @author dolphin
 *
 * 2012-2-2 上午11:23:11
 */
public interface Tree {
	/**
	 * 返回树的结点数
	 * @return
	 */
	int size(); 
	
	/**
	 * 返回树根结点
	 */
	TreeNode getRoot();
	
	/**
	 * 返回 x 结点的父结点
	 */
	TreeNode getParent( final TreeNode node );
	
	/**
	 * 返回 结点的第一个孩子
	 * @param node
	 * @return
	 */
	TreeNode getFirstChild( final TreeNode node );	
	
	/**
	 * 返回 结点 的下一个兄弟结点
	 * 如果 结点 是 最后一个孩子
	 * 则返回为空
	 * @param node
	 * @return
	 */
	TreeNode getNextSibling( final TreeNode node );
	
	/**
	 * 返回 结点 为根的树的高度
	 * @param node
	 * @return
	 */
	int getHeight( final TreeNode node );
	
	/**
	 * 将结点 child 为根的子树插入数中,作为结点 node 的子树
	 * @param node
	 * @param child
	 */
	void insertChild( final TreeNode node, final TreeNode child );
	
	/**
	 * 删除结点 node 的第 i 棵子树
	 * @param node
	 * @param i
	 */
	void deleteChild( final TreeNode node, int i );
	
	
}
