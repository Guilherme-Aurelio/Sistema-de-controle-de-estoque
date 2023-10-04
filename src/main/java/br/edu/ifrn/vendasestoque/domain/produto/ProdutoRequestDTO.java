package br.edu.ifrn.vendasestoque.domain.produto;

import java.math.BigDecimal;

import br.edu.ifrn.vendasestoque.domain.categoria.Categoria;
import br.edu.ifrn.vendasestoque.domain.fabricante.Fabricante;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProdutoRequestDTO(@NotBlank String nome,
                                @NotNull int quantidadeEstoque,
                                BigDecimal preco,
                                Categoria categoria,
                                Fabricante fabricante) {
  
}
