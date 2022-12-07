package com.sagor.blog.payloadordto;

import java.util.Set;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {
	private Long postId;
	private String postTitle;
	private String postContent;
	private UserDto user;
	private CategoryDto category;
	private Set<CommentDto> commentDto;

}
