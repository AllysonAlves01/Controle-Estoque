package com.allyson.controleprodutos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.allyson.controleprodutos.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}