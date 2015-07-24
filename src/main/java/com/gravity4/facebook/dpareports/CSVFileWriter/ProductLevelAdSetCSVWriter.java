package com.gravity4.facebook.dpareports.CSVFileWriter;


import com.gravity4.facebook.dpareports.model.CSVProductLevelAdSetStats;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.sql.Date;

/**
 * Created by niranjan on 7/23/15.
 */
@SuppressWarnings("unchecked")
public class ProductLevelAdSetCSVWriter {

    //Delimiter used in CSV file
    private static final String COMMA_DELIMITER = ",";
    private static final String NEW_LINE_SEPARATOR = "\n";

    //CSV file header
    private static final String FILE_HEADER = "Stats_Date,Page_ID," +
            "AdSet_ID,AdSet_Name,Product_id,Product_Desc,Reach, " +
            "Frequency,Clicks,Total_Actions,Impressions," +
            "Social_Reach,Social_Impressions,Unique_Impressions,Unique_Social_Impressions," +
            "CPM,CPP,Spend,CPC,CTR," +
            "Activity_Start_Date,Activity_End_Date";

    public static String writecsvfile(List<CSVProductLevelAdSetStats> productLevelAdSetStatsList,long page_id, java.sql.Date Stats_date) throws IOException {

        Logger logger = LoggerFactory.getLogger(ProductLevelAdSetCSVWriter.class);
        //boolean stored=false;

        String File_Starting_Name="DPAStats";

        //create File object
        File file = new File("src/main/ReportFiles/"+File_Starting_Name+page_id+"_"+"ProductLevelAdSetStats_"+Stats_date+".csv");

        /*
     * To actually create a file specified by a pathname, use
     * boolean createNewFile() method of Java File class.
     *
     * This method creates a new empty file specified if the file with same
     * name does not exists.
     *
     * This method returns true if the file with the same name did not exist and
     * it was created successfully, false otherwise.
        */

        boolean blnCreated = false;
        try
        {
            blnCreated = file.createNewFile();
        } catch(IOException ioe) {
            logger.info("Error while creating a new empty file :" + ioe);
        }
        logger.info("Was file " + file.getPath() + " created ? : " + blnCreated);

        String filename=file.getAbsolutePath();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");

        FileWriter fileWriter = null;
        try {
            fileWriter= new FileWriter(filename);

            //Write the CSV file header

            fileWriter.append(FILE_HEADER.toString());

            //Add a new line separator after the header
            fileWriter.append(NEW_LINE_SEPARATOR);

            for(CSVProductLevelAdSetStats csvOverAllAccountStats:productLevelAdSetStatsList){

                String att=csvOverAllAccountStats.getProduct_ID();
                String[] split=att.split(",");
                String product_id=split[0];
                String product_desc=split[1];

                Date stat=csvOverAllAccountStats.getStats_Date();
                fileWriter.append(formatter.format(stat));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(csvOverAllAccountStats.getPage_ID()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(csvOverAllAccountStats.getID()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(csvOverAllAccountStats.getName());
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(product_id);
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(product_desc);
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(csvOverAllAccountStats.getReach()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(csvOverAllAccountStats.getFrequency()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(csvOverAllAccountStats.getClicks()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(csvOverAllAccountStats.getTotal_Actions()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(csvOverAllAccountStats.getImpressions()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(csvOverAllAccountStats.getSocial_Reach()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(csvOverAllAccountStats.getSocial_Impressions()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(csvOverAllAccountStats.getUnique_Impressions()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(csvOverAllAccountStats.getUnique_Social_Impressions()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(csvOverAllAccountStats.getCPM()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(csvOverAllAccountStats.getCPP()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(csvOverAllAccountStats.getSpend()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(csvOverAllAccountStats.getCPC()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(csvOverAllAccountStats.getCTR()));
                fileWriter.append(COMMA_DELIMITER);
                Date start=csvOverAllAccountStats.getActivity_Start_Date();
                Date end=csvOverAllAccountStats.getActivity_End_Date();
                fileWriter.append(formatter.format(start));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(formatter.format(end));

                fileWriter.append(NEW_LINE_SEPARATOR);

            }

            //stored=true;

        } catch (Exception e) {
            logger.info("Error in CsvFileWriter !!!");
            e.printStackTrace();
        } finally {
            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("Error while flushing/closing fileWriter !!!");
                e.printStackTrace();
            }
        }

        return filename;
    }


}
