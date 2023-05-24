package com.hgsplanet.conversationservice.services;


import com.hgsplanet.conversationservice.dao.ConversationRepository;
import com.hgsplanet.conversationservice.documents.Conversation;
import com.hgsplanet.conversationservice.models.Message;
import com.hgsplanet.conversationservice.web.UserRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class ConversationService {
    private final ConversationRepository conversationRepository;
    private final UserRestClient userRestClient;

    @Autowired
    public ConversationService(ConversationRepository conversationRepository, UserRestClient userRestClient) {
        this.conversationRepository = conversationRepository;
        this.userRestClient = userRestClient;
    }

    public Conversation addConversation(Conversation conversation){
        return conversationRepository.save(conversation);
    }

    public Conversation findConversationById(String id){
        return conversationRepository.findById(id).orElseThrow(()-> new RuntimeException("Conversation Not Found"));
    }

    public List<Conversation> findAllConversations(){
        return conversationRepository.findAll();
    }

    public Conversation updateConversation(Conversation conversation){
        return conversationRepository.save(conversation);
    }

    public void deleteConversationById(String id){
        conversationRepository.deleteById(id);
    }

    public Collection<Conversation> getFullConversations(String username){
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJhdXRob3JpdGllcyI6W3siYXV0aG9yaXR5IjoiVVNFUiJ9XSwic3ViIjoiaXp1bmEtdGVzdDEiLCJpYXQiOjE2ODQ5MzA1ODMsImV4cCI6MTY4NTUzNTM4M30.g3g693jUqQB8c_3SgLjyEs6Et6WaYlQsgCzHChkFAVs";
        String authorization = "Bearer "+token;
        Collection<Conversation> conversations = conversationRepository.findAllByUserTo(username);
        for (Conversation conversation : conversations){
            conversation.setUserFromEntity(userRestClient.findFullUserByUsernameForServices(conversation.getUserFrom(), authorization));
        }
        return conversations;
    }

    public Collection<Message> getMessages(String userTo, String userFrom){
        Conversation conversation = conversationRepository.findByUserFromAndUserTo(userFrom, userTo);
        return conversation.getMessages();
    }
}
