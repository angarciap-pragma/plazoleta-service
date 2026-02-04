package com.poo.demo.solid;

public class SolidDemo {
    public static void main(String[] args) {
        // SRP
        UsuarioServicio usuarioServicio = new UsuarioServicio();
        usuarioServicio.guardar(new Usuario("1", "Ana", "ana@email.com"));
        usuarioServicio.guardar(new Usuario("2", "Luis", "luis@email.com"));
        System.out.println("Usuarios registrados: " + usuarioServicio.listar().size());

        // OCP
        CalculadoraImpuestos calculadora = new CalculadoraImpuestos();
        double base = 1000.0;
        double iva = calculadora.calcular(base, new ImpuestoIVA(0.19));
        double renta = calculadora.calcular(base, new ImpuestoRenta(0.10));
        System.out.println("IVA: " + iva + ", Renta: " + renta);

        // LSP
        Vehiculo vehiculo = new Vehiculo();
        Vehiculo carro = new Carro();
        Vehiculo moto = new Moto();
        System.out.println(vehiculo.mover());
        System.out.println(carro.mover());
        System.out.println(moto.mover());

        // ISP
        Pato pato = new Pato();
        Pez pez = new Pez();
        Perro perro = new Perro();
        pato.comer();
        pato.volar();
        pato.nadar();
        pez.comer();
        pez.nadar();
        perro.comer();

        // DIP
        Alerta alertaEmail = new Alerta(new EmailNotificador());
        Alerta alertaSms = new Alerta(new SMSNotificador());
        alertaEmail.enviar("Alerta por correo");
        alertaSms.enviar("Alerta por SMS");
    }
}
