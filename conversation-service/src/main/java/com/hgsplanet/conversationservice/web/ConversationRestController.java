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

    @GetMapping("/{id}")
    ResponseEntity<Conversation> getConversationById(@PathVariable String id){
        Conversation conversation = conversationService.findConversationById(id);
        return new ResponseEntity<>(conversation, HttpStatus.OK);
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

    @GetMapping("/getFullConversation/{username}")
    ResponseEntity<Collection<Conversation>> getFullConversation(@PathVariable("username") String username){
        Collection<Conversation> conversations = conversationService.getFullConversations(username);
        return new ResponseEntity<>(conversations, HttpStatus.OK);
    }

    @GetMapping("/getFullConversation/{userTo}")
    ResponseEntity<Collection<Message>> getMessages(@RequestParam String userFrom, @PathVariable("userTo") String userTo){
        Collection<Message> messages = conversationService.getMessages(userTo, userFrom);
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

}

