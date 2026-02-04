package com.poo.demo.solid;

public class ImpuestoIVA implements Impuesto {
    private final double tasa;

    public ImpuestoIVA(double tasa) {
        this.tasa = tasa;
    }

    @Override
    public double calcular(double base) {
        return base * tasa;
    }
}
