package com.gravity4.facebook.dpareports.CSVFileWriter.Actions;

import com.gravity4.facebook.dpareports.model.CSVModels.Actions.CSVActionsAdSetStats;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by niranjan on 7/23/15.
 */
@SuppressWarnings("unchecked")
public class ActionsAdSetCSVWriter {
    //Delimiter used in CSV file
    private static final String COMMA_DELIMITER = ",";
    private static final String NEW_LINE_SEPARATOR = "\n";

    //CSV file header
    private static final String FILE_HEADER = "Stats_Date,Page_ID," +
            "AdSet_ID,AdSet_Name,Action_Types, " +
            "Values,Activity_Start_Date,Activity_End_Date";

    public static String writecsvfile(List<CSVActionsAdSetStats> overAllAdGroupStatsList,long page_id, Date Stats_date,String csv_address) throws IOException {

        Logger logger = LoggerFactory.getLogger(ActionsAdSetCSVWriter.class);
        //boolean stored=false;

        String File_Starting_Name="DPAStats";

        //create File object
        File file = new File(csv_address+File_Starting_Name+"_"+"Action_Types_Ad_Set_Level_Stats_"+Stats_date+".csv");

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

            for(CSVActionsAdSetStats csvOverAllAccountStats:overAllAdGroupStatsList){
                Date stat=csvOverAllAccountStats.getStats_Date();
                fileWriter.append(formatter.format(stat));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(csvOverAllAccountStats.getPage_ID()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(csvOverAllAccountStats.getAdSet_ID()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(csvOverAllAccountStats.getAdSet_Name());
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(csvOverAllAccountStats.getAction_Type()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(csvOverAllAccountStats.getAction_Value()));
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
                logger.info("Error while flushing/closing fileWriter !!!");
                e.printStackTrace();
            }
        }

        return filename;
    }

}
