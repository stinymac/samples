/*
 * Copyright 2018-2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.mac.examples.classloader;

import java.io.File;
import java.lang.reflect.Field;

/**
 * Entry class description
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
