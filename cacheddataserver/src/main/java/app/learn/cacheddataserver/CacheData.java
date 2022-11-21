package app.learn.cacheddataserver;

import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@RedisHash(value = "cacheData", timeToLive = 4)
public class CacheData {
	@Id
	private String key;

	@Indexed
	private String value;

	public CacheData(String key, String value) {
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}

	@Override
	public int hashCode() {
		return Objects.hash(key, value);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if ((obj == null) || (getClass() != obj.getClass()))
			return false;
		CacheData other = (CacheData) obj;
		return Objects.equals(key, other.key) && Objects.equals(value, other.value);
	}

	@Override
	public String toString() {
		return "CacheData [key=" + key + ", value=" + value + "]";
	}

}