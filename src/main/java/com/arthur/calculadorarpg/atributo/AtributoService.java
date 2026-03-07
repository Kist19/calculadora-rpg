package com.arthur.calculadorarpg.atributo;

import org.springframework.stereotype.Service;

@Service
public class AtributoService {
    private final AtributoRepository atributoRepositorio;

    public AtributoService(AtributoRepository atributoRepositorio) {
        this.atributoRepositorio = atributoRepositorio;
    }

    public Atributo criarAtributo(Atributo atributo) {
        return atributoRepositorio.save(atributo);
    }

    public java.util.List<Atributo> listarAtributos() {
        return atributoRepositorio.findAll();
    }
}
