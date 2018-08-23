/*
 *       ___        |
 *      /_\ `*      |.===.         ,,,,,
 *     (o o)        {}o o{}       /(o o)\
 * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
 *
 * 虽不能至,心向往之。(Although it is not possible, the heart is longing for it.)
 */
package org.mac.examples.concurrency.pattern.reading_writing_separation;

import java.util.Random;

/**
 *
 * @author Mac
 * @create 2018-05-24 22:17
 **/

public class WriteWorker extends Thread{
    private static final Random random = new Random(System.currentTimeMillis());
    private final ReadWriteTask readWriteTask;
    private final String filler;
    private int index = 0;

    public WriteWorker(ReadWriteTask readWriteTask, String filler) {
        this.readWriteTask = readWriteTask;
        this.filler = filler;
    }

    @Override
    public void run() {
        try {
            while (true) {

                char c = nextChar();
                readWriteTask.write(c);
                Thread.sleep(random.nextInt(1000));

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private char nextChar() {
        char c = filler.charAt(index);
        index++;
        if (index >= filler.length())
            index = 0;
        return c;
    }
}
