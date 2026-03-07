package com.arthur.calculadorarpg.status;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;


@Controller
@RequestMapping("/status")
public class StatusController {
    private final StatusService statusService;
    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    @PostMapping
    public Status criarStatus(Status status) {
        return statusService.criarStatus(status);
    }

    @GetMapping
    public List<Status> listarStatus() {
        return statusService.listarStatus();
    }

}
