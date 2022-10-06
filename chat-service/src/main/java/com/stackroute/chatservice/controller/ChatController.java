package com.stackroute.chatservice.controller;




import com.stackroute.chatservice.model.Chat;
import com.stackroute.chatservice.service.ChatServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1")
public class ChatController {
     @Autowired
     private ChatServiceImpl chatServiceImpl;

    public ChatController(ChatServiceImpl chatServiceImpl) {
        super();
        this.chatServiceImpl = chatServiceImpl;
    }

    //build Rest api
    @PostMapping("/question")
    public ResponseEntity<Chat> saveChat(@RequestBody Chat chat){
        return new ResponseEntity<Chat>(chatServiceImpl.saveChat(chat), HttpStatus.CREATED);
    }

    //build get answer for particular question_id
    @GetMapping("{uuid}")
    public ResponseEntity<Chat> getChatById(@PathVariable("uuid") UUID questionId){
        return new ResponseEntity<Chat>(chatServiceImpl.getChatById(questionId), HttpStatus.OK);

    }

    //build update Chat Rest Api
    @PutMapping("{uuid}")
      public ResponseEntity<Chat> updateChat(@PathVariable("uuid") UUID questionId
                   ,@RequestBody Chat chat){
        return new ResponseEntity<Chat>(chatServiceImpl.updateChatById( questionId),HttpStatus.OK);

      }

      //build delete chat
      @DeleteMapping("{uuid}")
      public ResponseEntity<String> deleteChat(@PathVariable("uuid") UUID questionId){
        chatServiceImpl.deleteChat(questionId);//deleteChat
          return new ResponseEntity<String>("Chat deleted successfully", HttpStatus.OK);
      }

      //build update chat reply to answer posted earlier


      // build to get particular product comment by id
       @RequestMapping(value = "/product/{id}",method = RequestMethod.GET)
    public ResponseEntity<Chat> getChatByProductId(@PathVariable("productId") UUID productId){
        return new ResponseEntity<Chat>(chatServiceImpl.getProductChatById(productId),HttpStatus.OK) ;
    }

    // build update reply to given question
    @RequestMapping(value = "/answer/{questionId}",method = RequestMethod.PUT)
    public ResponseEntity<Chat> updateChatReply(@PathVariable("questionId")UUID questionId
            , @RequestBody Chat chat){
         return
                  new ResponseEntity<Chat>(chatServiceImpl.updateChatReply(questionId),HttpStatus.OK);
    }


}
