package com.poo.demo;

import com.poo.demo.abstraccion.Carro;
import com.poo.demo.abstraccion.Moto;
import com.poo.demo.abstraccion.Vehiculo;
import com.poo.demo.encapsulacion.CuentaBancaria;
import com.poo.demo.herencia.Desarrollador;
import com.poo.demo.herencia.Empleado;
import com.poo.demo.herencia.Gerente;

import java.util.List;

public class PooApplication {

	public static void main(String[] args) {

		// 1. Encapsulacion
		CuentaBancaria cuenta = new CuentaBancaria("Ana", 500);
		cuenta.depositar(200);
		boolean retiroOk = cuenta.retirar(800);
		System.out.println("Retiro permitido? " + retiroOk);
		System.out.println("Saldo actual: " + cuenta.getSaldo());

		// 2 y 3. Herencia y Polimorfismo
		Empleado gerente = new Gerente("Luis", 4500);
		Empleado desarrollador = new Desarrollador("Marta", 3500);
		List<Empleado> equipo = List.of(gerente, desarrollador);
		for (Empleado e : equipo) {
			e.trabajar();
		}
		((Gerente) gerente).aprobarProyecto();
		((Desarrollador) desarrollador).escribirCodigo();

		// 4. Abstraccion
		Vehiculo carro = new Carro();
		Vehiculo moto = new Moto();
		carro.arrancar();
		moto.arrancar();
		carro.detener();
		moto.detener();
		}
	}


