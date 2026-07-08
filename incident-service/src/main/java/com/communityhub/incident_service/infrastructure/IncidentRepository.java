package com.communityhub.incident_service.infrastructure;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.communityhub.incident_service.domain.Incident;

@Repository
public interface IncidentRepository extends MongoRepository<Incident, String> {

}
