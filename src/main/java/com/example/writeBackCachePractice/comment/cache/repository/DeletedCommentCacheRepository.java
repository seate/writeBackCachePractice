package com.example.writeBackCachePractice.comment.cache.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
@RequiredArgsConstructor
public class DeletedCommentCacheRepository {

    private final String key = "DeletedComment:";

    private final RedisTemplate<String, String> redisTemplate;


    public void saveDeletedComment(String id) {
        redisTemplate.opsForSet().add(key, id);
    }

    public Boolean hasDeletedComment(String id) {
        return redisTemplate.opsForSet().isMember(key, id);
    }

    public Set<String> getAll() {
        return redisTemplate.opsForSet().members(key);
    }

    public void deleteAll() {
        redisTemplate.delete(key);
    }
}
