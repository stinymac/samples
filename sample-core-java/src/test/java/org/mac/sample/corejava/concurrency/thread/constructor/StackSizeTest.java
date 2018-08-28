/*
 *       ___        |
 *      /_\ `*      |.===.         ,,,,,
 *     (o o)        {}o o{}       /(o o)\
 * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
 *
 * 虽不能至,心向往之。(Although it is not possible, the heart is longing for it.)
 */
package org.mac.sample.corejava.concurrency.thread.constructor;

/**
 *
 * @author Mac
 * @create 2018-05-07 20:55
 **/

public class StackSizeTest {
    private static int counter = 0;
    public static void main(String[] args) {

        Thread t = new Thread(null,new Runnable() {
            public void run(){
                try {
                    add(0);
                }catch (Error error) {
                    System.out.println(counter + "->" + error);
                }
            }
            private void add(int i){
                counter++;
                add(i+1);
            }
        },"stack-size-test",1<<24);
        t.start();
    }


}
