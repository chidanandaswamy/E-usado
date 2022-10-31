package com.stackroute.chatservice.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.chatservice.model.Chat;
import com.stackroute.chatservice.service.ChatService;
import com.stackroute.chatservice.service.ChatServiceImpl;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {ChatController.class, ChatService.class})
@ExtendWith(SpringExtension.class)
class ChatControllerTest {
    @Autowired
    private ChatController chatController;

    @MockBean
    private ChatService chatService;

    @MockBean
    private ChatServiceImpl chatServiceImpl;

    /**
     * Method under test: {@link ChatController#saveChat(Chat)}
     */
    @Test
    void testSaveChat() throws Exception {
        Chat chat = new Chat();
        chat.setProductId("42");
        chat.setQuestion("Question");
        chat.setQuestionId(123L);
        chat.setReply(new ArrayList<>());
        chat.setUserEmail("jane.doe@example.org");
        when(chatService.saveChat((Chat) any())).thenReturn(chat);
        when(chatServiceImpl.getSequenceNumber((String) any())).thenReturn(10);

        Chat chat1 = new Chat();
        chat1.setProductId("42");
        chat1.setQuestion("Question");
        chat1.setQuestionId(123L);
        chat1.setReply(new ArrayList<>());
        chat1.setUserEmail("jane.doe@example.org");
        String content = (new ObjectMapper()).writeValueAsString(chat1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/question")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(chatController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"questionId\":123,\"productId\":\"42\",\"question\":\"Question\",\"reply\":[],\"userEmail\":\"jane.doe@example"
                                        + ".org\"}"));
    }

    /**
     * Method under test: {@link ChatController#updateChat(long, Chat)}
     */
    @Test
    void testUpdateChat() throws Exception {
        Chat chat = new Chat();
        chat.setProductId("42");
        chat.setQuestion("Question");
        chat.setQuestionId(123L);
        chat.setReply(new ArrayList<>());
        chat.setUserEmail("jane.doe@example.org");
        when(chatService.updateChat((Chat) any(), anyLong())).thenReturn(chat);

        Chat chat1 = new Chat();
        chat1.setProductId("42");
        chat1.setQuestion("Question");
        chat1.setQuestionId(123L);
        chat1.setReply(new ArrayList<>());
        chat1.setUserEmail("jane.doe@example.org");
        String content = (new ObjectMapper()).writeValueAsString(chat1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/v1/reply/{questionId}", 123L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(chatController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"questionId\":123,\"productId\":\"42\",\"question\":\"Question\",\"reply\":[],\"userEmail\":\"jane.doe@example"
                                        + ".org\"}"));
    }

    /**
     * Method under test: {@link ChatController#getChatByQuestionId(long)}
     */
    @Test
    void testGetChatByQuestionId() throws Exception {
        Chat chat = new Chat();
        chat.setProductId("42");
        chat.setQuestion("Question");
        chat.setQuestionId(123L);
        chat.setReply(new ArrayList<>());
        chat.setUserEmail("jane.doe@example.org");
        when(chatService.getChatByQuestionId(anyLong())).thenReturn(chat);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/reply/{questionId}", 123L);
        MockMvcBuilders.standaloneSetup(chatController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"questionId\":123,\"productId\":\"42\",\"question\":\"Question\",\"reply\":[],\"userEmail\":\"jane.doe@example"
                                        + ".org\"}"));
    }

    /**
     * Method under test: {@link ChatController#replyChat(long, Chat)}
     */
    @Test
    void testReplyChat() throws Exception {
        Chat chat = new Chat();
        chat.setProductId("42");
        chat.setQuestion("Question");
        chat.setQuestionId(123L);
        chat.setReply(new ArrayList<>());
        chat.setUserEmail("jane.doe@example.org");
        when(chatService.replyChat((Chat) any(), anyLong())).thenReturn(chat);

        Chat chat1 = new Chat();
        chat1.setProductId("42");
        chat1.setQuestion("Question");
        chat1.setQuestionId(123L);
        chat1.setReply(new ArrayList<>());
        chat1.setUserEmail("jane.doe@example.org");
        String content = (new ObjectMapper()).writeValueAsString(chat1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/v1/answer/{questionId}", 123L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(chatController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"questionId\":123,\"productId\":\"42\",\"question\":\"Question\",\"reply\":[],\"userEmail\":\"jane.doe@example"
                                        + ".org\"}"));
    }

    /**
     * Method under test: {@link ChatController#deleteChat(long)}
     */
    @Test
    void testDeleteChat() throws Exception {
        doNothing().when(chatService).deleteChatByQuestionId(anyLong());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/v1/{questionId}", 123L);
        MockMvcBuilders.standaloneSetup(chatController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Chat deleted successfully."));
    }

    /**
     * Method under test: {@link ChatController#getChatByProductId(String)}
     */
    @Test
    void testGetChatByProductId() throws Exception {
        when(chatService.getChatByProductId((String) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/{productId}", "42");
        MockMvcBuilders.standaloneSetup(chatController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link ChatController#getChatByProductId(String)}
     */
    @Test
    void testGetChatByProductId2() throws Exception {
        when(chatService.getChatByProductId((String) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/{productId}", "", "Uri Vars");
        MockMvcBuilders.standaloneSetup(chatController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("BSDBFVKSBD"));
    }

    /**
     * Method under test: {@link ChatController#getChatByProductId(String)}
     */
    @Test
    void testGetChatByProductId3() throws Exception {
        when(chatService.getChatByProductId((String) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/v1/{productId}", "42");
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(chatController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link ChatController#deleteChat(long)}
     */
    @Test
    void testDeleteChat2() throws Exception {
        doNothing().when(chatService).deleteChatByQuestionId(anyLong());
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/api/v1/{questionId}", 123L);
        deleteResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(chatController)
                .build()
                .perform(deleteResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Chat deleted successfully."));
    }

    /**
     * Method under test: {@link ChatController#getMessage()}
     */
    @Test
    void testGetMessage() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1");
        MockMvcBuilders.standaloneSetup(chatController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("BSDBFVKSBD"));
    }

    /**
     * Method under test: {@link ChatController#getMessage()}
     */
    @Test
    void testGetMessage2() throws Exception {
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/v1");
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(chatController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("BSDBFVKSBD"));
    }
}

