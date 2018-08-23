/*
 *       ___        |
 *      /_\ `*      |.===.         ,,,,,
 *     (o o)        {}o o{}       /(o o)\
 * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
 *
 * 虽不能至,心向往之。(Although it is not possible, the heart is longing for it.)
 */
package org.mac.examples.concurrency.pattern.immutable;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mac
 * @create 2018-05-25 17:43
 **/

public class ImmutableClient {
    public static void main(String[] args) {
        List<String> addresses = new ArrayList<>();
        addresses.add("GuangZhou");
        addresses.add("GanSu");
        Person person = new Person("Alex",addresses);

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println(person);
                }
            }
        }).start();
    }
}
