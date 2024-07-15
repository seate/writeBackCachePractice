package com.example.writeBackCachePractice.comment.cache.repository;

import com.example.writeBackCachePractice.comment.cache.entity.CommentCache;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@RequiredArgsConstructor
public class CommentCacheRepository {

    private final String prefix = "comment:";

    private final RedisTemplate<String, CommentCache> redisTemplate;


    private Set<String> getKeysByPattern(String pattern) {
        ScanOptions scanOptions = ScanOptions.scanOptions().match(pattern).count(1000).build();
        Cursor<byte[]> cursor = redisTemplate.executeWithStickyConnection(redisConnection -> redisConnection.scan(scanOptions));

        Set<String> keys = new HashSet<>();
        while (cursor.hasNext()) {
            keys.add(new String(cursor.next()));
        }
        return keys;
    }

    private String getKey(Long id) {
        return prefix + id;
    }


    public Long save(CommentCache commentCache) {
        redisTemplate.opsForValue().set(getKey(commentCache.getId()), commentCache);
        return commentCache.getId();
    }


    public Optional<CommentCache> findById(Long id) {
        return Optional.ofNullable(redisTemplate.opsForValue().get(getKey(id)));
    }

    public List<CommentCache> findAll() {
        Set<String> keys = getKeysByPattern(prefix + "*");
        return redisTemplate.opsForValue().multiGet(keys);
    }


    public void deleteById(Long id) {
        redisTemplate.delete(getKey(id));
    }

    public void deleteAll() {
        redisTemplate.delete(getKeysByPattern("*"));
    }
}
