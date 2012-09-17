package sort;
import java.util.Arrays;
import util.Util;
/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 12-3-26
 * Time: 下午5:07
 * To change this template use File | Settings | File Templates.
 */
public class SampleSortTest {
    public static void main(String[] args) {
        int[] arr = new int[]{
                45,
                32,
                4,
                5,
                6,
                776,
                76,
                43,
                32,
                43,
                65,
                709
        };

        System.out.println("Before Sort:" + Arrays.toString(arr));
//        bubbleSort(arr);
//        selectSort( arr );
//        insertSort( arr );
        insertSort1(arr);

        System.out.println("After  Sort:" + Arrays.toString(arr));
    }

    private static void insertSort(int[] arr) {
        // 3 , 2 , 1
        int len = arr.length, in, out, count = 0;
        for (out = 1; out < len; out++) {
            int temp = arr[out];
            in = out;
            while (in > 0 && arr[in - 1] > temp) {
                arr[in] = arr[in - 1];
                --in;
            }
            arr[in] = temp;
        }
    }

    private static void insertSort1(int[] arr) {
        int len = arr.length, out, in, temp;
        for (out = 1; out < len; out++) {
            display(arr, out);
            temp = arr[out];
            in = out;

            // 将树组大于 哨兵 的树组后移，直到遇到小于哨兵的数值
            while (in > 0 && arr[in - 1] >= temp) {
                arr[in] = arr[in-- - 1];
            }
            arr[in] = temp;
        }

    }

    private static void display(int[] arr, int out) {
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= max) {
                max = arr[i];
            }
        }
        int count = ("" + max).length() + 1;


        String start = "[" + Util.formatPlaces(arr[out], count) + "]\t";

        String str = "";
        for (int i = 0; i < arr.length; i++) {
            if (i == out) {
                if (arr[i - 1] < arr[i]) {
                    str += "<" + Util.formatPlaces(arr[i], count).substring( 1 ) + "*\t";
                } else {
                    str += ">" + Util.formatPlaces(arr[i], count).substring( 1 ) + "*\t";
                }

            } else {
                str += Util.formatPlaces(arr[i], count) + "\t";
            }
        }
       // String regStr = "(.+\\*.+){2,}";


        System.out.println((start + str));
    }




    private static void selectSort(int[] arr) {
        // 10  3 4  5 8
        int len = arr.length;
        int out, in, min;
        for (out = 0; out < len - 1; out++) {
            min = out;
            for (in = out + 1; in < len; in++) {
                if (arr[in] < arr[min]) {
                    min = in;
                }
            }
            swap(arr, out, min);
        }
    }

    private static void swap(int[] arr, int out, int min) {
        int old = arr[out];
        arr[out] = arr[min];
        arr[min] = old;
    }

    private static void bubbleSort(int[] arr) {
        int len = arr.length;
        for (int i = len - 1; i > 0; i--) {
            for (int j = 0; j < i; j += 1) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j);
                }
            }
        }
    }

    private static void swap(int[] arr, int j) {
        int old = arr[j];
        arr[j] = arr[j + 1];
        arr[j + 1] = old;
    }


}
