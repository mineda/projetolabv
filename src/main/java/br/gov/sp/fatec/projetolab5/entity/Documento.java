package br.gov.sp.fatec.projetolab5.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "dcm_documento")
public class Documento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dcm_id")
    private Long id;

    @Column(name = "dcm_tipo")
    private String tipo;

    @Column(name = "dcm_numero")
    private Long numero;

    @Column(name = "dcm_digito")
    private Integer digito;

    @Column(name = "dcm_data_emissao")
    private LocalDate dataEmissao;

    @Column(name = "dcm_data_expiracao")
    private LocalDate dataExpiracao;
    
    @ManyToOne
    @JoinColumn(name = "dcm_usr_id")
    private Usuario usuario;

    public Documento(String tipo, Long numero, LocalDate dataEmissao) {
        this.tipo = tipo;
        this.numero = numero;
        this.dataEmissao = dataEmissao;
    }

    public Documento() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public Integer getDigito() {
        return digito;
    }

    public void setDigito(Integer digito) {
        this.digito = digito;
    }

    public LocalDate getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(LocalDate dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public LocalDate getDataExpiracao() {
        return dataExpiracao;
    }

    public void setDataExpiracao(LocalDate dataExpiracao) {
        this.dataExpiracao = dataExpiracao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}