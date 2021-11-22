package com.juri.XNXGAMES.business.service;

import com.juri.XNXGAMES.business.dto.BoardDTO;
import com.juri.XNXGAMES.business.dto.BoardGetListDTO;
import com.juri.XNXGAMES.business.entity.BoardEntity;
import com.juri.XNXGAMES.business.exception.BoardException;
import com.juri.XNXGAMES.business.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
	
	private final BoardRepository boardRepository;

	@Override
	public Long getBoard(@NonNull final BoardDTO boardDTO) {
		Optional<BoardEntity> boardEntityOptional =
				boardRepository.findByTypeAndSubType(boardDTO.getBoardType(), boardDTO.getBoardSubType());

		if(boardEntityOptional.isPresent()) {
			return boardEntityOptional.get().getId();
		}
		else {
			throw new BoardException(HttpStatus.NOT_FOUND, "board not exist");
		}
	}

	@Override
	public List<BoardGetListDTO> getBoardList() {
		List<BoardEntity> list = boardRepository.findAll();

		List<BoardGetListDTO> dtoList = new ArrayList<>();

		for(BoardEntity board : list) {
			BoardGetListDTO dto = BoardGetListDTO.builder()
					.boardId(board.getId())
					.boardType(board.getType())
					.boardSubType(board.getSubType())
					.build();

			dtoList.add(dto);
		}

		return dtoList;
	}

	@Override
	public BoardEntity insertBoard(@NonNull final BoardDTO boardDTO) {
		BoardEntity board = BoardEntity.builder()
				.type(boardDTO.getBoardType())
				.subType(boardDTO.getBoardSubType())
				.build();

		try {
			return boardRepository.save(board);
		}
		catch(IllegalArgumentException e) {
			throw new BoardException(HttpStatus.INTERNAL_SERVER_ERROR, "server can't save");
		}
	}

	@Override
	public void modifyBoard(final long boardId, @NonNull final BoardDTO boardDTO) {
		String type = boardDTO.getBoardType();
		String subType = boardDTO.getBoardSubType();

		if(boardRepository.existsByBoardId(boardId)) {
			boardRepository.updateById(boardId, type, subType);
		}
		else {
			throw new BoardException(HttpStatus.BAD_REQUEST, "board not exist");
		}
	}

	@Override
	public void deleteBoard(final long boardId) {
		if(boardRepository.existsByBoardId(boardId)) {
			boardRepository.deleteById(boardId);
		}
		else {
			throw new BoardException(HttpStatus.BAD_REQUEST, "board not exist");
		}
	}

}
