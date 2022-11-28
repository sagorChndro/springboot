package com.sagor.blog.payloadordto;

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
	private String imageName;
	private String imageLocation;
	private UserDto user;
	private CategoryDto category;

}
