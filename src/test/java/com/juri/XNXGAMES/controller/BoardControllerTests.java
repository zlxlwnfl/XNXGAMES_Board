package com.juri.XNXGAMES.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.juri.XNXGAMES.business.dto.BoardGetListResponseDTO;
import com.juri.XNXGAMES.business.dto.BoardPostResponseDTO;
import com.juri.XNXGAMES.business.dto.BoardRequestDTO;
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

    private static final BoardRequestDTO VALID_BOARD_REQUEST_DTO = new BoardRequestDTO(TYPE, SUB_TYPE);
    private static final BoardEntity VALID_BOARD_ENTITY = new BoardEntity(ID, TYPE, SUB_TYPE);

    @Autowired
    MockMvc mockMvc;

    @Autowired
    BoardService mockBoardService;

    @Test
    public void testGetBoardListReturnOk() throws Exception {
        List<BoardGetListResponseDTO> list = new ArrayList<>();

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
        Mockito.when(mockBoardService.insertBoard(any())).thenReturn(VALID_BOARD_ENTITY);

        mockMvc.perform(MockMvcRequestBuilders.post("/boards")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(VALID_BOARD_REQUEST_DTO)))
                .andExpect(status().isCreated())
                .andExpect(content().json(objectMapper.writeValueAsString(
                        BoardPostResponseDTO.fromBoardEntity(VALID_BOARD_ENTITY)
                )));
    }

    @Test
    public void testInsertBoardReturn5xx() throws Exception {
        Mockito.when(mockBoardService.insertBoard(any())).thenThrow(RuntimeException.class);

        mockMvc.perform(MockMvcRequestBuilders.post("/boards")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(VALID_BOARD_REQUEST_DTO)))
                .andExpect(status().is5xxServerError());
    }

    @Test
    public void testUpdateBoardReturnOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/boards/{boardId}", ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(VALID_BOARD_REQUEST_DTO)))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateBoardReturn5xx() throws Exception {
        Mockito.doThrow(RuntimeException.class).when(mockBoardService).updateBoard(anyLong(), any());

        mockMvc.perform(MockMvcRequestBuilders.put("/boards/{boardId}", ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(VALID_BOARD_REQUEST_DTO)))
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
