package com.juri.XNXGAMES.service;

import com.juri.XNXGAMES.business.dto.BoardDTO;
import com.juri.XNXGAMES.business.entity.Board;
import com.juri.XNXGAMES.business.exception.BoardException;
import com.juri.XNXGAMES.business.repository.BoardRepository;
import com.juri.XNXGAMES.business.service.BoardService;
import com.juri.XNXGAMES.config.MockRepoConfiguration;
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
@Import({ MockRepoConfiguration.class })
public class BoardServiceTests {

    private static final long ID = 1L;
    private static final String TYPE = "type";
    private static final String SUB_TYPE = "subType";

    private static final Board VALID_BOARD = new Board(ID, TYPE, SUB_TYPE);
    private static final BoardDTO.Request VALID_BOARD_REQUEST = new BoardDTO.Request(TYPE, SUB_TYPE);
    private static final BoardDTO.Response VALID_BOARD_RESPONSE = new BoardDTO.Response(ID, TYPE, SUB_TYPE);

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
        Mockito.when(mockBoardRepository.findByTypeAndSubType(TYPE, SUB_TYPE)).thenReturn(Optional.of(VALID_BOARD));

        Assertions.assertEquals(1, boardService.getBoard(VALID_BOARD_REQUEST));
    }

    @Test
    public void testGetBoardThrowBoardException() {
        Mockito.when(mockBoardRepository.findByTypeAndSubType(TYPE, SUB_TYPE)).thenReturn(Optional.empty());

        Assertions.assertThrows(BoardException.class, () -> boardService.getBoard(VALID_BOARD_REQUEST));
    }

    @Test
    public void testGetBoardListReturnValidList() {
        List<Board> list = Collections.singletonList(VALID_BOARD);
        List<BoardDTO.Response> dtoList = new ArrayList<>(Collections.singletonList(VALID_BOARD_RESPONSE));

        Mockito.when(mockBoardRepository.findAll()).thenReturn(list);

        Assertions.assertEquals(dtoList.toString(), boardService.getBoardList().toString());
    }

    @Test
    public void testInsertBoardReturnValidBoardEntity() {
        Mockito.when(mockBoardRepository.save(any())).thenReturn(VALID_BOARD);

        Assertions.assertEquals(VALID_BOARD, boardService.insertBoard(VALID_BOARD_REQUEST));
    }

    @Test
    public void testInsertBoardThrowIllegalArgumentException() {
        Mockito.when(mockBoardRepository.save(any())).thenThrow(IllegalArgumentException.class);

        Assertions.assertThrows(BoardException.class, () -> boardService.insertBoard(VALID_BOARD_REQUEST));
    }

    @Test
    public void testUpdateBoardSuccess() {
        Mockito.when(mockBoardRepository.existsByBoardId(ID)).thenReturn(true);

        Assertions.assertDoesNotThrow(() -> boardService.updateBoard(ID, VALID_BOARD_REQUEST));
    }

    @Test
    public void testUpdateBoardThrowBoardException() {
        Mockito.when(mockBoardRepository.existsByBoardId(ID)).thenReturn(false);

        Assertions.assertThrows(BoardException.class, () -> boardService.updateBoard(ID, VALID_BOARD_REQUEST));
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
