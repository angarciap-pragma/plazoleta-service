package com.poo.demo.solid;

public class SMSNotificador implements INotificador {
    @Override
    public void enviar(String mensaje) {
        System.out.println("SMS: " + mensaje);
    }
}
