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

public class User extends Thread{
    private final String name;
    private final String address;

    private final Gate gate;

    public User (final String name,final String address,final Gate gate) {
        this.name = name;
        this.address = address;
        this.gate = gate;
    }


    @Override
    public void run() {
        while (true) {
            if(!gate.pass(this.name,this.address)){
                break;
            };
        }
    }

}
