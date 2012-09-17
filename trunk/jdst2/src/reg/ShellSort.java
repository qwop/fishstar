/*
 * Copyright (c) 2012. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package reg;

import java.util.Arrays;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 12-3-31
 * Time: 下午4:12
 * Author: Dolphin
 */
public class ShellSort {
    public static void main(String[] args) {
        int[] idx = new int[]{00, 01, 02, 03, 04, 05, 06, 07,  8,  9};
        int[] arr = new int[]{49, 38, 65, 97, 76, 13, 27, 49, 55, 04};

        shellSort(arr);

        System.out.println(Arrays.toString(arr));
    }

    private static void shellSort(int[] arr) {
        int inner, outer, len = arr.length;
        int temp;

        int h = 1;
        while (h < len / 3) {
            h = h * 3 + 1;
        }
        boolean first = false;
        
        System.out.println( "h : " + h );
        while (h > 0) {
            for (outer = h; outer < len; outer++) {
                temp = arr[outer];
                inner = outer;


                if (!first) {
                    System.out.println(

                            " h - 1 : " + ( h - 1)   +
                                    "\t inner : " + inner  +
                                    "\t inner - h : " + ( inner  - h )  +

                                    "\t arr[inner] : " + arr [inner]  +
                                    "\t  arr[ inner - h ] : " + arr[ inner - h]  +

                                    "\t  temp : " + temp  + //outer + " -> " + arr[outer]
                                    ( ( arr[inner - h] >= temp ) ? " swap " : "not swap" )
                    );
                }

                while (inner > h - 1 && arr[inner - h] >= temp) {
                    arr[inner] = arr[inner - h];
                    inner -= h;
                }

                arr[inner] = temp;
            }

            first = true;


            h = (h - 1) / 3;
        }
    }
}