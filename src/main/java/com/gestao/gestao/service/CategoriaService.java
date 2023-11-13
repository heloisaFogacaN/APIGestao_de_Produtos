package com.gestao.gestao.service;

import com.gestao.gestao.exception.CadastroErradoException;
import com.gestao.gestao.model.Categoria;
import com.gestao.gestao.model.DTO.CategoriaDTO;
import com.gestao.gestao.repository.CategoriaRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@AllArgsConstructor
public class CategoriaService {

    private CategoriaRepository categoriaRepository;
    public Categoria cadastrar(CategoriaDTO categoriaDTO) throws CadastroErradoException {
        Categoria categoria = new Categoria();
        BeanUtils.copyProperties(categoriaDTO, categoria);
        return categoriaRepository.save(categoria);
    }

    public Categoria buscarUm(Integer id) {
        return categoriaRepository.findById(id).get();
    }

    public Collection<Categoria> buscarTodos() {
        return categoriaRepository.findAll();
    }

    public void deletar(Integer id) {
        categoriaRepository.deleteById(id);
    }

    public Categoria atualizar(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }
}
