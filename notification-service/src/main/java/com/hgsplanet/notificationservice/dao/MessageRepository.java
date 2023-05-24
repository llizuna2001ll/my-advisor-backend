package com.hgsplanet.notificationservice.dao;

import com.hgsplanet.notificationservice.documents.Conversation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Collection;

@RepositoryRestResource
public interface MessageRepository extends MongoRepository<Conversation, String> {
    Collection<Conversation> findAllByUserToAndUserFrom(String userTo, String UserFrom);
}
