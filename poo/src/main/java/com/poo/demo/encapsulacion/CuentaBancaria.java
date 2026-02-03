package com.poo.demo.encapsulacion;

/**1. Encapsulación Ejercicio:
Crea una clase CuentaBancaria con atributos privados (saldo, titular).
Implementa métodos públicos depositar, retirar y getSaldo.
Asegúrate de que no se pueda retirar más dinero del que hay.
        👉 Demuestra cómo la encapsulación protege el estado interno y expone solo lo necesario.
 **/

public class CuentaBancaria {
    private double saldo;
    private String titular;

    public CuentaBancaria(String titular, double saldoInicial) {
        this.titular = titular;
        this.saldo = Math.max(0, saldoInicial);
    }

    public void depositar(double monto) {
        if (monto <= 0) {
            return;
        }
        saldo += monto;
    }

    public boolean retirar(double monto) {
        if (monto <= 0 || monto > saldo) {
            return false;
        }
        saldo -= monto;
        return true;
    }

    public double getSaldo() {
        return saldo;
    }

    public String getTitular() {
        return titular;
    }
}