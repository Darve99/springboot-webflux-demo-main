package com.javatechie.webflux.handler;

import com.javatechie.webflux.dao.CustomerDao;
import com.javatechie.webflux.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * CustomerStreamHandler es una clase de servicio que maneja las solicitudes de flujo de clientes
 * utilizando el enfoque funcional de Spring WebFlux.
 */
@Service
public class CustomerStreamHandler {

    // Inyección de dependencias para el acceso a los datos de clientes
    @Autowired
    private CustomerDao dao;

    /**
     * Maneja la solicitud para obtener un flujo de clientes de manera reactiva.
     * 
     * @param request La solicitud del servidor.
     * @return La respuesta del servidor con el cuerpo que contiene un flujo de clientes.
     */
    public Mono<ServerResponse> getCustomers(ServerRequest request) {
        // Obtiene el flujo de clientes desde el DAO
        Flux<Customer> customersStream = dao.getCustomersStream();
        // Crea una respuesta del servidor con el cuerpo del flujo de clientes, utilizando el tipo de contenido 'text/event-stream'
        return ServerResponse.ok()
                .contentType(MediaType.TEXT_EVENT_STREAM) // Establece el tipo de contenido a 'text/event-stream' para el flujo de eventos
                .body(customersStream, Customer.class); // Establece el cuerpo de la respuesta como el flujo de clientes
    }
}
