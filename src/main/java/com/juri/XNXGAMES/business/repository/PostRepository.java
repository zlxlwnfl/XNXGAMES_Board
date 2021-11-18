package com.juri.XNXGAMES.business.repository;

import com.juri.XNXGAMES.business.entity.PostEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PostRepository extends JpaRepository<PostEntity, Long> {

	List<PostEntity> findByBoardIdOrderByRegdateDesc(long boardId, Pageable boardPaging);
	
	@Query("SELECT COUNT(id) FROM PostEntity WHERE boardId = :boardId")
	int findCountByBoardId(@Param("boardId") long boardId);
	
	@Modifying
	@Transactional
	@Query("UPDATE PostEntity " +
			"SET title = :title, content = :content WHERE id = :id")
	void updateById(@Param("id") long postId, @Param("title") String title, @Param("content") String content);
	
}
