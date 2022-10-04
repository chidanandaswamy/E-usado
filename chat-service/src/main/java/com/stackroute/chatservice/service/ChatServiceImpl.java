package com.stackroute.chatservice.service;




import com.stackroute.chatservice.exception.ResourceNotFoundException;
import com.stackroute.chatservice.model.Chat;
import com.stackroute.chatservice.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ChatServiceImpl implements ChatService{

   private ChatRepository chatRepository;
    private UUID uuid;

    @Autowired
    public ChatServiceImpl(ChatRepository chatRepository) {
        super();
        this.chatRepository = chatRepository;
    }

    @Override
    public Chat saveChat(Chat chat) {
        return chatRepository.save(chat);
    }

    @Override
    public Chat getChatById(UUID id) {
        Optional<Chat> chat = chatRepository.findById(id);
        if(chat.isPresent()){
            return chat.get();
        }else {
           //  throw ResourceNotFoundException("Chat","UUID", id);
        }
        return null;
    }

    @Override
    public Chat updateChat(Chat chat, UUID uuid) {

       Chat existingChat = chatRepository.findById(uuid).orElseThrow(
               () -> new ResourceNotFoundException("Chat", "UUID", uuid));

       existingChat.setQuestions(chat.getQuestions());
       chatRepository.save(existingChat);

        return existingChat;
    }

    @Override
    public void deleteChat(UUID id) {
       //check- whether a chat exist in a database or not
        chatRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Chat","UUID", uuid));

        chatRepository.deleteById(id);
    }


}
