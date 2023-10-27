package br.gov.sp.fatec.projetolab5.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.gov.sp.fatec.projetolab5.entity.Documento;
import br.gov.sp.fatec.projetolab5.entity.Usuario;
import br.gov.sp.fatec.projetolab5.repository.DocumentoRepository;
import br.gov.sp.fatec.projetolab5.repository.UsuarioRepository;

@Service
public class DocumentoServiceImpl implements DocumentoService {

    @Autowired
    private DocumentoRepository docRepo;

    @Autowired
    private UsuarioRepository usuarioRepo;

    public Documento novoDocumento(Documento documento) {
        if(documento.getId() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Novos documentos não podem ter id!");
        }
        if(documento.getNumero() == null ||
                documento.getDigito() == null ||
                documento.getTipo() == null ||
                documento.getTipo().isBlank() ||
                documento.getDataEmissao() == null ||
                documento.getUsuario() == null ||
                documento.getUsuario().getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Campos obrigatórios não informados!");
        }
        Optional<Documento> documentoOp = docRepo.findByTipoAndNumero(documento.getTipo(), documento.getNumero());
        if(documentoOp.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Documento já cadastrado!");
        }
        Optional<Usuario> usuarioOp = usuarioRepo.findById(documento.getUsuario().getId());
        if(usuarioOp.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário não encontrado!");
        }
        documento.setUsuario(usuarioOp.get());
        return docRepo.save(documento);
    }

    public Documento buscarDocumento(String tipo, Long numero) {
        Optional<Documento> documentoOp = docRepo.findByTipoAndNumero(tipo, numero);
        if(documentoOp.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Documento não encontrado!");
        }
        return documentoOp.get();
    }

    public List<Documento> buscarDocumentos(String nomeUsuario) {
        List<Documento> documentos = docRepo.findByUsuarioNome(nomeUsuario);
        if(documentos.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Documento não encontrado!");
        }
        return documentos;
    }
    
}
