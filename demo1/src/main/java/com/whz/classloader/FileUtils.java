package com.whz.classloader;

import java.io.*;

public class FileUtils {

    public static void main(String[] a) {
        (new FileUtils()).test("/Users/apple/Documents/myhome/java_demo/demo1/src/main/resources/lib/Test.class");
    }

    public static void test(String path) {
        File file = new File(path);
        try {
            FileInputStream fis= new FileInputStream(file);
            FileOutputStream fos = new FileOutputStream(path+"en");
            int b;
            int b1;
            try {
                while ((b= fis.read()) != -1) {
                    // 每一个byte异或一个数字2
                    fos.write(b^2);
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();;
        }
    }
}
