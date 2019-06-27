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

package org.mac.sample.corejava.concurrency.pattern.thread_pre_message;

import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author Mac
 * @create 2018-06-06 16:22
 **/

public class MessageHandler {
    private final static Random random = new Random(System.currentTimeMillis());

    private final static Executor executor = Executors.newFixedThreadPool(5);

    public void request(Message message) {
        executor.execute(() -> {
            String value = message.getValue();
            try {
                Thread.sleep(random.nextInt(1000));
                System.out.println("The message will be handle by " + Thread.currentThread().getName() + " " + value);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });/*
        new Thread(() -> {
            String value = message.getValue();

            try {
                Thread.sleep(random.nextInt(1000));
                System.out.println("The message will be handle by " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();*/
    }

    public void shutdown() {
        ((ExecutorService) executor).shutdown();
    }
}
