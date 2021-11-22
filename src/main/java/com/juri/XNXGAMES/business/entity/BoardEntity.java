package com.juri.XNXGAMES.business.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "board")
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BoardEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long boardId;

	@Column(length = 10, nullable = false)
	private String type;
	
	@Column(length = 20, nullable = false)
	private String subType;

	@Builder
	public BoardEntity(String type, String subType) {
		this.type = type;
		this.subType = subType;
	}
}
