package br.unisinos.aspect;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@ToString
class Pedido {
    final private int id;
    private String cliente;
    private List<String> produtos;
    private double valorTotal;
}

