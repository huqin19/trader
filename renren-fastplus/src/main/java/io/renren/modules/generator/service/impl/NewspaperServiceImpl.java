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
	
	public String removehg(String x) {
		if(null != x && x.length() > 0) {
			x = x.replaceAll("-", "");
		}
		return x;
	}

	@Override
	public List<NewspaperEntity> queryVCBONDFUTURESPOSITIONSDList(NewspaperEntity newspaperEntity) {
		newspaperEntity.setParamdate(removehg(newspaperEntity.getParamdate()));
		newspaperEntity.setDateinfoS(removehg(newspaperEntity.getDateinfoS()));
		newspaperEntity.setDateinfoE(removehg(newspaperEntity.getDateinfoE()));
		return newspaperDao.queryVCBONDFUTURESPOSITIONSDList(newspaperEntity);
	}

	@Override
	public List<NewspaperEntity> queryVCBONDFUTURESEODPRICESList(NewspaperEntity newspaperEntity) {
		newspaperEntity.setParamdate(removehg(newspaperEntity.getParamdate()));
		newspaperEntity.setDateinfoS(removehg(newspaperEntity.getDateinfoS()));
		newspaperEntity.setDateinfoE(removehg(newspaperEntity.getDateinfoE()));
		return newspaperDao.queryVCBONDFUTURESEODPRICESList(newspaperEntity);
	}

	@Override
	public List<String> queryHYNameOfVCBONDFUTURESPOSITIONSD(NewspaperEntity newspaperEntity) {
		newspaperEntity.setParamdate(removehg(newspaperEntity.getParamdate()));
		newspaperEntity.setDateinfoS(removehg(newspaperEntity.getDateinfoS()));
		newspaperEntity.setDateinfoE(removehg(newspaperEntity.getDateinfoE()));
		return newspaperDao.queryHYNameOfVCBONDFUTURESPOSITIONSD(newspaperEntity);
	}

	@Override
	public List<String> queryHYNameOfVCBONDFUTURESEODPRICES(NewspaperEntity newspaperEntity) {
		newspaperEntity.setParamdate(removehg(newspaperEntity.getParamdate()));
		newspaperEntity.setDateinfoS(removehg(newspaperEntity.getDateinfoS()));
		newspaperEntity.setDateinfoE(removehg(newspaperEntity.getDateinfoE()));
		return newspaperDao.queryHYNameOfVCBONDFUTURESEODPRICES(newspaperEntity);
	}

	@Override
	public List<NewspaperEntity> queryVCBONDFUTURESEODPRICESListByYYYYMM(NewspaperEntity newspaperEntity) {
		newspaperEntity.setParamdate(removehg(newspaperEntity.getParamdate()));
		newspaperEntity.setDateinfoS(removehg(newspaperEntity.getDateinfoS()));
		newspaperEntity.setDateinfoE(removehg(newspaperEntity.getDateinfoE()));
		return newspaperDao.queryVCBONDFUTURESEODPRICESListByYYYYMM(newspaperEntity);
	}

}
