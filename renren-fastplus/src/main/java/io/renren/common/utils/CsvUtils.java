package io.renren.common.utils;
import java.io.BufferedOutputStream;  
import java.io.IOException;  
import java.io.InputStream;  
import java.io.OutputStream;  
import java.net.URLEncoder;  
import java.nio.charset.Charset;  
import java.util.ArrayList;  
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import com.csvreader.CsvReader;  
import com.csvreader.CsvWriter; 

/**
 * 解析csv文件工具
 * @author DHB
 * @date 2018/5/12
 */
public class CsvUtils {
    /** 
     * 读取CSV文件 
     * @param csvFilePath 文件路径 
     */  
    public static ArrayList<String[]> readeCsv(String csvFilePath) {  
        ArrayList<String[]> csvList = new ArrayList<String[]>(); // 用来保存数据  
        try {  
            CsvReader reader = new CsvReader(csvFilePath, ',',Charset.forName("GBK")); // 一般用这编码读就可以了  
            //reader.readHeaders(); // 跳过表头 如果需要表头的话，不要写这句。  
            while (reader.readRecord()) { // 逐行读入除表头的数据  
                csvList.add(reader.getValues());  
            }  
            reader.close();  
        } catch (Exception ex) {  
            System.out.println(ex);  
        }  
        return csvList;  
    }  

    /** 
     * 读取CSV文件 
     * @param csvIs csv导入数据流 
     */  
    public static ArrayList<String[]> readeCsvByIs(InputStream csvIs) {  
        ArrayList<String[]> csvList = new ArrayList<String[]>(); // 用来保存数据  
        try {  
            CsvReader reader = new CsvReader(csvIs,Charset.forName("GBK")); // 一般用这编码读就可以了  
            //reader.readHeaders(); // 跳过表头 如果需要表头的话，不要写这句。  
            while (reader.readRecord()) { // 逐行读入除表头的数据  
                csvList.add(reader.getValues());  
            }  
            reader.close();  
        } catch (Exception ex) {  
            System.out.println(ex);  
        }  
        return csvList;  
    }  


    /** 
     * 写入CSV文件 
     *  
     * @param csvFilePath 写出路径 
     *  
     * @param contents 数据内容 
     */  
    public static void writeCsv(String csvFilePath ,List<String[]> contents) {  
        try {  
            //String csvFilePath = "c:/test.csv";  
            CsvWriter wr = new CsvWriter(csvFilePath, ',',Charset.forName("GBK"));  
            for (int i = 0; i < contents.size(); i++) {  
                wr.writeRecord(contents.get(i));  
            }  
            wr.close();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  


     /** 
     * 输出文件 
     * @param ou   文件流 
     * @param list      需要输出的数据 
     * @throws java.io.IOException 
     */  
    public static void writeCsv(OutputStream ou, List<String[]> list) throws IOException {  
        CsvWriter cw = new CsvWriter(ou, ',', Charset.forName("GBK"));  
        for(String[] s: list) {  
            cw.writeRecord(s);  
        }  
        //在文件中增加BOM，详细说明可以Google,该处的byte[] 可以针对不同编码进行修改  
        ou.write(new byte[] { (byte) 0xEF, (byte) 0xBB,(byte) 0xBF });  
        cw.flush();  
        cw.close();  
    }  

    /** 
     * 导出csv模板 
     * 
     * @param response 
     * @param headers 模板列标题 
     * @param csvName csv文件名 
     * @throws java.io.IOException 
     */  
    public static void exportCsv(HttpServletResponse response,String headers,String csvName) throws IOException {  
        OutputStream fos = response.getOutputStream();  
        BufferedOutputStream bos = new BufferedOutputStream(fos);  
        try {  
            fos = response.getOutputStream();  
            bos = new BufferedOutputStream(fos);  
            //这个就就是弹出下载对话框的关键代码  
            response.setContentType("text/csv");  
            response.setHeader("Content-disposition","attachment;filename=" +URLEncoder.encode(csvName+".csv", "GBK"));  
            headers += "\n";  
            bos.write(headers.getBytes("GBK"));  
            bos.flush();  
        } catch (IOException ex) {  
            System.out.print(ex);  
        } finally {  
            fos.close();  
            bos.close();  
        }  
    } 
	
}
