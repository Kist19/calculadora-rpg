package com.arthur.calculadorarpg.efeito;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class EfeitoService {

    private final EfeitoRepository efeitoRepository;

    public EfeitoService(EfeitoRepository efeitoRepository) {
        this.efeitoRepository = efeitoRepository;
    }

    public Efeito criarEfeito(Efeito efeito) {
        prepararCampos(efeito);
        return efeitoRepository.save(efeito);
    }

    public List<Efeito> listarEfeitos() {
        return efeitoRepository.findAll();
    }

    public Efeito atualizarEfeito(Long id, Efeito dadosAtualizados) {
        Efeito efeito = buscarPorId(id);

        if (dadosAtualizados.getEfeitoNome() != null) {
            efeito.setEfeitoNome(dadosAtualizados.getEfeitoNome());
        }
        if (dadosAtualizados.getEfeitoDuracaoTurno() != null) {
            efeito.setEfeitoDuracaoTurno(dadosAtualizados.getEfeitoDuracaoTurno());
        }
        if (dadosAtualizados.getEfeitoBonusAtaque() != null) {
            efeito.setEfeitoBonusAtaque(dadosAtualizados.getEfeitoBonusAtaque());
        }
        if (dadosAtualizados.getEfeitoBonusDano() != null) {
            efeito.setEfeitoBonusDano(dadosAtualizados.getEfeitoBonusDano());
        }
        if (dadosAtualizados.getEfeitoBonusDefesa() != null) {
            efeito.setEfeitoBonusDefesa(dadosAtualizados.getEfeitoBonusDefesa());
        }
        if (dadosAtualizados.getEfeitoPenalidadeAtaque() != null) {
            efeito.setEfeitoPenalidadeAtaque(dadosAtualizados.getEfeitoPenalidadeAtaque());
        }
        if (dadosAtualizados.getEfeitoPenalidadeDano() != null) {
            efeito.setEfeitoPenalidadeDano(dadosAtualizados.getEfeitoPenalidadeDano());
        }
        if (dadosAtualizados.getEfeitoPenalidadeDefesa() != null) {
            efeito.setEfeitoPenalidadeDefesa(dadosAtualizados.getEfeitoPenalidadeDefesa());
        }
        if (dadosAtualizados.getEfeitoDanoPorTurno() != null) {
            efeito.setEfeitoDanoPorTurno(dadosAtualizados.getEfeitoDanoPorTurno());
        }
        if (dadosAtualizados.getEfeitoCuraPorTurno() != null) {
            efeito.setEfeitoCuraPorTurno(dadosAtualizados.getEfeitoCuraPorTurno());
        }
        if (dadosAtualizados.getEfeitoIconeNome() != null) {
            efeito.setEfeitoIconeNome(dadosAtualizados.getEfeitoIconeNome());
        }
        if (dadosAtualizados.getEfeitoTipoDuracao() != null) {
            efeito.setEfeitoTipoDuracao(dadosAtualizados.getEfeitoTipoDuracao());
        }

        prepararCampos(efeito);
        return efeitoRepository.save(efeito);
    }

    public void deletarEfeito(Long id) {
        Efeito efeito = buscarPorId(id);
        efeitoRepository.delete(efeito);
    }

    private void prepararCampos(Efeito efeito) {
        if (efeito.getEfeitoBonusAtaque() == null)
            efeito.setEfeitoBonusAtaque(0);
        if (efeito.getEfeitoBonusDano() == null)
            efeito.setEfeitoBonusDano(0);
        if (efeito.getEfeitoBonusDefesa() == null)
            efeito.setEfeitoBonusDefesa(0);
        if (efeito.getEfeitoPenalidadeAtaque() == null)
            efeito.setEfeitoPenalidadeAtaque(0);
        if (efeito.getEfeitoPenalidadeDano() == null)
            efeito.setEfeitoPenalidadeDano(0);
        if (efeito.getEfeitoPenalidadeDefesa() == null)
            efeito.setEfeitoPenalidadeDefesa(0);
        if (efeito.getEfeitoDanoPorTurno() == null)
            efeito.setEfeitoDanoPorTurno(0);
        if (efeito.getEfeitoCuraPorTurno() == null)
            efeito.setEfeitoCuraPorTurno(0);
        if (efeito.getEfeitoTipoDuracao() == null)
            efeito.setEfeitoTipoDuracao(com.arthur.calculadorarpg.efeitoativo.EfeitoAtivoTipoDuracao.TURNO);
    }

    public Efeito buscarPorId(Long efeitoId) {
        return efeitoRepository.findById(efeitoId)
                .orElseThrow(() -> new RuntimeException("Efeito não encontrado"));
    }
}