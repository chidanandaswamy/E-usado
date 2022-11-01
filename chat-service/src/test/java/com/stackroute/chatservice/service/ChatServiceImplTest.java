package com.stackroute.chatservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.stackroute.chatservice.exception.ChatNotFoundException;
import com.stackroute.chatservice.model.Chat;
import com.stackroute.chatservice.model.DatabaseSequence;
import com.stackroute.chatservice.repository.ChatRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.UpdateDefinition;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ChatServiceImpl.class})
@ExtendWith(SpringExtension.class)
class ChatServiceImplTest {
    @MockBean
    private ChatRepository chatRepository;

    @Autowired
    private ChatServiceImpl chatServiceImpl;

    @MockBean
    private MongoOperations mongoOperations;

    /**
     * Method under test: {@link ChatServiceImpl#getSequenceNumber(String)}
     */
    @Test
    void testGetSequenceNumber() {
        DatabaseSequence databaseSequence = new DatabaseSequence();
        databaseSequence.setId("42");
        databaseSequence.setSeq(1);
        when(mongoOperations.findAndModify((Query) any(), (UpdateDefinition) any(), (FindAndModifyOptions) any(),
                (Class<DatabaseSequence>) any())).thenReturn(databaseSequence);
        assertEquals(1, chatServiceImpl.getSequenceNumber("Sequence Name"));
        verify(mongoOperations).findAndModify((Query) any(), (UpdateDefinition) any(), (FindAndModifyOptions) any(),
                (Class<DatabaseSequence>) any());
    }

    /**
     * Method under test: {@link ChatServiceImpl#getSequenceNumber(String)}
     */
    @Test
    void testGetSequenceNumber2() {
        DatabaseSequence databaseSequence = mock(DatabaseSequence.class);
        when(databaseSequence.getSeq()).thenReturn(1);
        doNothing().when(databaseSequence).setId((String) any());
        doNothing().when(databaseSequence).setSeq(anyInt());
        databaseSequence.setId("42");
        databaseSequence.setSeq(1);
        when(mongoOperations.findAndModify((Query) any(), (UpdateDefinition) any(), (FindAndModifyOptions) any(),
                (Class<DatabaseSequence>) any())).thenReturn(databaseSequence);
        assertEquals(1, chatServiceImpl.getSequenceNumber("Sequence Name"));
        verify(mongoOperations).findAndModify((Query) any(), (UpdateDefinition) any(), (FindAndModifyOptions) any(),
                (Class<DatabaseSequence>) any());
        verify(databaseSequence).getSeq();
        verify(databaseSequence).setId((String) any());
        verify(databaseSequence).setSeq(anyInt());
    }

    /**
     * Method under test: {@link ChatServiceImpl#saveChat(Chat)}
     */
    @Test
    void testSaveChat() {
        Chat chat = new Chat();
        chat.setProductId("42");
        chat.setQuestion("Question");
        chat.setQuestionId(123L);
        chat.setReply(new ArrayList<>());
        chat.setUserEmail("jane.doe@example.org");
        when(chatRepository.save((Chat) any())).thenReturn(chat);

        Chat chat1 = new Chat();
        chat1.setProductId("42");
        chat1.setQuestion("Question");
        chat1.setQuestionId(123L);
        chat1.setReply(new ArrayList<>());
        chat1.setUserEmail("jane.doe@example.org");
        assertSame(chat, chatServiceImpl.saveChat(chat1));
        verify(chatRepository).save((Chat) any());
    }

    /**
     * Method under test: {@link ChatServiceImpl#saveChat(Chat)}
     */
    @Test
    void testSaveChat2() {
        when(chatRepository.save((Chat) any())).thenThrow(new ChatNotFoundException("foo"));

        Chat chat = new Chat();
        chat.setProductId("42");
        chat.setQuestion("Question");
        chat.setQuestionId(123L);
        chat.setReply(new ArrayList<>());
        chat.setUserEmail("jane.doe@example.org");
        assertThrows(ChatNotFoundException.class, () -> chatServiceImpl.saveChat(chat));
        verify(chatRepository).save((Chat) any());
    }

