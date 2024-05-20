package com.javatechie.webflux.router;

import com.javatechie.webflux.handler.CustomerHandler;
import com.javatechie.webflux.handler.CustomerStreamHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * RouterConfig configura las rutas para manejar solicitudes HTTP de manera reactiva
 * utilizando funciones en Spring WebFlux.
 */
@Configuration
public class RouterConfig {

    // Inyección de dependencias para los manejadores de las solicitudes
    @Autowired
    private CustomerHandler handler;

    @Autowired
    private CustomerStreamHandler streamHandler;

    /**
     * Define las rutas y los manejadores correspondientes para las solicitudes HTTP.
     * 
     * @return La configuración de las rutas como un objeto RouterFunction.
     */
    @Bean
    public RouterFunction<ServerResponse> routerFunction() {
        return RouterFunctions.route()
                .GET("/router/customers", handler::loadCustomers) // Ruta para cargar todos los clientes
                .GET("/router/customers/stream", streamHandler::getCustomers) // Ruta para obtener un flujo de clientes
                .GET("/router/customer/{input}", handler::findCustomer) // Ruta para encontrar un cliente por ID
                .POST("/router/customer/save", handler::saveCustomer) // Ruta para guardar un nuevo cliente
                .build();
    }
}
