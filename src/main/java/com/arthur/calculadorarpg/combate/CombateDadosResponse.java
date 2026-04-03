package com.arthur.calculadorarpg.combate;

import java.util.List;

public class CombateDadosResponse {

    private String combatePersonagemNome;
    private String combateArmaEquipadaNome;
    private CombateResumoAtaqueResponse combateTesteAtaque;
    private CombateResumoDanoResponse combateDano;
    private CombateResumoCuraResponse combateCura;
    private String combateCritico;
    private String combateAlcance;
    private List<CombateEfeitoAtivoResumoResponse> combateEfeitosAtivos;
    private CombateResumoTurnoResponse combateTurno;

    public CombateResumoTurnoResponse getCombateTurno() {
        return combateTurno;
    }

    public void setCombateTurno(CombateResumoTurnoResponse combateTurno) {
        this.combateTurno = combateTurno;
    }

    public CombateDadosResponse() {
    }

    public String getCombatePersonagemNome() {
        return combatePersonagemNome;
    }

    public void setCombatePersonagemNome(String combatePersonagemNome) {
        this.combatePersonagemNome = combatePersonagemNome;
    }

    public String getCombateArmaEquipadaNome() {
        return combateArmaEquipadaNome;
    }

    public void setCombateArmaEquipadaNome(String combateArmaEquipadaNome) {
        this.combateArmaEquipadaNome = combateArmaEquipadaNome;
    }

    public CombateResumoAtaqueResponse getCombateTesteAtaque() {
        return combateTesteAtaque;
    }

    public void setCombateTesteAtaque(CombateResumoAtaqueResponse combateTesteAtaque) {
        this.combateTesteAtaque = combateTesteAtaque;
    }

    public CombateResumoDanoResponse getCombateDano() {
        return combateDano;
    }

    public void setCombateDano(CombateResumoDanoResponse combateDano) {
        this.combateDano = combateDano;
    }

    public CombateResumoCuraResponse getCombateCura() {
        return combateCura;
    }

    public void setCombateCura(CombateResumoCuraResponse combateCura) {
        this.combateCura = combateCura;
    }

    public String getCombateCritico() {
        return combateCritico;
    }

    public void setCombateCritico(String combateCritico) {
        this.combateCritico = combateCritico;
    }

    public String getCombateAlcance() {
        return combateAlcance;
    }

    public void setCombateAlcance(String combateAlcance) {
        this.combateAlcance = combateAlcance;
    }

    public List<CombateEfeitoAtivoResumoResponse> getCombateEfeitosAtivos() {
        return combateEfeitosAtivos;
    }

    public void setCombateEfeitosAtivos(List<CombateEfeitoAtivoResumoResponse> combateEfeitosAtivos) {
        this.combateEfeitosAtivos = combateEfeitosAtivos;
    }
}