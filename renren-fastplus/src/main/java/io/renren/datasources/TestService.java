package io.renren.datasources;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.renren.datasources.DataSourceNames;
import io.renren.datasources.annotation.DataSource;
import io.renren.modules.wd.entity.CBondFuturesEODPricesEntity;
import io.renren.modules.wd.service.CBondFuturesEODPricesService;
@Service
public class TestService {
	@Autowired
	private CBondFuturesEODPricesService cBondFuturesEODPricesService;
	
	@DataSource(name = DataSourceNames.WDDB_SOURCE)
	public List<CBondFuturesEODPricesEntity> queryList(Map<String, Object> map){
		return cBondFuturesEODPricesService.queryList(map);	
	}
	public void saveToLocal(CBondFuturesEODPricesEntity cBondFuturesEODPricesEntity) {
		cBondFuturesEODPricesService.save(cBondFuturesEODPricesEntity);

	}
}
