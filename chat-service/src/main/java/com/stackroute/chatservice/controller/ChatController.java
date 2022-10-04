package com.stackroute.chatservice.controller;




import com.stackroute.chatservice.model.Chat;
import com.stackroute.chatservice.service.ChatService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/questionId")
public class ChatController {

     private ChatService chatService;

    public ChatController(ChatService chatService) {
        super();
        this.chatService = chatService;
    }

    //build Rest api
    @PostMapping
    public ResponseEntity<Chat> saveChat(@RequestBody Chat chat){
        return new ResponseEntity<Chat>(chatService.saveChat(chat), HttpStatus.CREATED);
    }

    //build get answer for particular question_id
    @GetMapping("{uuid}")
    public ResponseEntity<Chat> getChatById(@PathVariable("uuid") UUID questionId){
        return new ResponseEntity<Chat>(chatService.getChatById(questionId), HttpStatus.OK);

    }

    //build update Chat Rest Api
    @PutMapping("{uuid}")
      public ResponseEntity<Chat> updateChat(@PathVariable("uuid") UUID questionId
                   ,@RequestBody Chat chat){
        return new ResponseEntity<Chat>(chatService.updateChat(chat, questionId),HttpStatus.OK);

      }

      //build delete chat
      @DeleteMapping("{uuid}")
      public ResponseEntity<String> deleteChat(@PathVariable("uuid") UUID questionId){
        chatService.deleteChat(questionId);//deleteChat
          return new ResponseEntity<String>("Chat deleted successfully", HttpStatus.OK);
      }

}
