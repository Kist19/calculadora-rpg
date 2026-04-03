package com.arthur.calculadorarpg.pericia;

import org.springframework.stereotype.Service;

import com.arthur.calculadorarpg.arma.ArmaTipoPericia;
import com.arthur.calculadorarpg.atributo.Atributo;
import com.arthur.calculadorarpg.personagem.Personagem;
import com.arthur.calculadorarpg.personagem.PersonagemRepository;

import java.util.LinkedHashMap;
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
                if (pericia.getPersonagem() == null || pericia.getPersonagem().getId() == null) {
                        throw new RuntimeException("Personagem é obrigatório");
                }

                Long personagemId = pericia.getPersonagem().getId();

                Personagem personagem = personagemRepository.findById(personagemId)
                                .orElseThrow(() -> new RuntimeException("Personagem não encontrado"));

                pericia.setPersonagem(personagem);

                return periciaRepository.save(pericia);
        }

        public java.util.List<Pericia> listarPericias() {
                return periciaRepository.findAll();
        }

        private int safe(Integer value) {
                return value == null ? 0 : value;
        }

        private int metadeNivel(Personagem personagem) {
                return personagem.getPersonagemNivel() / 2;
        }

        private void validarPersonagemParaCalculo(Personagem personagem) {
                if (personagem == null) {
                        throw new RuntimeException("Personagem não informado");
                }

                if (personagem.getAtributo() == null) {
                        throw new RuntimeException("Atributo não encontrado para o personagem");
                }

                if (personagem.getPericia() == null) {
                        throw new RuntimeException("Perícia não encontrada para o personagem");
                }
        }

        public Map<String, Integer> calcularPericias(Personagem personagem) {

                validarPersonagemParaCalculo(personagem);

                Atributo atributo = personagem.getAtributo();
                Pericia pericia = personagem.getPericia();
                int metadeNivel = metadeNivel(personagem);

                Map<String, Integer> resultado = new LinkedHashMap<>();

                adicionarSeTreinado(resultado, "Acrobacia",
                                pericia.getPericiaAcrobacia(),
                                safe(atributo.getAtributoDestreza()) + metadeNivel + safe(pericia.getPericiaAcrobacia())
                                                + safe(pericia.getOutrosAcrobacia()));

                adicionarSeTreinado(resultado, "Adestramento",
                                pericia.getPericiaAdestramento(),
                                safe(atributo.getAtributoCarisma()) + metadeNivel
                                                + safe(pericia.getPericiaAdestramento())
                                                + safe(pericia.getOutrosAdestramento()));

                adicionarSeTreinado(resultado, "Atletismo",
                                pericia.getPericiaAtletismo(),
                                safe(atributo.getAtributoForca()) + metadeNivel + safe(pericia.getPericiaAtletismo())
                                                + safe(pericia.getOutrosAtletismo()));

                adicionarSeTreinado(resultado, "Atuacao",
                                pericia.getPericiaAtuacao(),
                                safe(atributo.getAtributoCarisma()) + metadeNivel + safe(pericia.getPericiaAtuacao())
                                                + safe(pericia.getOutrosAtuacao()));

                adicionarSeTreinado(resultado, "Cavalgar",
                                pericia.getPericiaCavalgar(),
                                safe(atributo.getAtributoDestreza()) + metadeNivel + safe(pericia.getPericiaCavalgar())
                                                + safe(pericia.getOutrosCavalgar()));

                adicionarSeTreinado(resultado, "Conhecimento",
                                pericia.getPericiaConhecimento(),
                                safe(atributo.getAtributoInteligencia()) + metadeNivel
                                                + safe(pericia.getPericiaConhecimento())
                                                + safe(pericia.getOutrosConhecimento()));

                adicionarSeTreinado(resultado, "Cura",
                                pericia.getPericiaCura(),
                                safe(atributo.getAtributoSabedoria()) + metadeNivel + safe(pericia.getPericiaCura())
                                                + safe(pericia.getOutrosCura()));

                adicionarSeTreinado(resultado, "Diplomacia",
                                pericia.getPericiaDiplomacia(),
                                safe(atributo.getAtributoCarisma()) + metadeNivel + safe(pericia.getPericiaDiplomacia())
                                                + safe(pericia.getOutrosDiplomacia()));

                adicionarSeTreinado(resultado, "Enganacao",
                                pericia.getPericiaEnganacao(),
                                safe(atributo.getAtributoCarisma()) + metadeNivel + safe(pericia.getPericiaEnganacao())
                                                + safe(pericia.getOutrosEnganacao()));

                adicionarSeTreinado(resultado, "Fortitude",
                                pericia.getPericiaFortitude(),
                                safe(atributo.getAtributoConstituicao()) + metadeNivel
                                                + safe(pericia.getPericiaFortitude())
                                                + safe(pericia.getOutrosFortitude()));

                adicionarSeTreinado(resultado, "Furtividade",
                                pericia.getPericiaFurtividade(),
                                safe(atributo.getAtributoDestreza()) + metadeNivel
                                                + safe(pericia.getPericiaFurtividade())
                                                + safe(pericia.getOutrosFurtividade()));

                adicionarSeTreinado(resultado, "Guerra",
                                pericia.getPericiaGuerra(),
                                safe(atributo.getAtributoInteligencia()) + metadeNivel
                                                + safe(pericia.getPericiaGuerra()) + safe(pericia.getOutrosGuerra()));

                adicionarSeTreinado(resultado, "Iniciativa",
                                pericia.getPericiaIniciativa(),
                                safe(atributo.getAtributoDestreza()) + metadeNivel
                                                + safe(pericia.getPericiaIniciativa())
                                                + safe(pericia.getOutrosIniciativa()));

                adicionarSeTreinado(resultado, "Intimidacao",
                                pericia.getPericiaIntimidacao(),
                                safe(atributo.getAtributoCarisma()) + metadeNivel
                                                + safe(pericia.getPericiaIntimidacao())
                                                + safe(pericia.getOutrosIntimidacao()));

                adicionarSeTreinado(resultado, "Intuicao",
                                pericia.getPericiaIntuicao(),
                                safe(atributo.getAtributoSabedoria()) + metadeNivel + safe(pericia.getPericiaIntuicao())
                                                + safe(pericia.getOutrosIntuicao()));

                adicionarSeTreinado(resultado, "Investigacao",
                                pericia.getPericiaInvestigacao(),
                                safe(atributo.getAtributoInteligencia()) + metadeNivel
                                                + safe(pericia.getPericiaInvestigacao())
                                                + safe(pericia.getOutrosInvestigacao()));

                adicionarSeTreinado(resultado, "Ladinagem",
                                pericia.getPericiaLadinagem(),
                                safe(atributo.getAtributoDestreza()) + metadeNivel + safe(pericia.getPericiaLadinagem())
                                                + safe(pericia.getOutrosLadinagem()));

                adicionarSeTreinado(resultado, "Luta",
                                pericia.getPericiaLuta(),
                                safe(atributo.getAtributoForca()) + metadeNivel + safe(pericia.getPericiaLuta())
                                                + safe(pericia.getOutrosLuta()));

                adicionarSeTreinado(resultado, "Misticismo",
                                pericia.getPericiaMisticismo(),
                                safe(atributo.getAtributoInteligencia()) + metadeNivel
                                                + safe(pericia.getPericiaMisticismo())
                                                + safe(pericia.getOutrosMisticismo()));

                adicionarSeTreinado(resultado, "Nobreza",
                                pericia.getPericiaNobreza(),
                                safe(atributo.getAtributoInteligencia()) + metadeNivel
                                                + safe(pericia.getPericiaNobreza()) + safe(pericia.getOutrosNobreza()));

                adicionarSeTreinado(resultado, "Oficio",
                                pericia.getPericiaOficio(),
                                safe(atributo.getAtributoInteligencia()) + metadeNivel
                                                + safe(pericia.getPericiaOficio()) + safe(pericia.getOutrosOficio()));

                adicionarSeTreinado(resultado, "Percepcao",
                                pericia.getPericiaPercepcao(),
                                safe(atributo.getAtributoSabedoria()) + metadeNivel
                                                + safe(pericia.getPericiaPercepcao())
                                                + safe(pericia.getOutrosPercepcao()));

                adicionarSeTreinado(resultado, "Pontaria",
                                pericia.getPericiaPontaria(),
                                safe(atributo.getAtributoDestreza()) + metadeNivel + safe(pericia.getPericiaPontaria())
                                                + safe(pericia.getOutrosPontaria()));

                adicionarSeTreinado(resultado, "Reflexo",
                                pericia.getPericiaReflexo(),
                                safe(atributo.getAtributoDestreza()) + metadeNivel + safe(pericia.getPericiaReflexo())
                                                + safe(pericia.getOutrosReflexo()));

                adicionarSeTreinado(resultado, "Religiao",
                                pericia.getPericiaReligiao(),
                                safe(atributo.getAtributoSabedoria()) + metadeNivel + safe(pericia.getPericiaReligiao())
                                                + safe(pericia.getOutrosReligiao()));

                adicionarSeTreinado(resultado, "Sobrevivencia",
                                pericia.getPericiaSobrevivencia(),
                                safe(atributo.getAtributoSabedoria()) + metadeNivel
                                                + safe(pericia.getPericiaSobrevivencia())
                                                + safe(pericia.getOutrosSobrevivencia()));

                adicionarSeTreinado(resultado, "Vontade",
                                pericia.getPericiaVontade(),
                                safe(atributo.getAtributoSabedoria()) + metadeNivel + safe(pericia.getPericiaVontade())
                                                + safe(pericia.getOutrosVontade()));

                return resultado;
        }

        private void adicionarSeTreinado(Map<String, Integer> resultado, String nome, Integer treinamento,
                        Integer valor) {
                if (safe(treinamento) > 0) {
                        resultado.put(nome, valor);
                }
        }

        public Integer calcularPericiaPorNome(Personagem personagem, String nomePericia) {
                Map<String, Integer> periciasCalculadas = calcularPericias(personagem);

                Integer valor = periciasCalculadas.get(nomePericia);

                return valor == null ? 0 : valor;
        }

        public Integer calcularPericiaCombate(Personagem personagem, ArmaTipoPericia armaTipoPericia) {
                if (armaTipoPericia == null) {
                        return 0;
                }

                switch (armaTipoPericia) {
                        case PONTARIA:
                                return calcularPericiaPorNome(personagem, "Pontaria");
                        case LUTA:
                                return calcularPericiaPorNome(personagem, "Luta");
                        case MISTICISMO:
                                return calcularPericiaPorNome(personagem, "Misticismo");
                        default:
                                return 0;
                }
        }

        public Pericia criarPericiaPorPersonagemId(Long personagemId, Pericia pericia) {
                Personagem personagem = personagemRepository.findById(personagemId)
                                .orElseThrow(() -> new RuntimeException("Personagem não encontrado"));

                pericia.setPersonagem(personagem);

                return periciaRepository.save(pericia);
        }

        public Pericia buscarPericiaPorPersonagemId(Long personagemId) {
                Personagem personagem = personagemRepository.findById(personagemId)
                                .orElseThrow(() -> new RuntimeException("Personagem não encontrado"));

                Pericia pericia = personagem.getPericia();

                if (pericia == null) {
                        throw new RuntimeException("Perícia não encontrada para o personagem");
                }

                return pericia;
        }

        public Pericia atualizarPericiaPorPersonagemId(Long personagemId, Pericia dadosAtualizados) {
                Personagem personagem = personagemRepository.findById(personagemId)
                                .orElseThrow(() -> new RuntimeException("Personagem não encontrado"));

                Pericia pericia = personagem.getPericia();

                if (pericia == null) {
                        throw new RuntimeException("Perícia não encontrada para o personagem");
                }

                atualizarTreinamentos(pericia, dadosAtualizados);
                atualizarOutros(pericia, dadosAtualizados);

                return periciaRepository.save(pericia);
        }

        private void atualizarTreinamentos(Pericia pericia, Pericia dadosAtualizados) {
                if (dadosAtualizados.getPericiaAcrobacia() != null)
                        pericia.setPericiaAcrobacia(dadosAtualizados.getPericiaAcrobacia());
                if (dadosAtualizados.getPericiaAdestramento() != null)
                        pericia.setPericiaAdestramento(dadosAtualizados.getPericiaAdestramento());
                if (dadosAtualizados.getPericiaAtletismo() != null)
                        pericia.setPericiaAtletismo(dadosAtualizados.getPericiaAtletismo());
                if (dadosAtualizados.getPericiaAtuacao() != null)
                        pericia.setPericiaAtuacao(dadosAtualizados.getPericiaAtuacao());
                if (dadosAtualizados.getPericiaCavalgar() != null)
                        pericia.setPericiaCavalgar(dadosAtualizados.getPericiaCavalgar());
                if (dadosAtualizados.getPericiaConhecimento() != null)
                        pericia.setPericiaConhecimento(dadosAtualizados.getPericiaConhecimento());
                if (dadosAtualizados.getPericiaCura() != null)
                        pericia.setPericiaCura(dadosAtualizados.getPericiaCura());
                if (dadosAtualizados.getPericiaDiplomacia() != null)
                        pericia.setPericiaDiplomacia(dadosAtualizados.getPericiaDiplomacia());
                if (dadosAtualizados.getPericiaEnganacao() != null)
                        pericia.setPericiaEnganacao(dadosAtualizados.getPericiaEnganacao());
                if (dadosAtualizados.getPericiaFortitude() != null)
                        pericia.setPericiaFortitude(dadosAtualizados.getPericiaFortitude());
                if (dadosAtualizados.getPericiaFurtividade() != null)
                        pericia.setPericiaFurtividade(dadosAtualizados.getPericiaFurtividade());
                if (dadosAtualizados.getPericiaGuerra() != null)
                        pericia.setPericiaGuerra(dadosAtualizados.getPericiaGuerra());
                if (dadosAtualizados.getPericiaIniciativa() != null)
                        pericia.setPericiaIniciativa(dadosAtualizados.getPericiaIniciativa());
                if (dadosAtualizados.getPericiaIntimidacao() != null)
                        pericia.setPericiaIntimidacao(dadosAtualizados.getPericiaIntimidacao());
                if (dadosAtualizados.getPericiaIntuicao() != null)
                        pericia.setPericiaIntuicao(dadosAtualizados.getPericiaIntuicao());
                if (dadosAtualizados.getPericiaInvestigacao() != null)
                        pericia.setPericiaInvestigacao(dadosAtualizados.getPericiaInvestigacao());
                if (dadosAtualizados.getPericiaLadinagem() != null)
                        pericia.setPericiaLadinagem(dadosAtualizados.getPericiaLadinagem());
                if (dadosAtualizados.getPericiaLuta() != null)
                        pericia.setPericiaLuta(dadosAtualizados.getPericiaLuta());
                if (dadosAtualizados.getPericiaMisticismo() != null)
                        pericia.setPericiaMisticismo(dadosAtualizados.getPericiaMisticismo());
                if (dadosAtualizados.getPericiaNobreza() != null)
                        pericia.setPericiaNobreza(dadosAtualizados.getPericiaNobreza());
                if (dadosAtualizados.getPericiaOficio() != null)
                        pericia.setPericiaOficio(dadosAtualizados.getPericiaOficio());
                if (dadosAtualizados.getPericiaPercepcao() != null)
                        pericia.setPericiaPercepcao(dadosAtualizados.getPericiaPercepcao());
                if (dadosAtualizados.getPericiaPontaria() != null)
                        pericia.setPericiaPontaria(dadosAtualizados.getPericiaPontaria());
                if (dadosAtualizados.getPericiaReflexo() != null)
                        pericia.setPericiaReflexo(dadosAtualizados.getPericiaReflexo());
                if (dadosAtualizados.getPericiaReligiao() != null)
                        pericia.setPericiaReligiao(dadosAtualizados.getPericiaReligiao());
                if (dadosAtualizados.getPericiaSobrevivencia() != null)
                        pericia.setPericiaSobrevivencia(dadosAtualizados.getPericiaSobrevivencia());
                if (dadosAtualizados.getPericiaVontade() != null)
                        pericia.setPericiaVontade(dadosAtualizados.getPericiaVontade());
        }

        private void atualizarOutros(Pericia pericia, Pericia dadosAtualizados) {
                if (dadosAtualizados.getOutrosAcrobacia() != null)
                        pericia.setOutrosAcrobacia(dadosAtualizados.getOutrosAcrobacia());
                if (dadosAtualizados.getOutrosAdestramento() != null)
                        pericia.setOutrosAdestramento(dadosAtualizados.getOutrosAdestramento());
                if (dadosAtualizados.getOutrosAtletismo() != null)
                        pericia.setOutrosAtletismo(dadosAtualizados.getOutrosAtletismo());
                if (dadosAtualizados.getOutrosAtuacao() != null)
                        pericia.setOutrosAtuacao(dadosAtualizados.getOutrosAtuacao());
                if (dadosAtualizados.getOutrosCavalgar() != null)
                        pericia.setOutrosCavalgar(dadosAtualizados.getOutrosCavalgar());
                if (dadosAtualizados.getOutrosConhecimento() != null)
                        pericia.setOutrosConhecimento(dadosAtualizados.getOutrosConhecimento());
                if (dadosAtualizados.getOutrosCura() != null)
                        pericia.setOutrosCura(dadosAtualizados.getOutrosCura());
                if (dadosAtualizados.getOutrosDiplomacia() != null)
                        pericia.setOutrosDiplomacia(dadosAtualizados.getOutrosDiplomacia());
                if (dadosAtualizados.getOutrosEnganacao() != null)
                        pericia.setOutrosEnganacao(dadosAtualizados.getOutrosEnganacao());
                if (dadosAtualizados.getOutrosFortitude() != null)
                        pericia.setOutrosFortitude(dadosAtualizados.getOutrosFortitude());
                if (dadosAtualizados.getOutrosFurtividade() != null)
                        pericia.setOutrosFurtividade(dadosAtualizados.getOutrosFurtividade());
                if (dadosAtualizados.getOutrosGuerra() != null)
                        pericia.setOutrosGuerra(dadosAtualizados.getOutrosGuerra());
                if (dadosAtualizados.getOutrosIniciativa() != null)
                        pericia.setOutrosIniciativa(dadosAtualizados.getOutrosIniciativa());
                if (dadosAtualizados.getOutrosIntimidacao() != null)
                        pericia.setOutrosIntimidacao(dadosAtualizados.getOutrosIntimidacao());
                if (dadosAtualizados.getOutrosIntuicao() != null)
                        pericia.setOutrosIntuicao(dadosAtualizados.getOutrosIntuicao());
                if (dadosAtualizados.getOutrosInvestigacao() != null)
                        pericia.setOutrosInvestigacao(dadosAtualizados.getOutrosInvestigacao());
                if (dadosAtualizados.getOutrosLadinagem() != null)
                        pericia.setOutrosLadinagem(dadosAtualizados.getOutrosLadinagem());
                if (dadosAtualizados.getOutrosLuta() != null)
                        pericia.setOutrosLuta(dadosAtualizados.getOutrosLuta());
                if (dadosAtualizados.getOutrosMisticismo() != null)
                        pericia.setOutrosMisticismo(dadosAtualizados.getOutrosMisticismo());
                if (dadosAtualizados.getOutrosNobreza() != null)
                        pericia.setOutrosNobreza(dadosAtualizados.getOutrosNobreza());
                if (dadosAtualizados.getOutrosOficio() != null)
                        pericia.setOutrosOficio(dadosAtualizados.getOutrosOficio());
                if (dadosAtualizados.getOutrosPercepcao() != null)
                        pericia.setOutrosPercepcao(dadosAtualizados.getOutrosPercepcao());
                if (dadosAtualizados.getOutrosPontaria() != null)
                        pericia.setOutrosPontaria(dadosAtualizados.getOutrosPontaria());
                if (dadosAtualizados.getOutrosReflexo() != null)
                        pericia.setOutrosReflexo(dadosAtualizados.getOutrosReflexo());
                if (dadosAtualizados.getOutrosReligiao() != null)
                        pericia.setOutrosReligiao(dadosAtualizados.getOutrosReligiao());
                if (dadosAtualizados.getOutrosSobrevivencia() != null)
                        pericia.setOutrosSobrevivencia(dadosAtualizados.getOutrosSobrevivencia());
                if (dadosAtualizados.getOutrosVontade() != null)
                        pericia.setOutrosVontade(dadosAtualizados.getOutrosVontade());
        }

        public void deletarPericiaPorPersonagemId(Long personagemId) {
                Personagem personagem = personagemRepository.findById(personagemId)
                                .orElseThrow(() -> new RuntimeException("Personagem não encontrado"));

                Pericia pericia = personagem.getPericia();

                if (pericia == null) {
                        throw new RuntimeException("Perícia não encontrada para o personagem");
                }

                personagem.setPericia(null);
                personagemRepository.save(personagem);

                periciaRepository.delete(pericia);
        }
}