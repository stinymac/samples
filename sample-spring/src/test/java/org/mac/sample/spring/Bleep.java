package org.mac.sample.spring;

public class Bleep {

    private volatile String  bleepName = "Bleep";

    void setName(String name) {
        this.bleepName = name;
    }

    void backgroundSetName() throws InterruptedException {
        Thread t = new Thread() {
            @Override
            public void run() {
                setName("Blat");
                System.out.println(bleepName);
            }
        };
        t.start();
        t.join();
        System.out.println(bleepName);
    }

    public static void main(String[] args) throws InterruptedException {
        new Bleep().backgroundSetName();
    }
}