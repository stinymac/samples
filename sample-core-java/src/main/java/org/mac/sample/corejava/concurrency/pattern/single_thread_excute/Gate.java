/*
 *          (          (
 *          )\ )  (    )\   )  )     (
 *  (  (   (()/( ))\( ((_| /( /((   ))\
 *  )\ )\   ((_))((_)\ _ )(_)|_))\ /((_)
 * ((_|(_)  _| (_))((_) ((_)__)((_|_))
 * / _/ _ \/ _` / -_|_-< / _` \ V // -_)
 * \__\___/\__,_\___/__/_\__,_|\_/ \___|
 *
 * 东隅已逝，桑榆非晚。(The time has passed,it is not too late.)
 * 虽不能至，心向往之。(Although I can't, my heart is longing for it.)
 *
 */

package org.mac.sample.corejava.concurrency.pattern.single_thread_excute;

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
