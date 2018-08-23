/*
 *       ___        |
 *      /_\ `*      |.===.         ,,,,,
 *     (o o)        {}o o{}       /(o o)\
 * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
 *
 * 虽不能至,心向往之。(Although it is not possible, the heart is longing for it.)
 */
package org.mac.examples.concurrency.pattern.two_phase_termination;

import org.mac.examples.utils.Utils;

import java.io.*;
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
