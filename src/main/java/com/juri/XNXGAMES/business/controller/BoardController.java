package com.juri.XNXGAMES.business.controller;

import com.juri.XNXGAMES.business.dto.BoardDTO;
import com.juri.XNXGAMES.business.dto.BoardGetListDTO;
import com.juri.XNXGAMES.business.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/boards")
    public ResponseEntity<List<BoardGetListDTO>> getBoardList() {
        return new ResponseEntity<>(boardService.getBoardList(), HttpStatus.OK);
    }

    @GetMapping("/boards")
    public ResponseEntity<Long> getBoardId(@RequestBody BoardDTO boardDTO) {
        return new ResponseEntity<>(boardService.getBoard(
                boardDTO.getBoardType(),
                boardDTO.getBoardSubType()
        ), HttpStatus.OK);
    }

    @PostMapping("/boards")
    public ResponseEntity<Void> insertBoard(@RequestBody BoardDTO boardDTO) {
        boardService.insertBoard(boardDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/boards/{boardId}")
    public ResponseEntity<Void> modifyBoard(@PathVariable long boardId, @RequestBody BoardDTO boardDTO) {
        boardService.modifyBoard(boardId, boardDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/boards/{boardId}")
    public ResponseEntity<Void> deleteBoard(@PathVariable long boardId) {
        boardService.deleteBoard(boardId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
