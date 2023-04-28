package br.gov.sp.fatec.projetolab5.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.server.ResponseStatusException;

import br.gov.sp.fatec.projetolab5.controller.LancamentoDTO;
import br.gov.sp.fatec.projetolab5.entity.Lancamento;
import br.gov.sp.fatec.projetolab5.entity.Usuario;
import br.gov.sp.fatec.projetolab5.repository.LancamentoRepository;
import br.gov.sp.fatec.projetolab5.repository.UsuarioRepository;

@SpringBootTest
public class LancamentoServiceTest {

    @Autowired
    private LancamentoService service;

    @MockBean
    private LancamentoRepository lancamentoRepo;

    @MockBean
    private UsuarioRepository usuarioRepo;
    
    @Test
    public void novoLancamentoOk() {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNome("teste");
        usuario.setSenha("senha");
        Date inicio = new Date(2023, 3, 27, 19, 10);
        Date fim = new Date(2023, 3, 27, 21, 10);
        Lancamento lancamento = new Lancamento();
        lancamento.setId(1L);
        lancamento.setDataHoraInicio(new Date());
        lancamento.setUsuario(usuario);
        lancamento.setDescricao("Prova");
        lancamento.setDuracao(2f);
        LancamentoDTO lancamentoDTO = new LancamentoDTO();
        lancamentoDTO.setDataHoraInicio(inicio);
        lancamentoDTO.setDataHoraFim(fim);
        lancamentoDTO.setDescricao("Prova");
        lancamentoDTO.setIdUsuario(1L);
        Mockito.when(lancamentoRepo.save(any())).thenReturn(lancamento);
        Mockito.when(usuarioRepo.findById(1L)).thenReturn(Optional.of(usuario));
        assertEquals("Prova", service.novoLancamento(lancamentoDTO).getDescricao());
    }

    @Test
    public void novoLancamentoInicioVazioNOk() {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNome("teste");
        usuario.setSenha("senha");
        LancamentoDTO lancamentoDTO = new LancamentoDTO();
        lancamentoDTO.setDataHoraInicio(null);
        lancamentoDTO.setDataHoraFim(new Date());
        lancamentoDTO.setDescricao("Prova");
        lancamentoDTO.setIdUsuario(1L);
        Mockito.when(usuarioRepo.findById(1L)).thenReturn(Optional.of(usuario));
        assertThrows(ResponseStatusException.class, () -> {
            service.novoLancamento(lancamentoDTO);
        });
    }

    @Test
    public void novoLancamentoFimVazioNOk() {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNome("teste");
        usuario.setSenha("senha");
        LancamentoDTO lancamentoDTO = new LancamentoDTO();
        lancamentoDTO.setDataHoraInicio(new Date());
        lancamentoDTO.setDataHoraFim(null);
        lancamentoDTO.setDescricao("Prova");
        lancamentoDTO.setIdUsuario(1L);
        Mockito.when(usuarioRepo.findById(1L)).thenReturn(Optional.of(usuario));
        assertThrows(ResponseStatusException.class, () -> {
            service.novoLancamento(lancamentoDTO);
        });
    }

    @Test
    public void novoLancamentoUsuarioNaoEncontradoNOk() {
        LancamentoDTO lancamentoDTO = new LancamentoDTO();
        lancamentoDTO.setDataHoraInicio(new Date());
        lancamentoDTO.setDataHoraFim(null);
        lancamentoDTO.setDescricao("Prova");
        lancamentoDTO.setIdUsuario(1L);
        Mockito.when(usuarioRepo.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ResponseStatusException.class, () -> {
            service.novoLancamento(lancamentoDTO);
        });
    }

}
