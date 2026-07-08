package com.communityhub.incident_service.presentation;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.communityhub.incident_service.domain.Incident;
import com.communityhub.incident_service.infrastructure.IncidentRepository;
import com.communityhub.incident_service.infrastructure.S3StorageService;
import com.communityhub.incident_service.domain.IncidentStatus;

@RestController
@RequestMapping("/api/incidents")
public class IncidentController {

    private final S3StorageService s3StorageService;
    private final IncidentRepository incidentRepository;

    public IncidentController(S3StorageService s3StorageService, IncidentRepository incidentRepository) {
        this.s3StorageService = s3StorageService;
        this.incidentRepository = incidentRepository;
    }

    @PostMapping
    public Incident createIncident(
            @RequestPart("title") String title,
            @RequestPart("description") String description,
            @RequestPart("file") MultipartFile file) throws IOException {

        String url = s3StorageService.uploadImage(file);

        // "null" no ID porque o MongoDB vai gerar o ID automaticamente
        Incident incident = new Incident(
                null,
                title,
                description,
                url,
                IncidentStatus.OPEN,
                LocalDateTime.now());

        // Salva no banco e retorna o objeto salvo
        return incidentRepository.save(incident);
    }

}
