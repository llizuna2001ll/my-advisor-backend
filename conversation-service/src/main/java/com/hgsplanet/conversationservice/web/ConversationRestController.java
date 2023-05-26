package com.hgsplanet.conversationservice.web;

import com.hgsplanet.conversationservice.documents.Conversation;
import com.hgsplanet.conversationservice.models.Message;
import com.hgsplanet.conversationservice.services.ConversationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("api/v1/conversations")
public class ConversationRestController {

    private final ConversationService conversationService;

    public ConversationRestController(ConversationService conversationService) {
        this.conversationService = conversationService;
    }

    @GetMapping
    ResponseEntity<List<Conversation>> getAllConversations(){
        List<Conversation> conversations = conversationService.findAllConversations();
        return new ResponseEntity<>(conversations, HttpStatus.OK);
    }

    @PostMapping("/addConversation")
    ResponseEntity<Conversation> addConversation(@RequestBody Conversation conversation){
        Conversation newConversation = conversationService.addConversation(conversation);
        return new ResponseEntity<>(newConversation, HttpStatus.CREATED);
    }

    @PutMapping("/updateConversation/{id}")
    ResponseEntity<Conversation> updateConversation(@PathVariable String id, @RequestBody Conversation conversation){
        Conversation updateConversation = conversationService.updateConversation(conversation);
        return new ResponseEntity<>(updateConversation, HttpStatus.OK);
    }

    @DeleteMapping("/deleteConversation/{id}")
    ResponseEntity<Conversation> deleteConversation(@PathVariable("id") String id){
        conversationService.deleteConversationById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{username}")
    ResponseEntity<Collection<Conversation>> getFullConversation(@PathVariable("username") String username){
        Collection<Conversation> conversations = conversationService.findConversationsByUserTo(username);
        return new ResponseEntity<>(conversations, HttpStatus.OK);
    }

    @GetMapping("/messages/{username}/{userFrom}")
    ResponseEntity<Collection<Message>> getMessages(@PathVariable String userFrom, @PathVariable("username") String username){
        Collection<Message> messages = conversationService.getMessages(username, userFrom);
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

    @PostMapping("/addMessage/{username}/{userFrom}")
    ResponseEntity<Message> addMessage(@RequestBody Message message, @PathVariable String userFrom, @PathVariable String username){
        conversationService.addMessage(message, username, userFrom);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

}
