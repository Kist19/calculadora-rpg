package com.arthur.calculadorarpg.combate;

public class CombateFonteResponse {

    private String combateOrigemTipo;
    private String combateOrigemNome;
    private String combateTipoContribuicao;
    private String combateValor;

    public CombateFonteResponse() {
    }

    public CombateFonteResponse(String combateOrigemTipo, String combateOrigemNome,
            String combateTipoContribuicao, String combateValor) {
        this.combateOrigemTipo = combateOrigemTipo;
        this.combateOrigemNome = combateOrigemNome;
        this.combateTipoContribuicao = combateTipoContribuicao;
        this.combateValor = combateValor;
    }

    public String getCombateOrigemTipo() {
        return combateOrigemTipo;
    }

    public void setCombateOrigemTipo(String combateOrigemTipo) {
        this.combateOrigemTipo = combateOrigemTipo;
    }

    public String getCombateOrigemNome() {
        return combateOrigemNome;
    }

    public void setCombateOrigemNome(String combateOrigemNome) {
        this.combateOrigemNome = combateOrigemNome;
    }

    public String getCombateTipoContribuicao() {
        return combateTipoContribuicao;
    }

    public void setCombateTipoContribuicao(String combateTipoContribuicao) {
        this.combateTipoContribuicao = combateTipoContribuicao;
    }

    public String getCombateValor() {
        return combateValor;
    }

    public void setCombateValor(String combateValor) {
        this.combateValor = combateValor;
    }
}