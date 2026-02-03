package com.poo.demo.herencia;

public class Gerente extends Empleado {
    public Gerente(String nombre, double salario) {
        super(nombre, salario);
    }

    public void aprobarProyecto() {
        System.out.println(getNombre() + " aprueba un proyecto.");
    }

    //Polimotfismo
    @Override
    public void trabajar() {
        System.out.println(getNombre() + " coordina el equipo.");
    }
}
