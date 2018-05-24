package io.renren.datasources;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 配置多数据源
 * @author chenshun
 * @date 2017/8/19 0:41
 */
@Configuration
public class DynamicDataSourceConfig {

    @Bean
    @ConfigurationProperties("spring.datasource.druid.zqdb_source")
    public DataSource zqdbDataSource(){
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.druid.wddb_source")
    public DataSource wddbDataSource(){
        return DruidDataSourceBuilder.create().build();
    }
    
    @Bean
    @ConfigurationProperties("spring.datasource.druid.htdb_source")
    public DataSource htdbDataSource(){
        return DruidDataSourceBuilder.create().build();
    }  

    @Bean
    @Primary
    public DynamicDataSource dataSource(DataSource zqdbDataSource, DataSource wddbDataSource,DataSource htdbDataSource) {
        Map<String, DataSource> targetDataSources = new HashMap<>();
        targetDataSources.put(DataSourceNames.ZQDB_SOURCE, zqdbDataSource);
        targetDataSources.put(DataSourceNames.WDDB_SOURCE, wddbDataSource);
        targetDataSources.put(DataSourceNames.HTDB_SOURCE, htdbDataSource);
        return new DynamicDataSource(zqdbDataSource, targetDataSources);
    }
}
