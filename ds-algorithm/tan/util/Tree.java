package util;



/**
 * �� ( Tree ) : ��һ�������Լ��ڸü����϶����һ�ֹ�ϵ����
 * ��� ( Node ) : �����е�Ԫ��Ϊ���
 * ���ӹ�ϵ ( Relation ) : ���Ķ����ϵ,�� ���Ľ��֮�佨��һ����νṹ��
 * ���ڵ�( Root ) : ���������λ�Ľ��
 * 
 * �� ( Tree ) ��  n ( n >= 0 ) ���������޼���
 * 1) һ�ÿ��� ( n === 0 ) �� �����в������κεĽ��
 * 2) �ǿ��� ( n > 0 ), ��ʱ���ҽ���һ���ض��ĳ�Ϊ�� ( root ) �Ľ��
 *    �� n > 1, ����Ľ��ɷ�Ϊ m ( m > 0 ) �������ཻ�����޼�
 *    ÿһ����������һ������Ϊ �������� ( sub tree )
 *    
 * ��� ( level ): ����ʼ����
 * ���Ϊ 0 �Ľ�� Ϊ���ڵ㡣
 * �����ĸ��Ĳ�� Ϊ 1
 * ������� L ��,�������ĸ��ڵ���Ϊ ( L + 1 ) ��   
 * 
 * ���ڲ��Ϊ k ( k > 0 ) ��ÿ����� c�� �����ҽ���һ�����Ϊ  (k - 1) �Ľ�� p ��Ӧ
 * ����� ( parent ) : p Ϊ  c �ĸ���� 
 * ���� ( child ) : c Ϊ  p �ĺ��ӽ��
 * �����û�и��ף�����Ľ��ֻ��һ������㣬����ȴ�����ж�����ӣ�ͬһ���ĺ����໥��Ϊ �ֵ� ( sibling ) 
 * ��� ( Depth ) : ���н�������������
 * �߶� ( Height ) : �Ըý��Ϊ�������ĸ߶ȡ�
 * ��  ( Degree ) : ��� ӵ�е���������Ŀ 
 * Ҷ�� ( Leaf ) : �� Ϊ  0 �Ľ�� ���� ���ն˽��
 * ��֧��� / ���ն˽��: �Ȳ�Ϊ 0 �Ľ��
 * ����֮��ķ�֧����ڲ����
 * 
 * ���еĽ����Ŀ == ���ı���֮�� + 1 == ���Ķ���֮�� + 1
 * 
 * ��	�ȣ�	�Ӹ����ý��·���ϵ����н��
 * ��	�	ĳ���Ϊ����������һ��㶼Ϊ�ý�������
 * ���ֵܣ� 	������ͬһ��εĽ�㻥Ϊ���ֵ�
 * 
 * ������:���н��ĸ��������ɴ����������д����,��������������˳��Ϊ������
 * m����:�������еĽ����������Ϊm��������Ϊm����
 * ɭ�� (forest) : m ( m >= 0 ) �û����ཻ�����ļ��� 
 * �����ļ��ϼ�Ϊɭ��.
 * ����ɭ�ֵĸ������, ɾȥһ�����ĸ��õ�һ��ɭ��.
 * ����һ�������Ϊ������ɭ�ֱ��һ����
 * @author dolphin
 *
 * 2012-2-2 ����11:23:11
 */
public interface Tree {
	/**
	 * �������Ľ����
	 * @return
	 */
	int size(); 
	
	/**
	 * �����������
	 */
	TreeNode getRoot();
	
	/**
	 * ���� x ���ĸ����
	 */
	TreeNode getParent( final TreeNode node );
	
	/**
	 * ���� ���ĵ�һ������
	 * @param node
	 * @return
	 */
	TreeNode getFirstChild( final TreeNode node );	
	
	/**
	 * ���� ��� ����һ���ֵܽ��
	 * ��� ��� �� ���һ������
	 * �򷵻�Ϊ��
	 * @param node
	 * @return
	 */
	TreeNode getNextSibling( final TreeNode node );
	
	/**
	 * ���� ��� Ϊ�������ĸ߶�
	 * @param node
	 * @return
	 */
	int getHeight( final TreeNode node );
	
	/**
	 * ����� child Ϊ����������������,��Ϊ��� node ������
	 * @param node
	 * @param child
	 */
	void insertChild( final TreeNode node, final TreeNode child );
	
	/**
	 * ɾ����� node �ĵ� i ������
	 * @param node
	 * @param i
	 */
	void deleteChild( final TreeNode node, int i );

	// �������
	Iterator preOrder();

	// �������
	Iterator postOrder();

	// �������
	Iterator levelOrder();
}