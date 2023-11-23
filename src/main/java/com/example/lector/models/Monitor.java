package com.example.lector.models;

public class Monitor {
    private int nl;
    private int nle;
    private int nee;
    private boolean escribiendo;

    public Monitor(){
        nl = 0;
        nle = 0;
        nee = 0;
        escribiendo = false;
    }

    public synchronized void iniciarLectura(){
        nle++;
        while (escribiendo || nee>0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        nle--;
        nl++;
        if (nle>0)
            this.notifyAll();
    }

    public synchronized void finalizarLectura() {
        nl--;
        if (nl==0 && nee>0)
            this.notifyAll();
    }

    public  synchronized void iniciarEscritura() {
        nee++;

        while (nl>0 || escribiendo) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        nee--;
        escribiendo = true;

    }

    public  synchronized void finalizarEscritura() {
        escribiendo = false;
        if (nee>0)
            this.notifyAll();
        else if (nle>0)
            this.notifyAll();
    }
}
