/*
 *       ___        |
 *      /_\ `*      |.===.         ,,,,,
 *     (o o)        {}o o{}       /(o o)\
 * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
 *
 * 虽不能至,心向往之。(Although it is not possible, the heart is longing for it.)
 */
package org.mac.samples.io.nio.blocking;

import org.mac.samples.util.Utils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

/**
 *
 * @author Mac
 * @create 2018-06-25 21:50
 **/

public class Client {

    private final String ip;
    private final int port;
    private volatile boolean shutdown = false;

    public Client(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public void start() {
        SocketChannel socketChannel = null;
        try {
            System.out.println("start connect server...");
            socketChannel = SocketChannel.open(new InetSocketAddress(ip,port));
            System.out.println("Success connect to server");
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

            Scanner scanner = new Scanner(System.in);

            while (!shutdown) {
                String inStr = scanner.nextLine();
                System.out.println("client send:"+inStr);
                byteBuffer.put(inStr.getBytes());
                byteBuffer.flip();
                socketChannel.write(byteBuffer);
                byteBuffer.clear();
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
        Client client = new Client("127.0.0.1",8888);
        client.start();
    }
}
