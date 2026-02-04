package com.poo.demo.solid;

public class CalculadoraImpuestos {
    public double calcular(double base, Impuesto impuesto) {
        return impuesto.calcular(base);
    }
}
