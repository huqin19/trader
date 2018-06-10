package io.renren.modules.generator.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.renren.modules.generator.dao.WeixinDao;
import io.renren.modules.generator.dao.ZqSheetsDao;
import io.renren.modules.generator.entity.WeixinEntity;
import io.renren.modules.generator.entity.ZqSheetsEntity;
import io.renren.modules.generator.service.WeixinService;


@Service("weixinService")
public class WeixinServiceImpl implements WeixinService {

	@Autowired
	private WeixinDao weixinDao;
	
	@Autowired
	private ZqSheetsDao zqSheetsDao;
	
    @Override
    public List<WeixinEntity> queryList(Map<String, Object> map) {
    	return weixinDao.queryList(map);
    }

	@Override
	public List<WeixinEntity> querySheetList(Map<String, Object> map) {
		return zqSheetsDao.querySheetList(map);
	}

	@Override
	public ZqSheetsEntity queryZqSheetsObject(BigDecimal objectId) {
		return zqSheetsDao.queryObject(objectId);
	}
}
