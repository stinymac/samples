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

import org.mac.examples.utils.Utils;

import java.io.*;

/**
 * Entry class description
 *
 * @author Mac
 * @create 2018-06-10 14:56
 **/

public class SimpleDecryptClassLoader extends ClassLoader{

    private static final String DEFAULT_LOAD_DIR = "C:\\Users\\Mac\\Desktop\\cl";
    private String classFileLoadDir;

    public SimpleDecryptClassLoader() {
        this(DEFAULT_LOAD_DIR);
    }

    public SimpleDecryptClassLoader(String dir) {
        super();
        this.classFileLoadDir = dir;
    }

    public SimpleDecryptClassLoader(ClassLoader parent) {
        super(parent);
    }


    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String path = name.replace(".","/");
        File classFile = new File(classFileLoadDir,path+".class");

        if(! classFile.exists()) {
            throw new ClassNotFoundException("The class " + name + " not found under directory [" + classFileLoadDir + "]");
        }
        if (!classFile.canRead()) {
            throw new RuntimeException("The class " + name + " cant read under directory [" + classFileLoadDir + "]");
        }

        byte[] classBytes = SimpleFileEncryptAndDecrypt.decrypt(classFile);
        if (classBytes == null || classBytes.length <= 0){
            throw new RuntimeException("The class " + name + " not found under directory [" + classFileLoadDir + "]");
        }

        return this.defineClass(name, classBytes, 0, classBytes.length);
    }

    static class SimpleFileEncryptAndDecrypt {

        private final static byte FACTOR = (byte) 0xFF;

        public static void encrypt(File source, File target) {
            FileInputStream fis = null;
            FileOutputStream fos = null;
            try {
                fis = new FileInputStream(source);
                fos = new FileOutputStream(target);
                int data;
                while ((data = fis.read()) != -1) {
                    fos.write(data ^ FACTOR);
                }
                fos.flush();
            } catch (FileNotFoundException e) {
                //e.printStackTrace();
            } catch (IOException e) {
                //e.printStackTrace();
            } finally {
                Utils.close(fos,fis);
            }
        }

        public static byte[] decrypt(File source) {
            FileInputStream fis = null;
            ByteArrayOutputStream baos = null;
            try {
                fis = new FileInputStream(source);
                baos = new ByteArrayOutputStream();
                int data;
                while ((data = fis.read()) != -1) {
                    baos.write(data ^ FACTOR);
                }
                baos.flush();
                return baos.toByteArray();
            } catch (FileNotFoundException e) {
                //e.printStackTrace();
                return null;
            } catch (IOException e) {
                //e.printStackTrace();
                return null;
            }finally {
                Utils.close(baos,fis);
            }
        }
    }
}
