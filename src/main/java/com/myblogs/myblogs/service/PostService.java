package com.myblogs.myblogs.service;

import com.myblogs.myblogs.payload.PostDto;
import com.myblogs.myblogs.payload.PostResponse;

import java.util.List;

public interface PostService {
  PostDto createPost(PostDto postDto);

    public PostResponse AllPost(int pageNumber, int pageSize, String sortBy, String sortDir);

  public PostDto findById(Long id);

    public PostDto updatePost(PostDto postDto, Long id);

  public  void deletePost(long id);
}
