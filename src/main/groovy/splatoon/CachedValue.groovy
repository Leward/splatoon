package splatoon

import java.time.Duration
import java.time.Instant

class CachedValue<T> {

    private T value
    private Duration timeToLive
    private Instant expiresAt

    CachedValue(Duration timeToLive) {
        this.timeToLive = timeToLive
    }

    void cache(T value) {
        this.value = value
        expiresAt = Instant.now().plus(timeToLive)
    }

    boolean isCacheValid() {
        return value != null && Instant.now().isBefore(expiresAt)
    }

    T getValue() {
        return value
    }

    void invalid() {
        value = null
    }
}
