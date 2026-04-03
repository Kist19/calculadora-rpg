package com.arthur.calculadorarpg.combate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.arthur.calculadorarpg.arma.Arma;
import com.arthur.calculadorarpg.dadoextra.DadoExtra;
import com.arthur.calculadorarpg.dadoextra.DadoExtraAplicacao;
import com.arthur.calculadorarpg.dadoextra.DadoExtraOrigemTipo;
import com.arthur.calculadorarpg.dadoextra.DadoExtraService;
import com.arthur.calculadorarpg.efeitoativo.EfeitoAtivo;
import com.arthur.calculadorarpg.efeitoativo.EfeitoAtivoRepository;
import com.arthur.calculadorarpg.habilidade.Habilidade;
import com.arthur.calculadorarpg.habilidade.HabilidadeRepository;
import com.arthur.calculadorarpg.item.DadoTipoAcao;
import com.arthur.calculadorarpg.item.Item;
import com.arthur.calculadorarpg.item.ItemRepository;
import com.arthur.calculadorarpg.pericia.PericiaService;
import com.arthur.calculadorarpg.personagem.Personagem;
import com.arthur.calculadorarpg.personagem.PersonagemRepository;

@Service
public class CombateService {

    private final PersonagemRepository personagemRepository;
    private final EfeitoAtivoRepository efeitoAtivoRepository;
    private final DadoExtraService dadoExtraService;
    private final PericiaService periciaService;
    private final ItemRepository itemRepository;
    private final HabilidadeRepository habilidadeRepository;

    public CombateService(
            PersonagemRepository personagemRepository,
            EfeitoAtivoRepository efeitoAtivoRepository,
            DadoExtraService dadoExtraService,
            PericiaService periciaService,
            ItemRepository itemRepository,
            HabilidadeRepository habilidadeRepository) {
        this.personagemRepository = personagemRepository;
        this.efeitoAtivoRepository = efeitoAtivoRepository;
        this.dadoExtraService = dadoExtraService;
        this.periciaService = periciaService;
        this.itemRepository = itemRepository;
        this.habilidadeRepository = habilidadeRepository;
    }

    public CombateDadosResponse buscarDadosCombate(Long personagemId) {
        Personagem personagem = personagemRepository.findById(personagemId)
                .orElseThrow(() -> new RuntimeException("Personagem não encontrado"));

        if (!Boolean.TRUE.equals(personagem.getPersonagemEmCombate())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Personagem não está em modo de combate");
        }

        Arma armaEquipada = personagem.getArmaEquipada();

        List<EfeitoAtivo> efeitosAtivos = efeitoAtivoRepository
                .findByPersonagemIdAndEfeitoAtivoLigadoTrue(personagemId);

        CombateDadosResponse response = new CombateDadosResponse();
        response.setCombateTesteAtaque(montarResumoAtaque(personagem, armaEquipada, efeitosAtivos));
        response.setCombateDano(montarResumoDano(personagem, armaEquipada, efeitosAtivos));
        response.setCombateCura(montarResumoCura(personagem, efeitosAtivos));
        response.setCombateCritico(montarCritico(armaEquipada));
        response.setCombateAlcance(montarAlcance(armaEquipada));
        response.setCombateEfeitosAtivos(montarResumoEfeitos(efeitosAtivos));
        response.setCombateTurno(montarResumoTurno(efeitosAtivos));

        return response;
    }

