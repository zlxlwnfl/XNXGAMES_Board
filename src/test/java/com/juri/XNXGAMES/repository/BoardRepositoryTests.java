package com.juri.XNXGAMES.repository;

import com.juri.XNXGAMES.business.entity.BoardEntity;
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

    private BoardEntity firstBoardEntity;
    private BoardEntity secondBoardEntity;

    @BeforeEach
    public void initBoardRepository() {
        List<BoardEntity> boardEntityList = Arrays.asList(
                new BoardEntity(0L, "firstType", "firstSubType"),
                new BoardEntity(0L, "secondType", "secondSubType")
        );

        boardRepository.saveAll(boardEntityList);

        boardEntityList = boardRepository.findAll();
        firstBoardEntity = boardEntityList.get(0);
        secondBoardEntity = boardEntityList.get(1);
    }

    @Test
    public void testExistsByBoardIdWithValidBoardIdReturnTrue() {
        Assertions.assertTrue(boardRepository.existsByBoardId(firstBoardEntity.getBoardId()));
    }

    @Test
    public void testExistsByBoardIdWithInvalidBoardIdReturnFalse() {
        Assertions.assertFalse(boardRepository.existsByBoardId(0L));
    }

    @Test
    public void testFindByTypeAndSubTypeWithValidValue() {
        Assertions.assertEquals(
                Optional.of(firstBoardEntity),
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
                firstBoardEntity.getBoardId(), "changeType", "changeSubType"));
    }

}
