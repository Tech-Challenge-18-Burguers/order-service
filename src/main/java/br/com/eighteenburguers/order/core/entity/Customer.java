package br.com.eighteenburguers.order.core.entity;

import lombok.Data;

@Data
public class Customer {

    private String id;
    private final String document;
    private final String name;
    private String email;

}
