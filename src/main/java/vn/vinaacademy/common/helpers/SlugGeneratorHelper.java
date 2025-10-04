package vn.vinaacademy.common.helpers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import vn.vinaacademy.common.exception.RetryableException;
import vn.vinaacademy.common.utils.RandomUtils;

import java.util.UUID;
import java.util.function.Function;

@Slf4j
@Service
public class SlugGeneratorHelper {
    @Retryable(
            retryFor = RetryableException.class,
            maxAttempts = 5,
            backoff = @Backoff(delay = 100, multiplier = 2)
    )
    public String generateSlug(String baseSlug, Function<String, Boolean> isSlugUnique) {
        String slug = baseSlug + "-" + RandomUtils.generateRandomString(10).toLowerCase();
        if (!isSlugUnique.apply(slug)) {
            log.warn("Slug {} đã tồn tại, retry...", slug);
            throw RetryableException.message("Slug đã tồn tại");
        }
        return slug;
    }

    @Recover
    public String recoverSLug(
            RetryableException e,
            String baseSlug,
            Function<String, Boolean> isSlugUnique
    ) {
        String fallback = baseSlug + "-" + UUID.randomUUID();
        log.warn("Retry hết số lần, dùng fallback slug: {}", fallback);
        return fallback;
    }
}
