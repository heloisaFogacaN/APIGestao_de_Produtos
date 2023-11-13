package com.gestao.gestao.service;

import com.gestao.gestao.exception.AlreadyExistsException;
import com.gestao.gestao.exception.CadastroErradoException;
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

    public Produto cadastrar(ProdutoDTO produtoDTO) throws Exception {
        if (produtoRepository.existsByCodigoDeBarras(produtoDTO.getCodigoDeBarras())) {
            throw new AlreadyExistsException(produtoDTO.getCodigoDeBarras());
        } else if (produtoDTO.getQtdeEstoque() < 0) {
            throw new CadastroErradoException();
        }
        Produto produto = new Produto();
        BeanUtils.copyProperties(produtoDTO, produto);
        return produtoRepository.save(produto);
    }

    public Produto atualizar(Produto produto) throws Exception {
        if (!produtoRepository.existsByCodigoDeBarras(produto.getCodigoDeBarras())) {
            throw new Exception("Não há nenhum produto com o código " + produto.getCodigoDeBarras() + " registrado.");
        }
        deletar(produto.getCodigoDeBarras());
        return produtoRepository.save(produto);
    }

    public Produto buscarUm(Long codigo) {
        return produtoRepository.findById(codigo).get();
    }

    public Collection<Produto> buscarTodos() {
        return produtoRepository.findAll();
    }

    public void deletar(Long codigo) {
        produtoRepository.deleteById(codigo);
    }
}
