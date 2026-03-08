package com.arthur.calculadorarpg.pericia;

import org.springframework.stereotype.Service;

import com.arthur.calculadorarpg.atributo.Atributo;
import com.arthur.calculadorarpg.personagem.Personagem;
import com.arthur.calculadorarpg.personagem.PersonagemRepository;

import java.util.HashMap;
import java.util.Map;

@Service
public class PericiaService {
        private final PericiaRepository periciaRepository;
        private final PersonagemRepository personagemRepository;

        public PericiaService(PericiaRepository periciaRepository, PersonagemRepository personagemRepository) {
                this.periciaRepository = periciaRepository;
                this.personagemRepository = personagemRepository;
        }

        public Pericia criarPericia(Pericia pericia) {
                return periciaRepository.save(pericia);
        }

        public java.util.List<Pericia> listarPericias() {
                return periciaRepository.findAll();
        }

        private int safe(Integer value) {
                return value == null ? 0 : value;
        }

        public Map<String, Integer> calcularPericias(Personagem personagem) {

                Atributo atributo = personagem.getAtributo();
                Pericia pericia = personagem.getPericia();

                int metadeNivel = personagem.getPersonagemNivel() / 2;

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

        public Pericia atualizarPericia(Long id, Pericia dadosAtualizados) {

                Pericia pericia = periciaRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Perícia não encontrada"));

                if (dadosAtualizados.getPericiaAcrobacia() != null) {
                        pericia.setPericiaAcrobacia(dadosAtualizados.getPericiaAcrobacia());
                }

                if (dadosAtualizados.getPericiaAdestramento() != null) {
                        pericia.setPericiaAdestramento(dadosAtualizados.getPericiaAdestramento());
                }

                if (dadosAtualizados.getPericiaAtletismo() != null) {
                        pericia.setPericiaAtletismo(dadosAtualizados.getPericiaAtletismo());
                }

                if (dadosAtualizados.getPericiaAtuacao() != null) {
                        pericia.setPericiaAtuacao(dadosAtualizados.getPericiaAtuacao());
                }

                if (dadosAtualizados.getPericiaCavalgar() != null) {
                        pericia.setPericiaCavalgar(dadosAtualizados.getPericiaCavalgar());
                }

                if (dadosAtualizados.getPericiaConhecimento() != null) {
                        pericia.setPericiaConhecimento(dadosAtualizados.getPericiaConhecimento());
                }

                if (dadosAtualizados.getPericiaCura() != null) {
                        pericia.setPericiaCura(dadosAtualizados.getPericiaCura());
                }

                if (dadosAtualizados.getPericiaDiplomacia() != null) {
                        pericia.setPericiaDiplomacia(dadosAtualizados.getPericiaDiplomacia());
                }

                if (dadosAtualizados.getPericiaEnganacao() != null) {
                        pericia.setPericiaEnganacao(dadosAtualizados.getPericiaEnganacao());
                }

                if (dadosAtualizados.getPericiaFortitude() != null) {
                        pericia.setPericiaFortitude(dadosAtualizados.getPericiaFortitude());
                }

                if (dadosAtualizados.getPericiaFurtividade() != null) {
                        pericia.setPericiaFurtividade(dadosAtualizados.getPericiaFurtividade());
                }

                if (dadosAtualizados.getPericiaGuerra() != null) {
                        pericia.setPericiaGuerra(dadosAtualizados.getPericiaGuerra());
                }

                if (dadosAtualizados.getPericiaIniciativa() != null) {
                        pericia.setPericiaIniciativa(dadosAtualizados.getPericiaIniciativa());
                }

                if (dadosAtualizados.getPericiaIntimidacao() != null) {
                        pericia.setPericiaIntimidacao(dadosAtualizados.getPericiaIntimidacao());
                }

                if (dadosAtualizados.getPericiaIntuicao() != null) {
                        pericia.setPericiaIntuicao(dadosAtualizados.getPericiaIntuicao());
                }

                if (dadosAtualizados.getPericiaInvestigacao() != null) {
                        pericia.setPericiaInvestigacao(dadosAtualizados.getPericiaInvestigacao());
                }

                if (dadosAtualizados.getPericiaLadinagem() != null) {
                        pericia.setPericiaLadinagem(dadosAtualizados.getPericiaLadinagem());
                }

                if (dadosAtualizados.getPericiaLuta() != null) {
                        pericia.setPericiaLuta(dadosAtualizados.getPericiaLuta());
                }

                if (dadosAtualizados.getPericiaMisticismo() != null) {
                        pericia.setPericiaMisticismo(dadosAtualizados.getPericiaMisticismo());
                }

                if (dadosAtualizados.getPericiaNobreza() != null) {
                        pericia.setPericiaNobreza(dadosAtualizados.getPericiaNobreza());
                }

                if (dadosAtualizados.getPericiaOficio() != null) {
                        pericia.setPericiaOficio(dadosAtualizados.getPericiaOficio());
                }

                if (dadosAtualizados.getPericiaPercepcao() != null) {
                        pericia.setPericiaPercepcao(dadosAtualizados.getPericiaPercepcao());
                }

                if (dadosAtualizados.getPericiaPontaria() != null) {
                        pericia.setPericiaPontaria(dadosAtualizados.getPericiaPontaria());
                }

                if (dadosAtualizados.getPericiaReflexo() != null) {
                        pericia.setPericiaReflexo(dadosAtualizados.getPericiaReflexo());
                }

                if (dadosAtualizados.getPericiaReligiao() != null) {
                        pericia.setPericiaReligiao(dadosAtualizados.getPericiaReligiao());
                }

                if (dadosAtualizados.getPericiaSobrevivencia() != null) {
                        pericia.setPericiaSobrevivencia(dadosAtualizados.getPericiaSobrevivencia());
                }

                if (dadosAtualizados.getPericiaVontade() != null) {
                        pericia.setPericiaVontade(dadosAtualizados.getPericiaVontade());
                }

                return periciaRepository.save(pericia);
        }

        public void deletarPericia(Long id) {

                Pericia pericia = periciaRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Perícia não encontrada"));

                Personagem personagem = pericia.getPersonagem();

                if (personagem != null) {
                        personagem.setPericia(null);
                        personagemRepository.save(personagem);
                }

                periciaRepository.delete(pericia);
        }
}
