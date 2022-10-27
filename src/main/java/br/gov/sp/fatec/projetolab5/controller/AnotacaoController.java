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

import br.gov.sp.fatec.projetolab5.entity.Anotacao;
import br.gov.sp.fatec.projetolab5.service.AnotacaoService;

@RestController
@CrossOrigin
@RequestMapping(value = "/anotacao")
public class AnotacaoController {
    
    @Autowired
    private AnotacaoService service;

    @GetMapping(value = "/{texto}")
    public List<Anotacao> buscarPorTexto(@PathVariable("texto") String texto) {
        return service.buscaAnotacoes(texto);
    }

    @PostMapping
    public Anotacao novaAnotacao(@RequestBody Anotacao anotacao) {
        return service.novaAnotacao(anotacao);
    }
}
