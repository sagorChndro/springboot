package com.sagor.blog.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sagor.blog.model.Category;
import com.sagor.blog.model.Post;
import com.sagor.blog.model.User;
import com.sagor.blog.payloadordto.PostDto;
import com.sagor.blog.payloadordto.Response;
import com.sagor.blog.repository.CategoryRepository;
import com.sagor.blog.repository.PostRepository;
import com.sagor.blog.repository.UserRepository;
import com.sagor.blog.services.PostService;
import com.sagor.blog.utils.ResponseBuilder;
import com.sagor.blog.utils.UrlConstraint;

@Service
public class PostServiceImpl implements PostService {
	private final PostRepository postRepository;
	private final ModelMapper modelMapper;
	private final UserRepository userRepository;
	private final CategoryRepository categoryRepository;
	private String root = "Post";

	public PostServiceImpl(PostRepository postRepository, ModelMapper modelMapper, UserRepository userRepository,
			CategoryRepository categoryRepository) {
		this.postRepository = postRepository;
		this.modelMapper = modelMapper;
		this.userRepository = userRepository;
		this.categoryRepository = categoryRepository;
	}

	@Override
	public Response createPost(PostDto postDto, Long categoryId, Long userId) {
		Post post = modelMapper.map(postDto, Post.class);
		Category category = categoryRepository.findBycategoryIdAndIsActiveTrue(categoryId);
		post.setCategory(category);
		User user = userRepository.findByuserIdAndIsActiveTrue(userId);
		post.setUser(user);
		post = postRepository.save(post);
		if (post != null) {
			if (category != null) {
				post.setCategory(category);
			} else {
				return ResponseBuilder.getFailureResponse(HttpStatus.NOT_FOUND, "Category id not found");
			}
			if (user != null) {
				post.setUser(user);
			} else {
				return ResponseBuilder.getFailureResponse(HttpStatus.NOT_FOUND, "User id not found");
			}
			return ResponseBuilder.getSuccessResponse(HttpStatus.OK, root + " created successfully", null);
		}
		return ResponseBuilder.getFailureResponse(HttpStatus.NOT_FOUND, root + " not found");
	}

	@Override
	public Response updatePost(PostDto postDto, Long postId) {
		Post post = postRepository.findBypostIdAndIsActiveTrue(postId);
		if (post != null) {
			modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
			modelMapper.map(postDto, post);
			post = postRepository.save(post);
			if (post != null) {
				return ResponseBuilder.getSuccessResponse(HttpStatus.OK, root + " updated successfully", null);
			}
			return ResponseBuilder.getFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error occur");
		}
		return ResponseBuilder.getFailureResponse(HttpStatus.NOT_FOUND, root + " is not found");
	}

	@Override
	public Response getPost(Long postId) {
		Post post = postRepository.findBypostIdAndIsActiveTrue(postId);
		if (post != null) {
			PostDto postDto = modelMapper.map(post, PostDto.class);
			if (postDto != null) {
				return ResponseBuilder.getSuccessResponse(HttpStatus.OK, root + " retireve successfully", postDto);
			}
			return ResponseBuilder.getFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error occure");
		}

		return ResponseBuilder.getFailureResponse(HttpStatus.NOT_FOUND, root + " not found");
	}

	@Override
	public Response deletePost(Long postId) {
		Post post = postRepository.findBypostIdAndIsActiveTrue(postId);
		if (post != null) {
			post.setIsActive(false);
			post = postRepository.save(post);
			if (post != null) {
				return ResponseBuilder.getSuccessResponse(HttpStatus.OK, root + " deleted successfully", null);
			}
			return ResponseBuilder.getFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error occur");
		}
		return ResponseBuilder.getFailureResponse(HttpStatus.NOT_FOUND, root + " not found for delete");
	}

	@Override
	public Response getAllPostWithPageNoAndPageSize(Integer pageNumber, Integer pageSize, String sortBy,
			String sortDir) {
		Sort sort = sortDir.equalsIgnoreCase(UrlConstraint.PostManagement.SORT_DIR) ? Sort.by(sortBy).ascending()
				: Sort.by(sortBy).descending();
		Pageable p = PageRequest.of(pageNumber, pageSize, sort);
		Page<Post> pagePost = postRepository.findAll(p);
		List<Post> allPosts = pagePost.getContent();
		// List<Post> posts = postRepository.findAllByIsActiveTrue();
		List<PostDto> postDtos = this.getPosts(allPosts);
		return ResponseBuilder.getSuccessResponse(HttpStatus.OK, root + " retrieve successfully by page", postDtos,
				pageNumber, pageSize);
	}

	@Override
	public Response getALlPost() {
		List<Post> posts = postRepository.findAllByIsActiveTrue();
		List<PostDto> postDtos = this.getPosts(posts);
		return ResponseBuilder.getSuccessResponse(HttpStatus.OK, "All " + root + " retrieve successfully", postDtos);
	}

	@Override
	public Response getPostsByCategory(Long categoryId) {
		Category category = categoryRepository.findBycategoryIdAndIsActiveTrue(categoryId);
		if (category != null) {
			List<Post> posts = category.getPosts();
		} else {
			return ResponseBuilder.getFailureResponse(HttpStatus.NOT_FOUND, "Category id not found");
		}
		List<Post> posts = category.getPosts();
		List<PostDto> postDtos = this.getPosts(posts);
		return ResponseBuilder.getSuccessResponse(HttpStatus.OK, "All post retireve from Category", postDtos);
	}

	@Override
	public Response getPostsByUser(Long userId) {
		User user = userRepository.findByuserIdAndIsActiveTrue(userId);
		if (user != null) {
			List<Post> post = user.getPosts();
		} else {
			return ResponseBuilder.getFailureResponse(HttpStatus.NOT_FOUND, "User id not found");
		}
		List<Post> post = user.getPosts();
		List<PostDto> PostDtos = this.getPosts(post);
		return ResponseBuilder.getSuccessResponse(HttpStatus.OK, "All post retireve successfully from User", PostDtos);

	}

	@Override
	public Response searchPosts(String keywords) {
		List<Post> posts = postRepository.searchByPostTitle(keywords);
		List<PostDto> postDots = this.getPosts(posts);
		return ResponseBuilder.getSuccessResponse(HttpStatus.FOUND, root + " found that you have searched", postDots);
	}

	private List<PostDto> getPosts(List<Post> posts) {
		List<PostDto> dtoList = new ArrayList<>();
		posts.forEach(post -> {
			modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
			PostDto dto = modelMapper.map(post, PostDto.class);
			dtoList.add(dto);
		});
		return dtoList;
	}

}
