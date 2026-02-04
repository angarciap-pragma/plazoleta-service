package com.poo.demo.solid;

public class EmailNotificador implements INotificador {
    @Override
    public void enviar(String mensaje) {
        System.out.println("Email: " + mensaje);
    }
}
