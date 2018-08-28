/*
 *       ___        |
 *      /_\ `*      |.===.         ,,,,,
 *     (o o)        {}o o{}       /(o o)\
 * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
 *
 * 虽不能至,心向往之。(Although it is not possible, the heart is longing for it.)
 */
package org.mac.sample.corejava.concurrency.pattern.thread_pre_message;

import java.util.stream.IntStream;

/**
 * Entry class description
 *
 * @author Mac
 * @create 2018-06-06 16:23
 **/

public class ThreadPreMessageClient {
    public static void main(String[] args) {
        final MessageHandler handler = new MessageHandler();
        IntStream.rangeClosed(0, 10)
                .forEach(
                        i -> handler.request(new Message(String.valueOf(i)))
                );

        handler.shutdown();
    }
}
