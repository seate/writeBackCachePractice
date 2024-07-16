package com.example.writeBackCachePractice.comment.cache.service;

import com.example.writeBackCachePractice.comment.cache.repository.DeletedCommentCacheRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class DeletedCommentCacheService {

    private final DeletedCommentCacheRepository deletedCommentCacheRepository;

    public void saveDeletedComment(String id) {
        deletedCommentCacheRepository.saveDeletedComment(id);
    }

    public Boolean hasDeletedComment(String id) {
        return deletedCommentCacheRepository.hasDeletedComment(id);
    }

    public Set<String> getAll() {
        return deletedCommentCacheRepository.getAll();
    }

    public void deleteAll() {
        deletedCommentCacheRepository.deleteAll();
    }
}
