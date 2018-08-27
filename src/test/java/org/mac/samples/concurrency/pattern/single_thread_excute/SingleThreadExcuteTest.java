/*
 *       ___        |
 *      /_\ `*      |.===.         ,,,,,
 *     (o o)        {}o o{}       /(o o)\
 * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
 *
 * 虽不能至,心向往之。(Although it is not possible, the heart is longing for it.)
 */
package org.mac.samples.concurrency.pattern.single_thread_excute;

/**
 *
 * @author Mac
 * @create 2018-05-24 17:52
 **/

public class SingleThreadExcuteTest {
    public static void main(String[] args) {
        Gate gate = new Gate();
        User user1 = new User("AA","AA",gate);
        User user2 = new User("BB","BB",gate);
        User user3 = new User("CC","CC",gate);
        user1.start();
        user2.start();
        user3.start();
    }
}
