package com.javatechie.webflux.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Customer es una clase de transferencia de datos (DTO) que representa a un cliente.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    // Identificador único del cliente
    private int id;

    // Nombre del cliente
    private String name;
}
