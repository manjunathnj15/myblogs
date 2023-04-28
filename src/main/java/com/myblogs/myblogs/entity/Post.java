package com.myblogs.myblogs.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "posts",uniqueConstraints = {@UniqueConstraint(columnNames = {"tittle"})})
@Entity
public class Post {
@Id
@GeneratedValue(strategy =GenerationType.IDENTITY)
private long id;
@Column(name = "tittle",nullable = false)
private String tittle;
@Column(name = "description",nullable = false)
private String description;
@Column(name = "content",nullable = false)
private String content;
@OneToMany(mappedBy = "post",cascade=CascadeType.ALL,orphanRemoval = true)
   private Set<Comment> coments;








}
