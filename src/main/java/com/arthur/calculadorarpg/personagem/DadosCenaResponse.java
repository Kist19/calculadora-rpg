package com.arthur.calculadorarpg.personagem;

import java.util.Map;

public class DadosCenaResponse {
    private String personagemNome;
    private Boolean personagemEmCombate;
    private Map<String, Integer> itensTurnosRestantesNaCena;
    private Map<String, Integer> habilidadesTurnosRestantesNaCena;
    private Object ultimaAcao;

    public String getPersonagemNome() {
        return personagemNome;
    }

    public void setPersonagemNome(String personagemNome) {
        this.personagemNome = personagemNome;
    }

    public Boolean getPersonagemEmCombate() {
        return personagemEmCombate;
    }

    public void setPersonagemEmCombate(Boolean personagemEmCombate) {
        this.personagemEmCombate = personagemEmCombate;
    }

    public Map<String, Integer> getItensTurnosRestantesNaCena() {
        return itensTurnosRestantesNaCena;
    }

    public void setItensTurnosRestantesNaCena(Map<String, Integer> itensTurnosRestantesNaCena) {
        this.itensTurnosRestantesNaCena = itensTurnosRestantesNaCena;
    }

    public Map<String, Integer> getHabilidadesTurnosRestantesNaCena() {
        return habilidadesTurnosRestantesNaCena;
    }

    public void setHabilidadesTurnosRestantesNaCena(Map<String, Integer> habilidadesTurnosRestantesNaCena) {
        this.habilidadesTurnosRestantesNaCena = habilidadesTurnosRestantesNaCena;
    }

    public Object getUltimaAcao() {
        return ultimaAcao;
    }

    public void setUltimaAcao(Object ultimaAcao) {
        this.ultimaAcao = ultimaAcao;
    }
}