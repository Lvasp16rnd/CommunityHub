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

import io.awspring.cloud.sns.core.SnsTemplate;

@RestController
@RequestMapping("/api/incidents")
public class IncidentController {

    private final S3StorageService s3StorageService;
    private final IncidentRepository incidentRepository;
    private final SnsTemplate snsTemplate;

    public IncidentController(S3StorageService s3StorageService, IncidentRepository incidentRepository,
            SnsTemplate snsTemplate) {
        this.s3StorageService = s3StorageService;
        this.incidentRepository = incidentRepository;
        this.snsTemplate = snsTemplate;
    }

    @PostMapping(consumes = org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE)
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

        Incident savedIncident = incidentRepository.save(incident);

        snsTemplate.convertAndSend("incident-events-topic", savedIncident);

        return savedIncident;
    }

}
