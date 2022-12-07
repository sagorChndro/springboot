package com.sagor.blog.payloadordto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DocumentDto {
	private Long documentId;
	private String documentName;
	private String documentLocation;
	private PostDto post;

}
