package com.juri.XNXGAMES.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.juri.XNXGAMES.business.dto.PostGetDTO;
import com.juri.XNXGAMES.business.dto.PostGetListDTO;
import com.juri.XNXGAMES.business.dto.PostPostDTO;
import com.juri.XNXGAMES.business.dto.PostPutDTO;
import com.juri.XNXGAMES.business.entity.PostEntity;
import com.juri.XNXGAMES.business.service.PostService;
import com.juri.XNXGAMES.config.ControllerTestConfiguration;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@Import({ ControllerTestConfiguration.class })
public class PostControllerTests {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private static final long ID = 1L;

    private static final PostGetDTO validPostGetDTO = new PostGetDTO(ID, "", "", 0, "", "", "", 0, 0, new ArrayList<>());
    private static final PostPutDTO validPostPutDTO = new PostPutDTO("", "", "", "", new ArrayList<>());
    private static final PostPostDTO validPostPostDTO = new PostPostDTO(ID, "", "", "", "", new ArrayList<>());
    private static final PostEntity validPostEntity = new PostEntity();

    @Autowired
    MockMvc mockMvc;

    @Autowired
    PostService mockPostService;

    @Test
    public void testInsertPostReturnCreated() throws Exception {
        Mockito.when(mockPostService.insertPost(anyLong(), any())).thenReturn(validPostEntity);

        mockMvc.perform(MockMvcRequestBuilders.post("/boards/{boardId}/posts", ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(validPostPostDTO)))
                .andExpect(status().isCreated())
                .andExpect(content().json(objectMapper.writeValueAsString(validPostEntity)));
    }

    @Test
    public void testInsertPostReturn5xx() throws Exception {
        Mockito.when(mockPostService.insertPost(anyLong(), any())).thenThrow(RuntimeException.class);

        mockMvc.perform(MockMvcRequestBuilders.post("/boards/{boardId}/posts", ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(validPostPostDTO)))
                .andExpect(status().is5xxServerError());
    }

    @Test
    public void testUpdatePostReturnOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/boards/{boardId}/posts/{postId}", ID, ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(validPostPutDTO)))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdatePostReturn5xx() throws Exception {
        Mockito.doThrow(RuntimeException.class).when(mockPostService).modifyPost(anyLong(), any());

        mockMvc.perform(MockMvcRequestBuilders.put("/boards/{boardId}/posts/{postId}", ID, ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(validPostPutDTO)))
                .andExpect(status().is5xxServerError());
    }
    @Test
    public void testGetPostListReturnOk() throws Exception {
        List<PostGetListDTO> list = new ArrayList<>();

        Mockito.when(mockPostService.getPostList(anyLong(), any())).thenReturn(list);

        mockMvc.perform(MockMvcRequestBuilders.get("/boards/{boardId}/posts/list", ID)
                        .param("currentPageNum", "0")
                        .param("amountData", "0"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(list)));
    }

    @Test
    public void testGetPostListReturn5xx() throws Exception {
        Mockito.when(mockPostService.getPostList(anyLong(), any())).thenThrow(RuntimeException.class);

        mockMvc.perform(MockMvcRequestBuilders.get("/boards/{boardId}/posts/list", ID)
                        .param("currentPageNum", "0")
                        .param("amountData", "0"))
                .andExpect(status().is5xxServerError());
    }
    @Test
    public void testGetPostReturnOk() throws Exception {
        Mockito.when(mockPostService.getPost(anyLong())).thenReturn(validPostGetDTO);

        mockMvc.perform(MockMvcRequestBuilders.get("/boards/{boardId}/posts/{postId}", ID, ID))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(validPostGetDTO)));
    }

    @Test
    public void testGetPostReturn5xx() throws Exception {
        Mockito.when(mockPostService.getPost(anyLong())).thenThrow(RuntimeException.class);

        mockMvc.perform(MockMvcRequestBuilders.get("/boards/{boardId}/posts/{postId}", ID, ID))
                .andExpect(status().is5xxServerError());
    }
    @Test
    public void testDeletePostReturnOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/boards/{boardId}/posts/{postId}", ID, ID))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeletePostReturn5xx() throws Exception {
        Mockito.doThrow(RuntimeException.class).when(mockPostService).deletePost(anyLong());

        mockMvc.perform(MockMvcRequestBuilders.delete("/boards/{boardId}/posts/{postId}", ID, ID))
                .andExpect(status().is5xxServerError());
    }
    @Test
    public void testGetAmountPostReturnOk() throws Exception {
        Mockito.when(mockPostService.getAmountPost(anyLong())).thenReturn(0);

        mockMvc.perform(MockMvcRequestBuilders.get("/boards/{boardId}/posts/amount", ID))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(0)));
    }

    @Test
    public void testGetAmountPostReturn5xx() throws Exception {
        Mockito.when(mockPostService.getAmountPost(anyLong())).thenThrow(RuntimeException.class);

        mockMvc.perform(MockMvcRequestBuilders.get("/boards/{boardId}/posts/amount", ID))
                .andExpect(status().is5xxServerError());
    }

}
