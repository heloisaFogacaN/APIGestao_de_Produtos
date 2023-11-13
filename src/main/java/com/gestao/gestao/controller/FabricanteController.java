package com.gestao.gestao.controller;

import com.gestao.gestao.exception.AlreadyExistsException;
import com.gestao.gestao.exception.CadastroErradoException;
import com.gestao.gestao.model.DTO.FabricanteDTO;
import com.gestao.gestao.model.Fabricante;
import com.gestao.gestao.service.FabricanteService;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.NoSuchElementException;

@RestController
@AllArgsConstructor
@RequestMapping("/fabricante")
public class FabricanteController {
    private FabricanteService fabricanteService;

    @PostMapping
    public ResponseEntity<Fabricante> inserir(@RequestBody FabricanteDTO fabricanteDTO){
        try {
            return ResponseEntity.ok(fabricanteService.cadastrar(fabricanteDTO));
        } catch (AlreadyExistsException e){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } catch (CadastroErradoException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Fabricante> buscarUm(@PathVariable Integer id){
        try {
            return ResponseEntity.ok(fabricanteService.buscarUm(id));
        }catch (NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<Collection<Fabricante>> buscarTodos(){
        try {
            return ResponseEntity.ok(fabricanteService.buscarTodos());
        }catch (NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Integer id){
        fabricanteService.deletar(id);
    }

    @PutMapping
    public ResponseEntity<Fabricante> atualizar(@RequestBody FabricanteDTO fabricanteDTO){
        try {
            return ResponseEntity.ok(fabricanteService.atualizar(fabricanteDTO));
        }catch (NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



}
