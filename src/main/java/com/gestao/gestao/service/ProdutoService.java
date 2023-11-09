package com.gestao.gestao.service;

import com.gestao.gestao.model.DTO.ProdutoDTO;
import com.gestao.gestao.model.Produto;
import com.gestao.gestao.repository.ProdutoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@AllArgsConstructor
public class ProdutoService {
    private ProdutoRepository produtoRepository;

    public void cadastrar(Produto produto) throws Exception {
        if (produtoRepository.existsProdutoByCodigoDeBarras(produto.getCodigoDeBarras())) {
            throw new Exception("Há um produto com o código " + produto.getCodigoDeBarras() + " já cadastrado!");
        } else if (produto.getNome() == null) {
            throw new Exception("O produto deve conter um nome");
        } else if (produto.getQtdeEstoque() < 0) {
            throw new Exception("O estoque do produto não pode ser negativo!");
        }
        produtoRepository.save(produto);
    }

    public void atualizar(ProdutoDTO produtoDTO) throws Exception {
        if (!produtoRepository.existsProdutoByCodigoDeBarras(produtoDTO.getCodigoDeBarras())) {
            throw new Exception("Não há nenhum produto com o código " + produtoDTO.getCodigoDeBarras() + " registrado.");
        }
        Produto produto = new Produto();
        BeanUtils.copyProperties(produtoDTO, produto);
        produtoRepository.save(produto);
    }

    public Produto buscarUm(Integer id) {
        return produtoRepository.findById(id).get();
    }

    public Collection<Produto> buscarTodos() {
        return produtoRepository.findAll();
    }

    public void deletar(Integer id) {
        produtoRepository.deleteById(id);
    }
}
