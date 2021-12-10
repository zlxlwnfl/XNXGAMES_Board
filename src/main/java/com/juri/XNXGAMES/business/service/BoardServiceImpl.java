package com.juri.XNXGAMES.business.service;

import com.juri.XNXGAMES.business.dto.BoardRequestDTO;
import com.juri.XNXGAMES.business.dto.BoardGetListResponseDTO;
import com.juri.XNXGAMES.business.entity.BoardEntity;
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
	public Long getBoard(@NonNull final BoardRequestDTO boardRequestDTO) {
		Optional<BoardEntity> boardEntityOptional =
				boardRepository.findByTypeAndSubType(boardRequestDTO.getBoardType(), boardRequestDTO.getBoardSubType());

		if(boardEntityOptional.isPresent()) {
			return boardEntityOptional.get().getBoardId();
		}
		else {
			throw new BoardException(ErrorCode.BOARD_NOT_EXIST);
		}
	}

	@Override
	public List<BoardGetListResponseDTO> getBoardList() {
		List<BoardEntity> list = boardRepository.findAll();

		List<BoardGetListResponseDTO> dtoList = new ArrayList<>();

		for(BoardEntity board : list) {
			BoardGetListResponseDTO dto = BoardGetListResponseDTO.builder()
					.boardId(board.getBoardId())
					.boardType(board.getType())
					.boardSubType(board.getSubType())
					.build();

			dtoList.add(dto);
		}

		return dtoList;
	}

	@Override
	public BoardEntity insertBoard(@NonNull final BoardRequestDTO boardRequestDTO) {
		BoardEntity board = BoardEntity.builder()
				.type(boardRequestDTO.getBoardType())
				.subType(boardRequestDTO.getBoardSubType())
				.build();

		try {
			return boardRepository.save(board);
		}
		catch(IllegalArgumentException e) {
			throw new BoardException(ErrorCode.SERVER_CANNOT_SAVE);
		}
	}

	@Override
	public void modifyBoard(final long boardId, @NonNull final BoardRequestDTO boardRequestDTO) {
		String type = boardRequestDTO.getBoardType();
		String subType = boardRequestDTO.getBoardSubType();

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
