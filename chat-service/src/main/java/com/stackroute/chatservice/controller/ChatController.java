package com.stackroute.chatservice.controller;




import com.stackroute.chatservice.model.Chat;
import com.stackroute.chatservice.service.ChatService;
import com.stackroute.chatservice.service.ChatServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("api/v1")
public class ChatController {
     @Autowired
     private ChatService chatService;

    @Autowired
    private ChatServiceImpl chatServiceImpl;

    public ChatController(ChatService chatService) {
        super();
        this.chatService = chatService;
    }

  // build add question rest api
    @PostMapping("/question")
    public ResponseEntity<Chat> saveChat(@RequestBody Chat chat){
        chat.setQuestionId(chatServiceImpl.getSequenceNumber(Chat.SEQUENCE_NAME));
        return new ResponseEntity<Chat>(chatService.saveChat(chat), HttpStatus.CREATED);
    }
  // update reply to the question
    //{questionId}
    @PutMapping("/reply/{questionId}")
    public ResponseEntity<Chat> updateChat(@PathVariable("questionId") long questionId, @RequestBody Chat chat){
         return new ResponseEntity<Chat>(chatService.updateChat(chat, questionId), HttpStatus.OK);
    }

   // get reply by QuestionId
    //{questionId}
   @GetMapping("/reply/{questionId}")
   public ResponseEntity<Chat> getChatByQuestionId(@PathVariable("questionId") long questionId){
       return new ResponseEntity<Chat>(chatService.getChatByQuestionId(questionId), HttpStatus.OK);
   }

    //reply to previous answer
    //{questionId}
    @PutMapping("/answer/{questionId}")
    public ResponseEntity<Chat> replyChat(@PathVariable("questionId") long questionId
         ,@RequestBody Chat chat){
        return new ResponseEntity<>(chatService.replyChat(chat, questionId), HttpStatus.OK);

    }

    @GetMapping
    public String getMessage(){
        return "BSDBFVKSBD";
    }

    //{productId}
    @GetMapping("{productId}")
    public ResponseEntity<List<Chat>> getChatByProductId(@PathVariable("productId") String productId){
        System.out.println(productId);
        return new ResponseEntity<List<Chat>>(chatService.getChatByProductId(productId), HttpStatus.OK);
    }

    // delete chat by QuestionId
    //{questionId}
    @DeleteMapping("{questionId}")
    public ResponseEntity<String> deleteChat(@PathVariable("questionId") long questionId){
        chatService.deleteChatByQuestionId(questionId);
        return new ResponseEntity<String>("Chat deleted successfully.", HttpStatus.OK);
    }


}
