package com.example.writeBackCachePractice.comment.common.controller;

import com.example.writeBackCachePractice.comment.common.dto.request.CreateCommentRequestDTO;
import com.example.writeBackCachePractice.comment.common.dto.request.UpdateCommentRequestDTO;
import com.example.writeBackCachePractice.comment.common.dto.response.CommentResponseDTO;
import com.example.writeBackCachePractice.comment.common.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public Long createComment(@RequestBody CreateCommentRequestDTO createCommentRequestDTO) {
        // create comment
        return commentService.saveComment(createCommentRequestDTO);
    }

    @GetMapping("/{commentId}")
    public CommentResponseDTO getComment(@PathVariable Long commentId) {
        return new CommentResponseDTO(commentService.getComment(commentId));
    }

    @GetMapping
    public List<CommentResponseDTO> getAllComment() {
        return commentService.getAllComment().stream().map(CommentResponseDTO::new).toList();
    }

    @PatchMapping("/{commentId}")
    public void updateComment(@PathVariable Long commentId, @RequestBody UpdateCommentRequestDTO updateCommentRequestDTO) {
        commentService.updateComment(commentId, updateCommentRequestDTO.getNewContent());
    }

    @DeleteMapping("/{commentId}")
    public void deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
    }

}
