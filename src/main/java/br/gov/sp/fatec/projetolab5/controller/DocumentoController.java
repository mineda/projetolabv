package br.gov.sp.fatec.projetolab5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.sp.fatec.projetolab5.entity.Documento;
import br.gov.sp.fatec.projetolab5.service.DocumentoService;

@RestController
@CrossOrigin
@RequestMapping(value = "/documento")
public class DocumentoController {

    @Autowired
    private DocumentoService service;

    @PostMapping
    public Documento novoDocumento(@RequestBody Documento documento) {
        return service.novoDocumento(documento);
    }

    @GetMapping(value = "/{tipo}/{numero}")
    public Documento buscarDocumento(@PathVariable("tipo") String tipo, @PathVariable("numero") Long numero) {
        return service.buscarDocumento(tipo, numero);
    }
    
}
