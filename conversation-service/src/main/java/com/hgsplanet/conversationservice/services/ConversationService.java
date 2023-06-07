package com.hgsplanet.conversationservice.services;


import com.hgsplanet.conversationservice.dao.ConversationRepository;
import com.hgsplanet.conversationservice.documents.Conversation;
import com.hgsplanet.conversationservice.models.Message;
import com.hgsplanet.conversationservice.web.UserRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
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

    public Conversation addConversation(Conversation conversation) {
        return conversationRepository.save(conversation);
    }

    public Conversation findConversationById(String id) {
        return conversationRepository.findById(id).orElseThrow(() -> new RuntimeException("Conversation Not Found"));
    }

    public Collection<Conversation> findConversationsByUserTo(String username) {
        return conversationRepository.findAllByUsername(username);
    }

    public List<Conversation> findAllConversations() {
        return conversationRepository.findAll();
    }

    public Conversation updateConversation(Conversation conversation) {
        return conversationRepository.save(conversation);
    }

    public void deleteConversationById(String id) {
        conversationRepository.deleteById(id);
    }

    public Collection<Conversation> getFullConversations(String username) {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJhdXRob3JpdGllcyI6W3siYXV0aG9yaXR5IjoiVVNFUiJ9XSwic3ViIjoiaXp1bmEtdGVzdDEiLCJpYXQiOjE2ODYxNDAwODYsImV4cCI6MTY4Njc0NDg4Nn0.L22BDX1y4b3WfCxCcMGSEm81z6dE705iB3XKjmbpkpc";
        String authorization = "Bearer " + token;
        Collection<Conversation> conversations = conversationRepository.findAllByUsername(username);
        for (Conversation conversation : conversations) {
            conversation.setUserFromEntity(userRestClient.findFullUserByUsernameForServices(conversation.getUserFrom(), authorization));
        }
        return conversations;
    }

    public Collection<Message> addMessage(Message message, String username, String userFrom) {
        Conversation userFromConversation = conversationRepository.findByUserFromAndUsername(userFrom, username);
        Conversation userToConversation = conversationRepository.findByUserFromAndUsername(username, userFrom);

        if (userFromConversation == null) {
            Conversation newConversation1 = Conversation.builder()
                    .username(username)
                    .userFrom(userFrom)
                    .messages(new ArrayList<>())
                    .build();

            Conversation newConversation2 = Conversation.builder()
                    .username(userFrom)
                    .userFrom(username)
                    .messages(new ArrayList<>())
                    .build();

            userFromConversation = conversationRepository.save(newConversation1);
            userToConversation = conversationRepository.save(newConversation2);
        }

        Collection<Message> messages = userFromConversation.getMessages();
        messages.add(message);
        
        userToConversation.setMessages(messages);
        userFromConversation.setMessages(messages);

        userToConversation.setHasUnreadMessage(true);
        userToConversation.setLastMessageTime(LocalDateTime.now());
        userFromConversation.setLastMessageTime(LocalDateTime.now());
        conversationRepository.saveAll(Arrays.asList(userFromConversation, userToConversation));

        return messages;
    }


    public Collection<Message> getMessages(String username, String userFrom) {
        Conversation conversation = conversationRepository.findByUserFromAndUsername(userFrom, username);
        return conversation.getMessages();
    }

    public Conversation setMessageUnread(String id){
        Conversation conversation = findConversationById(id);
        conversation.setHasUnreadMessage(false);
        conversationRepository.save(conversation);
        return conversation;
    }
}
