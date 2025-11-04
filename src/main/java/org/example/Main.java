package org.example;

import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Semaphore[] forks = { new Semaphore(1), new Semaphore(1) };

        Thread p0 = new Thread(new Philosopher(0, forks));
        Thread p1 = new Thread(new Philosopher(1, forks));

        p0.start();
        p1.start();

        Thread.sleep(1000);
    }
}