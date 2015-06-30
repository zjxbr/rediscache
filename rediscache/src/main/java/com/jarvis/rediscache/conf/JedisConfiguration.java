package com.jarvis.rediscache.conf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import redis.clients.jedis.JedisPool;

/**
 * @author zjx
 * @funciton 注解用来生成jedispool
 */
@Configuration
public class JedisConfiguration {

	private static final Logger LOG = LoggerFactory
			.getLogger(JedisConfiguration.class);
	
	@Autowired
	private JedisPoolConfig jedisPoolConfig;

	/**
	 * hadoop 配置文件
	 */
//	@Autowired
//	private org.apache.hadoop.conf.Configuration hadoopConf;

	/**
	 * singleletion
	 * 
	 * @return
	 */
	@Bean
	@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
	public JedisPool jedisPool() {
		return new JedisPool(jedisPoolConfig, jedisPoolConfig.getHostName(),
				jedisPoolConfig.getHostPort(), jedisPoolConfig.getTimeOut());
	}

}
