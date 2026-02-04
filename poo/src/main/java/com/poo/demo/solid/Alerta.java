package com.poo.demo.solid;

public class Alerta {
    private final INotificador notificador;

    public Alerta(INotificador notificador) {
        this.notificador = notificador;
    }

    public void enviar(String mensaje) {
        notificador.enviar(mensaje);
    }
}
