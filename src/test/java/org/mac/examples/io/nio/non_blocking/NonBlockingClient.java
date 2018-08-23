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
import java.nio.channels.SocketChannel;
import java.util.Scanner;

/**
 *
 * @author Mac
 * @create 2018-06-25 23:48
 **/

public class NonBlockingClient {

    private final String ip;
    private final int port;
    private volatile boolean shutdown = false;

    public NonBlockingClient(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public void start() {
        SocketChannel socketChannel = null;
        try {
            System.out.println("start connect server...");
            socketChannel = SocketChannel.open(new InetSocketAddress(ip,port));

            socketChannel.configureBlocking(false);
            System.out.println("Success connect to server");
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

            Scanner scanner = new Scanner(System.in);

            while (!shutdown) {

                long startTimeMillis = System.currentTimeMillis();

                String inStr = scanner.nextLine();

                byteBuffer.put(inStr.getBytes());
                byteBuffer.flip();
                System.out.println("send:"+inStr);

                socketChannel.write(byteBuffer);
                byteBuffer.clear();

                long endTimeMillis = System.currentTimeMillis();

                System.out.println("client write Time:"+(endTimeMillis-startTimeMillis));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            Utils.close(socketChannel);
        }
    }

    public void shutdown() {
        this.shutdown = true;
    }

    public static void main(String[] args) {

        NonBlockingClient client = new NonBlockingClient("127.0.0.1",8888);
        client.start();
    }
}
