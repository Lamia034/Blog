package com.example.blog.entities;

import com.example.blog.entities.abstracts.React;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection="comment_react")
public class CommentReact extends React {
   private Comment comment;

}
