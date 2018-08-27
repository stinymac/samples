/*
 *       ___        |
 *      /_\ `*      |.===.         ,,,,,
 *     (o o)        {}o o{}       /(o o)\
 * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
 *
 * 虽不能至,心向往之。(Although it is not possible, the heart is longing for it.)
 */
package org.mac.samples.io.nio.blocking;

import org.mac.samples.utils.Utils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
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
