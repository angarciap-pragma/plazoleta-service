package com.poo.demo.solid;

public class Pez implements IComedor, INadador {
    @Override
    public void comer() {
        System.out.println("El pez come");
    }

    @Override
    public void nadar() {
        System.out.println("El pez nada");
    }
}
