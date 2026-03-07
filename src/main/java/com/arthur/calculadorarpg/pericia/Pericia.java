package com.arthur.calculadorarpg.pericia;

import jakarta.persistence.*;

import com.arthur.calculadorarpg.personagem.Personagem;

@Entity
@Table(name = "tb_pericia")
public class Pericia {
    @Id
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "personagem_id")
    private Personagem personagem;

    private Integer periciaReligiao = 0;
    private Integer periciaOficio = 0;
    private Integer periciaNobreza = 0; 
    private Integer periciaMisticismo = 0;
    private Integer periciaLuta = 0;
    private Integer periciaLadinagem = 0;
    private Integer periciaIntuicao = 0;
    private Integer periciaIntimidacao = 0;
    private Integer periciaGuerra = 0;
    private Integer periciaEnganacao = 0;
    private Integer periciaDiplomacia = 0;
    private Integer periciaCura = 0;
    private Integer periciaConhecimento = 0;
    private Integer periciaCavalgar = 0;
    private Integer periciaAtuacao = 0;
    private Integer periciaAcrobacia = 0;
    private Integer periciaReflexo = 0;
    private Integer periciaFortitude = 0;
    private Integer periciaVontade = 0;
    private Integer periciaAdestramento = 0;
    private Integer periciaAtletismo = 0;
    private Integer periciaFurtividade = 0;
    private Integer periciaIniciativa = 0;
    private Integer periciaInvestigacao = 0;
    private Integer periciaPercepcao = 0;
    private Integer periciaPontaria = 0;
    private Integer periciaSobrevivencia = 0;

    private Integer outrosReligiao = 0;
    private Integer outrosOficio = 0;
    private Integer outrosNobreza = 0; 
    private Integer outrosMisticismo = 0;
    private Integer outrosLuta = 0;
    private Integer outrosLadinagem = 0;
    private Integer outrosIntuicao = 0;
    private Integer outrosIntimidacao = 0;
    private Integer outrosGuerra = 0;
    private Integer outrosEnganacao = 0;
    private Integer outrosDiplomacia = 0;
    private Integer outrosCura = 0;
    private Integer outrosConhecimento = 0;
    private Integer outrosCavalgar = 0;
    private Integer outrosAtuacao = 0;
    private Integer outrosAcrobacia = 0;
    private Integer outrosReflexo = 0;
    private Integer outrosFortitude = 0;
    private Integer outrosVontade = 0;
    private Integer outrosAdestramento = 0;
    private Integer outrosAtletismo = 0;
    private Integer outrosFurtividade = 0;
    private Integer outrosIniciativa = 0;
    private Integer outrosInvestigacao = 0;
    private Integer outrosPercepcao = 0;
    private Integer outrosPontaria = 0;
    private Integer outrosSobrevivencia = 0;

    public Pericia() {

    }

    public Personagem getPersonagem() {
        return personagem;
    }

    public void setPersonagem(Personagem personagem) {
        this.personagem = personagem;
    }

    public Integer getPericiaReligiao() {
        return periciaReligiao;
    }

    public void setPericiaReligiao(Integer periciaReligiao) {
        this.periciaReligiao = periciaReligiao;
    }

    public Integer getPericiaOficio() {
        return periciaOficio;
    }

    public void setPericiaOficio(Integer periciaOficio) {
        this.periciaOficio = periciaOficio;
    }

    public Integer getPericiaNobreza() {
        return periciaNobreza;
    }

    public void setPericiaNobreza(Integer periciaNobreza) {
        this.periciaNobreza = periciaNobreza;
    }

    public Integer getPericiaMisticismo() {
        return periciaMisticismo;
    }

    public void setPericiaMisticismo(Integer periciaMisticismo) {
        this.periciaMisticismo = periciaMisticismo;
    }

    public Integer getPericiaLuta() {
        return periciaLuta;
    }

    public void setPericiaLuta(Integer periciaLuta) {
        this.periciaLuta = periciaLuta;
    }

    public Integer getPericiaLadinagem() {
        return periciaLadinagem;
    }

    public void setPericiaLadinagem(Integer periciaLadinagem) {
        this.periciaLadinagem = periciaLadinagem;
    }

    public Integer getPericiaIntuicao() {
        return periciaIntuicao;
    }

    public void setPericiaIntuicao(Integer periciaIntuicao) {
        this.periciaIntuicao = periciaIntuicao;
    }

    public Integer getPericiaIntimidacao() {
        return periciaIntimidacao;
    }

    public void setPericiaIntimidacao(Integer periciaIntimidacao) {
        this.periciaIntimidacao = periciaIntimidacao;
    }

    public Integer getPericiaGuerra() {
        return periciaGuerra;
    }

    public void setPericiaGuerra(Integer periciaGuerra) {
        this.periciaGuerra = periciaGuerra;
    }

    public Integer getPericiaEnganacao() {
        return periciaEnganacao;
    }

    public void setPericiaEnganacao(Integer periciaEnganacao) {
        this.periciaEnganacao = periciaEnganacao;
    }

    public Integer getPericiaDiplomacia() {
        return periciaDiplomacia;
    }

    public void setPericiaDiplomacia(Integer periciaDiplomacia) {
        this.periciaDiplomacia = periciaDiplomacia;
    }

    public Integer getPericiaCura() {
        return periciaCura;
    }

    public void setPericiaCura(Integer periciaCura) {
        this.periciaCura = periciaCura;
    }

    public Integer getPericiaConhecimento() {
        return periciaConhecimento;
    }

    public void setPericiaConhecimento(Integer periciaConhecimento) {
        this.periciaConhecimento = periciaConhecimento;
    }

    public Integer getPericiaCavalgar() {
        return periciaCavalgar;
    }

    public void setPericiaCavalgar(Integer periciaCavalgar) {
        this.periciaCavalgar = periciaCavalgar;
    }

    public Integer getPericiaAtuacao() {
        return periciaAtuacao;
    }

    public void setPericiaAtuacao(Integer periciaAtuacao) {
        this.periciaAtuacao = periciaAtuacao;
    }

    public Integer getPericiaAcrobacia() {
        return periciaAcrobacia;
    }

    public void setPericiaAcrobacia(Integer periciaAcrobacia) {
        this.periciaAcrobacia = periciaAcrobacia;
    }

    public Integer getPericiaReflexo() {
        return periciaReflexo;
    }

    public void setPericiaReflexo(Integer periciaReflexo) {
        this.periciaReflexo = periciaReflexo;
    }

    public Integer getPericiaFortitude() {
        return periciaFortitude;
    }

    public void setPericiaFortitude(Integer periciaFortitude) {
        this.periciaFortitude = periciaFortitude;
    }

    public Integer getPericiaVontade() {
        return periciaVontade;
    }

    public void setPericiaVontade(Integer periciaVontade) {
        this.periciaVontade = periciaVontade;
    }

    public Integer getPericiaAdestramento() {
        return periciaAdestramento;
    }

    public void setPericiaAdestramento(Integer periciaAdestramento) {
        this.periciaAdestramento = periciaAdestramento;
    }

    public Integer getPericiaAtletismo() {
        return periciaAtletismo;
    }

    public void setPericiaAtletismo(Integer periciaAtletismo) {
        this.periciaAtletismo = periciaAtletismo;
    }

    public Integer getPericiaFurtividade() {
        return periciaFurtividade;
    }

    public void setPericiaFurtividade(Integer periciaFurtividade) {
        this.periciaFurtividade = periciaFurtividade;
    }

    public Integer getPericiaIniciativa() {
        return periciaIniciativa;
    }

    public void setPericiaIniciativa(Integer periciaIniciativa) {
        this.periciaIniciativa = periciaIniciativa;
    }

    public Integer getPericiaInvestigacao() {
        return periciaInvestigacao;
    }

    public void setPericiaInvestigacao(Integer periciaInvestigacao) {
        this.periciaInvestigacao = periciaInvestigacao;
    }

    public Integer getPericiaPercepcao() {
        return periciaPercepcao;
    }

    public void setPericiaPercepcao(Integer periciaPercepcao) {
        this.periciaPercepcao = periciaPercepcao;
    }

    public Integer getPericiaPontaria() {
        return periciaPontaria;
    }

    public void setPericiaPontaria(Integer periciaPontaria) {
        this.periciaPontaria = periciaPontaria;
    }

    public Integer getPericiaSobrevivencia() {
        return periciaSobrevivencia;
    }

    public void setPericiaSobrevivencia(Integer periciaSobrevivencia) {
        this.periciaSobrevivencia = periciaSobrevivencia;
    }

    public Integer getOutrosReligiao() {
        return outrosReligiao;
    }

    public void setOutrosReligiao(Integer outrosReligiao) {
        this.outrosReligiao = outrosReligiao;
    }

    public Integer getOutrosOficio() {
        return outrosOficio;
    }

    public void setOutrosOficio(Integer outrosOficio) {
        this.outrosOficio = outrosOficio;
    }

    public Integer getOutrosNobreza() {
        return outrosNobreza;
    }

    public void setOutrosNobreza(Integer outrosNobreza) {
        this.outrosNobreza = outrosNobreza;
    }

    public Integer getOutrosMisticismo() {
        return outrosMisticismo;
    }

    public void setOutrosMisticismo(Integer outrosMisticismo) {
        this.outrosMisticismo = outrosMisticismo;
    }

    public Integer getOutrosLuta() {
        return outrosLuta;
    }

    public void setOutrosLuta(Integer outrosLuta) {
        this.outrosLuta = outrosLuta;
    }

    public Integer getOutrosLadinagem() {
        return outrosLadinagem;
    }

    public void setOutrosLadinagem(Integer outrosLadinagem) {
        this.outrosLadinagem = outrosLadinagem;
    }

    public Integer getOutrosIntuicao() {
        return outrosIntuicao;
    }

    public void setOutrosIntuicao(Integer outrosIntuicao) {
        this.outrosIntuicao = outrosIntuicao;
    }

    public Integer getOutrosIntimidacao() {
        return outrosIntimidacao;
    }

    public void setOutrosIntimidacao(Integer outrosIntimidacao) {
        this.outrosIntimidacao = outrosIntimidacao;
    }

    public Integer getOutrosGuerra() {
        return outrosGuerra;
    }

    public void setOutrosGuerra(Integer outrosGuerra) {
        this.outrosGuerra = outrosGuerra;
    }

    public Integer getOutrosEnganacao() {
        return outrosEnganacao;
    }

    public void setOutrosEnganacao(Integer outrosEnganacao) {
        this.outrosEnganacao = outrosEnganacao;
    }

    public Integer getOutrosDiplomacia() {
        return outrosDiplomacia;
    }

    public void setOutrosDiplomacia(Integer outrosDiplomacia) {
        this.outrosDiplomacia = outrosDiplomacia;
    }

    public Integer getOutrosCura() {
        return outrosCura;
    }

    public void setOutrosCura(Integer outrosCura) {
        this.outrosCura = outrosCura;
    }

    public Integer getOutrosConhecimento() {
        return outrosConhecimento;
    }

    public void setOutrosConhecimento(Integer outrosConhecimento) {
        this.outrosConhecimento = outrosConhecimento;
    }

    public Integer getOutrosCavalgar() {
        return outrosCavalgar;
    }

    public void setOutrosCavalgar(Integer outrosCavalgar) {
        this.outrosCavalgar = outrosCavalgar;
    }

    public Integer getOutrosAtuacao() {
        return outrosAtuacao;
    }

    public void setOutrosAtuacao(Integer outrosAtuacao) {
        this.outrosAtuacao = outrosAtuacao;
    }

    public Integer getOutrosAcrobacia() {
        return outrosAcrobacia;
    }

    public void setOutrosAcrobacia(Integer outrosAcrobacia) {
        this.outrosAcrobacia = outrosAcrobacia;
    }

    public Integer getOutrosReflexo() {
        return outrosReflexo;
    }

    public void setOutrosReflexo(Integer outrosReflexo) {
        this.outrosReflexo = outrosReflexo;
    }

    public Integer getOutrosFortitude() {
        return outrosFortitude;
    }

    public void setOutrosFortitude(Integer outrosFortitude) {
        this.outrosFortitude = outrosFortitude;
    }

    public Integer getOutrosVontade() {
        return outrosVontade;
    }

    public void setOutrosVontade(Integer outrosVontade) {
        this.outrosVontade = outrosVontade;
    }

    public Integer getOutrosAdestramento() {
        return outrosAdestramento;
    }

    public void setOutrosAdestramento(Integer outrosAdestramento) {
        this.outrosAdestramento = outrosAdestramento;
    }

    public Integer getOutrosAtletismo() {
        return outrosAtletismo;
    }

    public void setOutrosAtletismo(Integer outrosAtletismo) {
        this.outrosAtletismo = outrosAtletismo;
    }

    public Integer getOutrosFurtividade() {
        return outrosFurtividade;
    }

    public void setOutrosFurtividade(Integer outrosFurtividade) {
        this.outrosFurtividade = outrosFurtividade;
    }

    public Integer getOutrosIniciativa() {
        return outrosIniciativa;
    }

    public void setOutrosIniciativa(Integer outrosIniciativa) {
        this.outrosIniciativa = outrosIniciativa;
    }

    public Integer getOutrosInvestigacao() {
        return outrosInvestigacao;
    }

    public void setOutrosInvestigacao(Integer outrosInvestigacao) {
        this.outrosInvestigacao = outrosInvestigacao;
    }

    public Integer getOutrosPercepcao() {
        return outrosPercepcao;
    }

    public void setOutrosPercepcao(Integer outrosPercepcao) {
        this.outrosPercepcao = outrosPercepcao;
    }

    public Integer getOutrosPontaria() {
        return outrosPontaria;
    }

    public void setOutrosPontaria(Integer outrosPontaria) {
        this.outrosPontaria = outrosPontaria;
    }

    public Integer getOutrosSobrevivencia() {
        return outrosSobrevivencia;
    }

    public void setOutrosSobrevivencia(Integer outrosSobrevivencia) {
        this.outrosSobrevivencia = outrosSobrevivencia;
    }

    public Long getId() {
        return id;
    }

}
