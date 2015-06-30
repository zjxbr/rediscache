package com.jarvis.rediscache.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.google.protobuf.Parser;
import com.jarvis.rediscache.protobuf.ReportPB.ReportBean;

@Component("ForTestDao")
public class ForTestDao extends RedisMapBaseDao<ReportBean, ReportBean> {

	private static final Logger LOG = LoggerFactory.getLogger(ForTestDao.class);

	public static final String beanName;
	static {
		beanName = ForTestDao.class.getAnnotation(Component.class).value();
	}

	private static final String redisObjName = "forTest";

	@Override
	protected String getRedisObjName() {
		return redisObjName;
	}

	@Override
	protected Parser<ReportBean> getParser() {
		return ReportBean.PARSER;
	}

}
