package com.universityapp.common.redis;

import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RedisService {
    private final ObjectMapper objectMapper;
    private final StringRedisTemplate redisTemplate;

    public void setObject(String key, Object value, long ttl, TimeUnit unit) throws Exception {
        String obj = objectMapper.writeValueAsString(value);
        redisTemplate.opsForValue().set(key, obj, ttl, unit);
    }

    public <T> T getValue(String key, Class<T> clazz) throws Exception {
        String res = redisTemplate.opsForValue().get(key);
        return res != null ? objectMapper.readValue(res, clazz) : null;
    }
}
