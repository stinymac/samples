/*
 * Copyright 2018-2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.mac.examples.concurrency.pattern.two_phase_termination;

import org.mac.examples.utils.Utils;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.ExecutorService;

/**
 * Entry class description
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
