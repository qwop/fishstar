package util;
/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 12-3-27
 * Time: 下午3:18
 * To change this template use File | Settings | File Templates.
 */
public class Util {
    public static String formatPlaces(int temp, int count) {
        int selfCount = ("" + temp).length();
        String str = "";
        if (count > selfCount) {

        }            for (int i = 0; i < count - selfCount; i++) {
                        str += " ";
                    }

        str += temp;
        return str;
    }
}
