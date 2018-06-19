package io.renren.common.utils;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;

/**
 * @author DHB
 * @date 2018年6月15日下午1:37:03
 *
 */
public class ExcelUtil {
	public static ByteArrayOutputStream export(String title, List<List<String>> headsList, List<List<Object>> dtoList
			,List<Integer[]> mergeList, Integer[] mergeColume, Integer[] mergeRow, Integer[] fontColor) {
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet(title);
		sheet.setDefaultColumnWidth((short) 12);
		// 生成一个样式
		HSSFCellStyle style = wb.createCellStyle();
		HSSFFont headFont = wb.createFont();
		style.setBorderTop(BorderStyle.THIN);// 上边框
		style.setTopBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());//边框颜色
		style.setBorderBottom(BorderStyle.THIN);// 下边框
		style.setBottomBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
		style.setBorderLeft(BorderStyle.THIN);// 左边框
		style.setLeftBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
		style.setBorderRight(BorderStyle.THIN);// 右边框
		style.setRightBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);// 牢固填充
		style.setFillForegroundColor((short) 31);// 浅蓝色
		headFont.setBold(true);// 字体加粗
		headFont.setFontHeightInPoints((short) 10);// 设置字号
		style.setAlignment(HorizontalAlignment.CENTER);// 水平居中
		style.setVerticalAlignment(VerticalAlignment.CENTER);// 垂直居中
		style.setWrapText(true);// 自动换行
		style.setFont(headFont);
		HSSFCell cell;
		int rownum = 0;
		HSSFRow row;
		for (List<String> headList : headsList) {
			int headCellNum = 0;
			row = sheet.createRow(rownum);
			row.setHeightInPoints(25);// 设置高度
			Integer index = 0;
			CellRangeAddress region;
			for (String head : headList) {			
				if(mergeColume != null && ArrayUtils.contains(mergeColume, rownum)) {
					cell = row.createCell(index);
					cell.setCellValue(head);
					if(mergeList.get(rownum)[headCellNum] != null) {
						Integer difference = mergeList.get(rownum)[headCellNum];
						if(difference > 0) {
							region=new CellRangeAddress(rownum, rownum, index , index + difference);
							sheet.addMergedRegion(region);
						}
						index = index + difference + 1;	
					}else{
						index = headCellNum ++;
					}
				
				}else {
					cell = row.createCell(headCellNum);
					cell.setCellValue(head);
				}
				cell.setCellStyle(style);
				headCellNum++;
			}
			rownum++;
		}
		HSSFCellStyle contextstyle = wb.createCellStyle();
		contextstyle.setBorderTop(BorderStyle.THIN);// 上边框
		contextstyle.setTopBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
		contextstyle.setBorderBottom(BorderStyle.THIN);// 下边框
		contextstyle.setBottomBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
		contextstyle.setBorderLeft(BorderStyle.THIN);// 左边框
		contextstyle.setLeftBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
		contextstyle.setBorderRight(BorderStyle.THIN);// 右边框
		contextstyle.setRightBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
		contextstyle.setAlignment(HorizontalAlignment.CENTER);// 水平居中
		contextstyle.setVerticalAlignment(VerticalAlignment.CENTER);// 垂直居中
		contextstyle.setWrapText(true);// 自动换行
		
		HSSFCellStyle contextstyleRed = wb.createCellStyle();//红色字体
		contextstyleRed.setBorderTop(BorderStyle.THIN);// 上边框
		contextstyleRed.setTopBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
		contextstyleRed.setBorderBottom(BorderStyle.THIN);// 下边框
		contextstyleRed.setBottomBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
		contextstyleRed.setBorderLeft(BorderStyle.THIN);// 左边框
		contextstyleRed.setLeftBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
		contextstyleRed.setBorderRight(BorderStyle.THIN);// 右边框
		contextstyleRed.setRightBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
		contextstyleRed.setAlignment(HorizontalAlignment.CENTER);// 水平居中
		contextstyleRed.setVerticalAlignment(VerticalAlignment.CENTER);// 垂直居中
		contextstyleRed.setWrapText(true);// 自动换行
		HSSFFont fontRed = wb.createFont();
		fontRed.setColor(IndexedColors.RED.getIndex());
		contextstyleRed.setFont(fontRed);
		
		HSSFCellStyle contextstyleGreen = wb.createCellStyle();//绿色字体
		contextstyleGreen.setBorderTop(BorderStyle.THIN);// 上边框
		contextstyleGreen.setTopBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
		contextstyleGreen.setBorderBottom(BorderStyle.THIN);// 下边框
		contextstyleGreen.setBottomBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
		contextstyleGreen.setBorderLeft(BorderStyle.THIN);// 左边框
		contextstyleGreen.setLeftBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
		contextstyleGreen.setBorderRight(BorderStyle.THIN);// 右边框
		contextstyleGreen.setRightBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
		contextstyleGreen.setAlignment(HorizontalAlignment.CENTER);// 水平居中
		contextstyleGreen.setVerticalAlignment(VerticalAlignment.CENTER);// 垂直居中
		contextstyleGreen.setWrapText(true);// 自动换行
		HSSFFont fontGreen = wb.createFont();
		fontGreen.setColor(IndexedColors.GREEN.getIndex());
		contextstyleGreen.setFont(fontGreen);
		
		HSSFCell contentCell;
		Map<Integer, Object> cacheData = new HashMap<Integer, Object>();
		Map<Integer, Integer> cacheNum = new HashMap<Integer, Integer>();
		Integer num = 0;		
		CellRangeAddress regionRow;
		for (List<Object> list : dtoList) {
			int cellNum = 0;
			row = sheet.createRow(rownum);			
			for (Object data : list) {
				if (data == null) {
					data = "";
				}
				Boolean isNum = false;
				Boolean isInteger = false;
				Boolean isPercent = false;
				Boolean zero = false;
				Boolean isDate = false;
				if (data != null || !"".equals(data.toString().trim())) {
					isNum = data.toString().matches("^(-?\\d+)(\\.\\d+)?$");
					isInteger = data.toString().matches("^[-\\+]?[\\d]*$");
					isPercent = data.toString().contains("%");
					zero = data.toString().startsWith("0") && data.toString().trim().length() > 1;
					isDate = DateUtils.isValidDate(data.toString(), DateUtils.NUM_PATTERN);
				}
				contentCell = row.createCell(cellNum);
				if(ArrayUtils.contains(mergeRow, cellNum)) {
					if(data != null && data.toString().trim() != "") {						
						if(data == cacheData.get(cellNum)) {
							if(cacheNum.get(cellNum) != null && cacheNum.get(cellNum) >= 1 
									&& rownum == dtoList.size() + headsList.size() - 1) {
								regionRow = new CellRangeAddress(rownum - num - 1, rownum, cellNum , cellNum);
								sheet.addMergedRegion(regionRow);
								contentCell.setCellStyle(contextstyle);
							}	
							num ++;
							cacheNum.put(cellNum, num);
							cacheData.put(cellNum, data);
						}else{
							if(cacheNum.get(cellNum) != null &&cacheNum.get(cellNum) >= 1) {
								regionRow = new CellRangeAddress(rownum - num -1, rownum - 1, cellNum , cellNum);
								sheet.addMergedRegion(regionRow);
								contentCell.setCellStyle(contextstyle);
								num = 0;
								cacheNum.put(cellNum, num);
							}	
							cacheData.put(cellNum, data);
							if (isNum && !isPercent) {
								//HSSFDataFormat df = wb.createDataFormat();
								if (isDate) {
									contentCell.setCellStyle(contextstyle);
									contentCell.setCellValue(
											DateUtils.format(data.toString(), DateUtils.NUM_PATTERN, DateUtils.DATE_PATTERN));
									System.out.println(data.toString() + "====isDate");
								} else if (isInteger) {
									if (zero) {
										contentCell.setCellStyle(contextstyle);
										contentCell.setCellValue(data.toString());
										System.out.println(data.toString() + "====zero");
									} else {
										contentCell.setCellStyle(contextstyle);
										contentCell.setCellValue(Double.parseDouble(data.toString()));
										System.out.println(data.toString() + "====not zero");
									}
								} else {
									contentCell.setCellStyle(contextstyle);
									contentCell.setCellValue(Double.parseDouble(data.toString()));
									System.out.println(data.toString() + "====is not Integer");
								}
							} else {
								contentCell.setCellStyle(contextstyle);
								contentCell.setCellValue(data.toString());
								System.out.println(data.toString() + "====");
							}
						}					
					}			
				}else {
					if (isNum && !isPercent) {
						//HSSFDataFormat df = wb.createDataFormat();
						if (isDate) {
							contentCell.setCellStyle(contextstyle);
							contentCell.setCellValue(
									DateUtils.format(data.toString(), DateUtils.NUM_PATTERN, DateUtils.DATE_PATTERN));
							System.out.println(data.toString() + "====isDate");
						} else if (isInteger) {
							if (zero) {
								contentCell.setCellStyle(contextstyle);
								contentCell.setCellValue(data.toString());
								System.out.println(data.toString() + "====zero");
							} else {
								if(fontColor != null && ArrayUtils.contains(fontColor, cellNum)) {
									if(Double.parseDouble(data.toString()) > 0) {
										contentCell.setCellStyle(contextstyleRed);
										contentCell.setCellValue(Double.parseDouble(data.toString()));
									}else if(Double.parseDouble(data.toString()) < 0) {
										HSSFFont font = wb.createFont();
										font.setColor(IndexedColors.GREEN.getIndex());
										contentCell.setCellStyle(contextstyleGreen);
										contentCell.setCellValue(Double.parseDouble(data.toString()));
									}else {
										contentCell.setCellStyle(contextstyle);
										contentCell.setCellValue(Double.parseDouble(data.toString()));
									}
								}

								System.out.println(data.toString() + "====not zero");
							}
						} else {
							contentCell.setCellStyle(contextstyle);
							contentCell.setCellValue(Double.parseDouble(data.toString()));
							System.out.println(data.toString() + "====is not Integer");
						}
					} else {
						contentCell.setCellStyle(contextstyle);
						contentCell.setCellValue(data.toString());
						System.out.println(data.toString() + "====");
					}
				}
				cellNum ++;
			}
			rownum++;
		}
		ByteArrayOutputStream out = null;
		try {
			// exportXls = new FileOutputStream(fileName);
			out = new ByteArrayOutputStream();
			wb.write(out);
			out.close();
			wb.close();
			System.out.println("导出成功!");
		} catch (FileNotFoundException e) {
			System.out.println("导出失败!");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("导出失败!");
			e.printStackTrace();
		}
		return out;
	}
}
