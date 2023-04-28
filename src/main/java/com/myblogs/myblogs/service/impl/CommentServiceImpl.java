package com.myblogs.myblogs.service.impl;

import com.myblogs.myblogs.entity.Comment;
import com.myblogs.myblogs.entity.Post;
import com.myblogs.myblogs.exception.BlogAPIException;
import com.myblogs.myblogs.exception.ResourceNotFoundException;
import com.myblogs.myblogs.payload.CommentDto;
import com.myblogs.myblogs.repository.CommentRepository;
import com.myblogs.myblogs.repository.PostRepository;
import com.myblogs.myblogs.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

private PostRepository postRepository;
        private CommentRepository commentRepository;
        private ModelMapper modelMapper;

    public CommentServiceImpl(PostRepository postRepository, CommentRepository commentRepository,ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
        this.modelMapper=modelMapper;
    }

    @Override
    public List<CommentDto> getAll(long postId) {
     List<Comment> comments = commentRepository.findByPostId(postId);
  return comments.stream().map(x -> mapToDto(x)).collect(Collectors.toList());



    }

    @Override
    public CommentDto getAllByCommentIds(long postId, long commentId) {
         Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("post", "id", postId)
        );
       Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new ResourceNotFoundException("comment", "id", commentId)
        );
       if(comment.getPost().getId()!=post.getId()){
           throw new BlogAPIException(HttpStatus.BAD_REQUEST,"comment does not belong to post");
       }
       return mapToDto(comment);


    }

    @Override
    public CommentDto updateById(long postId, long commentId,CommentDto commentDto) {
         Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("post", "id", postId)
        );
    Comment comment = commentRepository.findById(commentId).orElseThrow(

                () -> new ResourceNotFoundException("comment", "id", commentId)
        );
        if(comment.getPost().getId()!=post.getId()){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST,"comment does not belongs to post");
        }
     comment.setId(commentId);
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(comment.getBody());
        comment.setPost(post);
         Comment comment1 = commentRepository.save(comment);
         return mapToDto(comment1);
    }

    @Override
    public void deleteById(long postId, long commentId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("post", "id", postId)
        );
        Comment comment = commentRepository.findById(commentId).orElseThrow(

                () -> new ResourceNotFoundException("comment", "id", commentId)
        );
        if(comment.getPost().getId()!=post.getId()){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST,"comment does not belongs to post");
        }
        commentRepository.deleteById(commentId);
    }

    @Override
    public CommentDto saveComments(long postId, CommentDto commentDto) {
         Post post = postRepository.findById(postId).orElseThrow(
            () -> new ResourceNotFoundException("Post", "id", postId));
       Comment comment = mapToEntity(commentDto);
        comment.setPost(post);
        Comment comment1 = commentRepository.save(comment);

        CommentDto commentDto1 = mapToDto(comment1);

        return commentDto1;
    }
       private CommentDto mapToDto(Comment comment1){
           CommentDto commentDto = modelMapper.map(comment1, CommentDto.class);
           // CommentDto commentDto=new CommentDto();
           // commentDto.setId(comment1.getId());
           // commentDto.setName(comment1.getName());
           //  commentDto.setEmail(comment1.getEmail());
           // commentDto.setBody(comment1.getBody());
           //  commentDto.setPostId(comment1.getPost().getId());
  return commentDto;

       }


    private Comment mapToEntity(CommentDto commentDto){
         Comment comment = modelMapper.map(commentDto, Comment.class);
        // Comment comment=new Comment();
        // comment.setName(commentDto.getName());
        // comment.setEmail(commentDto.getEmail());
        // comment.setBody(commentDto.getBody());

        return comment;
    }

    }

