package com.javatechie.webflux.controller;

import com.javatechie.webflux.dto.Customer;
import com.javatechie.webflux.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;

/**
 * CustomerController es un controlador REST que maneja las solicitudes relacionadas con los clientes.
 */
@RestController
@RequestMapping("/customers")
public class CustomerController {

    // Inyección de dependencias para el servicio de clientes
    @Autowired
    private CustomerService service;

    /**
     * Método para obtener todos los clientes de manera tradicional (bloqueante).
     * 
     * @return una lista de todos los clientes.
     */
    @GetMapping
    public List<Customer> getAllCustomers() {
        // Llama al servicio para cargar todos los clientes y devuelve la lista
        return service.loadAllCustomers();
    }

    /**
     * Método para obtener todos los clientes de manera reactiva en un flujo de datos.
     * Produce un flujo de eventos del tipo 'text/event-stream'.
     * 
     * @return un flujo (reactivo) de todos los clientes.
     */
    @GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Customer> getAllCustomersStream() {
        // Llama al servicio para cargar todos los clientes de manera reactiva y devuelve el flujo
        return service.loadAllCustomersStream();
    }
}
