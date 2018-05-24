package io.renren.common.utils;



import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

import javax.servlet.http.HttpServletResponse;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URLEncoder;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * @author DHB
 * @version 2018年5月21日上午12:56:42
 * Excel导出
 */
public class ExcelUtils {
	/**
	 * 传入参数为List<Map>
	 * @param fileName 文件名
	 * @param title 标题
	 * @param headersName 表头--标题栏
	 * @param headersId 字段---标题的字段
	 * @param dtoList 要处理的数据
	 * @param response 
	 */
	public static void exportExcel(String fileName, String title, List<String> headersName, List<String> headersId,
			List<Map<String, Object>> dtoList,HttpServletResponse response) {
		/*
		 * （一）表头--标题栏
		 */
		Map<Integer, String> headersNameMap = new HashMap<>();
		int key = 0;
		for (int i = 0; i < headersName.size(); i++) {
			if (!headersName.get(i).equals(null)) {
				headersNameMap.put(key, headersName.get(i));
				key++;
			}
		}
		/*
		 * （二）字段---标题的字段
		 */
		Map<Integer, String> titleFieldMap = new HashMap<>();
		int value = 0;
		for (int i = 0; i < headersId.size(); i++) {
			if (!headersId.get(i).equals(null)) {
				titleFieldMap.put(value, headersId.get(i));
				value++;
			}
		}
		/*
		 * （三）声明一个工作薄：包括构建工作簿、表格、样式
		 */
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet(title);
		sheet.setDefaultColumnWidth((short) 15);
		// 生成一个样式
		HSSFCellStyle style = wb.createCellStyle();
		HSSFRow row = sheet.createRow(0);
		//style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		HSSFCell cell;
		Collection c = headersNameMap.values();// 拿到表格所有标题的value的集合
		Iterator<String> headersNameIt = c.iterator();// 表格标题的迭代器
		/*
		 * （四）导出数据：包括导出标题栏以及内容栏
		 */
		// 根据选择的字段生成表头--标题
		short size = 0;
		while (headersNameIt.hasNext()) {
			cell = row.createCell(size);
			cell.setCellValue(headersNameIt.next().toString());
			cell.setCellStyle(style);
			size++;
		}
		// 表格一行的字段的集合，以便拿到迭代器
		Collection zdC = titleFieldMap.values();
		Iterator<Map<String, Object>> titleFieldIt = dtoList.iterator();// 总记录的迭代器
		int zdRow = 1;// 真正的数据记录的列序号
		while (titleFieldIt.hasNext()) {// 记录的迭代器，遍历总记录
			Map<String, Object> mapTemp = titleFieldIt.next();// 拿到一条记录
			row = sheet.createRow(zdRow);
			zdRow++;
			int zdCell = 0;
			Iterator<String> zdIt = zdC.iterator();// 一条记录的字段的集合的迭代器
			while (zdIt.hasNext()) {
				String tempField = zdIt.next();// 字段的暂存
				if (mapTemp.get(tempField) != null) {
					row.createCell((short) zdCell).setCellValue(String.valueOf(mapTemp.get(tempField)));// 写进excel对象
					zdCell++;
				}
			}
		}
		//下载
		try {
			downLoad(fileName, response, wb);
			System.out.println("导出成功!");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("导出失败!");
			
		}
/*		try {
			FileOutputStream exportXls = new FileOutputStream(fileName);
			wb.write(exportXls);
			exportXls.close();
			System.out.println("导出成功!");
		} catch (FileNotFoundException e) {
			System.out.println("导出失败!");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("导出失败!");
			e.printStackTrace();
		}*/
	}
	/**
	 * 下载
	 * @param fileName
	 * @param response
	 * @param workbook
	 * @throws IOException 
	 */
    private static void downLoad(String fileName, HttpServletResponse response, 
    		Workbook workbook) throws IOException {
       // try {
            response.setCharacterEncoding("UTF-8");
            response.setHeader("content-Type", "application/vnd.ms-excel");
            response.setHeader("Content-Disposition",
                    "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            workbook.write(response.getOutputStream());
        //} catch (IOException e) {
        //	e.printStackTrace();
       // }
    }
    /**
     * 传入参数为javaBean
	 * @param fileName 文件名
	 * @param title 标题
	 * @param headersName 表头--标题栏
	 * @param headersId 字段---标题的字段
	 * @param dtoList 要处理的数据
     */
	public static void exportBeanExcel(String fileName,String title, List<String> headersName,
			List<String> headersId, List<T> dtoList,HttpServletResponse response) {
		/* （一）表头--标题栏 */
		Map<Integer, String> headersNameMap = new HashMap<>();
		int key = 0;
		for (int i = 0; i < headersName.size(); i++) {
			if (!headersName.get(i).equals(null)) {
				headersNameMap.put(key, headersName.get(i));
				key++;
			}
		}
		/* （二）字段 */
		Map<Integer, String> titleFieldMap = new HashMap<>();
		int value = 0;
		for (int i = 0; i < headersId.size(); i++) {
			if (!headersId.get(i).equals(null)) {
				titleFieldMap.put(value, headersId.get(i));
				value++;
			}
		}
		/* （三）声明一个工作薄：包括构建工作簿、表格、样式 */
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet(title);
		sheet.setDefaultColumnWidth((short) 15);
		// 生成一个样式
		HSSFCellStyle style = wb.createCellStyle();
		HSSFRow row = sheet.createRow(0);
		//style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		HSSFCell cell;
		Collection c = headersNameMap.values();// 拿到表格所有标题的value的集合
		Iterator<String> it = c.iterator();// 表格标题的迭代器
		/* （四）导出数据：包括导出标题栏以及内容栏 */
		// 根据选择的字段生成表头
		short size = 0;
		while (it.hasNext()) {
			cell = row.createCell(size);
			cell.setCellValue(it.next().toString());
			cell.setCellStyle(style);
			size++;
		}
		// 表格标题一行的字段的集合
		Collection zdC = titleFieldMap.values();
		Iterator<T> labIt = dtoList.iterator();// 总记录的迭代器
		int zdRow = 0;// 列序号
		while (labIt.hasNext()) {// 记录的迭代器，遍历总记录
			int zdCell = 0;
			zdRow++;
			row = sheet.createRow(zdRow);
			T l = (T) labIt.next();
			// 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值
			Field[] fields = l.getClass().getDeclaredFields();// 获得JavaBean全部属性
			for (short i = 0; i < fields.length; i++) {// 遍历属性，比对
				Field field = fields[i];
				String fieldName = field.getName();// 属性名
				Iterator<String> zdIt = zdC.iterator();// 一条字段的集合的迭代器
				while (zdIt.hasNext()) {// 遍历要导出的字段集合
					if (zdIt.next().equals(fieldName)) {// 比对JavaBean的属性名，一致就写入，不一致就丢弃
						String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);// 拿到属性的get方法
						Class tCls = l.getClass();// 拿到JavaBean对象
						try {
							Method getMethod = tCls.getMethod(getMethodName, new Class[] {});// 通过JavaBean对象拿到该属性的get方法，从而进行操控
							Object val = getMethod.invoke(l, new Object[] {});// 操控该对象属性的get方法，从而拿到属性值
							String textVal = null;
							if (val != null) {
								textVal = String.valueOf(val);// 转化成String
							} else {
								textVal = null;
							}
							row.createCell((short) zdCell).setCellValue(textVal);// 写进excel对象
							zdCell++;
						} catch (SecurityException e) {
							e.printStackTrace();
						} catch (IllegalArgumentException e) {
							e.printStackTrace();
						} catch (NoSuchMethodException e) {
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
		try {
			downLoad(fileName, response, wb);
			System.out.println("导出成功!");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("导出失败!");
		}
		/*try {
			FileOutputStream exportXls = new FileOutputStream(fileName);
			wb.write(exportXls);
			exportXls.close();
			System.out.println("导出成功!");
		} catch (FileNotFoundException e) {
			System.out.println("导出失败!");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("导出失败!");
			e.printStackTrace();
		}*/
	}
	
	
}
