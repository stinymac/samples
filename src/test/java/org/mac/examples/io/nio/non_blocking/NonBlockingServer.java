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
package org.mac.examples.io.nio.non_blocking;

import org.mac.examples.utils.Utils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * Entry class description
 *
 * @author Mac
 * @create 2018-06-25 23:48
 **/

public class NonBlockingServer {

    private final int port;
    private volatile boolean shutdown = false;

    public NonBlockingServer(int port) {
        this.port = port;
    }

    public void start () {
        ServerSocketChannel serverSocketChannel = null;
        try {
            serverSocketChannel = ServerSocketChannel.open();

            serverSocketChannel.bind(new InetSocketAddress(port));
            serverSocketChannel.configureBlocking(false);

            Selector selector = Selector.open();

            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);


            while (selector.select() > 0) {

                System.out.println("non blocking...");

                Iterator<SelectionKey> it = selector.selectedKeys().iterator();
                while (it.hasNext()) {

                    SelectionKey selectionKey = it.next();

                    if (selectionKey.isAcceptable()) {
                        SocketChannel client = serverSocketChannel.accept();
                        System.out.println("client  successful connect..."+client);

                        client.configureBlocking(false);
                        client.register(selector,SelectionKey.OP_READ);

                    } else if (selectionKey.isReadable()) {
                        SocketChannel client = (SocketChannel) selectionKey.channel();

                        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

                        long startTimeMillis = System.currentTimeMillis();
                        client.read(byteBuffer);
                        byteBuffer.flip();
                        System.out.println("server receive :" + new String(byteBuffer.array()));
                        byteBuffer.clear();

                        long endTimeMillis = System.currentTimeMillis();

                        System.out.println("server read Time:"+(endTimeMillis-startTimeMillis));
                    }
                    it.remove();
                }

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
        NonBlockingServer server = new NonBlockingServer(8888);
        server.start();
    }
}
