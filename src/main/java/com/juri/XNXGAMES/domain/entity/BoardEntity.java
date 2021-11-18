package com.juri.XNXGAMES.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
	private Long id;

	@Column(length = 10, nullable = false)
	private String type;
	
	@Column(length = 20, nullable = false)
	private String subType;
	
}
