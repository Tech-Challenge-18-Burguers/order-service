package br.com.eighteenburguers.order.core.entity;

import java.util.stream.Stream;

public enum Category {

    LANCHE(1, "Lanche"),
    ACOMPANHAMENTO(2, "Acompanhamento"),
    BEBIDA(3, "Bebida"),
    SOBREMESA(4, "Sobremesa");

    private int codigo;
    private String descricao;

    private Category(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static Category ofCode(Integer code) {
        return Stream.of(Category.values()).filter(item -> item.getCodigo().equals(code))
            .findFirst().orElseThrow(IllegalArgumentException::new);
    }
}
