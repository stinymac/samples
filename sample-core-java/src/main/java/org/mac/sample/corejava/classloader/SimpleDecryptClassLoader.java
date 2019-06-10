/*
 *          (          (
 *          )\ )  (    )\   )  )     (
 *  (  (   (()/( ))\( ((_| /( /((   ))\
 *  )\ )\   ((_))((_)\ _ )(_)|_))\ /((_)
 * ((_|(_)  _| (_))((_) ((_)__)((_|_))
 * / _/ _ \/ _` / -_|_-< / _` \ V // -_)
 * \__\___/\__,_\___/__/_\__,_|\_/ \___|
 *
 * 东隅已逝，桑榆非晚。(The time has passed,it is not too late.)
 * 虽不能至，心向往之。(Although I can't, my heart is longing for it.)
 *
 */

package org.mac.sample.corejava.classloader;

import org.mac.sample.corejava.util.Utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


/**
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
