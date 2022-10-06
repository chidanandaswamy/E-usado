package com.stackroute.chatservice.service;


import com.stackroute.chatservice.model.Chat;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
public interface ChatService {
  Chat saveChat(Chat chat);
  Chat getChatById(UUID id);
 // Chat updateChat(Chat chat, UUID uuid);

  Chat updateChatById(UUID questionId);
  void deleteChat(UUID id);
  Chat getProductChatById(UUID productid);

  Chat updateChatReply( UUID questionId);

}

