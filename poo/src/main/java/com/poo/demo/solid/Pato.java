package com.poo.demo.solid;

public class Pato implements IComedor, IVolador, INadador {
    @Override
    public void comer() {
        System.out.println("El pato come");
    }

    @Override
    public void volar() {
        System.out.println("El pato vuela");
    }

    @Override
    public void nadar() {
        System.out.println("El pato nada");
    }
}
