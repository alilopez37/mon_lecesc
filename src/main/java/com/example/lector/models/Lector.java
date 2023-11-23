package com.example.lector.models;

import java.util.Observable;
import java.util.concurrent.ThreadLocalRandom;

public class Lector extends Observable implements Runnable{
    private Monitor monitor;

    public Lector(Monitor monitor){
        this.monitor = monitor;
    }


    @Override
    public void run(){
        this.setChanged();
        this.notifyObservers("1");

        monitor.iniciarLectura();

        this.setChanged();
        this.notifyObservers("2");
        System.out.println(Thread.currentThread().getName() + "LEYENDO");
        try {
            Thread.sleep(ThreadLocalRandom.current().nextInt(3000) + 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        monitor.finalizarLectura();

        this.setChanged();
        this.notifyObservers("3");
    }
}
