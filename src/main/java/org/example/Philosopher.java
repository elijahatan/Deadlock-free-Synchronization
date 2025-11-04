package org.example;

import java.util.concurrent.Semaphore;

public class Philosopher implements Runnable {
    private final int id;
    private final Semaphore[] forks;

    private static final int thinkMs = 500;
    private static final int eatMs   = 400;

    public Philosopher(int id, Semaphore[] forks) {
        this.id = id;
        this.forks = forks;
    }

    @Override
    public void run() {
        int left = id;
        int right = (id + 1) % 2;
        try {
            while (!Thread.currentThread().isInterrupted()) {
                think();

                if (id == 0) {
                    forks[left].acquire();
                    forks[right].acquire();
                } else {
                    forks[right].acquire();
                    forks[left].acquire();
                }
                try {
                    eat();
                } finally {
                    if (id == 0) {
                        forks[right].release();
                        forks[left].release();
                    } else {
                        forks[left].release();
                        forks[right].release();
                    }}
            }
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }}

    private void think() throws InterruptedException {
        System.out.printf("Phil %d thinking%n", id);
        Thread.sleep(thinkMs);
    }

    private void eat() throws InterruptedException {
        System.out.printf("Phil %d eating%n", id);
        Thread.sleep(eatMs);
    }
}