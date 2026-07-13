package com.communityhub.poll_service.infrastructure;

import com.communityhub.poll_service.domain.Vote;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VoteRepository extends MongoRepository<Vote,String> {
}
