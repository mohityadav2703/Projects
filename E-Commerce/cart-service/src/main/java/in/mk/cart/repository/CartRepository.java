package in.mk.cart.repository;

import java.util.Map;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CartRepository {

    private final RedisTemplate<String, Object> redisTemplate;

    private String key(String email) {
        return "CART:" + email;
    }

    public void addItem(String email, Long productId, int qty) {
        redisTemplate.opsForHash().put(key(email), productId.toString(), qty);
    }

    public Map<Object, Object> getCart(String email) {
        return redisTemplate.opsForHash().entries(key(email));
    }

    public void removeItem(String email, Long productId) {
        redisTemplate.opsForHash().delete(key(email), productId.toString());
    }

    public void clear(String email) {
        redisTemplate.delete(key(email));
    }
}
