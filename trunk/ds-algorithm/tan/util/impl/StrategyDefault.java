package util.impl;

import util.Strategy;

/**
 * 默认的对比对象策略
 * @author dolphin
 *
 * 2012-1-13 上午9:16:44
 */
public final class StrategyDefault implements Strategy {

	public boolean equal(Object obj1, Object obj2) {
		if (obj1 == null && obj2 == null) {
			return true;
		}
		if (obj1 == null) {
			return false;
		}
		return obj1.equals(obj2);
	}

	public int compare(Object obj1, Object obj2) {
		int comp = obj1.toString().compareTo(obj2.toString());
		if (comp == 0)
			return 0;
		else if (comp > 0)
			return 1;
		else
			return -1;
	}
}
