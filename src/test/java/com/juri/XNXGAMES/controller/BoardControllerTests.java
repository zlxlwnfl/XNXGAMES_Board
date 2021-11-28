package com.juri.XNXGAMES.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.juri.XNXGAMES.business.dto.BoardDTO;
import com.juri.XNXGAMES.business.dto.BoardGetListDTO;
import com.juri.XNXGAMES.business.entity.BoardEntity;
import com.juri.XNXGAMES.business.service.BoardService;
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
public class BoardControllerTests {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private static final long ID = 1L;
    private static final String TYPE = "type";
    private static final String SUB_TYPE = "subType";

    private static final BoardDTO validBoardDTO = new BoardDTO(TYPE, SUB_TYPE);
    private static final BoardEntity validBoardEntity = new BoardEntity();

    @Autowired
    MockMvc mockMvc;

    @Autowired
    BoardService mockBoardService;

    @Test
    public void testGetBoardListReturnOk() throws Exception {
        List<BoardGetListDTO> list = new ArrayList<>();

        Mockito.when(mockBoardService.getBoardList()).thenReturn(list);

        mockMvc.perform(MockMvcRequestBuilders.get("/boards/list"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(list)));
    }

    @Test
    public void testGetBoardListReturn5xx() throws Exception {
        Mockito.when(mockBoardService.getBoardList()).thenThrow(RuntimeException.class);

        mockMvc.perform(MockMvcRequestBuilders.get("/boards/list"))
                .andExpect(status().is5xxServerError());
    }

    @Test
    public void testGetBoardIdReturnOk() throws Exception {
        Mockito.when(mockBoardService.getBoard(any())).thenReturn(ID);

        mockMvc.perform(MockMvcRequestBuilders.get("/boards")
                        .queryParam("boardType", TYPE)
                        .queryParam("boardSubType", SUB_TYPE))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(ID)));
    }

    @Test
    public void testGetBoardIdReturn5xx() throws Exception {
        Mockito.when(mockBoardService.getBoard(any())).thenThrow(RuntimeException.class);

        mockMvc.perform(MockMvcRequestBuilders.get("/boards")
                        .queryParam("boardType", TYPE)
                        .queryParam("boardSubType", SUB_TYPE))
                .andExpect(status().is5xxServerError());
    }

    @Test
    public void testInsertBoardReturnCreated() throws Exception {
        Mockito.when(mockBoardService.insertBoard(any())).thenReturn(validBoardEntity);

        mockMvc.perform(MockMvcRequestBuilders.post("/boards")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(validBoardDTO)))
                .andExpect(status().isCreated())
                .andExpect(content().json(objectMapper.writeValueAsString(validBoardEntity)));
    }

    @Test
    public void testInsertBoardReturn5xx() throws Exception {
        Mockito.when(mockBoardService.insertBoard(any())).thenThrow(RuntimeException.class);

        mockMvc.perform(MockMvcRequestBuilders.post("/boards")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(validBoardDTO)))
                .andExpect(status().is5xxServerError());
    }

    @Test
    public void testModifyBoardReturnOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/boards/{boardId}", ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(validBoardDTO)))
                .andExpect(status().isOk());
    }

    @Test
    public void testModifyBoardReturn5xx() throws Exception {
        Mockito.doThrow(RuntimeException.class).when(mockBoardService).modifyBoard(anyLong(), any());

        mockMvc.perform(MockMvcRequestBuilders.put("/boards/{boardId}", ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(validBoardDTO)))
                .andExpect(status().is5xxServerError());
    }

    @Test
    public void testDeleteBoardReturnOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/boards/{boardId}", ID))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteBoardReturn5xx() throws Exception {
        Mockito.doThrow(RuntimeException.class).when(mockBoardService).deleteBoard(ID);

        mockMvc.perform(MockMvcRequestBuilders.delete("/boards/{boardId}", ID))
                .andExpect(status().is5xxServerError());
    }

}
