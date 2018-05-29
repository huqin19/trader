package io.renren.modules.wd.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import io.renren.modules.sys.dao.BaseDao;
import io.renren.modules.wd.entity.CBondIssuerRatingEntity;
import io.renren.modules.wd.entity.CBondRatingEntity;
/**
 * 万德数据库CBONDRATING表
 * @author DHB
 * @date 2018/5/15
 */
@Mapper
public interface CBondRatingDao extends BaseDao<CBondRatingEntity>{
	void deleteAll();
	List<CBondRatingEntity> queryAll(Map<String, Object> map);
	CBondRatingEntity queryFirst(Map<String, Object> map);
	List<CBondRatingEntity> queryLatest(Map<String, Object> map);
}
