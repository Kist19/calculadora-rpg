package com.arthur.calculadorarpg.combate;

public class CombateEfeitoAtivoResumoResponse {

    private String combateEfeitoAtivoNome;
    private String combateEfeitoAtivoIcone;
    private Integer combateEfeitoAtivoTurnoRestante;

    public CombateEfeitoAtivoResumoResponse() {
    }

    public CombateEfeitoAtivoResumoResponse(String combateEfeitoAtivoNome,
            String combateEfeitoAtivoIcone,
            Integer combateEfeitoAtivoTurnoRestante) {
        this.combateEfeitoAtivoNome = combateEfeitoAtivoNome;
        this.combateEfeitoAtivoIcone = combateEfeitoAtivoIcone;
        this.combateEfeitoAtivoTurnoRestante = combateEfeitoAtivoTurnoRestante;
    }

    public String getCombateEfeitoAtivoNome() {
        return combateEfeitoAtivoNome;
    }

    public void setCombateEfeitoAtivoNome(String combateEfeitoAtivoNome) {
        this.combateEfeitoAtivoNome = combateEfeitoAtivoNome;
    }

    public String getCombateEfeitoAtivoIcone() {
        return combateEfeitoAtivoIcone;
    }

    public void setCombateEfeitoAtivoIcone(String combateEfeitoAtivoIcone) {
        this.combateEfeitoAtivoIcone = combateEfeitoAtivoIcone;
    }

    public Integer getCombateEfeitoAtivoTurnoRestante() {
        return combateEfeitoAtivoTurnoRestante;
    }

    public void setCombateEfeitoAtivoTurnoRestante(Integer combateEfeitoAtivoTurnoRestante) {
        this.combateEfeitoAtivoTurnoRestante = combateEfeitoAtivoTurnoRestante;
    }
}