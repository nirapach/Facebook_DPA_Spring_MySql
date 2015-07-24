CREATE SCHEMA dpa-reports;

USE dpa-reports;

CREATE TABLE `Account_Information_Master` (
  `Application_Client_ID` bigint(20) NOT NULL DEFAULT '0',
  `Business_Manager_Ad_Account_ID` bigint(20) DEFAULT NULL,
  `Business_Client_Name` varchar(45) DEFAULT NULL,
  `Application_Long_Lived_Access_Token` longtext,
  PRIMARY KEY (`Application_Client_ID`)
);

CREATE TABLE `Overall_Account_Statistics_Results` (
  `Application_Client_ID` bigint(20) DEFAULT '0',
  `Application_Ad_Account_ID` bigint(20) DEFAULT NULL,
  `Application_Ad_Account_Name` varchar(45) DEFAULT NULL,
  `Client_Reports_Age_Stats_Range` varchar(45) DEFAULT NULL,
  `Client_Reports_Gender_Stats` varchar(45) DEFAULT NULL,
  `Client_Reports_Reach` int(11) DEFAULT NULL,
  `Client_Reports_Frequency` double DEFAULT NULL,
  `Client_Reports_Impressions` int(11) DEFAULT NULL,
  `Client_Reports_Clicks` int(11) DEFAULT NULL,
  `Client_Reports_Total_Actions` int(11) DEFAULT NULL,
  `Client_Reports_Social_Reach` int(11) DEFAULT NULL,
  `Client_Reports_Social_Impressions` int(11) DEFAULT NULL,
  `Client_Reports_Unique_Impressions` int(11) DEFAULT NULL,
  `Client_Reports_Unique_Social_Impressions` int(11) DEFAULT NULL,
  `Client_Reports_CPM` double DEFAULT NULL,
  `Client_Reports_CPP` double DEFAULT NULL,
  `Client_Reports_CPC` double DEFAULT NULL,
  `Client_Reports_CTR` double DEFAULT NULL,
  `Client_Reports_Spend` double DEFAULT NULL,
  `Stats_Date` date DEFAULT NULL,
  `Client_Reports_Ad_Activity_Date_Start` date DEFAULT NULL,
  `Client_Reports_Ad_Activity_Date_End` date DEFAULT NULL,
  `Client_Cost_Per_Unique_Click` double DEFAULT NULL);

CREATE TABLE `Overall_AdSet_Statistics_Results` (
  `Application_Client_ID` bigint(20) DEFAULT '0',
  `Application_Ad_AdSet_ID` bigint(20) DEFAULT NULL,
  `Application_Ad_AdSet_Name` varchar(45) DEFAULT NULL,
  `Client_Reports_Age_Stats_Range` varchar(45) DEFAULT NULL,
  `Client_Reports_Gender_Stats` varchar(45) DEFAULT NULL,
  `Client_Reports_Reach` int(11) DEFAULT NULL,
  `Client_Reports_Frequency` double DEFAULT NULL,
  `Client_Reports_Impressions` int(11) DEFAULT NULL,
  `Client_Reports_Clicks` int(11) DEFAULT NULL,
  `Client_Reports_Total_Actions` int(11) DEFAULT NULL,
  `Client_Reports_Social_Reach` int(11) DEFAULT NULL,
  `Client_Reports_Social_Impressions` int(11) DEFAULT NULL,
  `Client_Reports_Unique_Impressions` int(11) DEFAULT NULL,
  `Client_Reports_Unique_Social_Impressions` int(11) DEFAULT NULL,
  `Client_Reports_CPM` double DEFAULT NULL,
  `Client_Reports_CPP` double DEFAULT NULL,
  `Client_Reports_CPC` double DEFAULT NULL,
  `Client_Reports_CTR` double DEFAULT NULL,
  `Client_Reports_Spend` double DEFAULT NULL,
  `Stats_Date` date DEFAULT NULL,
  `Client_Reports_Ad_Activity_Date_Start` date DEFAULT NULL,
  `Client_Reports_Ad_Activity_Date_End` date DEFAULT NULL,
  `Client_Cost_Per_Unique_Click` double DEFAULT NULL);

