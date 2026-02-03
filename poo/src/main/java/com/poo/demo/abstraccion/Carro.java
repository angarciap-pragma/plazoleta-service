package com.poo.demo.abstraccion;

public class Carro implements Vehiculo {
    @Override
    public void arrancar() {
        System.out.println("El carro arranca.");
    }

    @Override
    public void detener() {
        System.out.println("El carro se detiene.");
    }
}
