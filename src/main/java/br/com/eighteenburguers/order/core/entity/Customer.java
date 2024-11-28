package br.com.eighteenburguers.order.core.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Customer {

    private Long id;
    private String document;
    private String name;
    private String email;

}
