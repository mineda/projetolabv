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

import br.gov.sp.fatec.projetolab5.entity.Autorizacao;
import br.gov.sp.fatec.projetolab5.service.AutorizacaoService;

@RestController
@CrossOrigin
@RequestMapping(value = "/autorizacao")
public class AutorizacaoController {
    
    @Autowired
    private AutorizacaoService service;

    @GetMapping
    public List<Autorizacao> buscarTodas() {
        return service.buscarTodas();
    }

    @GetMapping(value = "/{nome}")
    public Autorizacao buscarPeloNome(@PathVariable("nome") String nome) {
        return service.buscarPeloNome(nome);
    }

    @PostMapping
    public Autorizacao novaAutorizaco(@RequestBody String nome) {
        return service.novaAutorizacao(nome);
    }

}
