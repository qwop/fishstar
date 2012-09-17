/*
 * Copyright (c) 2012. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package intellij;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 12-3-31
 * Time: 下午2:57
 * To change this template use File | Settings | File Templates.
 */
public class Sample {
    public Sample() {
    }

    public void init() {
        int[] s = new int[] { 1, 2, 3 };
        for (int i = 0; i < s.length; i++) {
            int s1 = s[i];
            System.out.println( s1 );
        }
    }

    public static void main(String[] args) {
        Sample sample = new Sample();
        sample.init();
        InputStream in = null;
        try {
            in = new FileInputStream( "c:\\1.txt" );
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if ( null != in ) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


        }

        List<Object> list = new ArrayList<Object>();
        for (int i = 0; i < list.size(); i++) {
            Object o =  list.get(i);

        }


    }



}
