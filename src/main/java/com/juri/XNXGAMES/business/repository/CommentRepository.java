package com.juri.XNXGAMES.business.repository;

import com.juri.XNXGAMES.business.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

	List<CommentEntity> findByPostIdOrderByRegdateDesc(Long postId);
	
	@Modifying
	@Transactional
	@Query("UPDATE CommentEntity " +
			"SET content = :content WHERE id = :id")
	void updateById(@Param("id")Long commentId, @Param("content") String content);
	
}
