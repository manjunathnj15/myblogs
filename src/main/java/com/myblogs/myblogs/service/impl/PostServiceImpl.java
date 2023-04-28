package com.myblogs.myblogs.service.impl;

import com.myblogs.myblogs.entity.Post;
import com.myblogs.myblogs.exception.ResourceNotFoundException;
import com.myblogs.myblogs.payload.PostDto;
import com.myblogs.myblogs.payload.PostResponse;
import com.myblogs.myblogs.repository.PostRepository;
import com.myblogs.myblogs.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    public PostServiceImpl(PostRepository postRepository,ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.modelMapper= modelMapper;
    }

    private PostRepository postRepository;
    private ModelMapper modelMapper;

    @Override
    public PostDto createPost(PostDto postDto) {
        Post post = mapToEntity(postDto);
        Post newPost = postRepository.save(post);
        PostDto newPostDto = mapToDto(newPost);

        return newPostDto;
    }

    @Override
    public PostResponse AllPost(int pageNumber, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
       Pageable pageable= PageRequest.of(pageNumber, pageSize, sort);


        Page<Post> posts = postRepository.findAll(pageable);
        List<Post> content = posts.getContent();


        List<PostDto> postDtos = content.stream().map(s -> mapToDto(s)).collect(Collectors.toList());
        PostResponse postResponse=new PostResponse();
        postResponse.setContent(postDtos);
        postResponse.setPageNumber(posts.getNumber());
        postResponse.setPageSize(posts.getSize());
        postResponse.setTotalElements(posts.getTotalElements());
        postResponse.setTotalPages(posts.getTotalPages());
        postResponse.setLast(posts.isLast());
        return postResponse;
    }

    @Override
    public PostDto findById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("post", "id", id));
        return mapToDto(post);

    }

    @Override
    public PostDto updatePost(PostDto postDto, Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("post", "id", id));
         post.setTittle(postDto.getTittle());
         post.setDescription(postDto.getDescription());
         post.setContent(postDto.getContent());
        Post newpPost = postRepository.save(post);
      return  mapToDto(newpPost);


    }

    @Override
    public void deletePost(long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("post", "id", id));
        postRepository.delete(post);
    }

    public Post mapToEntity(PostDto postDto){
         Post post = modelMapper.map(postDto, Post.class);
        //  Post post=new Post();
      //  post.setTittle(postDto.getTittle());
        //  post.setDescription(postDto.getDescription());
        // post.setContent(postDto.getContent());
         return post;
    }
    public PostDto mapToDto(Post newPost){
       PostDto postDto = modelMapper.map(newPost, PostDto.class);
        //  PostDto postDto=new PostDto();
        //  postDto.setId(newPost.getId());
        // postDto.setTittle(newPost.getTittle());
        // postDto.setDescription(newPost.getDescription());
        //  postDto.setContent(newPost.getContent());

        return postDto;
    }
}
