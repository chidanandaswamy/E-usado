package com.stackroute.chatservice.service;



import com.stackroute.chatservice.exception.ChatNotFoundException;
import com.stackroute.chatservice.model.Chat;
import com.stackroute.chatservice.model.DatabaseSequence;
import com.stackroute.chatservice.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;




import org.springframework.data.mongodb.core.query.Criteria;

import org.springframework.data.mongodb.core.query.Update;


import java.util.List;
import java.util.Objects;
import java.util.Optional;

import java.util.UUID;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;


@Service
public class ChatServiceImpl implements ChatService{
   @Autowired
   private ChatRepository chatRepository;


    @Autowired
    private MongoOperations mongoOperations;
    public int getSequenceNumber(String sequenceName)
    {
        Query query = new Query(Criteria.where("id").is(sequenceName));
        Update update = new Update().inc("seq", 1);
        DatabaseSequence counter = mongoOperations.findAndModify(query, update, options().returnNew(true).upsert(true), DatabaseSequence.class);
        return !Objects.isNull(counter)?counter.getSeq() : 1;
    }


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
    public Chat updateChat(Chat chat, long questionId) {
        // we need to check whether question in database is present or not

        Chat existingQuestion = chatRepository.findById(questionId).orElseThrow(
                () -> new ChatNotFoundException("Question with Id" + questionId + "not Found"));
        existingQuestion.setReply(chat.getReply());

        // save existing question
        chatRepository.save(existingQuestion);
        return existingQuestion;
    }

    @Override
    public Chat getChatByQuestionId(long questionId) {
        Optional<Chat> chat = chatRepository.findById(questionId);
        if(chat.isPresent()){
            return chat.get();
        }else{
            throw new ChatNotFoundException("No reply found by Question with Id " + questionId);
        }

    }

    @Override
    public void deleteChatByQuestionId(long questionId) {
        //check whether a chat exist in db
        chatRepository.findById(questionId).orElseThrow(()
                -> new ChatNotFoundException("There is no chat found by Id " + questionId));
      chatRepository.deleteById(questionId);
    }

    @Override
    public Chat replyChat(Chat chat, long questionId) {
         Chat existingChat = chatRepository.findById(questionId).orElseThrow(
                 () -> new ChatNotFoundException("Chat does not Exist with Id " +questionId));

         existingChat.setReply(chat.getReply());
         //save
        chatRepository.save(existingChat);
        return existingChat;
    }



   /* @Override
    public Chat getChatByProductId(UUID productId) {
        Optional <Chat> chat = chatRepository.findById(productId);

        return chatRepository.findById(productId).orElseThrow(()
                -> new ChatNotFoundException("Chat is not present with Product Id " + productId));
    }*/

    @Override
    public List<Chat> getChatByProductId(String productId) {
        List<Chat> chat= chatRepository.findByProductId(productId);
        if(chat!=null&&chat.size() > 0){
            return chat;
        }else{
            throw new ChatNotFoundException(" Chat not is not present  with Product Id " + productId);
        }


    }

    /*if(chat.isPresent()){
            return chat.get();
        }else{
            throw new ChatNotFoundException("No reply found by Question with Id " + questionId);
        }

       */

    //return chatRepository.findById(productId).orElseThrow(()
    //      -> new ChatNotFoundException("Chat is not present with Product Id "  + productId));
}
