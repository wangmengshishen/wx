package com.wm.project.common.redis.cluster;

import java.util.ArrayList;
import java.util.List;

import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import com.wm.project.common.redis.JRedisPoolConfig;

public class RedisClient {

	private static ShardedJedisPool shardedJedisPool;// 切片连接池

	static {
		// 池基本配置
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(1000);
		config.setMaxIdle(10);
		config.setMaxWaitMillis(1000l);
		config.setTestOnBorrow(false);
		// slave链接
		List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
		String redisUrls = JRedisPoolConfig.redisUrls;
		for (String redisUrl : redisUrls.split(";")) {
			String[] params = redisUrl.split(":");
			shards.add(new JedisShardInfo(params[0], Integer.valueOf(params[1]), "master"));
		}
		// 构造池
		shardedJedisPool = new ShardedJedisPool(config, shards);
	}

	public static ShardedJedis getShardedJedis() {
		return shardedJedisPool.getResource();
	}

	public static void del(byte[] key) {
		ShardedJedis shardedJedis = getShardedJedis();
		shardedJedis.del(key);
		returnResource(shardedJedis);
	}

	@SuppressWarnings("deprecation")
	public static void returnResource(ShardedJedis shardedJedis) {
		shardedJedisPool.returnResource(shardedJedis);
	}

	public static void main(String[] args) {
		for (int i = 0; i < 1; i++) {
			ShardedJedis shardedJedis = getShardedJedis();
			shardedJedis.set("a" + i, "a" + i);
			returnResource(shardedJedis);
		}
	}

}