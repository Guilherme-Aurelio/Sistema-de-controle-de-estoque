package br.edu.ifrn.vendasestoque.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.edu.ifrn.vendasestoque.domain.entradaproduto.EntradaProduto;
import br.edu.ifrn.vendasestoque.repository.MovimentacaoEstoqueRepository;

@Service
public class MovimentacaoEstoqueService {

    @Autowired
    private MovimentacaoEstoqueRepository repository;

    @Transactional
    public void inserirEstoque(EntradaProduto entradaProduto){
        repository.inserirEstoque(entradaProduto.getQuantidade(), 
                                  entradaProduto.getProduto().getId(), 
                                  entradaProduto.getId());
    }
    
}
