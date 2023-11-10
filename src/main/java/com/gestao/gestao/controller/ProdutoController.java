package com.gestao.gestao.controller;

import com.gestao.gestao.exception.AlreadyExistsException;
import com.gestao.gestao.model.DTO.ProdutoDTO;
import com.gestao.gestao.model.Produto;
import com.gestao.gestao.service.ProdutoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.NoSuchElementException;

@RestController
@AllArgsConstructor
@RequestMapping("/products")
public class ProdutoController {
    private ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<Produto> inserir(@RequestBody Produto produto) throws Exception {
        try {
            return ResponseEntity.ok(produtoService.cadastrar(produto));
        } catch (AlreadyExistsException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarUm(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(produtoService.buscarUm(id));
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<Collection<Produto>> buscarTodos() {
        try {
            return ResponseEntity.ok(produtoService.buscarTodos());
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Integer id) {
        produtoService.deletar(id);
    }

    @PutMapping
    public ResponseEntity<Produto> atualizar(@RequestBody ProdutoDTO produto) throws Exception {
        try {
            return ResponseEntity.ok(produtoService.atualizar(produto));
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
