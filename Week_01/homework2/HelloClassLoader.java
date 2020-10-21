package com.example.classLoader;

import java.io.*;
import java.lang.reflect.InvocationTargetException;

public class HelloClassLoader extends ClassLoader {

    private String filePath;

    public HelloClassLoader(String filePath) {
        this.filePath = filePath;
    }

     public static void main(String[] args) {
         try {
             String path = "D:\\IdeaProjects\\demo\\src\\main\\java\\com\\example\\classLoader\\Hello.xlass";
             Class<?> hello = new HelloClassLoader(path).findClass("Hello");
             Object obj = hello.newInstance();
             hello.getMethod("hello").invoke(obj);
         } catch (ClassNotFoundException e) {
             e.printStackTrace();
         } catch (IllegalAccessException e) {
             e.printStackTrace();
         } catch (InstantiationException e) {
             e.printStackTrace();
         } catch (NoSuchMethodException e) {
             e.printStackTrace();
         } catch (InvocationTargetException e) {
             e.printStackTrace();
         }
     }

     @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
         byte[] bytes = readFile(filePath);
         if (bytes == null) {
             return null;
         }
         return defineClass(name, bytes, 0, bytes.length);
     }

    /**
     * 读取xlass文件字节流并解析
     * @param path
     * @return
     */
    private static byte[] readFile(String path) {
        File classFile = new File(path);
        FileInputStream input = null;
        try {
            input = new FileInputStream(path);
            byte[] buffer = new byte[(int) classFile.length()];
            try {
                input.read(buffer);
            } catch (IOException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < buffer.length; i++) {
                buffer[i] = (byte)(255 - buffer[i]);
            }
            return buffer;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
