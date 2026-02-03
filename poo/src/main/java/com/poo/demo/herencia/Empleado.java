package com.poo.demo.herencia;

/** 2.Herencia Ejercicio:
Crea una clase base Empleado con atributos nombre, salario.
Crea clases hijas Gerente y Desarrollador.
Gerente tiene un método aprobarProyecto().
Desarrollador tiene un método escribirCodigo().
        👉 Muestra cómo la herencia permite reutilizar y especializar comportamiento.
 **/

public class Empleado {
    private String nombre;
    private double salario;

    public Empleado(String nombre, double salario) {
        this.nombre = nombre;
        this.salario = salario;
    }

    public String getNombre() {
        return nombre;
    }

    public double getSalario() {
        return salario;
    }

    //Para polimorfismo
    public void trabajar() {
        System.out.println(nombre + " realiza tareas generales.");
    }
}
