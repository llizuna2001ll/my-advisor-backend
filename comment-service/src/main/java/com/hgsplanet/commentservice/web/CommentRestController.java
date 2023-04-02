package com.hgsplanet.commentservice.web;

import com.hgsplanet.commentservice.documents.Comment;
import com.hgsplanet.commentservice.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/comments")
public class CommentRestController {

    private CommentService commentService;

    public CommentRestController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    ResponseEntity<List<Comment>> getAllComments(){
        List<Comment> comments = commentService.findAllComments();
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<Comment> getCommentById(@PathVariable String id){
        Comment comment = commentService.findCommentById(id);
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }


    @PostMapping("/addComment")
    ResponseEntity<Comment> addComment(@RequestBody Comment comment){
        Comment newComment = commentService.addComment(comment);
        return new ResponseEntity<>(newComment, HttpStatus.CREATED);
    }

    @PutMapping("/updateComment/{id}")
    ResponseEntity<Comment> updateComment(@PathVariable String id, @RequestBody Comment comment){
        Comment updateComment = commentService.updateComment(comment);
        return new ResponseEntity<>(updateComment, HttpStatus.OK);
    }

    @DeleteMapping("/deleteComment/{id}")
    ResponseEntity<Comment> deleteComment(@PathVariable("id") String id){
        commentService.deleteCommentById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/getFullComment/{id}")
    ResponseEntity<Comment> getFullComment(@PathVariable("id") String id){
        Comment comment = commentService.getFullComment(id);
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    @GetMapping("/post")
    ResponseEntity<List<Comment>> getAllByPostId(@RequestParam String postId){
        List<Comment> comments = commentService.getAllByPostId(postId);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }
}
