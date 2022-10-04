package com.stackroute.chatservice.service;


import com.stackroute.chatservice.model.Chat;

import java.util.UUID;

public interface ChatService {
  Chat saveChat(Chat chat);
  Chat getChatById(UUID id);
  Chat updateChat(Chat chat, UUID uuid);
  void deleteChat(UUID id);

}
