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
package org.mac.examples.io.nio.blocking;

import org.mac.examples.utils.Utils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * Entry class description
 *
 * @author Mac
 * @create 2018-06-25 21:50
 **/

public class Server {

    private final int port;
    private volatile boolean shutdown = false;

    public Server(int port) {
        this.port = port;
    }

    public void start () {
        ServerSocketChannel serverSocketChannel = null;
        try {
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress(port));
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            while (!shutdown) {
                System.out.println("waiting client connect...");
                SocketChannel client = serverSocketChannel.accept();//blocking
                System.out.println("client  successful connect...");

                client.read(byteBuffer);//blocking

                byteBuffer.flip();
                System.out.println("server receive :"+new String(byteBuffer.array()));
                byteBuffer.clear();

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            Utils.close(serverSocketChannel);
        }
    }

    public void shutdown() {
        this.shutdown = true;
    }

    public static void main(String[] args) {
        Server server = new Server(8888);
        server.start();
    }
}
