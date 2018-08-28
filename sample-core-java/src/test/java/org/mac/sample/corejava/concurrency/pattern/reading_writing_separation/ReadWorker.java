/*
 *       ___        |
 *      /_\ `*      |.===.         ,,,,,
 *     (o o)        {}o o{}       /(o o)\
 * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
 *
 * 虽不能至,心向往之。(Although it is not possible, the heart is longing for it.)
 */
package org.mac.sample.corejava.concurrency.pattern.reading_writing_separation;

/**
 *
 * @author Mac
 * @create 2018-05-24 22:12
 **/

public class ReadWorker extends Thread{
    private final ReadWriteTask readWriteTask;

    public ReadWorker(ReadWriteTask readWriteTask) {
        this.readWriteTask = readWriteTask;
    }

    @Override
    public void run() {
            while (true) {
                readWriteTask.read();
            }
    }
}
