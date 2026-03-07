package com.arthur.calculadorarpg.status;

import org.springframework.stereotype.Service;

@Service
public class StatusService {
    private final StatusRepository statusRepositorio;

    public StatusService(StatusRepository statusRepositorio) {
        this.statusRepositorio = statusRepositorio;
    }

    public Status criarStatus(Status status) {
        return statusRepositorio.save(status);
    }

    public java.util.List<Status> listarStatus() {
        return statusRepositorio.findAll();
    }
}
