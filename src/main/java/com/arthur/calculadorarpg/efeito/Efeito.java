package com.arthur.calculadorarpg.efeito;

import com.arthur.calculadorarpg.efeitoativo.EfeitoAtivoIconeNome;
import com.arthur.calculadorarpg.efeitoativo.EfeitoAtivoTipoDuracao;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_efeito")
public class Efeito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30, nullable = false)
    private String efeitoNome;

    @Column(nullable = false)
    private Integer efeitoDuracaoTurno;

    @Column(nullable = false)
    private Integer efeitoBonusAtaque = 0;

    @Column(nullable = false)
    private Integer efeitoBonusDano = 0;

    @Column(nullable = false)
    private Integer efeitoBonusDefesa = 0;

    @Column(nullable = false)
    private Integer efeitoPenalidadeAtaque = 0;

    @Column(nullable = false)
    private Integer efeitoPenalidadeDano = 0;

    @Column(nullable = false)
    private Integer efeitoPenalidadeDefesa = 0;

    @Column(nullable = false)
    private Integer efeitoDanoPorTurno = 0;

    @Column(nullable = false)
    private Integer efeitoCuraPorTurno = 0;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EfeitoAtivoIconeNome efeitoIconeNome;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private EfeitoAtivoTipoDuracao efeitoTipoDuracao;

    public Efeito() {
    }

    public Long getId() {
        return id;
    }

    public String getEfeitoNome() {
        return efeitoNome;
    }

    public void setEfeitoNome(String efeitoNome) {
        this.efeitoNome = efeitoNome;
    }

    public Integer getEfeitoDuracaoTurno() {
        return efeitoDuracaoTurno;
    }

    public void setEfeitoDuracaoTurno(Integer efeitoDuracaoTurno) {
        this.efeitoDuracaoTurno = efeitoDuracaoTurno;
    }

    public Integer getEfeitoBonusAtaque() {
        return efeitoBonusAtaque;
    }

    public void setEfeitoBonusAtaque(Integer efeitoBonusAtaque) {
        this.efeitoBonusAtaque = efeitoBonusAtaque;
    }

    public Integer getEfeitoBonusDano() {
        return efeitoBonusDano;
    }

    public void setEfeitoBonusDano(Integer efeitoBonusDano) {
        this.efeitoBonusDano = efeitoBonusDano;
    }

    public Integer getEfeitoBonusDefesa() {
        return efeitoBonusDefesa;
    }

    public void setEfeitoBonusDefesa(Integer efeitoBonusDefesa) {
        this.efeitoBonusDefesa = efeitoBonusDefesa;
    }

    public Integer getEfeitoPenalidadeAtaque() {
        return efeitoPenalidadeAtaque;
    }

    public void setEfeitoPenalidadeAtaque(Integer efeitoPenalidadeAtaque) {
        this.efeitoPenalidadeAtaque = efeitoPenalidadeAtaque;
    }

    public Integer getEfeitoPenalidadeDano() {
        return efeitoPenalidadeDano;
    }

    public void setEfeitoPenalidadeDano(Integer efeitoPenalidadeDano) {
        this.efeitoPenalidadeDano = efeitoPenalidadeDano;
    }

    public Integer getEfeitoPenalidadeDefesa() {
        return efeitoPenalidadeDefesa;
    }

    public void setEfeitoPenalidadeDefesa(Integer efeitoPenalidadeDefesa) {
        this.efeitoPenalidadeDefesa = efeitoPenalidadeDefesa;
    }

    public Integer getEfeitoDanoPorTurno() {
        return efeitoDanoPorTurno;
    }

    public void setEfeitoDanoPorTurno(Integer efeitoDanoPorTurno) {
        this.efeitoDanoPorTurno = efeitoDanoPorTurno;
    }

    public Integer getEfeitoCuraPorTurno() {
        return efeitoCuraPorTurno;
    }

    public void setEfeitoCuraPorTurno(Integer efeitoCuraPorTurno) {
        this.efeitoCuraPorTurno = efeitoCuraPorTurno;
    }

    public EfeitoAtivoIconeNome getEfeitoIconeNome() {
        return efeitoIconeNome;
    }

    public void setEfeitoIconeNome(EfeitoAtivoIconeNome efeitoIconeNome) {
        this.efeitoIconeNome = efeitoIconeNome;
    }

    public EfeitoAtivoTipoDuracao getEfeitoTipoDuracao() {
        return efeitoTipoDuracao;
    }

    public void setEfeitoTipoDuracao(EfeitoAtivoTipoDuracao efeitoTipoDuracao) {
        this.efeitoTipoDuracao = efeitoTipoDuracao;
    }
}