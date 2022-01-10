package com.juri.XNXGAMES.business.repository;

import com.juri.XNXGAMES.business.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {

	boolean existsByBoardId(long boardId);

	Optional<Board> findByTypeAndSubType(String type, String subType);

	@Modifying
	@Transactional
	@Query("UPDATE Board " +
			"SET type = :type, subType = :subType WHERE id = :id")
	void updateById(@Param("id")long boardId, @Param("type") String type, @Param("subType") String subType);

}
