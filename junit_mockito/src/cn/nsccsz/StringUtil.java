package cn.nsccsz;

public class StringUtil
{

    public static String trim(final String value)
    {
        if (null == value)
        {
            throw new IllegalArgumentException("无效的参数");
        }
        return value.replaceAll("(^\\s+)|(\\s+$)", "");
    }

    public static void mustThrowException()
    {
        throw new RuntimeException();
    }
}
