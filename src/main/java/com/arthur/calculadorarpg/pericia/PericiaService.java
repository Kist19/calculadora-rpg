package com.arthur.calculadorarpg.pericia;

import org.springframework.stereotype.Service;

import com.arthur.calculadorarpg.atributo.Atributo;
import com.arthur.calculadorarpg.personagem.Personagem;

import java.util.HashMap;
import java.util.Map;

@Service
public class PericiaService {
    private final PericiaRepository periciaRepositorio;

    public PericiaService(PericiaRepository periciaRepositorio) {
        this.periciaRepositorio = periciaRepositorio;
    }

    public Pericia criarPericia(Pericia pericia) {
        return periciaRepositorio.save(pericia);
    }

    public java.util.List<Pericia> listarPericias() {
        return periciaRepositorio.findAll();
    }

    private int safe(Integer value) {
        return value == null ? 0 : value;
    }

    public Map<String, Integer> calcularPericias(Personagem personagem) {

        Atributo atributo = personagem.getAtributo();
        Pericia pericia = personagem.getPericia();

        int metadeNivel = personagem.getNivelPersonagem() / 2;

        Map<String, Integer> resultado = new HashMap<>();

        resultado.put("Reflexo",
                atributo.getAtributoDestreza()
                        + metadeNivel
                        + safe(pericia.getPericiaReflexo())
                        + safe(pericia.getOutrosReflexo()));

        resultado.put("Fortitude",
                atributo.getAtributoConstituicao()
                        + metadeNivel
                        + safe(pericia.getPericiaFortitude())
                        + safe(pericia.getOutrosFortitude()));

        resultado.put("Vontade",
                atributo.getAtributoSabedoria()
                        + metadeNivel
                        + safe(pericia.getPericiaVontade())
                        + safe(pericia.getOutrosVontade()));

        resultado.put("Adestramento",
                atributo.getAtributoCarisma()
                        + metadeNivel
                        + safe(pericia.getPericiaAdestramento())
                        + safe(pericia.getOutrosAdestramento()));

        resultado.put("Atletismo",
                atributo.getAtributoForca()
                        + metadeNivel
                        + safe(pericia.getPericiaAtletismo())
                        + safe(pericia.getOutrosAtletismo()));

        resultado.put("Furtividade",
                atributo.getAtributoDestreza()
                        + metadeNivel
                        + safe(pericia.getPericiaFurtividade())
                        + safe(pericia.getOutrosFurtividade()));

        resultado.put("Iniciativa",
                atributo.getAtributoDestreza()
                        + metadeNivel
                        + safe(pericia.getPericiaIniciativa())
                        + safe(pericia.getOutrosIniciativa()));

        resultado.put("Investigacao",
                atributo.getAtributoInteligencia()
                        + metadeNivel
                        + safe(pericia.getPericiaInvestigacao())
                        + safe(pericia.getOutrosInvestigacao()));

        resultado.put("Percepcao",
                atributo.getAtributoSabedoria()
                        + metadeNivel
                        + safe(pericia.getPericiaPercepcao())
                        + safe(pericia.getOutrosPercepcao()));

        resultado.put("Pontaria",
                atributo.getAtributoDestreza()
                        + metadeNivel
                        + safe(pericia.getPericiaPontaria())
                        + safe(pericia.getOutrosPontaria()));

        resultado.put("Sobrevivencia",
                atributo.getAtributoSabedoria()
                        + metadeNivel
                        + safe(pericia.getPericiaSobrevivencia())
                        + safe(pericia.getOutrosSobrevivencia()));

        return resultado;
    }
}