    /**
     * Method under test: {@link ChatServiceImpl#updateChat(Chat, long)}
     */
    @Test
    void testUpdateChat() {
        Chat chat = new Chat();
        chat.setProductId("42");
        chat.setQuestion("Question");
        chat.setQuestionId(123L);
        ArrayList<Object> objectList = new ArrayList<>();
        chat.setReply(objectList);
        chat.setUserEmail("jane.doe@example.org");
        Optional<Chat> ofResult = Optional.of(chat);

        Chat chat1 = new Chat();
        chat1.setProductId("42");
        chat1.setQuestion("Question");
        chat1.setQuestionId(123L);
        chat1.setReply(new ArrayList<>());
        chat1.setUserEmail("jane.doe@example.org");
        when(chatRepository.save((Chat) any())).thenReturn(chat1);
        when(chatRepository.findById((Long) any())).thenReturn(ofResult);

        Chat chat2 = new Chat();
        chat2.setProductId("42");
        chat2.setQuestion("Question");
        chat2.setQuestionId(123L);
        chat2.setReply(new ArrayList<>());
        chat2.setUserEmail("jane.doe@example.org");
        Chat actualUpdateChatResult = chatServiceImpl.updateChat(chat2, 123L);
        assertSame(chat, actualUpdateChatResult);
        assertEquals(objectList, actualUpdateChatResult.getReply());
        verify(chatRepository).save((Chat) any());
        verify(chatRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link ChatServiceImpl#updateChat(Chat, long)}
     */
    @Test
    void testUpdateChat2() {
        Chat chat = new Chat();
        chat.setProductId("42");
        chat.setQuestion("Question");
        chat.setQuestionId(123L);
        chat.setReply(new ArrayList<>());
        chat.setUserEmail("jane.doe@example.org");
        Optional<Chat> ofResult = Optional.of(chat);
        when(chatRepository.save((Chat) any())).thenThrow(new ChatNotFoundException("foo"));
        when(chatRepository.findById((Long) any())).thenReturn(ofResult);

        Chat chat1 = new Chat();
        chat1.setProductId("42");
        chat1.setQuestion("Question");
        chat1.setQuestionId(123L);
        chat1.setReply(new ArrayList<>());
        chat1.setUserEmail("jane.doe@example.org");
        assertThrows(ChatNotFoundException.class, () -> chatServiceImpl.updateChat(chat1, 123L));
        verify(chatRepository).save((Chat) any());
        verify(chatRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link ChatServiceImpl#updateChat(Chat, long)}
     */
    @Test
    void testUpdateChat3() {
        Chat chat = mock(Chat.class);
        doNothing().when(chat).setProductId((String) any());
        doNothing().when(chat).setQuestion((String) any());
        doNothing().when(chat).setQuestionId(anyLong());
        doNothing().when(chat).setReply((List) any());
        doNothing().when(chat).setUserEmail((String) any());
        chat.setProductId("42");
        chat.setQuestion("Question");
        chat.setQuestionId(123L);
        chat.setReply(new ArrayList<>());
        chat.setUserEmail("jane.doe@example.org");
        Optional<Chat> ofResult = Optional.of(chat);

        Chat chat1 = new Chat();
        chat1.setProductId("42");
        chat1.setQuestion("Question");
        chat1.setQuestionId(123L);
        chat1.setReply(new ArrayList<>());
        chat1.setUserEmail("jane.doe@example.org");
        when(chatRepository.save((Chat) any())).thenReturn(chat1);
        when(chatRepository.findById((Long) any())).thenReturn(ofResult);

        Chat chat2 = new Chat();
        chat2.setProductId("42");
        chat2.setQuestion("Question");
        chat2.setQuestionId(123L);
        chat2.setReply(new ArrayList<>());
        chat2.setUserEmail("jane.doe@example.org");
        chatServiceImpl.updateChat(chat2, 123L);
        verify(chatRepository).save((Chat) any());
        verify(chatRepository).findById((Long) any());
        verify(chat).setProductId((String) any());
        verify(chat).setQuestion((String) any());
        verify(chat).setQuestionId(anyLong());
        verify(chat, atLeast(1)).setReply((List) any());
        verify(chat).setUserEmail((String) any());
    }

    /**
     * Method under test: {@link ChatServiceImpl#updateChat(Chat, long)}
     */
    @Test
    void testUpdateChat4() {
        Chat chat = new Chat();
        chat.setProductId("42");
        chat.setQuestion("Question");
        chat.setQuestionId(123L);
        chat.setReply(new ArrayList<>());
        chat.setUserEmail("jane.doe@example.org");
        when(chatRepository.save((Chat) any())).thenReturn(chat);
        when(chatRepository.findById((Long) any())).thenReturn(Optional.empty());
        Chat chat1 = mock(Chat.class);
        doNothing().when(chat1).setProductId((String) any());
        doNothing().when(chat1).setQuestion((String) any());
        doNothing().when(chat1).setQuestionId(anyLong());
        doNothing().when(chat1).setReply((List) any());
        doNothing().when(chat1).setUserEmail((String) any());
        chat1.setProductId("42");
        chat1.setQuestion("Question");
        chat1.setQuestionId(123L);
        chat1.setReply(new ArrayList<>());
        chat1.setUserEmail("jane.doe@example.org");

        Chat chat2 = new Chat();
        chat2.setProductId("42");
        chat2.setQuestion("Question");
        chat2.setQuestionId(123L);
        chat2.setReply(new ArrayList<>());
        chat2.setUserEmail("jane.doe@example.org");
        assertThrows(ChatNotFoundException.class, () -> chatServiceImpl.updateChat(chat2, 123L));
        verify(chatRepository).findById((Long) any());
        verify(chat1).setProductId((String) any());
        verify(chat1).setQuestion((String) any());
        verify(chat1).setQuestionId(anyLong());
        verify(chat1).setReply((List) any());
        verify(chat1).setUserEmail((String) any());
    }

    /**
     * Method under test: {@link ChatServiceImpl#getChatByQuestionId(long)}
     */
    @Test
    void testGetChatByQuestionId() {
        Chat chat = new Chat();
        chat.setProductId("42");
        chat.setQuestion("Question");
        chat.setQuestionId(123L);
        chat.setReply(new ArrayList<>());
        chat.setUserEmail("jane.doe@example.org");
        Optional<Chat> ofResult = Optional.of(chat);
        when(chatRepository.findById((Long) any())).thenReturn(ofResult);
        assertSame(chat, chatServiceImpl.getChatByQuestionId(123L));
        verify(chatRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link ChatServiceImpl#getChatByQuestionId(long)}
     */
    @Test
    void testGetChatByQuestionId2() {
        when(chatRepository.findById((Long) any())).thenReturn(Optional.empty());
        assertThrows(ChatNotFoundException.class, () -> chatServiceImpl.getChatByQuestionId(123L));
        verify(chatRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link ChatServiceImpl#getChatByQuestionId(long)}
     */
    @Test
    void testGetChatByQuestionId3() {
        when(chatRepository.findById((Long) any())).thenThrow(new ChatNotFoundException("foo"));
        assertThrows(ChatNotFoundException.class, () -> chatServiceImpl.getChatByQuestionId(123L));
        verify(chatRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link ChatServiceImpl#deleteChatByQuestionId(long)}
     */
    @Test
    void testDeleteChatByQuestionId() {
        Chat chat = new Chat();
        chat.setProductId("42");
        chat.setQuestion("Question");
        chat.setQuestionId(123L);
        chat.setReply(new ArrayList<>());
        chat.setUserEmail("jane.doe@example.org");
        Optional<Chat> ofResult = Optional.of(chat);
        doNothing().when(chatRepository).deleteById((Long) any());
        when(chatRepository.findById((Long) any())).thenReturn(ofResult);
        chatServiceImpl.deleteChatByQuestionId(123L);
        verify(chatRepository).findById((Long) any());
        verify(chatRepository).deleteById((Long) any());
    }

    /**
     * Method under test: {@link ChatServiceImpl#deleteChatByQuestionId(long)}
     */
    @Test
    void testDeleteChatByQuestionId2() {
        Chat chat = new Chat();
        chat.setProductId("42");
        chat.setQuestion("Question");
        chat.setQuestionId(123L);
        chat.setReply(new ArrayList<>());
        chat.setUserEmail("jane.doe@example.org");
        Optional<Chat> ofResult = Optional.of(chat);
        doThrow(new ChatNotFoundException("foo")).when(chatRepository).deleteById((Long) any());
        when(chatRepository.findById((Long) any())).thenReturn(ofResult);
        assertThrows(ChatNotFoundException.class, () -> chatServiceImpl.deleteChatByQuestionId(123L));
        verify(chatRepository).findById((Long) any());
        verify(chatRepository).deleteById((Long) any());
    }

    /**
     * Method under test: {@link ChatServiceImpl#deleteChatByQuestionId(long)}
     */
    @Test
    void testDeleteChatByQuestionId3() {
        doNothing().when(chatRepository).deleteById((Long) any());
        when(chatRepository.findById((Long) any())).thenReturn(Optional.empty());
        assertThrows(ChatNotFoundException.class, () -> chatServiceImpl.deleteChatByQuestionId(123L));
        verify(chatRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link ChatServiceImpl#replyChat(Chat, long)}
     */
    @Test
    void testReplyChat() {
        Chat chat = new Chat();
        chat.setProductId("42");
        chat.setQuestion("Question");
        chat.setQuestionId(123L);
        ArrayList<Object> objectList = new ArrayList<>();
        chat.setReply(objectList);
        chat.setUserEmail("jane.doe@example.org");
        Optional<Chat> ofResult = Optional.of(chat);

        Chat chat1 = new Chat();
        chat1.setProductId("42");
        chat1.setQuestion("Question");
        chat1.setQuestionId(123L);
        chat1.setReply(new ArrayList<>());
        chat1.setUserEmail("jane.doe@example.org");
        when(chatRepository.save((Chat) any())).thenReturn(chat1);
        when(chatRepository.findById((Long) any())).thenReturn(ofResult);

        Chat chat2 = new Chat();
        chat2.setProductId("42");
        chat2.setQuestion("Question");
        chat2.setQuestionId(123L);
        chat2.setReply(new ArrayList<>());
        chat2.setUserEmail("jane.doe@example.org");
        Chat actualReplyChatResult = chatServiceImpl.replyChat(chat2, 123L);
        assertSame(chat, actualReplyChatResult);
        assertEquals(objectList, actualReplyChatResult.getReply());
        verify(chatRepository).save((Chat) any());
        verify(chatRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link ChatServiceImpl#replyChat(Chat, long)}
     */
    @Test
    void testReplyChat2() {
        Chat chat = new Chat();
        chat.setProductId("42");
        chat.setQuestion("Question");
        chat.setQuestionId(123L);
        chat.setReply(new ArrayList<>());
        chat.setUserEmail("jane.doe@example.org");
        Optional<Chat> ofResult = Optional.of(chat);
        when(chatRepository.save((Chat) any())).thenThrow(new ChatNotFoundException("foo"));
        when(chatRepository.findById((Long) any())).thenReturn(ofResult);

        Chat chat1 = new Chat();
        chat1.setProductId("42");
        chat1.setQuestion("Question");
        chat1.setQuestionId(123L);
        chat1.setReply(new ArrayList<>());
        chat1.setUserEmail("jane.doe@example.org");
        assertThrows(ChatNotFoundException.class, () -> chatServiceImpl.replyChat(chat1, 123L));
        verify(chatRepository).save((Chat) any());
        verify(chatRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link ChatServiceImpl#replyChat(Chat, long)}
     */
    @Test
    void testReplyChat3() {
        Chat chat = mock(Chat.class);
        doNothing().when(chat).setProductId((String) any());
        doNothing().when(chat).setQuestion((String) any());
        doNothing().when(chat).setQuestionId(anyLong());
        doNothing().when(chat).setReply((List) any());
        doNothing().when(chat).setUserEmail((String) any());
        chat.setProductId("42");
        chat.setQuestion("Question");
        chat.setQuestionId(123L);
        chat.setReply(new ArrayList<>());
        chat.setUserEmail("jane.doe@example.org");
        Optional<Chat> ofResult = Optional.of(chat);

        Chat chat1 = new Chat();
        chat1.setProductId("42");
        chat1.setQuestion("Question");
        chat1.setQuestionId(123L);
        chat1.setReply(new ArrayList<>());
        chat1.setUserEmail("jane.doe@example.org");
        when(chatRepository.save((Chat) any())).thenReturn(chat1);
        when(chatRepository.findById((Long) any())).thenReturn(ofResult);

        Chat chat2 = new Chat();
        chat2.setProductId("42");
        chat2.setQuestion("Question");
        chat2.setQuestionId(123L);
        chat2.setReply(new ArrayList<>());
        chat2.setUserEmail("jane.doe@example.org");
        chatServiceImpl.replyChat(chat2, 123L);
        verify(chatRepository).save((Chat) any());
        verify(chatRepository).findById((Long) any());
        verify(chat).setProductId((String) any());
        verify(chat).setQuestion((String) any());
        verify(chat).setQuestionId(anyLong());
        verify(chat, atLeast(1)).setReply((List) any());
        verify(chat).setUserEmail((String) any());
    }

    /**
     * Method under test: {@link ChatServiceImpl#replyChat(Chat, long)}
     */
    @Test
    void testReplyChat4() {
        Chat chat = new Chat();
        chat.setProductId("42");
        chat.setQuestion("Question");
        chat.setQuestionId(123L);
        chat.setReply(new ArrayList<>());
        chat.setUserEmail("jane.doe@example.org");
        when(chatRepository.save((Chat) any())).thenReturn(chat);
        when(chatRepository.findById((Long) any())).thenReturn(Optional.empty());
        Chat chat1 = mock(Chat.class);
        doNothing().when(chat1).setProductId((String) any());
        doNothing().when(chat1).setQuestion((String) any());
        doNothing().when(chat1).setQuestionId(anyLong());
        doNothing().when(chat1).setReply((List) any());
        doNothing().when(chat1).setUserEmail((String) any());
        chat1.setProductId("42");
        chat1.setQuestion("Question");
        chat1.setQuestionId(123L);
        chat1.setReply(new ArrayList<>());
        chat1.setUserEmail("jane.doe@example.org");

        Chat chat2 = new Chat();
        chat2.setProductId("42");
        chat2.setQuestion("Question");
        chat2.setQuestionId(123L);
        chat2.setReply(new ArrayList<>());
        chat2.setUserEmail("jane.doe@example.org");
        assertThrows(ChatNotFoundException.class, () -> chatServiceImpl.replyChat(chat2, 123L));
        verify(chatRepository).findById((Long) any());
        verify(chat1).setProductId((String) any());
        verify(chat1).setQuestion((String) any());
        verify(chat1).setQuestionId(anyLong());
        verify(chat1).setReply((List) any());
        verify(chat1).setUserEmail((String) any());
    }

    /**
     * Method under test: {@link ChatServiceImpl#getChatByProductId(String)}
     */
    @Test
    void testGetChatByProductId() {
        when(chatRepository.findByProductId((String) any())).thenReturn(new ArrayList<>());
        assertThrows(ChatNotFoundException.class, () -> chatServiceImpl.getChatByProductId("42"));
        verify(chatRepository).findByProductId((String) any());
    }

    /**
     * Method under test: {@link ChatServiceImpl#getChatByProductId(String)}
     */
    @Test
    void testGetChatByProductId2() {
        Chat chat = new Chat();
        chat.setProductId("42");
        chat.setQuestion("Question");
        chat.setQuestionId(123L);
        chat.setReply(new ArrayList<>());
        chat.setUserEmail("jane.doe@example.org");

        ArrayList<Chat> chatList = new ArrayList<>();
        chatList.add(chat);
        when(chatRepository.findByProductId((String) any())).thenReturn(chatList);
        List<Chat> actualChatByProductId = chatServiceImpl.getChatByProductId("42");
        assertSame(chatList, actualChatByProductId);
        assertEquals(1, actualChatByProductId.size());
        verify(chatRepository).findByProductId((String) any());
    }

    /**
     * Method under test: {@link ChatServiceImpl#getChatByProductId(String)}
     */
    @Test
    void testGetChatByProductId3() {
        when(chatRepository.findByProductId((String) any())).thenThrow(new ChatNotFoundException("foo"));
        assertThrows(ChatNotFoundException.class, () -> chatServiceImpl.getChatByProductId("42"));
        verify(chatRepository).findByProductId((String) any());
    }
}

