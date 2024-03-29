package com.juri.XNXGAMES.business.entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "post")
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long postId;

	@Column(length = 10, nullable = false)
	private String type;
	
	@Column(nullable = false)
	private long boardId;
	
	@Column(length = 10, nullable = false)
	private String writerId;
	
	@ColumnDefault("0")
	private int commentCount;
	
	@CreationTimestamp
	private Date regdate;
	
	@Column(length = 45, nullable = false)
	private String title;
	
	@Column(nullable = false)
	private String content;
	
	@ColumnDefault("0")
	private int hits;
	
	@ColumnDefault("0")
	private int heartCount;

	@ElementCollection(fetch = FetchType.EAGER)
	private List<String> gameTagList;

	@Builder
	public Post(String type, long boardId, String writerId, String title, String content, List<String> gameTagList) {
		this.type = type;
		this.boardId = boardId;
		this.writerId = writerId;
		this.title = title;
		this.content = content;
		this.gameTagList = gameTagList;
	}
}
