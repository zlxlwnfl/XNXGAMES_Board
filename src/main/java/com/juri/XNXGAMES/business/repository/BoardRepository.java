package com.juri.XNXGAMES.business.repository;

import com.juri.XNXGAMES.business.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {

	boolean existsByBoardId(long boardId);

	Optional<BoardEntity> findByTypeAndSubType(String type, String subType);

	@Modifying
	@Transactional
	@Query("UPDATE BoardEntity " +
			"SET type = :type, subType = :subType WHERE id = :id")
	void updateById(@Param("id")long boardId, @Param("type") String type, @Param("subType") String subType);

}
