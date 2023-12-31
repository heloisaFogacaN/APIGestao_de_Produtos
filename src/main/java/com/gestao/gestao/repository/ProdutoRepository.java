package com.gestao.gestao.repository;

import com.gestao.gestao.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    boolean existsByCodigoDeBarras(Long codigo);

}
