/*
 *       ___        |
 *      /_\ `*      |.===.         ,,,,,
 *     (o o)        {}o o{}       /(o o)\
 * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
 *
 * 虽不能至,心向往之。(Although it is not possible, the heart is longing for it.)
 */
package org.mac.samples.concurrency.pattern.guarded_suspension;

import org.mac.samples.concurrency.pattern.guarded_suspesion.Client;
import org.mac.samples.concurrency.pattern.guarded_suspesion.Server;
import org.mac.samples.concurrency.pattern.guarded_suspesion.WaitQueue;

/**
 *
 * @author Mac
 * @create 2018-05-25 22:10
 **/

public class GuardedSuspensionClient {
    public static void main(String[] args) throws InterruptedException {
        final WaitQueue queue = new WaitQueue();
        Client client = new Client(queue,"Mac");
        Server server = new Server(queue);
        client.start();
        server.start();

        Thread.sleep(10000);

        server.close();
    }
}
