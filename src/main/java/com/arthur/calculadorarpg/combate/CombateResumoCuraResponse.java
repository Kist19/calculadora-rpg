package com.arthur.calculadorarpg.combate;

import java.util.List;

public class CombateResumoCuraResponse {

    private List<String> combateDadosBase;
    private List<String> combateDadosExtras;
    private String combateFormatado;
    private List<CombateFonteResponse> combateFontes;

    public CombateResumoCuraResponse() {
    }

    public List<String> getCombateDadosBase() {
        return combateDadosBase;
    }

    public void setCombateDadosBase(List<String> combateDadosBase) {
        this.combateDadosBase = combateDadosBase;
    }

    public List<String> getCombateDadosExtras() {
        return combateDadosExtras;
    }

    public void setCombateDadosExtras(List<String> combateDadosExtras) {
        this.combateDadosExtras = combateDadosExtras;
    }

    public String getCombateFormatado() {
        return combateFormatado;
    }

    public void setCombateFormatado(String combateFormatado) {
        this.combateFormatado = combateFormatado;
    }

    public List<CombateFonteResponse> getCombateFontes() {
        return combateFontes;
    }

    public void setCombateFontes(List<CombateFonteResponse> combateFontes) {
        this.combateFontes = combateFontes;
    }
}