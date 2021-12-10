package com.juri.XNXGAMES.service;

import com.juri.XNXGAMES.business.dto.BoardRequestDTO;
import com.juri.XNXGAMES.business.dto.BoardGetListResponseDTO;
import com.juri.XNXGAMES.business.entity.BoardEntity;
import com.juri.XNXGAMES.business.exception.BoardException;
import com.juri.XNXGAMES.business.repository.BoardRepository;
import com.juri.XNXGAMES.business.service.BoardService;
import com.juri.XNXGAMES.config.ServiceTestConfiguration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@Import({ ServiceTestConfiguration.class })
public class BoardServiceTests {

    private static final long ID = 1L;
    private static final String TYPE = "type";
    private static final String SUB_TYPE = "subType";

    private static final BoardRequestDTO VALID_BOARD_REQUEST_DTO = new BoardRequestDTO(TYPE, SUB_TYPE);
    private static final BoardEntity validBoardEntity = new BoardEntity(ID, TYPE, SUB_TYPE);
    private static final BoardGetListResponseDTO VALID_BOARD_GET_LIST_RESPONSE_DTO = new BoardGetListResponseDTO(ID, TYPE, SUB_TYPE);

    @Autowired
    private BoardService boardService;

    @Autowired
    private BoardRepository mockBoardRepository;

    @Test
    public void testGetBoardWithNullThrowException() {
        Assertions.assertThrows(Exception.class, () -> boardService.getBoard(null));
    }

    @Test
    public void testGetBoardReturnValidBoardId() {
        Mockito.when(mockBoardRepository.findByTypeAndSubType(TYPE, SUB_TYPE)).thenReturn(Optional.of(validBoardEntity));

        Assertions.assertEquals(1, boardService.getBoard(VALID_BOARD_REQUEST_DTO));
    }

    @Test
    public void testGetBoardThrowBoardException() {
        Mockito.when(mockBoardRepository.findByTypeAndSubType(TYPE, SUB_TYPE)).thenReturn(Optional.empty());

        Assertions.assertThrows(BoardException.class, () -> boardService.getBoard(VALID_BOARD_REQUEST_DTO));
    }

    @Test
    public void testGetBoardListReturnValidList() {
        List<BoardEntity> list = Collections.singletonList(validBoardEntity);
        List<BoardGetListResponseDTO> dtoList = new ArrayList<>(Collections.singletonList(VALID_BOARD_GET_LIST_RESPONSE_DTO));

        Mockito.when(mockBoardRepository.findAll()).thenReturn(list);

        Assertions.assertEquals(dtoList.toString(), boardService.getBoardList().toString());
    }

    @Test
    public void testInsertBoardReturnValidBoardEntity() {
        Mockito.when(mockBoardRepository.save(any())).thenReturn(validBoardEntity);

        Assertions.assertEquals(validBoardEntity, boardService.insertBoard(VALID_BOARD_REQUEST_DTO));
    }

    @Test
    public void testInsertBoardThrowIllegalArgumentException() {
        Mockito.when(mockBoardRepository.save(any())).thenThrow(IllegalArgumentException.class);

        Assertions.assertThrows(BoardException.class, () -> boardService.insertBoard(VALID_BOARD_REQUEST_DTO));
    }

    @Test
    public void testModifyBoardSuccess() {
        Mockito.when(mockBoardRepository.existsByBoardId(ID)).thenReturn(true);

        Assertions.assertDoesNotThrow(() -> boardService.modifyBoard(ID, VALID_BOARD_REQUEST_DTO));
    }

    @Test
    public void testModifyBoardThrowBoardException() {
        Mockito.when(mockBoardRepository.existsByBoardId(ID)).thenReturn(false);

        Assertions.assertThrows(BoardException.class, () -> boardService.modifyBoard(ID, VALID_BOARD_REQUEST_DTO));
    }

    @Test
    public void testDeleteBoardSuccess() {
        Mockito.when(mockBoardRepository.existsByBoardId(ID)).thenReturn(true);

        Assertions.assertDoesNotThrow(() -> boardService.deleteBoard(ID));
    }

    @Test
    public void testDeleteBoardThrowBoardException() {
        Mockito.when(mockBoardRepository.existsByBoardId(ID)).thenReturn(false);

        Assertions.assertThrows(BoardException.class, () -> boardService.deleteBoard(ID));
    }

}
