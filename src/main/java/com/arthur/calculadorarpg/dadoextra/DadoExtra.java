package com.arthur.calculadorarpg.dadoextra;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
@Table(name = "tb_dado_extra")
public class DadoExtra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30, nullable = false)
    private String dadoExtraNome;

    @Column(nullable = false)
    private Integer dadoExtraQuantidade;

    @Enumerated(EnumType.STRING)
    @Column(length = 10, nullable = false)
    private DadoExtraTipoDado dadoExtraTipoDado;

    @Column(length = 80, nullable = true)
    private String dadoExtraDescricao;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private DadoExtraContexto dadoExtraContexto;

    @Enumerated(EnumType.STRING)
    @Column( length = 30, nullable = false)
    private DadoExtraAplicacao dadoExtraAplicacao;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private DadoExtraOrigemTipo dadoExtraOrigemTipo;

    @Column(nullable = false)
    private Long dadoExtraOrigemId;

    @Column(nullable = false)
    private Boolean dadoExtraLigado;

    @Column(nullable = false)
    private Integer dadoExtraOrdem;

    public DadoExtra() {
    }

    public Long getId() {
        return id;
    }

    public String getDadoExtraNome() {
        return dadoExtraNome;
    }

    public void setDadoExtraNome(String dadoExtraNome) {
        this.dadoExtraNome = dadoExtraNome;
    }

    public Integer getDadoExtraQuantidade() {
        return dadoExtraQuantidade;
    }

    public void setDadoExtraQuantidade(Integer dadoExtraQuantidade) {
        this.dadoExtraQuantidade = dadoExtraQuantidade;
    }

    public DadoExtraTipoDado getDadoExtraTipoDado() {
        return dadoExtraTipoDado;
    }

    public void setDadoExtraTipoDado(DadoExtraTipoDado dadoExtraTipoDado) {
        this.dadoExtraTipoDado = dadoExtraTipoDado;
    }

    public String getDadoExtraDescricao() {
        return dadoExtraDescricao;
    }

    public void setDadoExtraDescricao(String dadoExtraDescricao) {
        this.dadoExtraDescricao = dadoExtraDescricao;
    }

    public DadoExtraContexto getDadoExtraContexto() {
        return dadoExtraContexto;
    }

    public void setDadoExtraContexto(DadoExtraContexto dadoExtraContexto) {
        this.dadoExtraContexto = dadoExtraContexto;
    }

    public DadoExtraAplicacao getDadoExtraAplicacao() {
        return dadoExtraAplicacao;
    }

    public void setDadoExtraAplicacao(DadoExtraAplicacao dadoExtraAplicacao) {
        this.dadoExtraAplicacao = dadoExtraAplicacao;
    }

    public DadoExtraOrigemTipo getDadoExtraOrigemTipo() {
        return dadoExtraOrigemTipo;
    }

    public void setDadoExtraOrigemTipo(DadoExtraOrigemTipo dadoExtraOrigemTipo) {
        this.dadoExtraOrigemTipo = dadoExtraOrigemTipo;
    }

    public Long getDadoExtraOrigemId() {
        return dadoExtraOrigemId;
    }

    public void setDadoExtraOrigemId(Long dadoExtraOrigemId) {
        this.dadoExtraOrigemId = dadoExtraOrigemId;
    }

    public Boolean getDadoExtraLigado() {
        return dadoExtraLigado;
    }

    public void setDadoExtraLigado(Boolean dadoExtraLigado) {
        this.dadoExtraLigado = dadoExtraLigado;
    }

    public Integer getDadoExtraOrdem() {
        return dadoExtraOrdem;
    }

    public void setDadoExtraOrdem(Integer dadoExtraOrdem) {
        this.dadoExtraOrdem = dadoExtraOrdem;
    }

    public String getDadoExtraFormatado() {
        String dadoFormatado = dadoExtraQuantidade + dadoExtraTipoDado.name();

        if (dadoExtraDescricao == null || dadoExtraDescricao.isBlank()) {
            return dadoFormatado;
        }

        return dadoFormatado + " " + dadoExtraDescricao;
    }
}