    private CombateResumoAtaqueResponse montarResumoAtaque(
            Personagem personagem,
            Arma armaEquipada,
            List<EfeitoAtivo> efeitosAtivos) {

        CombateResumoAtaqueResponse response = new CombateResumoAtaqueResponse();
        List<String> dadosExtras = new ArrayList<>();
        List<CombateFonteResponse> fontes = new ArrayList<>();

        String dadoBase = "1d20";
        int modificadorBase = buscarValorPericiaDaArma(personagem, armaEquipada);
        int bonusTotal = 0;
        int penalidadeTotal = 0;

        if (armaEquipada != null && armaEquipada.getArmaTipoPericia() != null) {
            fontes.add(new CombateFonteResponse(
                    "PERICIA",
                    armaEquipada.getArmaTipoPericia().name(),
                    "MODIFICADOR_BASE",
                    "+" + modificadorBase));
        }

        for (EfeitoAtivo efeito : efeitosAtivos) {
            if (efeito.getEfeito() == null) {
                continue;
            }

            if (efeito.getEfeito().getEfeitoBonusAtaque() != null && efeito.getEfeito().getEfeitoBonusAtaque() > 0) {
                bonusTotal += efeito.getEfeito().getEfeitoBonusAtaque();
                fontes.add(new CombateFonteResponse(
                        "EFEITO_ATIVO",
                        efeito.getEfeito().getEfeitoNome(),
                        "BONUS_TESTE_ATAQUE",
                        "+" + efeito.getEfeito().getEfeitoBonusAtaque()));
            }

            if (efeito.getEfeito().getEfeitoPenalidadeAtaque() != null
                    && efeito.getEfeito().getEfeitoPenalidadeAtaque() > 0) {
                penalidadeTotal += efeito.getEfeito().getEfeitoPenalidadeAtaque();
                fontes.add(new CombateFonteResponse(
                        "EFEITO_ATIVO",
                        efeito.getEfeito().getEfeitoNome(),
                        "PENALIDADE_TESTE_ATAQUE",
                        "-" + efeito.getEfeito().getEfeitoPenalidadeAtaque()));
            }

            adicionarDadosExtrasAtaquePorEfeito(efeito, dadosExtras, fontes);
        }

        aplicarItensAtivosNoAtaque(personagem, dadosExtras, fontes);
        aplicarHabilidadesAtivasNoAtaque(personagem, dadosExtras, fontes);

        int modificadorFinal = modificadorBase + bonusTotal - penalidadeTotal;

        response.setCombateDadoBase(dadoBase);
        response.setCombateDadosExtras(dadosExtras);
        response.setCombateModificadorBase(modificadorBase);
        response.setCombateBonusTotal(bonusTotal);
        response.setCombatePenalidadeTotal(penalidadeTotal);
        response.setCombateModificadorFinal(modificadorFinal);
        response.setCombateFormatado(formatarAtaque(dadoBase, dadosExtras, modificadorFinal));
        response.setCombateFontes(fontes);

        return response;
    }

