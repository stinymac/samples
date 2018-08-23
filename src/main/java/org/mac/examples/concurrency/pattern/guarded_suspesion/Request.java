/*
 *       ___        |
 *      /_\ `*      |.===.         ,,,,,
 *     (o o)        {}o o{}       /(o o)\
 * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
 *
 * 虽不能至,心向往之。(Although it is not possible, the heart is longing for it.)
 */
package org.mac.examples.concurrency.pattern.guarded_suspesion;

/**
 *
 * @author Mac
 * @create 2018-05-25 21:55
 **/

public class Request {
    private final String value;

    public Request(String value) {
        this.value = value;
    }


    public String getValue() {
        return value;
    }
}
