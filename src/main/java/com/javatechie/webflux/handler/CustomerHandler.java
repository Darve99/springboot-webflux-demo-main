package com.javatechie.webflux.handler;

import com.javatechie.webflux.dao.CustomerDao;
import com.javatechie.webflux.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * CustomerHandler es una clase de servicio que maneja las solicitudes
 * utilizando el enfoque funcional de Spring WebFlux.
 */
@Service
public class CustomerHandler {

    // Inyección de dependencias para el acceso a los datos de clientes
    @Autowired
    private CustomerDao dao;

    /**
     * Maneja la solicitud para cargar todos los clientes de manera reactiva.
     * 
     * @param request La solicitud del servidor.
     * @return La respuesta del servidor con el cuerpo que contiene un flujo de clientes.
     */
    public Mono<ServerResponse> loadCustomers(ServerRequest request) {
        // Obtiene la lista de clientes como un flujo reactivo
        Flux<Customer> customerList = dao.getCustomerList();
        // Crea una respuesta del servidor con el cuerpo del flujo de clientes
        return ServerResponse.ok().body(customerList, Customer.class);
    }

    /**
     * Maneja la solicitud para encontrar un cliente por su ID de manera reactiva.
     * 
     * @param request La solicitud del servidor.
     * @return La respuesta del servidor con el cuerpo que contiene el cliente encontrado.
     */
    public Mono<ServerResponse> findCustomer(ServerRequest request) {
        // Obtiene el ID del cliente de los parámetros de la solicitud
        int customerId = Integer.valueOf(request.pathVariable("input"));
        // Filtra el flujo de clientes para encontrar el que coincide con el ID y obtiene el primer resultado
        Mono<Customer> customerMono = dao.getCustomerList().filter(c -> c.getId() == customerId).next();
        // Crea una respuesta del servidor con el cuerpo del cliente encontrado
        return ServerResponse.ok().body(customerMono, Customer.class);
    }

    /**
     * Maneja la solicitud para guardar un nuevo cliente.
     * 
     * @param request La solicitud del servidor.
     * @return La respuesta del servidor con el cuerpo que contiene el ID y el nombre del cliente guardado.
     */
    public Mono<ServerResponse> saveCustomer(ServerRequest request) {
        // Convierte el cuerpo de la solicitud a un objeto Mono de tipo Customer
        Mono<Customer> customerMono = request.bodyToMono(Customer.class);
        // Mapea el objeto Customer a una cadena de texto que representa el ID y el nombre del cliente
        Mono<String> saveResponse = customerMono.map(dto -> dto.getId() + ":" + dto.getName());
        // Crea una respuesta del servidor con el cuerpo de la cadena de texto
        return ServerResponse.ok().body(saveResponse, String.class);
    }
}
