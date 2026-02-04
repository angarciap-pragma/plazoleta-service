package com.poo.demo.solid;

public class ImpuestoRenta implements Impuesto {
    private final double tasa;

    public ImpuestoRenta(double tasa) {
        this.tasa = tasa;
    }

    @Override
    public double calcular(double base) {
        return base * tasa;
    }
}
