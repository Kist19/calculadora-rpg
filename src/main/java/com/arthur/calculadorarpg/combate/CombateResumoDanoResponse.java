package com.arthur.calculadorarpg.combate;

import java.util.List;

public class CombateResumoDanoResponse {

    private List<String> combateDadosBase;
    private List<String> combateDadosExtras;
    private Integer combateBonusTotal;
    private Integer combatePenalidadeTotal;
    private Integer combateModificadorFinal;
    private String combateFormatado;
    private List<CombateFonteResponse> combateFontes;

    public CombateResumoDanoResponse() {
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

    public Integer getCombateBonusTotal() {
        return combateBonusTotal;
    }

    public void setCombateBonusTotal(Integer combateBonusTotal) {
        this.combateBonusTotal = combateBonusTotal;
    }

    public Integer getCombatePenalidadeTotal() {
        return combatePenalidadeTotal;
    }

    public void setCombatePenalidadeTotal(Integer combatePenalidadeTotal) {
        this.combatePenalidadeTotal = combatePenalidadeTotal;
    }

    public Integer getCombateModificadorFinal() {
        return combateModificadorFinal;
    }

    public void setCombateModificadorFinal(Integer combateModificadorFinal) {
        this.combateModificadorFinal = combateModificadorFinal;
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