package com.poo.demo.abstraccion;

public class Moto implements Vehiculo{

    @Override
    public void arrancar() {
    System.out.println("La moto arranca.");
}

    @Override
    public void detener() {
        System.out.println("La moto se detiene.");
    }
}
