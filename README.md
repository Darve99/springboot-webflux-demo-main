# Spring WebFlux Demo

Este es un proyecto de demostración que utiliza Spring WebFlux para manejar solicitudes de manera reactiva. Proporciona ejemplos de acceso a datos tanto de manera sincrónica como reactiva, utilizando Reactor.

## Descripción

El proyecto incluye controladores REST, servicios, manejadores de solicitudes, enrutadores y pruebas unitarias para Mono y Flux en Reactor. Proporciona funcionalidades básicas como obtener una lista de clientes, obtener un flujo de clientes, encontrar un cliente por su ID y guardar un nuevo cliente.

## Tecnologías Utilizadas

- Java
- Spring Boot
- Spring WebFlux
- Reactor

## Estructura del Proyecto

El proyecto está estructurado de la siguiente manera:

- **com.javatechie.webflux.controller:** Contiene los controladores REST, como `CustomerController`, para manejar las solicitudes HTTP.
- **com.javatechie.webflux.dto:** Define las clases de transferencia de datos, como `Customer`.
- **com.javatechie.webflux.dao:** Contiene las clases que simulan el acceso a los datos, como `CustomerDao`.
- **com.javatechie.webflux.handler:** Contiene las clases que manejan las solicitudes utilizando el enfoque funcional de Spring WebFlux, como `CustomerHandler` y `CustomerStreamHandler`.
- **com.javatechie.webflux.router:** Configura las rutas de la aplicación utilizando el enfoque funcional de Spring WebFlux, como `RouterConfig`.
- **com.javatechie.webflux.service:** Contiene la lógica de negocio, encapsulada en servicios como `CustomerService`.
- **com.javatechie.webflux:** Contiene la clase principal de la aplicación Spring Boot y las pruebas unitarias para Mono y Flux en Reactor.

## Ejecución del Proyecto

Para ejecutar el proyecto, puedes utilizar el siguiente comando Maven:

