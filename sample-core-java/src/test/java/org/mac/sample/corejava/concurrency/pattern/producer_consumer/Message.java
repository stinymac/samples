/*
 *       ___        |
 *      /_\ `*      |.===.         ,,,,,
 *     (o o)        {}o o{}       /(o o)\
 * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
 *
 * 虽不能至,心向往之。(Although it is not possible, the heart is longing for it.)
 */
package org.mac.sample.corejava.concurrency.pattern.producer_consumer;

/**
 * Entry class description
 *
 * @author Mac
 * @create 2018-06-06 15:20
 **/

public class Message {
    private String data;

    public Message(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }
}
