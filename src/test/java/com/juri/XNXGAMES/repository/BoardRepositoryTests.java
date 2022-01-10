package com.juri.XNXGAMES.repository;

import com.juri.XNXGAMES.business.entity.Board;
import com.juri.XNXGAMES.business.repository.BoardRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@DataJpaTest(properties = "spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.H2Dialect")
public class BoardRepositoryTests {

    @Autowired
    private BoardRepository boardRepository;

    private Board firstBoard;
    private Board secondBoard;

    @BeforeEach
    public void initBoardRepository() {
        List<Board> boardList = Arrays.asList(
                new Board(0L, "firstType", "firstSubType"),
                new Board(0L, "secondType", "secondSubType")
        );

        boardRepository.saveAll(boardList);

        boardList = boardRepository.findAll();
        firstBoard = boardList.get(0);
        secondBoard = boardList.get(1);
    }

    @Test
    public void testExistsByBoardIdWithValidBoardIdReturnTrue() {
        Assertions.assertTrue(boardRepository.existsByBoardId(firstBoard.getBoardId()));
    }

    @Test
    public void testExistsByBoardIdWithInvalidBoardIdReturnFalse() {
        Assertions.assertFalse(boardRepository.existsByBoardId(0L));
    }

    @Test
    public void testFindByTypeAndSubTypeWithValidValue() {
        Assertions.assertEquals(
                Optional.of(firstBoard),
                boardRepository.findByTypeAndSubType("firstType", "firstSubType")
        );
    }

    @Test
    public void testFindByTypeAndSubTypeWithInvalidValueReturnEmpty() {
        Assertions.assertEquals(
                Optional.empty(),
                boardRepository.findByTypeAndSubType("noType", "noSubType")
        );
    }

    @Test
    public void testUpdateByIdWithValidValueSucess() {
        Assertions.assertDoesNotThrow(() -> boardRepository.updateById(
                firstBoard.getBoardId(), "changeType", "changeSubType"));
    }

}
