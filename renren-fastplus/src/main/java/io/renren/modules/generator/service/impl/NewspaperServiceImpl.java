package io.renren.modules.generator.service.impl;

import java.util.List;

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
	public List<NewspaperEntity> queryZQJDList(NewspaperEntity newspaperEntity) {
		return newspaperDao.queryZQJDList(newspaperEntity);
	}

	@Override
	public List<NewspaperEntity> queryVCBONDFUTURESPOSITIONSDList(NewspaperEntity newspaperEntity) {
		return newspaperDao.queryVCBONDFUTURESPOSITIONSDList(newspaperEntity);
	}

	@Override
	public List<NewspaperEntity> queryVCBONDFUTURESEODPRICESList(NewspaperEntity newspaperEntity) {
		return newspaperDao.queryVCBONDFUTURESEODPRICESList(newspaperEntity);
	}

}
