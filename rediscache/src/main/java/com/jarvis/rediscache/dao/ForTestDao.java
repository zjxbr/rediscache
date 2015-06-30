package com.jarvis.rediscache.dao;

import java.io.IOException;

import com.jarvis.rediscache.conf.RedisResource;
import com.jarvis.rediscache.protobuf.ReportPB.ReportBean;

public class ForTestDao extends RedisResource<ReportBean>{

	@Override
	protected byte[] getRedisObjName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void init() throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected long getLastVisitTime(ReportBean t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected long getWaitTime() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected ReportBean setAndGetVisitTime(ReportBean t) {
		// TODO Auto-generated method stub
		return null;
	}

}
