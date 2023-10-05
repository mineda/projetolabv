package br.gov.sp.fatec.projetolab5.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.gov.sp.fatec.projetolab5.controller.LancamentoDTO;
import br.gov.sp.fatec.projetolab5.entity.Lancamento;
import br.gov.sp.fatec.projetolab5.entity.Usuario;
import br.gov.sp.fatec.projetolab5.repository.LancamentoRepository;
import br.gov.sp.fatec.projetolab5.repository.UsuarioRepository;

@Service
public class LancamentoServiceImpl implements LancamentoService {

    @Autowired
    private LancamentoRepository lancamentoRepo;

    @Autowired
    private UsuarioRepository usuarioRepo;

    public Lancamento novoLancamento(LancamentoDTO lancamentoDTO) {
        // Busca usuario
        Optional<Usuario> usuarioOp = usuarioRepo.findById(lancamentoDTO.getIdUsuario());
        // Verifica se usuario foi encontrado. Caso contrario o Optional contem Option.empty()
        if(usuarioOp.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario nao encontrado!");
        }
        // Valida data/hora de início e fim
        if(lancamentoDTO.getDataHoraInicio() == null ||
                lancamentoDTO.getDataHoraFim() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Data/hora de inicio e fim sao obrigatorias!");
        }
        // Recupera duracao em milisegundos
        Float duracaoMili = 1f*(lancamentoDTO.getDataHoraFim().getTime() - lancamentoDTO.getDataHoraInicio().getTime());
        Lancamento lancamento = new Lancamento();
        lancamento.setDescricao(lancamentoDTO.getDescricao());
        lancamento.setDataHoraInicio(lancamentoDTO.getDataHoraInicio());
        // Converte para horas
        lancamento.setDuracao(duracaoMili/3600000f);
        lancamento.setUsuario(usuarioOp.get());

        return lancamentoRepo.save(lancamento);
    }

    // Contas quantos lancamentos ultrapassam a duracao passada como parametro
    public Integer contarLancamentosComDuracaoSuperior(Float duracao) {
        if(duracao <= 10f) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Duracao deve ser maior que zero!");
        }
        return lancamentoRepo.countByDuracaoGreaterThan(duracao);
    }

    public List<Lancamento> buscarTodos() {
        return lancamentoRepo.findAll();
    }
    
}
