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

package org.mac.sample.corejava.concurrency.pattern.producer_consumer;

import java.util.Random;

/**
 *
 * @author Mac
 * @create 2018-06-06 15:34
 **/

public class Consumer extends Thread{
    private final MessageQueue messageQueue;

    private final static Random random = new Random(System.currentTimeMillis());

    public Consumer(MessageQueue messageQueue, int seq) {
        super("Consumer-" + seq);
        this.messageQueue = messageQueue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Message message = messageQueue.take();
                System.out.println(Thread.currentThread().getName() + " take a message " + message.getData());
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
