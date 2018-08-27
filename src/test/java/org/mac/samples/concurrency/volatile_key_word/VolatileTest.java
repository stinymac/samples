/*
 *       ___        |
 *      /_\ `*      |.===.         ,,,,,
 *     (o o)        {}o o{}       /(o o)\
 * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
 *
 * 虽不能至,心向往之。(Although it is not possible, the heart is longing for it.)
 */
package org.mac.samples.concurrency.volatile_key_word;

/**
 *
 * @author Mac
 * @create 2018-05-22 12:59
 **/

public class VolatileTest {
    //private static volatile int shareValue = 0;
    private static  int shareValue = 0;
    private static final int LIMIT = 5;

    public static void main(String[] args) {

        new Thread(()->{
            int localValue = shareValue;
            while (localValue < LIMIT) {
                if (localValue != shareValue) {
                    System.out.printf("The vale have be updated to [%d]\n",shareValue);
                    localValue = shareValue;
                }
            }
        },"READER").start();

        new Thread(()->{
            int localValue = shareValue;
            while (localValue < LIMIT) {
                System.out.printf("update vale  to [%d]\n",++localValue);
                shareValue = localValue;
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("shareValue:"+shareValue);
        },"WRITER").start();
    }
}
