package com.test.crud.dto;

import com.test.crud.entity.BoardEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BoardDto {
	private long id;
	private String title;
	private String writer;
	private String content;
	
	public BoardEntity toEntity() {
		BoardEntity build = BoardEntity.builder()
				.id(id)
				.title(title)
				.writer(writer)
				.content(content)
				.build();
		return build;
	}
	
	@Builder
	public BoardDto(long id, String title, String writer, String content) {
		this.id=id;
		this.title=title;
		this.writer=writer;
		this.content=content;
	}

}
