package com.javatechie.webflux.dao;

import com.javatechie.webflux.dto.Customer;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * CustomerDao es un componente DAO (Data Access Object) que simula el acceso a los datos de clientes.
 */
@Component
public class CustomerDao {

    /**
     * Método auxiliar para simular una ejecución que se duerme por un segundo.
     * 
     * @param i El número de iteración actual.
     */
    private static void sleepExecution(int i){
        try {
            Thread.sleep(1000); // Duerme el hilo por 1 segundo
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Obtiene una lista de clientes de manera sincrónica (bloqueante).
     * Simula un proceso de carga que tarda 1 segundo por cliente.
     * 
     * @return Una lista de clientes.
     */
    public List<Customer> getCustomers() {
        return IntStream.rangeClosed(1, 10) // Crea un rango de 1 a 10
                .peek(CustomerDao::sleepExecution) // Simula el retardo de procesamiento
                .peek(i -> System.out.println("processing count : " + i)) // Imprime el contador de procesamiento
                .mapToObj(i -> new Customer(i, "customer" + i)) // Mapea cada número a un nuevo objeto Customer
                .collect(Collectors.toList()); // Recoge los resultados en una lista
    }

    /**
     * Obtiene un flujo reactivo de clientes.
     * Cada cliente se emite con un retardo de 1 segundo.
     * 
     * @return Un flujo (reactivo) de clientes.
     */
    public Flux<Customer> getCustomersStream() {
        return Flux.range(1, 10) // Crea un rango reactivo de 1 a 10
                .delayElements(Duration.ofSeconds(1)) // Añade un retardo de 1 segundo entre elementos
                .doOnNext(i -> System.out.println("processing count in stream flow : " + i)) // Imprime el contador de procesamiento
                .map(i -> new Customer(i, "customer" + i)); // Mapea cada número a un nuevo objeto Customer
    }

    /**
     * Obtiene un flujo reactivo de una lista de clientes.
     * Emite los clientes sin retardo.
     * 
     * @return Un flujo (reactivo) de clientes.
     */
    public Flux<Customer> getCustomerList() {
        return Flux.range(1, 50) // Crea un rango reactivo de 1 a 50
                .doOnNext(i -> System.out.println("processing count in stream flow : " + i)) // Imprime el contador de procesamiento
                .map(i -> new Customer(i, "customer" + i)); // Mapea cada número a un nuevo objeto Customer
    }
}
