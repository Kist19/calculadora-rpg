package com.arthur.calculadorarpg.status;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/status")
public class StatusController {
    private final StatusService statusService;

    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    @PostMapping
    public Status criarStatus(@RequestBody Status status) {
        return statusService.criarStatus(status);
    }

    @GetMapping
    public List<Status> listarStatus() {
        return statusService.listarStatus();
    }

    @PatchMapping("/{id}")
    public Status atualizarStatus(@PathVariable Long id, @RequestBody Status dadosAtualizados) {
        return statusService.atualizarStatus(id, dadosAtualizados);
    }

    @DeleteMapping("/{id}")
    public void deletarStatus(@PathVariable Long id) {
        statusService.deletarStatus(id);
    }
}
