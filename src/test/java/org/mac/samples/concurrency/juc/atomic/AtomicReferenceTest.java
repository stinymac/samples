/*
 *       ___        |
 *      /_\ `*      |.===.         ,,,,,
 *     (o o)        {}o o{}       /(o o)\
 * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
 *
 * 虽不能至,心向往之。(Although it is not possible, the heart is longing for it.)
 */
package org.mac.samples.concurrency.juc.atomic;

import java.util.concurrent.atomic.AtomicReference;

/**
 *
 * @author Mac
 * @create 2018-06-11 16:59
 **/

public class AtomicReferenceTest {

    public static void main(String[] args) {
        AtomicReference<Simple> simple = new AtomicReference<>(new Simple("jack","30"));
        System.out.println(simple.get());
    }

    static class Simple {
        String name;
        String age;

        public Simple(String name, String age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }
    }
}
