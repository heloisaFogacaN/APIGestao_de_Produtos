package com.gestao.gestao.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class ProdutoDTO {
    private String nome;
    private Double preco;
    private String dataValidade;
    private String descricao;
    private Integer qtdeEstoque;
    private Long codigoDeBarras;
    private Double peso;
    private Double medida;
    private String fabricante;
    private String categoria;
}
