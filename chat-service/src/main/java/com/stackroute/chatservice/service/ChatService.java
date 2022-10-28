package com.stackroute.chatservice.service;


import com.stackroute.chatservice.model.Chat;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface ChatService {

  Chat saveChat(Chat chat);

  Chat updateChat(Chat chat, long questionId);

  Chat getChatByQuestionId(long questionId);

   void deleteChatByQuestionId(long questionId);

   Chat replyChat(Chat chat, long questionId);

   List<Chat> getChatByProductId(String productId);

}

