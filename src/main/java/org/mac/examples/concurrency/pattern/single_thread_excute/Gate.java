/*
 *       ___        |
 *      /_\ `*      |.===.         ,,,,,
 *     (o o)        {}o o{}       /(o o)\
 * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
 *
 * 虽不能至,心向往之。(Although it is not possible, the heart is longing for it.)
 */
package org.mac.examples.concurrency.pattern.single_thread_excute;

/**
 *
 * @author Mac
 * @create 2018-05-23 20:38
 **/

public class Gate {

    private  String name;
    private  String address;
    private int counter;



    public synchronized boolean pass(final String name, final String address) {
        this.name = name;
        this.address = address;
        this.counter ++;
        return verfiy();
    }

    private boolean verfiy() {
        if (this.name.charAt(0) != this.address.charAt(0)){

            System.out.println("==========broken======"+toString());
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Gate{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", counter=" + counter +
                '}';
    }
}
