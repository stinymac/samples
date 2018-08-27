/*
 *       ___        |
 *      /_\ `*      |.===.         ,,,,,
 *     (o o)        {}o o{}       /(o o)\
 * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
 *
 * 虽不能至,心向往之。(Although it is not possible, the heart is longing for it.)
 */
package org.mac.samples.concurrency.pattern.reading_writing_separation;

/**
 *
 * @author Mac
 * @create 2018-05-24 22:02
 **/

public class ReadWriteSeparationTest {
    public static void main(String[] args) {
        char[] sharedData = new char[10];
        for (int i = 0; i < sharedData.length;i++) {
            sharedData[i] = '*';
        }
        final ReadWriteTask task = new ReadWriteTask(sharedData);

        new ReadWorker(task).start();
        new ReadWorker(task).start();
        new ReadWorker(task).start();
        new ReadWorker(task).start();

        new WriteWorker(task,"some").start();
        new WriteWorker(task,"data").start();
    }
}
