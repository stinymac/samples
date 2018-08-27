/*
 *       ___        |
 *      /_\ `*      |.===.         ,,,,,
 *     (o o)        {}o o{}       /(o o)\
 * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
 *
 * 虽不能至,心向往之。(Although it is not possible, the heart is longing for it.)
 */
package org.mac.samples.concurrency.pattern.active_object;

/**
 *
 * @author Mac
 * @create 2018-06-08 15:43
 **/

public class ActiveObjectClient {
    public static void main(String[] args) {
        ActiveObject activeObject = ActiveObjectFactory.createActiveObject();
        Thread buildStringThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for(int i = 0;true;i++){
                        Result result = activeObject.buildString( 'A',i + 1);
                        Thread.sleep(20);
                        String value = (String) result.getResultValue();
                        System.out.println(Thread.currentThread().getName()+": value="+value);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        Thread displayStringThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; true; i++) {
                        String text = Thread.currentThread().getName() + ":" + i;
                        activeObject.displayString(text);
                        Thread.sleep(200);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        buildStringThread.start();
        displayStringThread.start();
    }
}