    private CombateResumoDanoResponse montarResumoDano(
            Personagem personagem,
            Arma armaEquipada,
            List<EfeitoAtivo> efeitosAtivos) {

        CombateResumoDanoResponse response = new CombateResumoDanoResponse();
        List<String> dadosBase = new ArrayList<>();
        List<String> dadosExtras = new ArrayList<>();
        List<CombateFonteResponse> fontes = new ArrayList<>();

        int bonusTotal = 0;
        int penalidadeTotal = 0;

        if (armaEquipada != null && armaEquipada.getArmaQuantidadeDado() != null
                && armaEquipada.getArmaTipoDado() != null) {
            dadosBase.add(
                    armaEquipada.getArmaQuantidadeDado() + "d" + armaEquipada.getArmaTipoDado().name().substring(1));
        }

        for (EfeitoAtivo efeito : efeitosAtivos) {
            if (efeito.getEfeito() == null) {
                continue;
            }

            if (efeito.getEfeito().getEfeitoBonusDano() != null && efeito.getEfeito().getEfeitoBonusDano() > 0) {
                bonusTotal += efeito.getEfeito().getEfeitoBonusDano();
                fontes.add(new CombateFonteResponse(
                        "EFEITO_ATIVO",
                        efeito.getEfeito().getEfeitoNome(),
                        "BONUS_DANO",
                        "+" + efeito.getEfeito().getEfeitoBonusDano()));
            }

            if (efeito.getEfeito().getEfeitoPenalidadeDano() != null
                    && efeito.getEfeito().getEfeitoPenalidadeDano() > 0) {
                penalidadeTotal += efeito.getEfeito().getEfeitoPenalidadeDano();
                fontes.add(new CombateFonteResponse(
                        "EFEITO_ATIVO",
                        efeito.getEfeito().getEfeitoNome(),
                        "PENALIDADE_DANO",
                        "-" + efeito.getEfeito().getEfeitoPenalidadeDano()));
            }

            adicionarDadosExtrasDanoPorEfeito(efeito, dadosExtras, fontes);
        }

        aplicarItensAtivosNoDano(personagem, dadosExtras, fontes);
        aplicarHabilidadesAtivasNoDano(personagem, dadosExtras, fontes);

        int modificadorFinal = bonusTotal - penalidadeTotal;

        response.setCombateDadosBase(dadosBase);
        response.setCombateDadosExtras(dadosExtras);
        response.setCombateBonusTotal(bonusTotal);
        response.setCombatePenalidadeTotal(penalidadeTotal);
        response.setCombateModificadorFinal(modificadorFinal);
        response.setCombateFormatado(formatarDano(dadosBase, dadosExtras, modificadorFinal));
        response.setCombateFontes(fontes);

        return response;
    }

    private CombateResumoCuraResponse montarResumoCura(
            Personagem personagem,
            List<EfeitoAtivo> efeitosAtivos) {

        CombateResumoCuraResponse response = new CombateResumoCuraResponse();
        List<String> dadosBase = new ArrayList<>();
        List<String> dadosExtras = new ArrayList<>();
        List<CombateFonteResponse> fontes = new ArrayList<>();

        for (EfeitoAtivo efeito : efeitosAtivos) {
            if (efeito.getEfeito() == null) {
                continue;
            }

            if (efeito.getEfeito().getEfeitoCuraPorTurno() != null
                    && efeito.getEfeito().getEfeitoCuraPorTurno() > 0) {
                dadosBase.add(String.valueOf(efeito.getEfeito().getEfeitoCuraPorTurno()));
                fontes.add(new CombateFonteResponse(
                        "EFEITO_ATIVO",
                        efeito.getEfeito().getEfeitoNome(),
                        "CURA_POR_TURNO",
                        String.valueOf(efeito.getEfeito().getEfeitoCuraPorTurno())));
            }

            adicionarDadosExtrasCuraPorEfeito(efeito, dadosExtras, fontes);
        }

        aplicarItensAtivosNaCura(personagem, dadosExtras, fontes);
        aplicarHabilidadesAtivasNaCura(personagem, dadosExtras, fontes);

        response.setCombateDadosBase(dadosBase);
        response.setCombateDadosExtras(dadosExtras);
        response.setCombateFormatado(formatarCura(dadosBase, dadosExtras));
        response.setCombateFontes(fontes);

        return response;
    }

    private List<CombateEfeitoAtivoResumoResponse> montarResumoEfeitos(List<EfeitoAtivo> efeitosAtivos) {
        List<CombateEfeitoAtivoResumoResponse> lista = new ArrayList<>();

        for (EfeitoAtivo efeito : efeitosAtivos) {
            if (efeito.getEfeito() == null) {
                continue;
            }

            lista.add(new CombateEfeitoAtivoResumoResponse(
                    efeito.getEfeito().getEfeitoNome(),
                    efeito.getEfeito().getEfeitoIconeNome() != null ? efeito.getEfeito().getEfeitoIconeNome().name()
                            : null,
                    efeito.getEfeitoAtivoTurnoRestante()));
        }

        return lista;
    }

