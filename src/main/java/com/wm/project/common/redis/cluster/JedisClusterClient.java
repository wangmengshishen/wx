package com.wm.project.common.redis.cluster;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.exceptions.JedisClusterException;

import com.wm.project.common.redis.JRedisPoolConfig;

public class JedisClusterClient {

	private static JedisCluster jedisCluster;

	/** REDIS可用的 */
	public static boolean REDIS_AVAILABLE = true;

	static {
		// slave链接
		Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
		String redisUrls = JRedisPoolConfig.redisUrls;
		for (String redisUrl : redisUrls.split(";")) {
			String[] params = redisUrl.split(":");
			jedisClusterNodes.add(new HostAndPort(params[0], Integer.valueOf(params[1])));
		}
		// 构造池
		GenericObjectPoolConfig config = new GenericObjectPoolConfig();
		config.setMaxTotal(JRedisPoolConfig.maxTotal);
		config.setMaxIdle(JRedisPoolConfig.maxIdle);
		config.setMaxWaitMillis(JRedisPoolConfig.maxWaitMillis);
		config.setTestOnBorrow(JRedisPoolConfig.testOnBorrow);
		jedisCluster = new JedisCluster(jedisClusterNodes, config);
	}

	public static String get(String key) {
		String value = null;
		try {
			value = jedisCluster.get(key);
			JedisClusterClient.REDIS_AVAILABLE = false;
		} catch (JedisClusterException e) {
			JedisClusterClient.REDIS_AVAILABLE = false;
		}
		return value;
	}

	public static byte[] get(byte[] key) {
		byte[] value = null;
		try {
			value = jedisCluster.get(key);
			JedisClusterClient.REDIS_AVAILABLE = false;
		} catch (JedisClusterException e) {
			JedisClusterClient.REDIS_AVAILABLE = false;
		}
		return value;
	}

	public static String setex(byte[] key, int seconds, byte[] value) {
		String result = null;
		try {
			result = jedisCluster.setex(key, seconds, value);
			JedisClusterClient.REDIS_AVAILABLE = false;
		} catch (JedisClusterException e) {
			e.printStackTrace();
			JedisClusterClient.REDIS_AVAILABLE = false;
		}
		return result;
	}

	public static Long del(byte[] key) {
		Long result = null;

		try {
			result = jedisCluster.del(key);
			JedisClusterClient.REDIS_AVAILABLE = false;
		} catch (JedisClusterException e) {
			JedisClusterClient.REDIS_AVAILABLE = false;
		}
		return result;
	}

	public static JedisCluster getJedisCluster() {
		return jedisCluster;
	}
}