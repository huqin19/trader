package io.renren.modules.generator.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.renren.modules.generator.dao.WeixinDao;
import io.renren.modules.generator.entity.WeixinEntity;
import io.renren.modules.generator.service.WeixinService;


@Service("weixinService")
public class WeixinServiceImpl implements WeixinService {

	@Autowired
	private WeixinDao weixinDao;
	
    @Override
    public List<WeixinEntity> queryList(Map<String, Object> map) {
    	return weixinDao.queryList(map);
    }

}