    private void aplicarItensAtivosNoAtaque(
            Personagem personagem,
            List<String> dadosExtras,
            List<CombateFonteResponse> fontes) {

        Map<String, Integer> itensAtivos = personagem.getItensTurnosRestantesNaCena();
        if (itensAtivos == null || itensAtivos.isEmpty()) {
            return;
        }

        for (String nomeItem : itensAtivos.keySet()) {
            Item item = itemRepository.findByItemNome(nomeItem).orElse(null);
            if (item == null) {
                continue;
            }

            if (item.getItemQuantidadeDado() != null
                    && item.getItemQuantidadeDado() > 0
                    && item.getItemTipoDado() != null
                    && item.getItemDadoTipoAcao() == DadoTipoAcao.DANO) {
                dadosExtras.add(formatarDado(item.getItemQuantidadeDado(), item.getItemTipoDado().name()));
                fontes.add(new CombateFonteResponse(
                        "ITEM",
                        item.getItemNome(),
                        "DADO_EXTRA_TESTE_ATAQUE",
                        formatarDado(item.getItemQuantidadeDado(), item.getItemTipoDado().name())));
            }

            if (item.getItemBonusAtaque() != null && item.getItemBonusAtaque() > 0) {
                fontes.add(new CombateFonteResponse(
                        "ITEM",
                        item.getItemNome(),
                        "BONUS_TESTE_ATAQUE",
                        "+" + item.getItemBonusAtaque()));
            }

            if (item.getItemPenalidadeAtaque() != null && item.getItemPenalidadeAtaque() > 0) {
                fontes.add(new CombateFonteResponse(
                        "ITEM",
                        item.getItemNome(),
                        "PENALIDADE_TESTE_ATAQUE",
                        "-" + item.getItemPenalidadeAtaque()));
            }
        }
    }

    private void aplicarHabilidadesAtivasNoAtaque(
            Personagem personagem,
            List<String> dadosExtras,
            List<CombateFonteResponse> fontes) {

        Map<String, Integer> habilidadesAtivas = personagem.getHabilidadesTurnosRestantesNaCena();
        if (habilidadesAtivas == null || habilidadesAtivas.isEmpty()) {
            return;
        }

        for (String nomeHabilidade : habilidadesAtivas.keySet()) {
            Habilidade habilidade = habilidadeRepository.findByHabilidadeNome(nomeHabilidade).orElse(null);
            if (habilidade == null) {
                continue;
            }

            if (habilidade.getHabilidadeBonusAtaque() != null && habilidade.getHabilidadeBonusAtaque() > 0) {
                fontes.add(new CombateFonteResponse(
                        "HABILIDADE",
                        habilidade.getHabilidadeNome(),
                        "BONUS_TESTE_ATAQUE",
                        "+" + habilidade.getHabilidadeBonusAtaque()));
            }

            if (habilidade.getHabilidadePenalidadeAtaque() != null && habilidade.getHabilidadePenalidadeAtaque() > 0) {
                fontes.add(new CombateFonteResponse(
                        "HABILIDADE",
                        habilidade.getHabilidadeNome(),
                        "PENALIDADE_TESTE_ATAQUE",
                        "-" + habilidade.getHabilidadePenalidadeAtaque()));
            }
        }
    }

