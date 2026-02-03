📝 Ejercicios prácticos de POO en Java

1.Encapsulación Ejercicio:

Crea una clase CuentaBancaria con atributos privados (saldo, titular).
Implementa métodos públicos depositar, retirar y getSaldo.
Asegúrate de que no se pueda retirar más dinero del que hay.

👉 Demuestra cómo la encapsulación protege el estado interno y expone solo lo necesario.

2.Herencia Ejercicio:

Crea una clase base Empleado con atributos nombre, salario.
Crea clases hijas Gerente y Desarrollador.
Gerente tiene un método aprobarProyecto().
Desarrollador tiene un método escribirCodigo().

👉 Muestra cómo la herencia permite reutilizar y especializar comportamiento.

3.Polimorfismo Ejercicio:

Usando el ejemplo anterior de Empleado, crea un método trabajar() en la clase base.
Sobrescribe trabajar() en Gerente y Desarrollador con comportamientos distintos.
En el main, usa una lista de Empleado y llama a trabajar() en cada uno.

👉 Demuestra cómo el polimorfismo permite que diferentes objetos respondan de manera distinta al mismo método.

4.Abstracción Ejercicio:

Define una interfaz Vehiculo con métodos arrancar() y detener().
Implementa Carro y Moto que cumplen el contrato de la interfaz.
En el main, usa referencias de tipo Vehiculo para manejar ambos objetos.

👉 Muestra cómo la abstracción define contratos y oculta la implementación.