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

import br.gov.sp.fatec.projetolab5.entity.Comentario;
import br.gov.sp.fatec.projetolab5.service.ComentarioService;

@RestController
@CrossOrigin
@RequestMapping(value = "/comentario")
public class ComentarioController {

    @Autowired
    private ComentarioService service;

    @GetMapping(value = "/id/{id}")
    public Comentario buscarPorId(@PathVariable("id") Long id) {
        return service.buscarPeloId(id);
    }

    @GetMapping
    public List<Comentario> todos() {
        return service.listarTodos();
    }

    @PostMapping
    public Comentario novoComentario(@RequestBody Comentario comentario) {
        return service.novoComentario(comentario.getTexto(), comentario.getAnotacao().getId());
    }    
    
}
