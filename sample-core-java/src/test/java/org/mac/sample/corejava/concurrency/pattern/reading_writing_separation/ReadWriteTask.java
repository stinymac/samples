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
 * @create 2018-05-24 21:36
 **/

public class ReadWriteTask {

    private final char[] sharedData;
    private final ReadWriteLock lock = new ReadWriteLock();

    public ReadWriteTask(char[] sharedData) {
        this.sharedData = sharedData;
    }

    public char[] read() {
        try {
            lock.readLock();
            return doRead();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                lock.readUnLock();
            } catch (ReadWriteLock.IllegalOperationException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private char[] doRead() {
        char[] copy = new char[sharedData.length];
        for (int i = 0; i < sharedData.length; i++)
            copy[i] = sharedData[i];

        slowly(50);
        System.out.println(Thread.currentThread().getName() + " reads " + String.valueOf(copy));
        return copy;
    }

    public void write (char c) {
        try {

            lock.writeLock();
            doWrite(c);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {

                lock.writeUnLock();
            } catch (ReadWriteLock.IllegalOperationException e) {
                e.printStackTrace();
            }
        }
    }

    private void doWrite(char c) {
        for (int i = 0; i < sharedData.length; i++) {
            sharedData[i] = c;
            slowly(10);
        }
    }

    private void slowly(int mills) {
        try {
            Thread.sleep(mills);
        } catch (InterruptedException e) {
        }
    }
}
