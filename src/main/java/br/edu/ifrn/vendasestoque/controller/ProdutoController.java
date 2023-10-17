package br.edu.ifrn.vendasestoque.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.edu.ifrn.vendasestoque.domain.produto.Produto;
import br.edu.ifrn.vendasestoque.domain.produto.ProdutoRequestDTO;
import br.edu.ifrn.vendasestoque.domain.produto.ProdutoResponseDTO;
import br.edu.ifrn.vendasestoque.repository.ProdutoRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("produtos")
public class ProdutoController {

  @Autowired
  private ProdutoRepository repository;

  @PostMapping
  @Transactional
  public ResponseEntity<Object> cadastrar(@RequestBody @Valid ProdutoRequestDTO produtoDTO,
      UriComponentsBuilder uriBuilder) {
    Produto produto = repository.save(new Produto(produtoDTO));
    var uri = uriBuilder.path("/produtos/{id}").buildAndExpand(produto.getId()).toUri();
    return ResponseEntity.created(uri).build();
  }

  @GetMapping
  public ResponseEntity<Object> listar(Pageable paginacao) {
    var page = repository.findAllByAtivoTrue(paginacao).stream().map(ProdutoResponseDTO::new);
    return ResponseEntity.ok(page);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Produto> detalhar(@PathVariable Long id) {
    var produto = repository.getReferenceById(id);
    return ResponseEntity.ok(produto);
  }

  @DeleteMapping("/{id}")
  @Transactional
  public ResponseEntity<Object> excluir(@PathVariable Long id) {
    var produto = repository.getReferenceById(id);
    produto.setAtivo(false);
    return ResponseEntity.noContent().build();
  }

}
