/*
 *       ___        |
 *      /_\ `*      |.===.         ,,,,,
 *     (o o)        {}o o{}       /(o o)\
 * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
 *
 * 虽不能至,心向往之。(Although it is not possible, the heart is longing for it.)
 */
package org.mac.sample.corejava.pattern.observer;

/**
 *
 * @author Mac
 * @create 2018-05-23 15:59
 **/

public abstract class Observer {
    public abstract void update(Subject subject, Object... arg);
}
