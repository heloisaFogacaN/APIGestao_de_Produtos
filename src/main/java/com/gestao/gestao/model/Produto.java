package com.gestao.gestao.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private Double preco;

    @Column(nullable = false)
    private Integer qtdeEstoque;

    @Column(nullable = false)
    private String dataValidade;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private Long codigoDeBarras;

    @Column(nullable = false)
    private Double peso;

    @Column(nullable = false)
    private Double medida;

    @Column(nullable = false)
    private String fabricante;

    @Column(nullable = false)
    private String categoria;
}