    private void aplicarItensAtivosNoDano(
            Personagem personagem,
            List<String> dadosExtras,
            List<CombateFonteResponse> fontes) {

        Map<String, Integer> itensAtivos = personagem.getItensTurnosRestantesNaCena();
        if (itensAtivos == null || itensAtivos.isEmpty()) {
            return;
        }

        for (String nomeItem : itensAtivos.keySet()) {
            Item item = itemRepository.findByItemNome(nomeItem).orElse(null);
            if (item == null) {
                continue;
            }

            if (item.getItemQuantidadeDado() != null
                    && item.getItemQuantidadeDado() > 0
                    && item.getItemTipoDado() != null
                    && item.getItemDadoTipoAcao() == DadoTipoAcao.DANO) {
                String dadoFormatado = formatarDado(item.getItemQuantidadeDado(), item.getItemTipoDado().name());
                dadosExtras.add(dadoFormatado);
                fontes.add(new CombateFonteResponse(
                        "ITEM",
                        item.getItemNome(),
                        "DADO_EXTRA_DANO",
                        dadoFormatado));
            }

            if (item.getItemBonusDano() != null && item.getItemBonusDano() > 0) {
                fontes.add(new CombateFonteResponse(
                        "ITEM",
                        item.getItemNome(),
                        "BONUS_DANO",
                        "+" + item.getItemBonusDano()));
            }

            if (item.getItemPenalidadeDano() != null && item.getItemPenalidadeDano() > 0) {
                fontes.add(new CombateFonteResponse(
                        "ITEM",
                        item.getItemNome(),
                        "PENALIDADE_DANO",
                        "-" + item.getItemPenalidadeDano()));
            }
        }
    }

    private void aplicarHabilidadesAtivasNoDano(
            Personagem personagem,
            List<String> dadosExtras,
            List<CombateFonteResponse> fontes) {

        Map<String, Integer> habilidadesAtivas = personagem.getHabilidadesTurnosRestantesNaCena();
        if (habilidadesAtivas == null || habilidadesAtivas.isEmpty()) {
            return;
        }

        for (String nomeHabilidade : habilidadesAtivas.keySet()) {
            Habilidade habilidade = habilidadeRepository.findByHabilidadeNome(nomeHabilidade).orElse(null);
            if (habilidade == null) {
                continue;
            }

            if (habilidade.getHabilidadeQuantidadeDado() != null
                    && habilidade.getHabilidadeQuantidadeDado() > 0
                    && habilidade.getHabilidadeTipoDado() != null
                    && habilidade.getHabilidadeDadoTipoAcao() == DadoTipoAcao.DANO) {
                String dadoFormatado = formatarDado(
                        habilidade.getHabilidadeQuantidadeDado(),
                        habilidade.getHabilidadeTipoDado().name());
                dadosExtras.add(dadoFormatado);
                fontes.add(new CombateFonteResponse(
                        "HABILIDADE",
                        habilidade.getHabilidadeNome(),
                        "DADO_EXTRA_DANO",
                        dadoFormatado));
            }

            if (habilidade.getHabilidadeBonusDano() != null && habilidade.getHabilidadeBonusDano() > 0) {
                fontes.add(new CombateFonteResponse(
                        "HABILIDADE",
                        habilidade.getHabilidadeNome(),
                        "BONUS_DANO",
                        "+" + habilidade.getHabilidadeBonusDano()));
            }

            if (habilidade.getHabilidadePenalidadeDano() != null && habilidade.getHabilidadePenalidadeDano() > 0) {
                fontes.add(new CombateFonteResponse(
                        "HABILIDADE",
                        habilidade.getHabilidadeNome(),
                        "PENALIDADE_DANO",
                        "-" + habilidade.getHabilidadePenalidadeDano()));
            }
        }
    }

    private void aplicarItensAtivosNaCura(
            Personagem personagem,
            List<String> dadosExtras,
            List<CombateFonteResponse> fontes) {

        Map<String, Integer> itensAtivos = personagem.getItensTurnosRestantesNaCena();
        if (itensAtivos == null || itensAtivos.isEmpty()) {
            return;
        }

        for (String nomeItem : itensAtivos.keySet()) {
            Item item = itemRepository.findByItemNome(nomeItem).orElse(null);
            if (item == null) {
                continue;
            }

            if (item.getItemQuantidadeDado() != null
                    && item.getItemQuantidadeDado() > 0
                    && item.getItemTipoDado() != null
                    && item.getItemDadoTipoAcao() == DadoTipoAcao.CURA) {
                String dadoFormatado = formatarDado(item.getItemQuantidadeDado(), item.getItemTipoDado().name());
                dadosExtras.add(dadoFormatado);
                fontes.add(new CombateFonteResponse(
                        "ITEM",
                        item.getItemNome(),
                        "DADO_EXTRA_CURA",
                        dadoFormatado));
            }
        }
    }

