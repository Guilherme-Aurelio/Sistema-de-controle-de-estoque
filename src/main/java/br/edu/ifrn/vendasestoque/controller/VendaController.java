package br.edu.ifrn.vendasestoque.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import br.edu.ifrn.vendasestoque.domain.venda.Venda;
import br.edu.ifrn.vendasestoque.repository.VendaRepository;
import br.edu.ifrn.vendasestoque.service.MovimentacaoEstoqueService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/vendas")
public class VendaController {

    @Autowired
    private VendaRepository repository;

    @Autowired
    private MovimentacaoEstoqueService service;

    //@ResponseEntity 
    //@ResponseStatus(code=HttpStatus.CREATED)
    @Transactional
    public ResponseEntity<Object>  cadastrar(@RequestBody @Valid Venda venda,
            UriComponentsBuilder uriBuilder) {
        Venda vendaLocal = repository.save(venda);
        service.reduzirEstoque(vendaLocal);
        var uri = uriBuilder.path("/vendas/{id}").buildAndExpand(venda.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
