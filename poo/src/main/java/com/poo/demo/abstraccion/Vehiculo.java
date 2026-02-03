package com.poo.demo.abstraccion;

/**4.
 Abstracción Ejercicio:
 Define una interfaz Vehiculo con métodos arrancar() y detener().
 Implementa Carro y Moto que cumplen el contrato de la interfaz.
 En el main, usa referencias de tipo Vehiculo para manejar ambos objetos.
 👉 Muestra cómo la abstracción define contratos y oculta la implementación.
 **/

public interface Vehiculo {
    void arrancar();
    void detener();
}
