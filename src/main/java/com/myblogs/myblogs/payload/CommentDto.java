package com.myblogs.myblogs.payload;

import com.myblogs.myblogs.entity.Post;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class CommentDto {
    private long id;
  @NotEmpty(message = "name is mandatory")
  @Size(min=3,message = "name should be at least 3 characters")
    private String name;
    @NotEmpty(message = "email is mandatory")
  @Email(message = "invalid email id")
    private String email;
    @NotEmpty(message = "message is mandatory")
   @Size(min=3,message = "message  should be at least 3 characters")
    private String body;

  private long postId;

}
