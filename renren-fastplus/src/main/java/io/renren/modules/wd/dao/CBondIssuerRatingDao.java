package io.renren.modules.wd.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import io.renren.modules.sys.dao.BaseDao;
import io.renren.modules.wd.entity.CBondIssuerRatingEntity;

@Mapper
public interface CBondIssuerRatingDao extends BaseDao<CBondIssuerRatingEntity>{
	void deleteAll();
	List<CBondIssuerRatingEntity> queryAll(Map<String, Object> map);
	CBondIssuerRatingEntity queryFirst(Map<String, Object> map);
	List<CBondIssuerRatingEntity> queryLatest(Map<String, Object> map);
}
