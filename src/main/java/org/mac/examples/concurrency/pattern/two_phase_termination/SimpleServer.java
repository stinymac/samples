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

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Entry class description
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