CREATE TABLE `Overall_Ad_Statistics_Results` (
  `Application_Client_ID` bigint(20) DEFAULT '0',
  `Application_Ad_Group_ID` bigint(20) DEFAULT NULL,
  `Application_Ad_Group_Name` varchar(45) DEFAULT NULL,
  `Client_Reports_Age_Stats_Range` varchar(45) DEFAULT NULL,
  `Client_Reports_Gender_Stats` varchar(45) DEFAULT NULL,
  `Client_Reports_Reach` int(11) DEFAULT NULL,
  `Client_Reports_Frequency` double DEFAULT NULL,
  `Client_Reports_Impressions` int(11) DEFAULT NULL,
  `Client_Reports_Clicks` int(11) DEFAULT NULL,
  `Client_Reports_Total_Actions` int(11) DEFAULT NULL,
  `Client_Reports_Social_Reach` int(11) DEFAULT NULL,
  `Client_Reports_Social_Impressions` int(11) DEFAULT NULL,
  `Client_Reports_Unique_Impressions` int(11) DEFAULT NULL,
  `Client_Reports_Unique_Social_Impressions` int(11) DEFAULT NULL,
  `Client_Reports_CPM` double DEFAULT NULL,
  `Client_Reports_CPP` double DEFAULT NULL,
  `Client_Reports_CPC` double DEFAULT NULL,
  `Client_Reports_CTR` double DEFAULT NULL,
  `Client_Reports_Spend` double DEFAULT NULL,
  `Stats_Date` date DEFAULT NULL,
  `Client_Reports_Ad_Activity_Date_Start` date DEFAULT NULL,
  `Client_Reports_Ad_Activity_Date_End` date DEFAULT NULL,
  `Client_Cost_Per_Unique_Click` double DEFAULT NULL,
  `Client_Ad_Relevance_Score` double DEFAULT NULL);


CREATE TABLE `Overall_Campaign_Statistics_Results` (
  `Application_Client_ID` bigint(20) DEFAULT '0',
  `Application_Campaign_ID` bigint(20) DEFAULT NULL,
  `Application_Campaign_Name` varchar(45) DEFAULT NULL,
  `Client_Reports_Age_Stats_Range` varchar(45) DEFAULT NULL,
  `Client_Reports_Gender_Stats` varchar(45) DEFAULT NULL,
  `Client_Reports_Reach` int(11) DEFAULT NULL,
  `Client_Reports_Frequency` double DEFAULT NULL,
  `Client_Reports_Impressions` int(11) DEFAULT NULL,
  `Client_Reports_Clicks` int(11) DEFAULT NULL,
  `Client_Reports_Total_Actions` int(11) DEFAULT NULL,
  `Client_Reports_Social_Reach` int(11) DEFAULT NULL,
  `Client_Reports_Social_Impressions` int(11) DEFAULT NULL,
  `Client_Reports_Unique_Impressions` int(11) DEFAULT NULL,
  `Client_Reports_Unique_Social_Impressions` int(11) DEFAULT NULL,
  `Client_Reports_CPM` double DEFAULT NULL,
  `Client_Reports_CPP` double DEFAULT NULL,
  `Client_Reports_CPC` double DEFAULT NULL,
  `Client_Reports_CTR` double DEFAULT NULL,
  `Client_Reports_Spend` double DEFAULT NULL,
  `Stats_Date` date DEFAULT NULL,
  `Client_Reports_Ad_Activity_Date_Start` date DEFAULT NULL,
  `Client_Reports_Ad_Activity_Date_End` date DEFAULT NULL,
  `Client_Cost_Per_Unique_Click` double DEFAULT NULL);

