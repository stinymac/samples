/*
 *       ___        |
 *      /_\ `*      |.===.         ,,,,,
 *     (o o)        {}o o{}       /(o o)\
 * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
 *
 * 虽不能至,心向往之。(Although it is not possible, the heart is longing for it.)
 */
package org.mac.samples.io.nio;

import org.mac.samples.utils.Utils;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Mac
 * @create 2018-06-25 15:33
 **/

public class ChannelTest {

    public static void main(String[] args) throws IOException {
        //fileCopy();
        //fileCopyDirect();
        //fileCopyDirect2();
        //ioScatterAndGather();
        //diplayCharset();
        charEncodeAndDecode();
    }

    public static void fileCopy() {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        FileChannel fin = null;
        FileChannel fout = null;
        try {
            fis =new FileInputStream("C:\\Users\\Mac\\Pictures\\Saved Pictures\\1.jpg");
            fos = new FileOutputStream("C:\\Users\\Mac\\Desktop\\2.jpg");

            fin  = fis.getChannel();
            fout = fos.getChannel();

            ByteBuffer buffer = ByteBuffer.allocate(1024);

            while (fin.read(buffer)!=-1) {
                buffer.flip();
                fout.write(buffer);
                buffer.clear();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            Utils.close(fin,fout,fis,fos);
        }
    }

    public static void fileCopyDirect() {
        FileChannel in = null;
        FileChannel out = null;
        try {
            in = FileChannel.open(Paths.get("C:\\Users\\Mac\\Pictures\\Saved Pictures\\1.jpg"), StandardOpenOption.READ);
            out = FileChannel.open(Paths.get("C:\\Users\\Mac\\Desktop\\2.jpg"), StandardOpenOption.WRITE, StandardOpenOption.READ, StandardOpenOption.CREATE);

            //内存映射文件
            MappedByteBuffer inMappedBuf = in.map(FileChannel.MapMode.READ_ONLY, 0, in.size());
            MappedByteBuffer outMappedBuf = out.map(FileChannel.MapMode.READ_WRITE, 0, in.size());

            //直接对缓冲区进行数据的读写操作
            byte[] dst = new byte[inMappedBuf.limit()];
            inMappedBuf.get(dst);
            outMappedBuf.put(dst);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            Utils.close(in,out);
        }
    }

    public static void fileCopyDirect2() {
        FileChannel inChannel = null;
        FileChannel outChannel = null;
        try {
            inChannel = FileChannel.open(Paths.get("C:\\Users\\Mac\\Pictures\\Saved Pictures\\1.jpg"), StandardOpenOption.READ);
            outChannel = FileChannel.open(Paths.get("C:\\Users\\Mac\\Desktop\\2.jpg"), StandardOpenOption.WRITE, StandardOpenOption.READ, StandardOpenOption.CREATE);
            //inChannel.transferTo(0, inChannel.size(), outChannel);
            outChannel.transferFrom(inChannel, 0, inChannel.size());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            Utils.close(inChannel,outChannel);
        }
    }

    public static void ioScatterAndGather() {
        RandomAccessFile raf = null;
        FileChannel channel  = null;
        RandomAccessFile rafOut = null;
        FileChannel out = null;
        try {
            raf = new RandomAccessFile("C:\\Users\\Mac\\Desktop\\tip.txt","rw");
            channel = raf.getChannel();

            //分散
            ByteBuffer buffer_size_1 = ByteBuffer.allocate(1);
            ByteBuffer buffer_size_1024 = ByteBuffer.allocate(1024);

            ByteBuffer[] buffers =  new ByteBuffer[]{buffer_size_1,buffer_size_1024};

            channel.read(buffers);

            buffer_size_1.flip();
            buffer_size_1024.flip();

            System.out.println(new String(buffer_size_1.array(),0,buffer_size_1.limit()));
            System.out.println(new String(buffer_size_1024.array(),0,buffer_size_1024.limit()));

            //聚集
            rafOut = new RandomAccessFile("C:\\Users\\Mac\\Desktop\\tip2.txt","rw");
            out = rafOut.getChannel();

            out.write(buffers);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            Utils.close(out,rafOut,channel,raf);
        }
    }

    public static void diplayCharset() {
        Map<String, Charset> charsets = Charset.availableCharsets();

        Set<Map.Entry<String, Charset>> set = charsets.entrySet();

        for (Map.Entry<String, Charset> entry : set) {
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }
    }

    public static void charEncodeAndDecode(){

        Charset gbk = Charset.forName("GBK");

        CharBuffer charBuffer = CharBuffer.allocate(1024);

        charBuffer.put("世界你好！");
        charBuffer.flip();

        try {
            ByteBuffer byteBuffer = gbk.newEncoder().encode(charBuffer);

            System.out.print("[ ");
            for (int i = 0; i < byteBuffer.limit();i++){
                System.out.print(byteBuffer.get()+" ");
            }
            System.out.println("]");

            byteBuffer.flip();

            System.out.println(gbk.newDecoder().decode(byteBuffer).toString());
        } catch (CharacterCodingException e) {
            e.printStackTrace();
        }
    }
}