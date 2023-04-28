package com.myblogs.myblogs.controller;

import com.myblogs.myblogs.payload.CommentDto;
import com.myblogs.myblogs.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentController {
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    private CommentService commentService;
    @PostMapping("/post/{postId}")
    public ResponseEntity<Object> saveComment(@PathVariable("postId")long postId, @Valid @RequestBody CommentDto commentDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResponseEntity<>(bindingResult.getFieldError().getDefaultMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
       CommentDto commentDto1 = commentService.saveComments(postId, commentDto);
       return new ResponseEntity<>(commentDto1, HttpStatus.CREATED);
    }
    @GetMapping("/comment/{postId}")
    public ResponseEntity<List<CommentDto>> getAllByPostId(@PathVariable("postId")long postId){
         List<CommentDto> dto = commentService.getAll(postId);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }
   @GetMapping("/post/{postId}/{commentId}")
    public ResponseEntity<CommentDto> getAllByCommentId(@PathVariable("postId")long postId,@PathVariable("commentId")long commentId){
         CommentDto dto = commentService.getAllByCommentIds(postId, commentId);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }
   @PutMapping("/post/{postId}/{commentId}")
    public ResponseEntity<CommentDto> updateById(@PathVariable("postId")long postId,@PathVariable("commentId")long commentId,@RequestBody CommentDto commentDto){
        CommentDto dto = commentService.updateById(postId, commentId, commentDto);
        return new ResponseEntity<>(dto,HttpStatus.CREATED);
    }
    @DeleteMapping("/post/{postId}/{commentId}")
    public ResponseEntity<String> deleteById(@PathVariable("postId")long postId,@PathVariable("commentId")long commentId){
        commentService.deleteById(postId,commentId);
        return new ResponseEntity<>("Record is deleted",HttpStatus.OK);
    }





}
