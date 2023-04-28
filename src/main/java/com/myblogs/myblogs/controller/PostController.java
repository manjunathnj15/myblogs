package com.myblogs.myblogs.controller;

import com.myblogs.myblogs.payload.CommentDto;
import com.myblogs.myblogs.payload.PostDto;
import com.myblogs.myblogs.payload.PostResponse;
import com.myblogs.myblogs.service.CommentService;
import com.myblogs.myblogs.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/api/post")
@RestController
public class PostController {


    public PostController(PostService postService) {
        this.postService = postService;
    }

    private PostService postService;

@PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Object> savePost(@Valid @RequestBody PostDto postDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResponseEntity<>(bindingResult.getFieldError().getDefaultMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        PostDto dto = postService.createPost(postDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
    @GetMapping
    public PostResponse getAllPost(@RequestParam(value = "pageNumber",defaultValue="0",required = false)int pageNumber, @RequestParam(value ="pageSize",defaultValue ="10",required = false)int pageSize, @RequestParam(value ="sortBy",defaultValue ="id",required = false)String sortBy, @RequestParam(value="sortDir",defaultValue ="asc",required = false)String sortDir){
          return postService.AllPost(pageNumber,pageSize,sortBy,sortDir);

    }
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostByID(@PathVariable("id") Long id){
     PostDto postDto =   postService.findById(id);
    return ResponseEntity.ok(postDto);
    }
    @PreAuthorize("hasRole('ADMIN')")
@PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePostById(@RequestBody PostDto postDto,@PathVariable("id") Long id){
        PostDto dto = postService.updatePost(postDto, id);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping({"/{id}"})
    public ResponseEntity<String> deletePostById(@PathVariable("id") long id){
        postService.deletePost(id);

   return new ResponseEntity<>("Record deleted succesfull!!",HttpStatus.CREATED);
    }


}
