 /*
  *       ___        |
  *      /_\ `*      |.===.         ,,,,,
  *     (o o)        {}o o{}       /(o o)\
  * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
  *
  * 虽不能至,心向往之。(Although it is not possible, the heart is longing for it.)
  */
package org.mac.sample.corejava.concurrency.juc.utils.locks;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;
import java.util.stream.Collectors;

/**
 *
 * @author Mac
 * @create 2018-06-17 14:19
 **/

public class StampedLockTest {

    private static final StampedLock LOCK = new StampedLock();

    private static final List<Long> DATA = new ArrayList<>();


    public static void main(String[] args) {
        Runnable read = new Runnable() {
            @Override
            public void run() {
                for (;;) {
                    //pessimistecRead();
                    optimisticRead();
                }
            }
        };
        Runnable write = new Runnable() {
            @Override
            public void run() {
                for (;;) {
                    write();
                }
            }
        };

        ExecutorService service = Executors.newFixedThreadPool(10);

        service.submit(read);
        service.submit(read);
        service.submit(read);
        service.submit(read);
        service.submit(read);
        service.submit(read);
        service.submit(read);
        service.submit(read);
        service.submit(read);
        service.submit(write);
    }

    private static void pessimistecRead() {
        long stamp = -1;
        try {
            stamp = LOCK.readLock();//悲观读
            System.out.println("read stamp:"+stamp);
            Optional.of(
                    DATA.stream().map(String::valueOf).collect(Collectors.joining("#","R-",""))
            ).ifPresent(System.out::println);
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            LOCK.unlockRead(stamp);
        }
    }

    private static void optimisticRead() {
        long stamp = LOCK.tryOptimisticRead();
        if(LOCK.validate(stamp)) {
            try {
                stamp = LOCK.readLock();
                System.out.println("optimistic read stamp:" + stamp);
                Optional.of(
                        DATA.stream().map(String::valueOf).collect(Collectors.joining("#", "R-", ""))
                ).ifPresent(System.out::println);
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                LOCK.unlockRead(stamp);
            }
        }
    }

    private static void write() {
        long stamp = -1;
        try {
            stamp = LOCK.writeLock();
            System.out.println("write stamp:"+stamp);
            DATA.add(System.currentTimeMillis());
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            LOCK.unlockWrite(stamp);
        }
    }
}
