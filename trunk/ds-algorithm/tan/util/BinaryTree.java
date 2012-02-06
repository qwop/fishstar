package util;

/**
 * �������ӿ� ������: 
 * 	����������һ�ÿ�����
 *  ������һ���ɸ��ڵ�����û����ཻ �ķֱ�
 *  ��Ϊ����������������������������ɵķǿ���
 * 
 * ÿ�����ĺ�����ֻ�� 0��1��2����
 * 	����ÿ�����Ӷ�������֮�֡���
 *  λ����ߵĺ��ӳ�Ϊ����
 *  λ���ұߵĺ��ӳ�Ϊ�Һ���
 * 
 * ����Ϊ��������Ϊ������ �Һ���Ϊ��������Ϊ������
 * ����
 * @author dolphin
 * 
 *         2012-2-2 ����5:19:29
 */
public interface BinaryTree {
	/**
	 * ���ض������Ľ����
	 * 
	 * @return
	 */
	int size();

	/**
	 * �ж϶������Ƿ�Ϊ��
	 * 
	 * @return
	 */
	boolean isEmpty();

	/**
	 * ��ȡ���ڵ�
	 * 
	 * @return
	 */
	TreeNode getRoot();

	/**
	 * ���ض������ĸ߶�
	 * 
	 * @return
	 */
	int getHeight();

	/**
	 * �ҵ�����Ԫ�أ����ڵĽ�㣬��e�����ڣ��򷵻�Ϊ��
	 * 
	 * @param data
	 * @return
	 */
	TreeNode find(Object e);

	// �������������
	Iterator preOrder();

	// �������������
	Iterator inOrder();

	// �������������
	Iterator postOrder();

	// �������������
	Iterator levelOrder();
}
