package dpa.main;

/**
 * Created by niranjan on 6/19/15.
 */

import dpa.views.StatsInformationCaller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.PropertyVetoException;
import java.io.IOException;

public class ReportStatsClient {


    public static void main(String args[]) throws IOException, PropertyVetoException {
        /*
        call the account information caller view component so that it will in turn call other modules to get the stats
         */
        Logger logger= LoggerFactory.getLogger(ReportStatsClient.class);

        try{
            StatsInformationCaller statsInformationCaller = new StatsInformationCaller();
            statsInformationCaller.getAccountInformation();
            System.out.println("Everything Executed Check for Results in Database");
        }
        catch(Exception e){
            logger.error(String.valueOf(e));

        }

    }
}