    private void aplicarHabilidadesAtivasNaCura(
            Personagem personagem,
            List<String> dadosExtras,
            List<CombateFonteResponse> fontes) {

        Map<String, Integer> habilidadesAtivas = personagem.getHabilidadesTurnosRestantesNaCena();
        if (habilidadesAtivas == null || habilidadesAtivas.isEmpty()) {
            return;
        }

        for (String nomeHabilidade : habilidadesAtivas.keySet()) {
            Habilidade habilidade = habilidadeRepository.findByHabilidadeNome(nomeHabilidade).orElse(null);
            if (habilidade == null) {
                continue;
            }

            if (habilidade.getHabilidadeQuantidadeDado() != null
                    && habilidade.getHabilidadeQuantidadeDado() > 0
                    && habilidade.getHabilidadeTipoDado() != null
                    && habilidade.getHabilidadeDadoTipoAcao() == DadoTipoAcao.CURA) {
                String dadoFormatado = formatarDado(
                        habilidade.getHabilidadeQuantidadeDado(),
                        habilidade.getHabilidadeTipoDado().name());
                dadosExtras.add(dadoFormatado);
                fontes.add(new CombateFonteResponse(
                        "HABILIDADE",
                        habilidade.getHabilidadeNome(),
                        "DADO_EXTRA_CURA",
                        dadoFormatado));
            }
        }
    }

    private String formatarDado(Integer quantidade, String tipoDado) {
        return quantidade + "d" + tipoDado.replaceFirst("^D", "");
    }

    private void adicionarDadosExtrasAtaque(
            List<String> dadosExtras,
            List<CombateFonteResponse> fontes,
            String origemNome,
            DadoExtraOrigemTipo origemTipo,
            Long origemId) {
        if (origemId == null) {
            return;
        }

        List<DadoExtra> dados = dadoExtraService.buscarDadosExtrasCombatePorOrigemEAplicacao(
                origemTipo,
                origemId,
                DadoExtraAplicacao.TESTE_DE_ATAQUE);

        for (DadoExtra dado : dados) {
            dadosExtras.add(dado.getDadoExtraFormatado());
            fontes.add(new CombateFonteResponse(
                    origemTipo.name(),
                    origemNome,
                    "DADO_EXTRA_TESTE_ATAQUE",
                    dado.getDadoExtraFormatado()));
        }
    }

    private void adicionarDadosExtrasDano(
            List<String> dadosExtras,
            List<CombateFonteResponse> fontes,
            String origemNome,
            DadoExtraOrigemTipo origemTipo,
            Long origemId) {
        if (origemId == null) {
            return;
        }

        List<DadoExtra> dados = dadoExtraService.buscarDadosExtrasCombatePorOrigemEAplicacao(
                origemTipo,
                origemId,
                DadoExtraAplicacao.DANO);

        for (DadoExtra dado : dados) {
            dadosExtras.add(dado.getDadoExtraFormatado());
            fontes.add(new CombateFonteResponse(
                    origemTipo.name(),
                    origemNome,
                    "DADO_EXTRA_DANO",
                    dado.getDadoExtraFormatado()));
        }
    }

    private void adicionarDadosExtrasCura(
            List<String> dadosExtras,
            List<CombateFonteResponse> fontes,
            String origemNome,
            DadoExtraOrigemTipo origemTipo,
            Long origemId) {
        if (origemId == null) {
            return;
        }

        List<DadoExtra> dados = dadoExtraService.buscarDadosExtrasCombatePorOrigemEAplicacao(
                origemTipo,
                origemId,
                DadoExtraAplicacao.CURA);

        for (DadoExtra dado : dados) {
            dadosExtras.add(dado.getDadoExtraFormatado());
            fontes.add(new CombateFonteResponse(
                    origemTipo.name(),
                    origemNome,
                    "DADO_EXTRA_CURA",
                    dado.getDadoExtraFormatado()));
        }
    }

