package com.juri.XNXGAMES.business.repository;

import com.juri.XNXGAMES.business.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

	boolean existsByCommentId(long commentId);

	List<Comment> findByPostIdOrderByRegdateDesc(long postId);
	
	@Modifying
	@Transactional
	@Query("UPDATE Comment " +
			"SET content = :content WHERE id = :id")
	void updateById(@Param("id")long commentId, @Param("content") String content);
	
}
