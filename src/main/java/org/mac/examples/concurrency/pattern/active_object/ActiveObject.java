/*
 *       ___        |
 *      /_\ `*      |.===.         ,,,,,
 *     (o o)        {}o o{}       /(o o)\
 * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
 *
 * 虽不能至,心向往之。(Although it is not possible, the heart is longing for it.)
 */
package org.mac.examples.concurrency.pattern.active_object;

/**
 *
 * @author Mac
 * @create 2018-06-08 9:56
 **/
public interface ActiveObject {

    public Result<String> buildString (char fillChar,int count);
    public void displayString (String text);
}
