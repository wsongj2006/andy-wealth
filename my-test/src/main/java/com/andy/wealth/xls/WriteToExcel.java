package com.andy.wealth.xls;

import com.alibaba.fastjson.JSONObject;
import org.apache.poi.ss.usermodel.*;

import java.io.*;
import java.util.Date;
import java.util.List;

public class WriteToExcel {
    private static final String EXCEL_XLS = "xls";
    private static final String EXCEL_XLSX = "xlsx";

    public static void writeToExcel(List<JSONObject> jsonObjectList) throws Exception {
        OutputStream out = null;
        String fileName = "kx-devices.xlsx";
        String fullPath = "/Users/andy/Documents/" + fileName;

        File finalXlsxFile = new File(fullPath);
        if (!finalXlsxFile.exists()) {
            finalXlsxFile.createNewFile();
        }
        Workbook workBook = getWorkbok(finalXlsxFile);
        // sheet 对应一个工作页
        Sheet sheet = workBook.getSheetAt(0);

        out = new FileOutputStream(finalXlsxFile);
        workBook.write(out);

        for (int j = 0; j < jsonObjectList.size(); j++) {
            // 创建一行：从第二行开始，跳过属性列
            Row row = sheet.createRow(j + 1);

            Cell first = row.createCell(0);
            first.setCellValue(jsonObjectList.get(j).getString("title"));

            Cell second = row.createCell(1);
            second.setCellValue(jsonObjectList.get(j).getString("imei"));

            Cell third = row.createCell(2);
            third.setCellValue(jsonObjectList.get(j).getString("imsi"));

            Cell fourth = row.createCell(3);
            fourth.setCellValue(new Date().getTime() + "-" + j);
        }
        // 创建文件输出流，准备输出电子表格：这个必须有，否则你在sheet上做的任何操作都不会有效
        out = new FileOutputStream(finalXlsxFile);
        workBook.write(out);

        out.flush();
        out.close();

    }

    public static Workbook getWorkbok(File file) throws Exception {
        FileInputStream in = new FileInputStream(file);
        Workbook workbook = WorkbookFactory.create(in);

        return workbook;
    }
}
