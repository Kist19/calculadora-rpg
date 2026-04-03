package com.arthur.calculadorarpg.efeitoativo;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arthur.calculadorarpg.efeito.Efeito;
import com.arthur.calculadorarpg.efeito.EfeitoRepository;
import com.arthur.calculadorarpg.personagem.Personagem;
import com.arthur.calculadorarpg.personagem.PersonagemRepository;
import com.arthur.calculadorarpg.status.Status;
import com.arthur.calculadorarpg.status.StatusRepository;

@Service
public class EfeitoAtivoService {

    private final EfeitoAtivoRepository efeitoAtivoRepository;
    private final EfeitoRepository efeitoRepository;
    private final PersonagemRepository personagemRepository;
    private final StatusRepository statusRepository;

    public EfeitoAtivoService(
            EfeitoAtivoRepository efeitoAtivoRepository,
            EfeitoRepository efeitoRepository,
            PersonagemRepository personagemRepository,
            StatusRepository statusRepository) {
        this.efeitoAtivoRepository = efeitoAtivoRepository;
        this.efeitoRepository = efeitoRepository;
        this.personagemRepository = personagemRepository;
        this.statusRepository = statusRepository;
    }

    public EfeitoAtivo aplicarEfeitoAoPersonagem(Long efeitoId, Long personagemId) {
        Efeito efeito = efeitoRepository.findById(efeitoId)
                .orElseThrow(() -> new RuntimeException("Efeito não encontrado"));

        Personagem personagem = personagemRepository.findById(personagemId)
                .orElseThrow(() -> new RuntimeException("Personagem não encontrado"));

        EfeitoAtivo efeitoAtivo = efeitoAtivoRepository
                .findByPersonagemIdAndEfeitoId(personagemId, efeitoId)
                .orElse(null);

        if (efeitoAtivo != null) {
            efeitoAtivo.setEfeitoAtivoTurnoRestante(efeito.getEfeitoDuracaoTurno());
            efeitoAtivo.setEfeitoAtivoLigado(true);
            return efeitoAtivoRepository.save(efeitoAtivo);
        }

        EfeitoAtivo novo = new EfeitoAtivo();
        novo.setEfeito(efeito);
        novo.setPersonagem(personagem);
        novo.setEfeitoAtivoTurnoRestante(efeito.getEfeitoDuracaoTurno());
        novo.setEfeitoAtivoLigado(true);

        return efeitoAtivoRepository.save(novo);
    }

    public List<EfeitoAtivo> listarEfeitosPorPersonagem(Long personagemId) {
        return efeitoAtivoRepository.findByPersonagemId(personagemId);
    }

    public void removerEfeitoAtivo(Long personagemId, Long efeitoAtivoId) {
        Personagem personagem = personagemRepository.findById(personagemId)
                .orElseThrow(() -> new RuntimeException("Personagem não encontrado"));

        EfeitoAtivo efeitoAtivo = efeitoAtivoRepository.findById(efeitoAtivoId)
                .orElseThrow(() -> new RuntimeException("Efeito ativo não encontrado"));

        if (!efeitoAtivo.getPersonagem().getId().equals(personagem.getId())) {
            throw new RuntimeException("Efeito ativo não pertence ao personagem informado");
        }

        efeitoAtivoRepository.delete(efeitoAtivo);
    }

    @Transactional
    public Map<String, Object> passarTurno(Long personagemId) {
        Personagem personagem = personagemRepository.findById(personagemId)
                .orElseThrow(() -> new RuntimeException("Personagem não encontrado"));

        Status status = personagem.getStatus();
        if (status == null) {
            throw new RuntimeException("Status do personagem não encontrado");
        }

        boolean emCombate = Boolean.TRUE.equals(personagem.getPersonagemEmCombate());

        atualizarTurnos(personagem.getItensTurnosRestantesNaCena());
        atualizarTurnos(personagem.getHabilidadesTurnosRestantesNaCena());

        List<EfeitoAtivo> efeitosAtivos = efeitoAtivoRepository
                .findByPersonagemIdAndEfeitoAtivoLigadoTrueAndEfeito_EfeitoTipoDuracao(
                        personagemId,
                        EfeitoAtivoTipoDuracao.TURNO);

        int danoTotalTurno = 0;
        int curaTotalTurno = 0;
        int efeitosExpirados = 0;

        for (EfeitoAtivo efeitoAtivo : efeitosAtivos) {
            Efeito efeito = efeitoAtivo.getEfeito();

            if (emCombate) {
                danoTotalTurno += valorOuZero(efeito.getEfeitoDanoPorTurno());
                curaTotalTurno += valorOuZero(efeito.getEfeitoCuraPorTurno());
            }

            int novoTurno = valorOuZero(efeitoAtivo.getEfeitoAtivoTurnoRestante()) - 1;
            efeitoAtivo.setEfeitoAtivoTurnoRestante(Math.max(novoTurno, 0));

            if (efeitoAtivo.getEfeitoAtivoTurnoRestante() <= 0) {
                efeitoAtivoRepository.delete(efeitoAtivo);
                efeitosExpirados++;
            } else {
                efeitoAtivoRepository.save(efeitoAtivo);
            }
        }

        personagemRepository.save(personagem);

        if (emCombate) {
            int vidaAtual = valorOuZero(status.getStatusVidaAtual());
            int vidaBase = status.getStatusVidaBase() != null ? status.getStatusVidaBase() : Integer.MAX_VALUE;

            vidaAtual = vidaAtual - danoTotalTurno + curaTotalTurno;
            if (vidaAtual < 0)
                vidaAtual = 0;
            if (vidaAtual > vidaBase)
                vidaAtual = vidaBase;

            status.setStatusVidaAtual(vidaAtual);
            statusRepository.save(status);
        }

        Map<String, Object> resposta = new HashMap<>();
        resposta.put("mensagem", emCombate
                ? "Turno de combate processado com sucesso"
                : "Turno fora de combate processado com sucesso");
        resposta.put("personagemEmCombate", emCombate);
        resposta.put("itensTurnosRestantesNaCena", personagem.getItensTurnosRestantesNaCena());
        resposta.put("habilidadesTurnosRestantesNaCena", personagem.getHabilidadesTurnosRestantesNaCena());
        resposta.put("efeitosProcessados", efeitosAtivos.size());
        resposta.put("efeitosExpirados", efeitosExpirados);
        resposta.put("danoTotalTurno", emCombate ? danoTotalTurno : 0);
        resposta.put("curaTotalTurno", emCombate ? curaTotalTurno : 0);
        resposta.put("vidaAtual", status.getStatusVidaAtual());

        return resposta;
    }

    private void atualizarTurnos(Map<String, Integer> mapa) {
        Iterator<Map.Entry<String, Integer>> iterator = mapa.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<String, Integer> entry = iterator.next();
            Integer turnos = entry.getValue();

            if (turnos == null) {
                iterator.remove();
                continue;
            }

            turnos = turnos - 1;

            if (turnos < 0) {
                iterator.remove();
            } else {
                entry.setValue(turnos);
            }
        }
    }

    private int valorOuZero(Integer valor) {
        return valor == null ? 0 : valor;
    }
}