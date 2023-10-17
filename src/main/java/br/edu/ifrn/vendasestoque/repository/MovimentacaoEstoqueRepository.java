package br.edu.ifrn.vendasestoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifrn.vendasestoque.domain.movimentacaoestoque.MovimentacaoEstoque;

public interface MovimentacaoEstoqueRepository extends JpaRepository<MovimentacaoEstoque, Long> {
  
}
