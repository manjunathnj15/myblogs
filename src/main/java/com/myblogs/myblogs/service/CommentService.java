package com.myblogs.myblogs.service;

import com.myblogs.myblogs.payload.CommentDto;

import java.util.List;

public interface CommentService {



   public CommentDto saveComments(long postId, CommentDto commentDto);

    public List<CommentDto> getAll(long postId);

    public CommentDto getAllByCommentIds(long postId, long commentId);

   public CommentDto updateById(long postId, long commentId,CommentDto commentDto);

    public void deleteById(long postId, long commentId);
}
