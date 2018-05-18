package io.renren.modules.job.utils;


import java.io.IOException;
import java.util.ArrayList;

import com.csvreader.CsvReader;

/**
 * 解析csv文件工具
 * @author DHB
 * @date 2018/5/12
 */
public class CsvUtils {
	public static void readCsv(String filePath) {
		//用来保存数据 
		ArrayList<String[]> csvList = new ArrayList<String[]>(); 
		// 创建CSV读对象
		CsvReader csvReader = null;
		try {
			csvReader = new CsvReader(filePath);
			// 读表头
            csvReader.readHeaders();
            while (csvReader.readRecord()) {
            	
            }
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void writeCsv(String filePath) {
		
	}
	
}
