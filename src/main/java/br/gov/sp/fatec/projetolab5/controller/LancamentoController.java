package br.gov.sp.fatec.projetolab5.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.sp.fatec.projetolab5.entity.Lancamento;
import br.gov.sp.fatec.projetolab5.service.LancamentoService;

@RestController
@CrossOrigin
@RequestMapping(value = "/lancamento")
public class LancamentoController {
    
    @Autowired
    private LancamentoService service;

    @GetMapping(value = "/{float}")
    public Integer contaDuracaoSuperior(@PathVariable("float") Float duracao) {
        return service.contarLancamentosComDuracaoSuperior(duracao);
    }

    @GetMapping
    public List<Lancamento> buscarTodos() {
        return service.buscarTodos();
    }

    @PostMapping
    public Lancamento novoLancamento(@RequestBody LancamentoDTO lancamento) {
        return service.novoLancamento(lancamento);
    }
}
