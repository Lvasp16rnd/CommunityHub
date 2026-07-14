package com.communityhub.poll_service.infrastructure;

import com.communityhub.poll_service.domain.Poll;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PollRepository extends MongoRepository<Poll, String> {
}
