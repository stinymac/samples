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


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ExecutorService;

/**
 *
 * @author Mac
 * @create 2018-06-07 15:22
 **/

public class SimpleHandler {

    private final ExecutorService executor;
    private volatile boolean running = true;

    public SimpleHandler (ExecutorService executor) {
        this.executor = executor;
    }

    public void handle(Socket clientSocket) {
        executor.submit(new Runnable() {
            @Override
            public void run() {
                InputStream is = null;OutputStream os = null;
                BufferedReader bufferedReader = null;PrintWriter printWriter = null;
                try {
                    is = clientSocket.getInputStream();os = clientSocket.getOutputStream();
                    bufferedReader = new BufferedReader(new InputStreamReader(is));printWriter = new PrintWriter(os);
                    while (running) {
                        String message = bufferedReader.readLine();
                        if (message == null)
                            break;
                        System.out.println("Come from client >" + message);
                        printWriter.write("echo " + message + "\n");
                        printWriter.flush();
                    }
                } catch (IOException e) {
                    //e.printStackTrace();
                    running = false;
                } finally {
                    Utils.close(printWriter,bufferedReader,os,is,clientSocket);
                }
            }
        });
    }

    public void over() {
        if (!running) {
            return;
        }

        this.running = false;
    }
}
