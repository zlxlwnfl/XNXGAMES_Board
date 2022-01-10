package com.juri.XNXGAMES.business.entity;

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
public class Comment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long commentId;
	
	@Column(nullable = false)
	private long postId;
	
	@Column(length = 10, nullable = false)
	private String writerId;
	
	@CreationTimestamp
	private Date regdate;
	
	@Column(nullable = false)
	private String content;
	
	@ColumnDefault("0")
	private int heartCount;

	@Builder
	public Comment(long postId, String writerId, String content) {
		this.postId = postId;
		this.writerId = writerId;
		this.content = content;
	}
}
