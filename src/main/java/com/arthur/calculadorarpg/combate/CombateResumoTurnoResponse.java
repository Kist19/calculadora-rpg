package com.arthur.calculadorarpg.combate;

import java.util.List;

public class CombateResumoTurnoResponse {

    private List<String> combateDanosPorTurno;
    private List<String> combateCurasPorTurno;
    private List<CombateFonteResponse> combateFontes;

    public List<String> getCombateDanosPorTurno() {
        return combateDanosPorTurno;
    }

    public void setCombateDanosPorTurno(List<String> combateDanosPorTurno) {
        this.combateDanosPorTurno = combateDanosPorTurno;
    }

    public List<String> getCombateCurasPorTurno() {
        return combateCurasPorTurno;
    }

    public void setCombateCurasPorTurno(List<String> combateCurasPorTurno) {
        this.combateCurasPorTurno = combateCurasPorTurno;
    }

    public List<CombateFonteResponse> getCombateFontes() {
        return combateFontes;
    }

    public void setCombateFontes(List<CombateFonteResponse> combateFontes) {
        this.combateFontes = combateFontes;
    }
}