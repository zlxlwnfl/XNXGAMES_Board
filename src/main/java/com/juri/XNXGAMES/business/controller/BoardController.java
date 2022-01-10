package com.juri.XNXGAMES.business.controller;

import com.juri.XNXGAMES.business.dto.BoardDTO;
import com.juri.XNXGAMES.business.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/boards/list")
    public ResponseEntity<List<BoardDTO.Response>> getBoardList() {
        return new ResponseEntity<>(boardService.getBoardList(), HttpStatus.OK);
    }

    @GetMapping("/boards")
    public ResponseEntity<Long> getBoardId(@RequestParam String type, @RequestParam String subType) {
        return new ResponseEntity<>(boardService.getBoard(new BoardDTO.Request(type, subType)), HttpStatus.OK);
    }

    @PostMapping("/boards")
    public ResponseEntity<BoardDTO.Response> insertBoard(@Valid @RequestBody BoardDTO.Request boardRequest) {
        return new ResponseEntity<>(
                BoardDTO.Response.fromBoard(boardService.insertBoard(boardRequest)),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/boards/{boardId}")
    public ResponseEntity<Void> updateBoard(@PathVariable long boardId, @Valid @RequestBody BoardDTO.Request boardRequest) {
        boardService.updateBoard(boardId, boardRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/boards/{boardId}")
    public ResponseEntity<Void> deleteBoard(@PathVariable long boardId) {
        boardService.deleteBoard(boardId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
