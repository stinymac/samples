/*
 *       ___        |
 *      /_\ `*      |.===.         ,,,,,
 *     (o o)        {}o o{}       /(o o)\
 * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
 *
 * 虽不能至,心向往之。(Although it is not possible, the heart is longing for it.)
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
