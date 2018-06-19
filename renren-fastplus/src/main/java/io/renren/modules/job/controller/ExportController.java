package io.renren.modules.job.controller;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.api.annotation.AuthIgnore;
import io.renren.common.utils.CsvUtils;
import io.renren.common.utils.DateUtils;
import io.renren.common.utils.ExcelUtil;

/**
*@author DHB
*@version 2018年5月19日上午12:57:59
*easypoi test
*/
@RestController
@RequestMapping(value = "/api")
public class ExportController {
/*	@Autowired
	private DepartmentService departmentService;
	@AuthIgnore
	@RequestMapping("/export") 
	public void exportTest(HttpServletResponse response){
		List<DepartmentEntity> dept = departmentService.queryAllDepartment();
		ExcelUtils.exportExcel(dept, "AllDepartment", "dept", DepartmentEntity.class,
				"dept.xls", response);
	}*/
	
/*	@AuthIgnore
	@RequestMapping("/e")
	public ResponseEntity<byte[]> exportTest2(){
        List<String> listName = new ArrayList<>();
        ResponseEntity<byte[]> resEntity = null;
        listName.add("编号");
        listName.add("名字");
        listName.add("性别");
        listName.add("日期");
        listName.add("数字");
        List<String> listId = new ArrayList<>();
        listId.add("id");
        listId.add("name");
        listId.add("sex");
        listId.add("date");
        listId.add("num");
        List<Map<String,Object>> listB = new ArrayList<>();
        for (int t=0;t<12;t++){
            Map<String,Object> map = new HashMap<>();
            map.put("id","0"+t+"0");
            map.put("name",t+".0");
            map.put("sex",null);
            map.put("date",DateUtils.format(new Date(), DateUtils.NUM_PATTERN));
            map.put("num",t+"POI中可能会用到一些需要设置EXCEL单元格格式的操作小结");
            listB.add(map);
        }
        System.out.println("listB  : "+listB.toString());
        //ExportExcelUtil exportExcelUtil = new ExportExcelUtil();
        ByteArrayOutputStream out = ExcelUtils.exportExcel("银行间每日债券借贷",
        		listName,listId, listB);
        HttpHeaders head = new HttpHeaders();
        String fileName = null;
        String date = DateUtils.format(new Date(), DateUtils.DATE_TIME_PATTERN);
        System.out.println(date + "-----------");
        try {
        	fileName = URLEncoder.encode("Test"+date+".xls", "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
        head.setContentDispositionFormData("attachment", fileName);
        head.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        byte [] bytes = out.toByteArray();
        resEntity = new ResponseEntity<byte[]>(bytes,head,HttpStatus.OK);
		return resEntity; 
	}*/
	
	@AuthIgnore
	@RequestMapping("/t")
	public ResponseEntity<byte[]> exportTest3(){
        ResponseEntity<byte[]> resEntity = null;
        List<Integer[]> mergeList = new ArrayList<>();
        Integer[] arr1 = {1,1,0};
        mergeList.add(arr1);
        Integer[] mergeColume = {0};
        Integer[] needRow = {0};
        Integer[] fontColor = {2};
        List<String> headName1 = new ArrayList<>();
        headName1.add("编号");
        headName1.add("名字");
        headName1.add("性别");
        List<String> headName2 = new ArrayList<>();
        headName2.add("可能");
        headName2.add("会用到");
        headName2.add("一些需要");
        headName2.add("设置EXCEL");
        headName2.add("单元格"); 
        List<List<String>> headsList = new ArrayList<>();
        headsList.add(headName1);
        headsList.add(headName2);       
        List<List<Object>> dtoList = new ArrayList<>();
        for (int t=0;t<5;t++){
        	List<Object> dataList = new ArrayList<>();
        	if(t == 0) {
        		dataList.add("2");	
        	}else {
        		dataList.add("1");
        	}
        	dataList.add(t+".0");
        	if(t == 3) {
        		dataList.add("-1");
        	}else{
        		dataList.add(t);
        	}
        	dataList.add(DateUtils.format(new Date(), DateUtils.NUM_PATTERN));
        	dataList.add(t+"POI中可能");
        	dtoList.add(dataList);
        }
        System.out.println("listB  : "+dtoList.toString());
        ByteArrayOutputStream out = ExcelUtil.export("Test", headsList, dtoList,mergeList,mergeColume,needRow,fontColor);
        HttpHeaders head = new HttpHeaders();
        String fileName = null;
        String date = DateUtils.format(new Date(), DateUtils.DATE_TIME_PATTERN);
        System.out.println(date + "-----------");
        try {
        	fileName = URLEncoder.encode("Test"+date+".xls", "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
        head.setContentDispositionFormData("attachment", fileName);
        head.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        byte [] bytes = out.toByteArray();
        resEntity = new ResponseEntity<byte[]>(bytes,head,HttpStatus.OK);
		return resEntity; 
	}
	
	
	@AuthIgnore
	@RequestMapping("/inport")
	public void inportTest(){
		ArrayList<String[]> list = CsvUtils.readeCsv("C:\\Users\\Administrator\\Desktop\\森浦CSV文件\\Data20171108_Sumscope.csv");
		for(String[] senpu : list) {
			for(int i = 0; i < senpu.length; i ++) {
				System.out.print(senpu[i]+"=====");
			}
			System.out.println();
		}		
	}
}
