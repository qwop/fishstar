package cn.nsccsz;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class MyTestRunner
{

    public static void main(String[] args)
    {
        Result result = JUnitCore.runClasses(JUnit1Test.class,
                StringUtilTest.class);

        for (Failure failure : result.getFailures())
        {
            System.out.println(failure.toString());
        }

        System.out.println("执行方法个数:" + result.getRunCount());
        System.out.println("执行时间:" + result.getRunTime());
    }

}
