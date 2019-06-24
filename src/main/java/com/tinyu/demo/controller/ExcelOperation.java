package com.tinyu.demo.controller;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.tinyu.demo.config.ErrorCode;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "Excel 操作相关")
@RequestMapping(value = "/excel")
public class ExcelOperation {
	@GetMapping(value = "/read")
	@ApiOperation(value = "读取文件", notes = "解析excel文件")
	public String read() {
		//excel文件路径
        String excelPath = "C:\\Users\\Administrator\\Desktop\\test.xls";
        JSONArray jarr=new JSONArray();
        try {
            //String encoding = "GBK";
            File excel = new File(excelPath);
            if (excel.isFile() && excel.exists()) {   //判断文件是否存在

                String[] split = excel.getName().split("\\.");  //.是特殊字符，需要转义！！！！！
                Workbook wb=null;
                //根据文件后缀（xls/xlsx）进行判断
                if ( "xls".equals(split[1])){
                    FileInputStream fis = new FileInputStream(excel);   //文件流对象
                    wb = new HSSFWorkbook(fis);
                }else if ("xlsx".equals(split[1])){
                    wb = new XSSFWorkbook(excel);
                }else {
                	return ErrorCode.err(ErrorCode.SYSTEM_ERR, "文件格式错误！");
                }
                //开始解析
                Sheet sheet = wb.getSheetAt(0);     //读取sheet 0
                //int firstRowIndex = sheet.getFirstRowNum()+1;   //第一行是列名，所以不读
                int firstRowIndex = sheet.getFirstRowNum();
                int lastRowIndex = sheet.getLastRowNum();
    
                for(int rIndex = firstRowIndex; rIndex <= lastRowIndex; rIndex++) {   //遍历行
                    Row row = sheet.getRow(rIndex);
                    if (row != null) 
                    {
                        int firstCellIndex = row.getFirstCellNum();
                        int lastCellIndex = row.getLastCellNum();
                        JSONObject line=new JSONObject();
                        for (int cIndex = firstCellIndex; cIndex < lastCellIndex; cIndex++) {   //遍历列
                            Cell cell = row.getCell(cIndex);
                            if (cell != null) {
                            	line.putOpt("column"+cIndex, cell.toString());
                            }
                        }
                        jarr.put(line);
                    }
                }
            } else {
            	return ErrorCode.err(ErrorCode.SYSTEM_ERR, "找不到指定文件");
            }
        } catch (Exception e) {
        	return ErrorCode.err(ErrorCode.SYSTEM_ERR,e.toString());
        }
      return ErrorCode.ok(jarr);
	}
}
