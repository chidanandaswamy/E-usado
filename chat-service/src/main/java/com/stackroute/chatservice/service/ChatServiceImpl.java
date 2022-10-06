package com.stackroute.chatservice.service;




import com.fasterxml.uuid.Generators;
import com.stackroute.chatservice.exception.ChatNotFoundException;
import com.stackroute.chatservice.model.Chat;
import com.stackroute.chatservice.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ChatServiceImpl implements ChatService{
   @Autowired
   private ChatRepository chatRepository;


    @Autowired
    public ChatServiceImpl(ChatRepository chatRepository) {
        super();
        this.chatRepository = chatRepository;
    }

   @Override
    public Chat saveChat(Chat chat) {
        chat.setQuestionId(Generators.timeBasedGenerator().generate());
        return chatRepository.save(chat);
    }

    @Override
    public Chat getChatById(UUID id) {
        Optional<Chat> chat = chatRepository.findById(id);
        if(chat.isPresent()){
            return chat.get();
        }else {
            throw new ChatNotFoundException("product chat with id" + id + "is not found.");
        }
        
    }

    @Override
    public Chat updateChatById(UUID questionId) {
        Optional<Chat> chat = chatRepository.findById(questionId);
        if (chat.isPresent()) {
            return chat.get();
        } else {
            throw new ChatNotFoundException("Chat not found with id" + questionId);
        }
    }

    @Override
    public void deleteChat(UUID id) {
       //check- whether a chat exist in a database or not
        chatRepository.findById(id).orElseThrow(() -> new ChatNotFoundException("chat with id" + id + "is not found."));

        chatRepository.deleteById(id);
    }

    @Override
    public Chat getProductChatById(UUID productId) {
        Optional<Chat>  chat = chatRepository.findById(productId);
        if(chat.isPresent()){
            return chat.get();
            
        }else{
            throw new ChatNotFoundException("product chat with id" + productId + "is not found.");
        }
    }

    @Override
    public Chat updateChatReply(UUID questionId) {
        Chat existingChat = chatRepository.findById(questionId).orElseThrow(
                () -> new ChatNotFoundException(" Chat with " + questionId + "is not found "));
        existingChat.setReply(updateChatById(questionId).getReply());
        chatRepository.save(existingChat);

        return existingChat;
    }





}
