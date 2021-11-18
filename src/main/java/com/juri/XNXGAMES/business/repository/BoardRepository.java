package com.juri.XNXGAMES.business.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.juri.XNXGAMES.business.entity.BoardEntity;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
	
	Optional<BoardEntity> findByTypeAndSubType(String type, String subType);

}
