/*
 *       ___        |
 *      /_\ `*      |.===.         ,,,,,
 *     (o o)        {}o o{}       /(o o)\
 * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
 *
 * 虽不能至,心向往之。(Although it is not possible, the heart is longing for it.)
 */
package org.mac.examples.classloader;

import java.io.File;
import java.lang.reflect.Field;

/**
 *
 * @author Mac
 * @create 2018-06-10 15:39
 **/

public class SimpleDecryptClassLoaderTest {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException {
        File source =  new File("C:\\Users\\Mac\\Desktop\\cl\\org\\mac\\examples\\concurrency\\thread\\shutdown\\forcible\\ThreadService.class0");
        File target =  new File("C:\\Users\\Mac\\Desktop\\cl\\org\\mac\\examples\\concurrency\\thread\\shutdown\\forcible\\ThreadService.class");

        SimpleDecryptClassLoader.SimpleFileEncryptAndDecrypt.encrypt(source,target);


        SimpleDecryptClassLoader simpleDecryptClassLoader = new SimpleDecryptClassLoader();
        Class<?> aClass = simpleDecryptClassLoader.findClass("org.mac.examples.concurrency.thread.shutdown.forcible.ThreadService");
        Field[] fields = aClass.getDeclaredFields();
        for (Field field:fields) {
            field.setAccessible(true);
            System.out.println("->"+field);
        }

        System.out.println(aClass);
        System.out.println(aClass.getClassLoader());

    }
}
