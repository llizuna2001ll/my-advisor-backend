package com.hgsplanet.conversationservice.dao;

import com.hgsplanet.conversationservice.documents.Conversation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Collection;

@RepositoryRestResource
public interface ConversationRepository extends MongoRepository<Conversation, String> {
    Collection<Conversation> findAllByUserTo(String userTo);
    Conversation findByUserFromAndUserTo(String userFrom, String userTo);
}
