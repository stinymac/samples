/*
 *       ___        |
 *      /_\ `*      |.===.         ,,,,,
 *     (o o)        {}o o{}       /(o o)\
 * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
 *
 * 虽不能至,心向往之。(Although it is not possible, the heart is longing for it.)
 */
package org.mac.examples.io.nio;


import java.nio.ByteBuffer;

/**
 *
 * @author Mac
 * @create 2018-06-25 14:16
 **/

public class BufferTest {
    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(64);//分配一个指定大小的缓冲区

        System.out.println("======allocate()======");
        System.out.println("position:"+byteBuffer.position()+" limit:"+byteBuffer.limit()+" capacity:"+byteBuffer.capacity());


        String s = "Hello World!";

        byteBuffer.put(s.getBytes());//用 put() 存入数据到缓冲区中

        System.out.println("======put()======");
        System.out.println("position:"+byteBuffer.position()+" limit:"+byteBuffer.limit()+" capacity:"+byteBuffer.capacity());


        byteBuffer.flip();//切换读取数据模式
        System.out.println("======flip()======");
        System.out.println("position:"+byteBuffer.position()+" limit:"+byteBuffer.limit()+" capacity:"+byteBuffer.capacity());


        System.out.println("======get()======");
        byte[] bytes = new byte[byteBuffer.limit()];
        byteBuffer.get(bytes);//利用 get() 读取缓冲区中的数据
        System.out.println(new String(bytes));
        System.out.println("position:"+byteBuffer.position()+" limit:"+byteBuffer.limit()+" capacity:"+byteBuffer.capacity());


        byteBuffer.rewind();//可重复读
        System.out.println("======rewind()======");
        System.out.println("position:"+byteBuffer.position()+" limit:"+byteBuffer.limit()+" capacity:"+byteBuffer.capacity());

        byteBuffer.get(bytes);//利用 get() 读取缓冲区中的数据
        System.out.println("rewind:"+new String(bytes));
        byteBuffer.mark();

        System.out.println("======mark()======");
        System.out.println("position:"+byteBuffer.position()+" limit:"+byteBuffer.limit()+" capacity:"+byteBuffer.capacity());
        String ns = "......";
        byteBuffer.limit(byteBuffer.limit()+ns.length());
        System.out.println("======limit(newLimit) repeat======");
        System.out.println("position:"+byteBuffer.position()+" limit:"+byteBuffer.limit()+" capacity:"+byteBuffer.capacity());

        byteBuffer.put(ns.getBytes(),0,ns.length());
        System.out.println("======mark()->put()======");
        System.out.println("position:"+byteBuffer.position()+" limit:"+byteBuffer.limit()+" capacity:"+byteBuffer.capacity());
        byteBuffer.reset();
        System.out.println("======mark()->reset()======");
        System.out.println("position:"+byteBuffer.position()+" limit:"+byteBuffer.limit()+" capacity:"+byteBuffer.capacity());


        byteBuffer.clear();//清空缓冲区. 但是缓冲区中的数据依然存在，但是处于“被遗忘”状态
        System.out.println("======clear()======");
        System.out.println("position:"+byteBuffer.position()+" limit:"+byteBuffer.limit()+" capacity:"+byteBuffer.capacity());
    }
}