    private void adicionarDadosExtrasAtaquePorEfeito(
            EfeitoAtivo efeito,
            List<String> dadosExtras,
            List<CombateFonteResponse> fontes) {
        if (efeito.getEfeito() == null) {
            return;
        }

        adicionarDadosExtrasAtaque(
                dadosExtras,
                fontes,
                efeito.getEfeito().getEfeitoNome(),
                DadoExtraOrigemTipo.EFEITO,
                efeito.getEfeito().getId());
    }

    private void adicionarDadosExtrasDanoPorEfeito(
            EfeitoAtivo efeito,
            List<String> dadosExtras,
            List<CombateFonteResponse> fontes) {
        if (efeito.getEfeito() == null) {
            return;
        }

        adicionarDadosExtrasDano(
                dadosExtras,
                fontes,
                efeito.getEfeito().getEfeitoNome(),
                DadoExtraOrigemTipo.EFEITO,
                efeito.getEfeito().getId());
    }

    private void adicionarDadosExtrasCuraPorEfeito(
            EfeitoAtivo efeito,
            List<String> dadosExtras,
            List<CombateFonteResponse> fontes) {
        if (efeito.getEfeito() == null) {
            return;
        }

        adicionarDadosExtrasCura(
                dadosExtras,
                fontes,
                efeito.getEfeito().getEfeitoNome(),
                DadoExtraOrigemTipo.EFEITO,
                efeito.getEfeito().getId());
    }

    private String montarCritico(Arma armaEquipada) {
        if (armaEquipada == null) {
            return "Sem arma equipada";
        }

        return "x" + armaEquipada.getArmaCriticoMultiplicador() + " / " + armaEquipada.getArmaCriticoMinimo();
    }

    private String montarAlcance(Arma armaEquipada) {
        if (armaEquipada == null || armaEquipada.getArmaAlcance() == null) {
            return "Sem arma equipada";
        }

        return armaEquipada.getArmaAlcance().name();
    }

    private String formatarAtaque(String dadoBase, List<String> dadosExtras, int modificadorFinal) {
        List<String> partes = new ArrayList<>();
        partes.add(dadoBase);

        if (dadosExtras != null && !dadosExtras.isEmpty()) {
            partes.addAll(dadosExtras);
        }

        String base = String.join(" + ", partes);

        if (modificadorFinal > 0) {
            return base + " + " + modificadorFinal;
        }

        if (modificadorFinal < 0) {
            return base + " - " + Math.abs(modificadorFinal);
        }

        return base;
    }

    private String formatarDano(List<String> dadosBase, List<String> dadosExtras, int modificadorFinal) {
        List<String> partes = new ArrayList<>();

        if (dadosBase != null) {
            partes.addAll(dadosBase);
        }

        if (dadosExtras != null) {
            partes.addAll(dadosExtras);
        }

        String base = String.join(" + ", partes);

        if (base.isBlank()) {
            base = "Sem dano";
        }

        if (modificadorFinal > 0) {
            return base + " + " + modificadorFinal;
        }

        if (modificadorFinal < 0) {
            return base + " - " + Math.abs(modificadorFinal);
        }

        return base;
    }

    private String formatarCura(List<String> dadosBase, List<String> dadosExtras) {
        List<String> partes = new ArrayList<>();

        if (dadosBase != null) {
            partes.addAll(dadosBase);
        }

        if (dadosExtras != null) {
            partes.addAll(dadosExtras);
        }

        String base = String.join(" + ", partes);

        if (base.isBlank()) {
            return "Sem cura";
        }

        return base;
    }

