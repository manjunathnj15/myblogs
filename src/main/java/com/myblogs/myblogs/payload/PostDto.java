package com.myblogs.myblogs.payload;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class PostDto {

    private long id;
  @NotEmpty(message = "it is mandatory")
  @Size(min = 2,message = "Post title atlest 2 charcters")
    private String tittle;
    @NotEmpty(message = "it is mandatory")
    @Size(min = 4,message = "Post description atlest 4 charcters")
    private String description;
    @NotEmpty(message = "it is mandatory")
    @Size(min = 4,message = "Post description atlest 4 charcters")
    private String content;

}
