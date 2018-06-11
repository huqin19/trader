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
		String x = newspaperEntity.getParamdate();
		if(null != x && x.length() > 0) {
			newspaperEntity.setParamdate(x.replaceAll("-", ""));
		}
		return newspaperDao.queryVCBONDFUTURESPOSITIONSDList(newspaperEntity);
	}

	@Override
	public List<NewspaperEntity> queryVCBONDFUTURESEODPRICESList(NewspaperEntity newspaperEntity) {
		String x = newspaperEntity.getParamdate();
		if(null != x && x.length() > 0) {
			newspaperEntity.setParamdate(x.replaceAll("-", ""));
		}
		return newspaperDao.queryVCBONDFUTURESEODPRICESList(newspaperEntity);
	}

	@Override
	public List<String> queryHYNameOfVCBONDFUTURESPOSITIONSD(NewspaperEntity newspaperEntity) {
		String x = newspaperEntity.getParamdate();
		if(null != x && x.length() > 0) {
			newspaperEntity.setParamdate(x.replaceAll("-", ""));
		}
		return newspaperDao.queryHYNameOfVCBONDFUTURESPOSITIONSD(newspaperEntity);
	}

	@Override
	public List<String> queryHYNameOfVCBONDFUTURESEODPRICES(NewspaperEntity newspaperEntity) {
		String x = newspaperEntity.getParamdate();
		if(null != x && x.length() > 0) {
			newspaperEntity.setParamdate(x.replaceAll("-", ""));
		}
		return newspaperDao.queryHYNameOfVCBONDFUTURESEODPRICES(newspaperEntity);
	}

	@Override
	public List<NewspaperEntity> queryVCBONDFUTURESEODPRICESListByYYYYMM(NewspaperEntity newspaperEntity) {
		String x = newspaperEntity.getParamdate();
		if(null != x && x.length() > 0) {
			newspaperEntity.setParamdate(x.replaceAll("-", ""));
		}
		return newspaperDao.queryVCBONDFUTURESEODPRICESListByYYYYMM(newspaperEntity);
	}

}
