package com.poo.demo.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * Ejercicios practicos
 *
 * List
 * - Crea una lista de nombres y ordenala alfabeticamente.
 * - Elimina el segundo elemento y muestra el resultado.
 *
 * Set
 * - Crea un conjunto de numeros y prueba anadir duplicados.
 * - Usa un TreeSet para ver como se ordenan automaticamente.
 *
 * Map
 * - Crea un mapa con nombres y edades.
 * - Recorre el mapa mostrando: "Nombre: Ana, Edad: 25".
 * - Cambia la edad de un nombre existente.
 */

public class Collections {

    public static void main(String[] args) {
        // List
        List<String> nombres = new ArrayList<>(Arrays.asList("Carlos", "Ana", "Beatriz", "David"));
        java.util.Collections.sort(nombres);
        System.out.println("Lista ordenada: " + nombres);

        if (nombres.size() > 1) {
            nombres.remove(1);
        }
        System.out.println("Lista sin el segundo elemento: " + nombres);

        // Set
        Set<Integer> numeros = new HashSet<>();
        numeros.add(5);
        numeros.add(2);
        numeros.add(5); // duplicado, no se agrega
        numeros.add(9);
        System.out.println("HashSet (sin orden garantizado): " + numeros);

        Set<Integer> numerosOrdenados = new TreeSet<>(numeros);
        numerosOrdenados.add(3);
        numerosOrdenados.add(2); // duplicado, no se agrega
        System.out.println("TreeSet (ordenado): " + numerosOrdenados);

        // Map
        Map<String, Integer> edades = new HashMap<>();
        edades.put("Ana", 25);
        edades.put("Luis", 30);
        edades.put("Sofia", 22);

        for (Map.Entry<String, Integer> entry : edades.entrySet()) {
            System.out.println("Nombre: " + entry.getKey() + ", Edad: " + entry.getValue());
        }

        edades.put("Luis", 31);
        System.out.println("Edad actualizada de Luis: " + edades.get("Luis"));
    }
}
