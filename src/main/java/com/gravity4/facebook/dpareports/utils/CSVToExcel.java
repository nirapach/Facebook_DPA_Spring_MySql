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
import org.apache.poi.ss.usermodel.*;
import java.util.Date;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.gravity4.facebook.dpareports.utils.StatisticsDate;

@Service
public class CSVToExcel {

    public static String getExcelFileName(String Client_Name,String excel_address){

        Date yesterday = StatisticsDate.getYesterday();
        DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
        String Stats_Date=dateformat.format(yesterday);
        //create File object
        File excel_file = new File(excel_address + Client_Name+"_"+"DPAStats"+"_"+Stats_Date+".xls");

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

    public static boolean csvindex(List<String> csv_files_list,HSSFWorkbook workBook, String sheetname,String Excel_File_Name) {

        Logger logger = LoggerFactory.getLogger(OAuthExpirationTokenChecker.class);


        try{

            int RowNum = 3;
            String currentLine="Index for Breakdown of DPA Stats";
            //String csvFile = filename; //csv file address
            //BufferedReader br = new BufferedReader(new FileReader(csvFile));

            HSSFSheet sheet = workBook.createSheet(sheetname);

            //cell font
            Cell cell;
            CellStyle hlink_style = workBook.createCellStyle();
            Font hlink_font = workBook.createFont();
            hlink_font.setUnderline(Font.U_SINGLE);
            hlink_font.setColor(IndexedColors.BLUE.getIndex());
            hlink_style.setFont(hlink_font);
            //HSSFRow indexcurrentRow = sheet.createRow(RowNum);
            //RowNum++;
            cell = sheet.createRow(1).createCell(3);
            cell.setCellValue(currentLine);
            cell.setCellStyle(hlink_style);


            int file_cell=3;
            for (String csvfile : csv_files_list) {

                String csvFile = csvfile.toString();
                String second_part = csvFile.split("DPAStats")[1];
                int start_index = second_part.indexOf("_");
                int finish_index = second_part.indexOf(".");
                String link_name = second_part.substring(start_index, finish_index);
                HSSFRow currentRow = sheet.createRow(RowNum);
                    RowNum++;
                        currentRow.createCell(file_cell).setCellValue(link_name);

            }
            FileOutputStream fileOutputStream = new FileOutputStream(Excel_File_Name);
            workBook.write(fileOutputStream);
            fileOutputStream.close();
            logger.info(sheetname + "-" + "created");
        }catch (Exception ex) {
            logger.info(String.valueOf(ex));
        }

        return true;

    }

}
