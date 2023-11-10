package com.gestao.gestao.service;

import com.gestao.gestao.exception.AlreadyExistsException;
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

    public Produto cadastrar(Produto produto) throws Exception {
        if (produtoRepository.existsByCodigoDeBarras(produto.getCodigoDeBarras())) {
            throw new AlreadyExistsException(produto.getCodigoDeBarras());
        } else if (produto.getNome() == null) {
            throw new Exception("O produto deve conter um nome");
        } else if (produto.getQtdeEstoque() < 0) {
            throw new Exception("O estoque do produto não pode ser negativo!");
        }
        return produtoRepository.save(produto);
    }

    public Produto atualizar(ProdutoDTO produtoDTO) throws Exception {
        if (!produtoRepository.existsByCodigoDeBarras(produtoDTO.getCodigoDeBarras())) {
            throw new Exception("Não há nenhum produto com o código " + produtoDTO.getCodigoDeBarras() + " registrado.");
        }
        Produto produto = new Produto();
        BeanUtils.copyProperties(produtoDTO, produto);
        return produtoRepository.save(produto);
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
