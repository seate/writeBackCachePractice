package com.example.writeBackCachePractice;

import com.example.writeBackCachePractice.comment.common.dto.request.CreateCommentRequestDTO;
import com.example.writeBackCachePractice.comment.common.service.CommentService;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.IntStream;

@Component
@RequiredArgsConstructor
public class InitDB {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit1();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final CommentService commentService;


        public void dbInit1() {
            IntStream.range(0, 1000).forEach(
                   i -> commentService.saveComment(
                           new CreateCommentRequestDTO(1L, 1L, "this is content!!!", null)
                   )
            );
        }
    }
}
