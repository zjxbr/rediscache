package com.jarvis.rediscache.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import com.google.protobuf.InvalidProtocolBufferException;

public abstract class RedisMapBaseDao<K extends com.google.protobuf.GeneratedMessage, V extends com.google.protobuf.GeneratedMessage> {

	private static final Logger LOG = LoggerFactory
			.getLogger(RedisMapBaseDao.class);

	@Autowired
	private JedisPool jedisPool;

	final public V get(K k) throws InvalidProtocolBufferException {
		Jedis jedis = jedisPool.getResource();
		byte[] bs;
		try {
			bs = jedis.hget(getRedisObjName().getBytes(), k.toByteArray());
		} finally {
			if(LOG.isDebugEnabled()){
				LOG.debug("jedispool closed");
			}
			jedisPool.returnResource(jedis);
		}
		return bs == null || bs.length == 0 ? null : getParser().parseFrom(bs);
	}
	
	final public void put(K k, V v) {
		Jedis jedis = jedisPool.getResource();
		try {
			jedis.hset(getRedisObjName().getBytes(), k.toByteArray(),
					v.toByteArray());
		} finally {
			jedisPool.returnResource(jedis);
		}
	}

	protected abstract String getRedisObjName();
	
	abstract protected com.google.protobuf.Parser<V> getParser();

}
