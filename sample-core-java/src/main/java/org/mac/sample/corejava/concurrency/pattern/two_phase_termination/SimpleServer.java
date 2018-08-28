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
package org.mac.sample.corejava.concurrency.pattern.two_phase_termination;

import org.mac.sample.corejava.util.Utils;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author Mac
 * @create 2018-06-06 20:21
 **/

public class SimpleServer extends Thread{

    private final static int DEFAULT_PORT = 17221;
    private final int port;

    private volatile boolean shutdown = false;
    private ServerSocket serverSocket;

    private List<SimpleHandler> simpleHandlers = new ArrayList<>();
    ExecutorService threadPool = Executors.newFixedThreadPool(10);


    public SimpleServer() {
        this(DEFAULT_PORT);
    }

    public SimpleServer(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(port);

            while (!shutdown) {

                Socket clientSocket = serverSocket.accept();
                SimpleHandler simpleHandler = new SimpleHandler(threadPool);
                simpleHandlers.add(simpleHandler);
                simpleHandler.handle(clientSocket);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            dispose();
        }

    }

    public void shutdown () throws IOException {
        shutdown = true;
        this.interrupt();
    }

    public void dispose()  {
        for (SimpleHandler handler:simpleHandlers) {
            handler.over();
        }

        ((ExecutorService)threadPool).shutdown();
        Utils.close(serverSocket);
    }
}
