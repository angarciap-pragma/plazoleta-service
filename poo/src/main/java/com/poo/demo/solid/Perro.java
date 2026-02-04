package com.poo.demo.solid;

public class Perro implements IComedor {
    @Override
    public void comer() {
        System.out.println("El perro come");
    }
}
