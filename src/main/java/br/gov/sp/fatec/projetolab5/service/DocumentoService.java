package br.gov.sp.fatec.projetolab5.service;

import java.util.List;

import br.gov.sp.fatec.projetolab5.entity.Documento;

public interface DocumentoService {

    public Documento novoDocumento(Documento documento);

    public Documento buscarDocumento(String tipo, Long numero);

    public List<Documento> buscarDocumentos(String nomeUsuario);
}
