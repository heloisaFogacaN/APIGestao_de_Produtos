package com.gestao.gestao.service;

import com.gestao.gestao.exception.CadastroErradoException;
import com.gestao.gestao.model.DTO.FabricanteDTO;
import com.gestao.gestao.model.Fabricante;
import com.gestao.gestao.repository.FabricanteRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;

@Service
@AllArgsConstructor
public class FabricanteService {
    private FabricanteRepository fabricanteRepository;
    public Fabricante cadastrar(FabricanteDTO fabricanteDTO) throws CadastroErradoException {
        if (fabricanteDTO.getNome() == null){
            throw new CadastroErradoException();
        }
        Fabricante fabricante = new Fabricante();
        BeanUtils.copyProperties(fabricanteDTO, fabricante);
        return fabricanteRepository.save(fabricante);
    }

    public Fabricante buscarUm(Integer id) {
        return fabricanteRepository.findById(id).get();
    }

    public Collection<Fabricante> buscarTodos() {
        return fabricanteRepository.findAll();
    }

    public void deletar(Integer id) {
         fabricanteRepository.deleteById(id);
    }

    public Fabricante atualizar(FabricanteDTO fabricanteDTO) {
        Fabricante fabricante =  new Fabricante();
        BeanUtils.copyProperties(fabricanteDTO, fabricante);
        return fabricanteRepository.save(fabricante);
    }
}
