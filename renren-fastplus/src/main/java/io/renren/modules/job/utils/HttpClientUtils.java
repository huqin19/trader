package io.renren.modules.job.utils;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
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
	private static Logger logger = LoggerFactory.getLogger(HttpClientUtils.class);	
	/**
	 * 发送get请求
	 * @param url
	 * @param para
	 * @param needResponse  需要返回结果
	 * @return
	 */
	public static String doGet(String url,Map<String, String> para,boolean needResponse) {
		//创建一个httpclient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		//定义返回结果
		//JSONObject jsonResult = null;
		String strResult =null;
		//创建一个url对象
		URIBuilder builder = null;
		CloseableHttpResponse response  = null;
		try {
			builder = new URIBuilder(url);
			Set<String> set = para.keySet();
			//放入参数
	        for(String key: set){
	            builder.setParameter(key, para.get(key));
	        }
			HttpGet request = new HttpGet(builder.build());
			//执行请求
			logger.info(request.toString());
			response = httpClient.execute(request);
			//请求发送成功并得到响应
			if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				//服务器返回的json字符串
				strResult = EntityUtils.toString(response.getEntity());				
				if(!needResponse) {
					//如果needResponse为false就返回null
					logger.info(strResult);
					return null;
				}				
				//再把字符串转换为json对象
				//jsonResult = JSONObject.fromObject(strResult);		
			}else {
				logger.error("get请求提交失败：" + url);
			}
		} catch (Exception e) {
			logger.error("get请求提交失败：" + url, e);
		} finally {
            if(response != null){
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(httpClient != null){
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
		return strResult;		
	}
	/**
     * post请求
     * @param url         url地址
     * @param jsonParam     参数
     * @param needResponse  需要返回结果
     * @return
     */
	public static String doPost(String url, String param, boolean needResponse) {
		//创建一个httpclient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		// post请求返回结果
		//JSONObject jsonResult = null;
		String strResult =null;
		CloseableHttpResponse response = null;
		HttpPost request = new HttpPost(url);
		if (null != param) {
			// 解决中文乱码问题
			StringEntity entity = new StringEntity(param, "utf-8");
			entity.setContentEncoding("UTF-8");
			entity.setContentType("application/json");
			request.setEntity(entity);
		}
		try {
			response = httpClient.execute(request);
			url = URLDecoder.decode(url, "UTF-8");
			// 请求发送成功，并得到响应
			if (response.getStatusLine().getStatusCode() == 200) {
				strResult = EntityUtils.toString(response.getEntity());
				logger.info(strResult);
				// 读取服务器返回过来的json字符串数据				
				if (!needResponse) {
					//如果needResponse为false就返回null
					return null;
				}
				// 把json字符串转换成json对象
				//jsonResult = JSONObject.fromObject(strResult);
			}
		} catch (Exception e) {
			logger.error("post请求提交失败:" + url, e);
		}finally {
            if(response != null){
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(httpClient != null){
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
		return strResult;
	}

}
