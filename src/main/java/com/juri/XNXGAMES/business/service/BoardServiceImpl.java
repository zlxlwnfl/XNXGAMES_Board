package com.juri.XNXGAMES.business.service;

import com.juri.XNXGAMES.business.dto.BoardDTO;
import com.juri.XNXGAMES.business.entity.Board;
import com.juri.XNXGAMES.business.exception.BoardException;
import com.juri.XNXGAMES.business.exception.ErrorCode;
import com.juri.XNXGAMES.business.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
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
	public Long getBoard(@NonNull final BoardDTO.Request boardRequest) {
		Optional<Board> boardEntityOptional =
				boardRepository.findByTypeAndSubType(boardRequest.getType(), boardRequest.getSubType());

		if(boardEntityOptional.isPresent()) {
			return boardEntityOptional.get().getBoardId();
		}
		else {
			throw new BoardException(ErrorCode.BOARD_NOT_EXIST);
		}
	}

	@Override
	public List<BoardDTO.Response> getBoardList() {
		List<Board> list = boardRepository.findAll();

		List<BoardDTO.Response> dtoList = new ArrayList<>();

		for(Board board : list) {
			BoardDTO.Response dto = BoardDTO.Response.builder()
					.boardId(board.getBoardId())
					.type(board.getType())
					.subType(board.getSubType())
					.build();

			dtoList.add(dto);
		}

		return dtoList;
	}

	@Override
	public Board insertBoard(@NonNull final BoardDTO.Request boardRequest) {
		Board board = Board.builder()
				.type(boardRequest.getType())
				.subType(boardRequest.getSubType())
				.build();

		try {
			return boardRepository.save(board);
		}
		catch(IllegalArgumentException e) {
			throw new BoardException(ErrorCode.SERVER_CANNOT_SAVE);
		}
	}

	@Override
	public void updateBoard(final long boardId, @NonNull final BoardDTO.Request boardRequest) {
		String type = boardRequest.getType();
		String subType = boardRequest.getSubType();

		if(boardRepository.existsByBoardId(boardId)) {
			boardRepository.updateById(boardId, type, subType);
		}
		else {
			throw new BoardException(ErrorCode.BOARD_NOT_EXIST);
		}
	}

	@Override
	public void deleteBoard(final long boardId) {
		if(boardRepository.existsByBoardId(boardId)) {
			boardRepository.deleteById(boardId);
		}
		else {
			throw new BoardException(ErrorCode.BOARD_NOT_EXIST);
		}
	}

}
