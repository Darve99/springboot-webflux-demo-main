package com.javatechie.webflux.service;

import com.javatechie.webflux.dao.CustomerDao;
import com.javatechie.webflux.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

/**
 * CustomerService es una clase de servicio que maneja la lógica de negocio relacionada con los clientes.
 */
@Service
public class CustomerService {

    // Inyección de dependencias para el acceso a los datos de clientes
    @Autowired
    private CustomerDao dao;

    /**
     * Carga todos los clientes de manera sincrónica.
     * 
     * @return Una lista de clientes.
     */
    public List<Customer> loadAllCustomers() {
        // Mide el tiempo de inicio
        long start = System.currentTimeMillis();
        // Obtiene la lista de clientes desde el DAO
        List<Customer> customers = dao.getCustomers();
        // Mide el tiempo de fin
        long end = System.currentTimeMillis();
        // Imprime el tiempo total de ejecución
        System.out.println("Total execution time : " + (end - start));
        // Retorna la lista de clientes
        return customers;
    }

    /**
     * Carga todos los clientes de manera reactiva.
     * 
     * @return Un flujo (reactivo) de clientes.
     */
    public Flux<Customer> loadAllCustomersStream() {
        // Mide el tiempo de inicio
        long start = System.currentTimeMillis();
        // Obtiene el flujo de clientes desde el DAO
        Flux<Customer> customers = dao.getCustomersStream();
        // Mide el tiempo de fin
        long end = System.currentTimeMillis();
        // Imprime el tiempo total de ejecución
        System.out.println("Total execution time : " + (end - start));
        // Retorna el flujo de clientes
        return customers;
    }
}
