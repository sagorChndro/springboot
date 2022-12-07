package com.sagor.blog.payloadordto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDto {
	private Long commentId;
	private String content;

}
