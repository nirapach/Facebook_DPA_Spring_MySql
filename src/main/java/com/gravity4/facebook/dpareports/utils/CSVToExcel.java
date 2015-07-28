package com.gravity4.facebook.dpareports.utils;

/**
 * Created by niranjan on 7/28/15.
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.gravity4.facebook.dpareports.utils.StatisticsDate;

@Service
public class CSVToExcel {

    public static String getExcelFileName(String Client_Name){

        Date yesterday = StatisticsDate.getYesterday();
        DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
        String Stats_Date=dateformat.format(yesterday);
        //create File object
        File excel_file = new File("src/main/ReportFiles/Excel_Files/"+Client_Name+"_"+"DPAStats"+"_"+Stats_Date+".xls");

        String excel_file_name=excel_file.getAbsolutePath();

        return excel_file_name;
    }


    public static boolean csvToXLS(HSSFWorkbook workBook,String filename, String sheetname,String Excel_File_Name) {

        Logger logger = LoggerFactory.getLogger(OAuthExpirationTokenChecker.class);


        try{

            String csvFile = filename; //csv file address

            HSSFSheet sheet = workBook.createSheet(sheetname);
            String currentLine;
            int RowNum = 0;
            BufferedReader br = new BufferedReader(new FileReader(csvFile));

            while ((currentLine = br.readLine()) != null) {
                String str[] = currentLine.split(",");
                HSSFRow currentRow = sheet.createRow(RowNum);
                RowNum++;
                for (int i = 0; i < str.length; i++) {
                    currentRow.createCell(i).setCellValue(str[i]);
                }
            }
            FileOutputStream fileOutputStream = new FileOutputStream(Excel_File_Name);
            workBook.write(fileOutputStream);
            fileOutputStream.close();
            logger.info(filename + "-" + sheetname + "-" + "created");
        }catch (Exception ex) {
            logger.info(String.valueOf(ex));
        }

        return true;

    }


}
