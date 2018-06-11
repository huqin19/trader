package io.renren.common.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.HashMap;

import org.ho.yaml.Yaml;

/**
 * 读取yml文件
 * @author Milk
 *
 */
@SuppressWarnings("unchecked")
public class ReadYml {
	private static HashMap<String,Object> ml;
	
	/**
	 * 静态代码段，在类路径下加载属性文件mainconfig.properties
	 */
	static {
		URL url = ReadYml.class.getClassLoader().getResource("const.yml");
        File dumpFile=new File(url.getFile());
        try {
			ml = Yaml.loadType(dumpFile, HashMap.class);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
        System.out.println((String) ml.get("JOB_SENDWX"));
	}
	   //读取yaml配置文件
	public  void  read() throws FileNotFoundException {
        File dumpFile=new File("const.yml");
        ml = Yaml.loadType(dumpFile, HashMap.class);
        System.out.println((String) ml.get("JOB_SENDWX"));
    }

	/**
	 * 根据key获取对应的值
	 * 
	 * @param key
	 * @return String类型的值
	 */
	public static Object getMl(String key) {
		
		return ml.get(key);
	}
}
