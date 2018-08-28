/*
 *       ___        |
 *      /_\ `*      |.===.         ,,,,,
 *     (o o)        {}o o{}       /(o o)\
 * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
 *
 * 虽不能至,心向往之。(Although it is not possible, the heart is longing for it.)
 */
package org.mac.sample.corejava.concurrency.pattern.balking;

import java.io.IOException;
import java.util.Random;

/**
 *
 * @author Mac
 * @create 2018-06-06 14:40
 **/

public class CustomerThread extends Thread{
    private BalkingData balkingData;
    private final Random random = new Random(System.currentTimeMillis());

    public CustomerThread(BalkingData data) {
        super("Customer");
        this.balkingData = data;
    }

    @Override
    public void run() {
        try {
            balkingData.save();
            for (int i = 0; i < 20; i++) {
                // change save 必须成对使用
                balkingData.change("No." + i);
                Thread.sleep(random.nextInt(1000));
                balkingData.save();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
