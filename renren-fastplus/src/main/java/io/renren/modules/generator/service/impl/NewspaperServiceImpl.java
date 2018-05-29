package io.renren.modules.generator.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.renren.modules.generator.dao.NewspaperDao;
import io.renren.modules.generator.entity.NewspaperEntity;
import io.renren.modules.generator.service.NewspaperService;


@Service("newspaperService")
public class NewspaperServiceImpl implements NewspaperService {

	@Autowired
	private NewspaperDao newspaperDao;
	
	@Override
	public List<NewspaperEntity> queryZQJDList(Map<String, Object> map) {
		return newspaperDao.queryZQJDList(map);
	}

	@Override
	public List<NewspaperEntity> queryVCBONDFUTURESPOSITIONSDList(Map<String, Object> map) {
		return newspaperDao.queryVCBONDFUTURESPOSITIONSDList(map);
	}

	@Override
	public List<NewspaperEntity> queryVCBONDFUTURESEODPRICESList(Map<String, Object> map) {
		return newspaperDao.queryVCBONDFUTURESEODPRICESList(map);
	}

}
