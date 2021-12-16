package com.juri.XNXGAMES.business.controller;

import com.juri.XNXGAMES.business.dto.BoardGetListResponseDTO;
import com.juri.XNXGAMES.business.dto.BoardPostResponseDTO;
import com.juri.XNXGAMES.business.dto.BoardRequestDTO;
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
    public ResponseEntity<List<BoardGetListResponseDTO>> getBoardList() {
        return new ResponseEntity<>(boardService.getBoardList(), HttpStatus.OK);
    }

    @GetMapping("/boards")
    public ResponseEntity<Long> getBoardId(@RequestParam String boardType, @RequestParam String boardSubType) {
        return new ResponseEntity<>(boardService.getBoard(new BoardRequestDTO(boardType, boardSubType)), HttpStatus.OK);
    }

    @PostMapping("/boards")
    public ResponseEntity<BoardPostResponseDTO> insertBoard(@Valid @RequestBody BoardRequestDTO boardRequestDTO) {
        return new ResponseEntity<>(
                BoardPostResponseDTO.fromBoardEntity(boardService.insertBoard(boardRequestDTO)),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/boards/{boardId}")
    public ResponseEntity<Void> updateBoard(@PathVariable long boardId, @Valid @RequestBody BoardRequestDTO boardRequestDTO) {
        boardService.updateBoard(boardId, boardRequestDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/boards/{boardId}")
    public ResponseEntity<Void> deleteBoard(@PathVariable long boardId) {
        boardService.deleteBoard(boardId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
