package com.gestao.gestao.controller;

import com.gestao.gestao.exception.AlreadyExistsException;
import com.gestao.gestao.exception.CadastroErradoException;
import com.gestao.gestao.model.Categoria;
import com.gestao.gestao.model.DTO.CategoriaDTO;
import com.gestao.gestao.service.CategoriaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.NoSuchElementException;

@RestController
@AllArgsConstructor
@RequestMapping("/categoria")
public class CategoriaController {
    private CategoriaService categoriaService;

    @PostMapping
    public ResponseEntity<Categoria> inserir(@RequestBody CategoriaDTO categoriaDTO){
        try {
            return ResponseEntity.ok(categoriaService.cadastrar(categoriaDTO));
        } catch (AlreadyExistsException e){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } catch (CadastroErradoException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> buscarUm(@PathVariable Integer id){
        try {
            return ResponseEntity.ok(categoriaService.buscarUm(id));
        }catch (NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<Collection<Categoria>> buscarTodos(){
        try {
            return ResponseEntity.ok(categoriaService.buscarTodos());
        }catch (NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Integer id){
        categoriaService.deletar(id);
    }

    @PutMapping
    public ResponseEntity<Categoria> atualizar(@RequestBody Categoria categoria ){
        try {
            return ResponseEntity.ok(categoriaService.atualizar(categoria));
        }catch (NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
