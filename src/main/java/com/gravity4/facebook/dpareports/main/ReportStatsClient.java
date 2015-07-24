package com.gravity4.facebook.dpareports.main;

/**
 * Created by niranjan on 6/19/15.
 */

import com.gravity4.facebook.dpareports.views.StatsInformationCaller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.beans.PropertyVetoException;
import java.io.IOException;


@Component
@SuppressWarnings("unchecked")
public class ReportStatsClient {


    @Autowired
    StatsInformationCaller statsInformationCaller;

    public static void main(String args[]) throws IOException, PropertyVetoException {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("dpa-reports-app-context.xml");
        context.getBean(ReportStatsClient.class).statsInformationCaller.getAccountInformation();
    }

}
