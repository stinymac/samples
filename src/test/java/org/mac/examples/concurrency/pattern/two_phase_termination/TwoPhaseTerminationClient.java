/*
 *       ___        |
 *      /_\ `*      |.===.         ,,,,,
 *     (o o)        {}o o{}       /(o o)\
 * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
 *
 * 虽不能至,心向往之。(Although it is not possible, the heart is longing for it.)
 */
package org.mac.examples.concurrency.pattern.two_phase_termination;

import java.io.IOException;

/**
 *
 * @author Mac
 * @create 2018-06-07 16:08
 **/

public class TwoPhaseTerminationClient {

    public static void main(String[] args) throws InterruptedException, IOException {
        SimpleServer simpleServer = new SimpleServer();
        simpleServer.start();
        Thread.sleep(10_000L);

        simpleServer.shutdown();
    }
}
