package com.wm.project.common.redis;

import java.io.IOException;
import java.util.Properties;

/**
 * 
 * redis缓存配置
 * 
 * @author Sunrise
 *
 */
public class JRedisPoolConfig {

	/**
	 * 控制一个pool可分配多少个jedis实例，通过pool.getResource()来获取
	 * 如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，
	 * 则此时pool的状态就成exhausted了，在JedisPoolConfig
	 */
	public static int maxTotal;

	/**
	 * 控制一个pool最多有多少个状态为idle的jedis实例
	 */
	public static int maxIdle;

	/**
	 * 表示当borrow一个jedis实例时，最大的等待时间，如果超过等待时间，则直接抛出JedisConnectionException
	 */
	public static long maxWaitMillis;

	/**
	 * redis缓存服务器地址,格式为 ip:port:password;ip:port:password;ip:port:password
	 */
	public static String redisUrls;
	
	/**
	 * 在return给pool时，是否提前进行validate操作
	 */
	public static Boolean testOnBorrow;

	/**
	 * 属性配置
	 */
	private static Properties redisConfig;

	/** 获取属性配置 */
	static {
		try {
			/*redisConfig = PropertiesUtils.loadProperties(new String[] { "classpath:/properties/redis.properties" });
			maxTotal = Integer.parseInt(redisConfig.getProperty("redis.pool.maxTotal"));
			maxIdle = Integer.parseInt(redisConfig.getProperty("redis.pool.maxIdle"));
			maxWaitMillis = Long.parseLong(redisConfig.getProperty("redis.pool.maxWaitMillis"));
			testOnBorrow = Boolean.parseBoolean(redisConfig.getProperty("redis.pool.testOnBorrow"));
			redisUrls = redisConfig.getProperty("redis.pool.redisUrls");*/
			maxTotal = Integer.parseInt("1024");
			maxIdle = Integer.parseInt("200");
			maxWaitMillis = Long.parseLong("1000");
			testOnBorrow = Boolean.parseBoolean("true");
			redisUrls = "192.168.6.8:7001;192.168.6.8:7002;192.168.6.8:7003;192.168.6.8:7004;192.168.6.8:7005;192.168.6.8:7006;";
		} catch (Exception e) {
		}
	}

	/** 状态码 */
	public enum RequestStatus {
		Success, Fail, A, I;
	}
}