    private Integer buscarValorPericiaDaArma(Personagem personagem, Arma arma) {
        if (arma == null || arma.getArmaTipoPericia() == null) {
            return 0;
        }

        return periciaService.calcularPericiaCombate(personagem, arma.getArmaTipoPericia());
    }

    private CombateResumoTurnoResponse montarResumoTurno(List<EfeitoAtivo> efeitosAtivos) {

        CombateResumoTurnoResponse response = new CombateResumoTurnoResponse();

        List<String> danos = new ArrayList<>();
        List<String> curas = new ArrayList<>();
        List<CombateFonteResponse> fontes = new ArrayList<>();

        for (EfeitoAtivo efeitoAtivo : efeitosAtivos) {

            if (efeitoAtivo.getEfeito() == null) {
                continue;
            }

            var efeito = efeitoAtivo.getEfeito();

            if (efeito.getEfeitoDanoPorTurno() != null && efeito.getEfeitoDanoPorTurno() > 0) {
                danos.add(String.valueOf(efeito.getEfeitoDanoPorTurno()));

                fontes.add(new CombateFonteResponse(
                        "EFEITO_ATIVO",
                        efeito.getEfeitoNome(),
                        "DANO_POR_TURNO",
                        "-" + efeito.getEfeitoDanoPorTurno()));
            }

            if (efeito.getEfeitoCuraPorTurno() != null && efeito.getEfeitoCuraPorTurno() > 0) {
                curas.add(String.valueOf(efeito.getEfeitoCuraPorTurno()));

                fontes.add(new CombateFonteResponse(
                        "EFEITO_ATIVO",
                        efeito.getEfeitoNome(),
                        "CURA_POR_TURNO",
                        "+" + efeito.getEfeitoCuraPorTurno()));
            }

            List<DadoExtra> dadosDanoTurno = buscarDadosExtrasDeTurnoDoEfeito(
                    efeito.getId(),
                    DadoExtraAplicacao.DANO_POR_TURNO,
                    DadoExtraAplicacao.DANO);

            for (DadoExtra dado : dadosDanoTurno) {
                danos.add(dado.getDadoExtraFormatado());

                fontes.add(new CombateFonteResponse(
                        "EFEITO_ATIVO",
                        efeito.getEfeitoNome(),
                        "DADO_DANO_TURNO",
                        dado.getDadoExtraFormatado()));
            }

            List<DadoExtra> dadosCuraTurno = buscarDadosExtrasDeTurnoDoEfeito(
                    efeito.getId(),
                    DadoExtraAplicacao.CURA_POR_TURNO,
                    DadoExtraAplicacao.CURA);

            for (DadoExtra dado : dadosCuraTurno) {
                curas.add(dado.getDadoExtraFormatado());

                fontes.add(new CombateFonteResponse(
                        "EFEITO_ATIVO",
                        efeito.getEfeitoNome(),
                        "DADO_CURA_TURNO",
                        dado.getDadoExtraFormatado()));
            }
        }

        response.setCombateDanosPorTurno(danos);
        response.setCombateCurasPorTurno(curas);
        response.setCombateFontes(fontes);

        return response;
    }

    private List<DadoExtra> buscarDadosExtrasDeTurnoDoEfeito(
            Long efeitoId,
            DadoExtraAplicacao aplicacaoTurno,
            DadoExtraAplicacao aplicacaoLegada) {

        List<DadoExtra> dadosTurno = dadoExtraService.buscarDadosExtrasTurnoPorOrigem(
                DadoExtraOrigemTipo.EFEITO,
                efeitoId,
                aplicacaoTurno);

        if (!dadosTurno.isEmpty()) {
            return dadosTurno;
        }

        return dadoExtraService.buscarDadosExtrasCombatePorOrigemEAplicacao(
                DadoExtraOrigemTipo.EFEITO,
                efeitoId,
                aplicacaoLegada);
    }
}