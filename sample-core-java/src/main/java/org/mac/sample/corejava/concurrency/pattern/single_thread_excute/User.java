 /*
  *      (             |"|           !!!       #   ___                             o
  *      _)_          _|_|_       `  _ _  '    #  <_*_>             ,,,         ` /_\ '       __MMM__
  *     (o o)         (o o)      -  (OXO)  -   #  (o o)            (o o)       - (o o) -       (o o)
  * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo--8---(_)--Ooo----ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
  *
  *
  * 虽不能至,心向往之。(Although I can't, my heart is longing for it.)
  *
  *
  *       ___        |
  *      /_\ `*      |.===.         ,,,,,
  *     (o o)        {}o o{}       /(o o)\
  * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
  *
  */
package org.mac.sample.corejava.concurrency.pattern.single_thread_excute;

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
