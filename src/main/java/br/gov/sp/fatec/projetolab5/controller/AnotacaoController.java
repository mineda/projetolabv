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

    @GetMapping
    public List<Anotacao> buscarTodasAnotacoes() {
        return service.buscarTodasAnotacoes();
    }

    @GetMapping(value = "/{id}")
    public Anotacao buscarAnotacaoPorId(@PathVariable("id") Long id) {
        return service.buscarAnotacao(id);
    }

    @PostMapping(value = "/novo/{texto}/{usuario}")
    public Anotacao novaAnotacao(@PathVariable("texto") String texto, @PathVariable("usuario") String usuario) {
        return service.novaAnotacao(texto, usuario);
    }

    @PostMapping(value = "/complemento/{texto}/{anotacao}")
    public Anotacao anotacaoComplementar(@PathVariable("texto") String texto, @PathVariable("anotacao") Long idAnotacao) {
        return service.anotacaoComplementar(idAnotacao, texto);
    }
}
