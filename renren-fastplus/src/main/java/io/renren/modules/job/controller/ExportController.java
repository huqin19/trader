package io.renren.modules.job.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.api.annotation.AuthIgnore;
import io.renren.common.utils.CsvUtils;
import io.renren.common.utils.ExcelUtils;

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
	
	@AuthIgnore
	@RequestMapping("/listexport")
	public void exportTest2(HttpServletResponse response){
        List<String> listName = new ArrayList<>();
        listName.add("id");
        listName.add("名字");
        listName.add("性别");
        List<String> listId = new ArrayList<>();
        listId.add("id");
        listId.add("name");
        listId.add("sex");

        List<Map<String,Object>> listB = new ArrayList<>();
        for (int t=0;t<6;t++){
            Map<String,Object> map = new HashMap<>();
            map.put("id",t);
            map.put("name","abc"+t);
            map.put("sex","男"+t);
            listB.add(map);
        }
        System.out.println("listB  : "+listB.toString());
        //ExportExcelUtil exportExcelUtil = new ExportExcelUtil();
        ExcelUtils.exportExcel("工单信息表Map.xls","测试POI导出EXCEL文档",listName,listId,listB,response);
        
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
