package com.poo.demo.herencia;

public class Desarrollador extends Empleado {
    public Desarrollador(String nombre, double salario) {
        super(nombre, salario);
    }

    public void escribirCodigo() {
        System.out.println(getNombre() + " escribe codigo.");
    }

    //Polimorfismo
    @Override
    public void trabajar() {
        System.out.println(getNombre() + " implementa funcionalidades.");
    }
}
