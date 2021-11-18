package com.juri.XNXGAMES.domain.entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "comment")
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CommentEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private Long postId;
	
	@Column(length = 10, nullable = false)
	private String writerId;
	
	@CreationTimestamp
	private Date regdate;
	
	@Column(nullable = false)
	private String content;
	
	@ColumnDefault("0")
	private int heartCount;

	@Builder
	public CommentEntity(Long postId, String writerId, String content) {
		this.postId = postId;
		this.writerId = writerId;
		this.content = content;
	}
}
