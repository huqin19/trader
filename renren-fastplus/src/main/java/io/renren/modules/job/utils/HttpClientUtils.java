package io.renren.modules.job.utils;

import java.util.Map;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * HttpClient工具类
 * @author DHB
 * @date 2018/5/4
 */
public class HttpClientUtils {
	//创建一个httpclient对象
	private Logger logger = LoggerFactory.getLogger(getClass());
	CloseableHttpClient httpClient = HttpClients.createDefault();
	public void doGetPush(String url,Map<String, String> para) throws Exception{
		//创建一个url对象
		URIBuilder builder = new URIBuilder(url);
		Set<String> set = para.keySet();
        for(String key: set){
            builder.setParameter(key, para.get(key));
        }
		HttpGet get = new HttpGet(builder.build());
		//执行请求
		logger.info(get.toString());
		CloseableHttpResponse response = httpClient.execute(get);
		//取响应的结果
		int status = response.getStatusLine().getStatusCode();
		System.out.println(status);
		logger.info(String.valueOf(status));
		HttpEntity entity =response.getEntity();
		String result = EntityUtils.toString(entity,"utf-8");
		logger.info(result);
		//关闭httpclient
		response.close();
		httpClient.close();
		
	}

}