CREATE TABLE `Product_Account_Statistics_Results` (
  `Application_Client_ID` bigint(20) DEFAULT NULL,
  `Application_Ad_Account_ID` bigint(20) DEFAULT NULL,
  `Application_Ad_Account_Name` varchar(45) DEFAULT NULL,
  `Client_Product_ID` varchar(45) DEFAULT NULL,
  `Client_Reports_Reach` int(11) DEFAULT NULL,
  `Client_Reports_Frequency` double DEFAULT NULL,
  `Client_Reports_Impressions` int(11) DEFAULT NULL,
  `Client_Reports_Clicks` int(11) DEFAULT NULL,
  `Client_Reports_Total_Actions` int(11) DEFAULT NULL,
  `Client_Reports_Social_Reach` int(11) DEFAULT NULL,
  `Client_Reports_Social_Impressions` int(11) DEFAULT NULL,
  `Client_Reports_Unique_Impressions` int(11) DEFAULT NULL,
  `Client_Reports_Unique_Social_Impressions` int(11) DEFAULT NULL,
  `Client_Reports_CPM` double DEFAULT NULL,
  `Client_Reports_CPP` double DEFAULT NULL,
  `Client_Reports_CPC` double DEFAULT NULL,
  `Client_Reports_CTR` double DEFAULT NULL,
  `Client_Reports_Spend` double DEFAULT NULL,
  `Stats_Date` date DEFAULT NULL,
  `Client_Reports_Ad_Activity_Date_Start` date DEFAULT NULL,
  `Client_Reports_Ad_Activity_Date_End` date DEFAULT NULL);

  CREATE TABLE `Product_AdSet_Statistics_Results` (
    `Application_Client_ID` bigint(20) DEFAULT NULL,
    `Client_AdSet_ID` bigint(20) DEFAULT NULL,
    `Client_AdSet_Name` varchar(45) DEFAULT NULL,
    `Client_Product_ID` varchar(45) DEFAULT NULL,
    `Client_Reports_Reach` int(11) DEFAULT NULL,
    `Client_Reports_Frequency` double DEFAULT NULL,
    `Client_Reports_Impressions` int(11) DEFAULT NULL,
    `Client_Reports_Clicks` int(11) DEFAULT NULL,
    `Client_Reports_Total_Actions` int(11) DEFAULT NULL,
    `Client_Reports_Social_Reach` int(11) DEFAULT NULL,
    `Client_Reports_Social_Impressions` int(11) DEFAULT NULL,
    `Client_Reports_Unique_Impressions` int(11) DEFAULT NULL,
    `Client_Reports_Unique_Social_Impressions` int(11) DEFAULT NULL,
    `Client_Reports_CPM` double DEFAULT NULL,
    `Client_Reports_CPP` double DEFAULT NULL,
    `Client_Reports_CPC` double DEFAULT NULL,
    `Client_Reports_CTR` double DEFAULT NULL,
    `Client_Reports_Spend` double DEFAULT NULL,
    `Stats_Date` date DEFAULT NULL,
    `Client_Reports_Ad_Activity_Date_Start` date DEFAULT NULL,
    `Client_Reports_Ad_Activity_Date_End` date DEFAULT NULL);

    CREATE TABLE `Product_Ad_Statistics_Results` (
      `Application_Client_ID` bigint(20) DEFAULT NULL,
      `Client_Ad_Group_ID` bigint(20) DEFAULT NULL,
      `Client_Ad_Group_Name` varchar(45) DEFAULT NULL,
      `Client_Product_ID` varchar(45) DEFAULT NULL,
      `Client_Reports_Reach` int(11) DEFAULT NULL,
      `Client_Reports_Frequency` double DEFAULT NULL,
      `Client_Reports_Impressions` int(11) DEFAULT NULL,
      `Client_Reports_Clicks` int(11) DEFAULT NULL,
      `Client_Reports_Total_Actions` int(11) DEFAULT NULL,
      `Client_Reports_Relevancy_Score` double DEFAULT NULL,
      `Client_Reports_Social_Reach` int(11) DEFAULT NULL,
      `Client_Reports_Social_Impressions` int(11) DEFAULT NULL,
      `Client_Reports_Unique_Impressions` int(11) DEFAULT NULL,
      `Client_Reports_Unique_Social_Impressions` int(11) DEFAULT NULL,
      `Client_Reports_CPM` double DEFAULT NULL,
      `Client_Reports_CPP` double DEFAULT NULL,
      `Client_Reports_CPC` double DEFAULT NULL,
      `Client_Reports_CTR` double DEFAULT NULL,
      `Client_Reports_Spend` double DEFAULT NULL,
      `Stats_Date` date DEFAULT NULL,
      `Client_Reports_Ad_Activity_Date_Start` date DEFAULT NULL,
      `Client_Reports_Ad_Activity_Date_End` date DEFAULT NULL);

      CREATE TABLE `Product_Campaign_Statistics_Results` (
        `Application_Client_ID` bigint(20) DEFAULT NULL,
        `Client_Campaign_ID` bigint(20) DEFAULT NULL,
        `Client_Campaign_Name` varchar(45) DEFAULT NULL,
        `Client_Product_ID` varchar(45) DEFAULT NULL,
        `Client_Reports_Reach` int(11) DEFAULT NULL,
        `Client_Reports_Frequency` double DEFAULT NULL,
        `Client_Reports_Impressions` int(11) DEFAULT NULL,
        `Client_Reports_Clicks` int(11) DEFAULT NULL,
        `Client_Reports_Total_Actions` int(11) DEFAULT NULL,
        `Client_Reports_Social_Reach` int(11) DEFAULT NULL,
        `Client_Reports_Social_Impressions` int(11) DEFAULT NULL,
        `Client_Reports_Unique_Impressions` int(11) DEFAULT NULL,
        `Client_Reports_Unique_Social_Impressions` int(11) DEFAULT NULL,
        `Client_Reports_CPM` double DEFAULT NULL,
        `Client_Reports_CPP` double DEFAULT NULL,
        `Client_Reports_CPC` double DEFAULT NULL,
        `Client_Reports_CTR` double DEFAULT NULL,
        `Client_Reports_Spend` double DEFAULT NULL,
        `Stats_Date` date DEFAULT NULL,
        `Client_Reports_Ad_Activity_Date_Start` date DEFAULT NULL,
        `Client_Reports_Ad_Activity_Date_End` date DEFAULT NULL);