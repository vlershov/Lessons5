package Lessons5;

import online.ClientGUI;

import javax.swing.*;
import java.lang.reflect.Array;

public class Main {

    static final int size = 10000000;
    static final int h = size / 2;

    public static void main(String[] args) {
        float[] arr = new float[size];
        float[] lp_arr1 = new float[size];
        float[] lp_arr2 = new float[size];

        for (int i = 0; i < size; i++) {
            arr[i] = 1;
        }
        first(arr);

        System.out.println("Реализация многопоточности");
        long a = System.currentTimeMillis();
        System.arraycopy(arr, 0, lp_arr1, 0, h);
        System.arraycopy(arr, 0, lp_arr2, h, h);

        Runnable r = new second(lp_arr1);
        new Thread(r).start();
        r = new second(lp_arr2);
        new Thread(r).start();
        arr = new float[size];
        System.arraycopy(lp_arr1, 0, arr, 0, h);
        System.arraycopy(lp_arr2, 0, arr, 0, h);

        System.out.println(System.currentTimeMillis() - a);
    }

    static void first(float[] arr) {

        long a = System.currentTimeMillis();

        raschet(arr);

        System.out.println(System.currentTimeMillis() - a);
    }

    static void raschet(float[] arr) {

       for (int i = 0; i < size; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }

    }

    static class second implements Runnable {
        private float[] lp_arr;
        public second(float[] arr) {
            this.lp_arr = arr;
        }

        @Override
        public void run() {
            raschet(lp_arr);
        }
    }

}
