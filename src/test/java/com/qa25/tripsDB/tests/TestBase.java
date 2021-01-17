package com.qa25.tripsDB.tests;

import com.qa25.tripsDB.fw.ApplicationManager;
import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.lang.reflect.Method;
import java.util.Arrays;

public class TestBase {
    protected static ApplicationManager appManager =
            new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

    Logger logger = LoggerFactory.getLogger(TestBase.class);

    @BeforeSuite(alwaysRun = true)
    public void setUp(){
        appManager.init();
    }

    @BeforeMethod(alwaysRun = true)
    public void startTest(Method m, Object[] p)
    {
        logger.info("Start test: " + m.getName());
        if(p.length != 0) {
            logger.info(" --> With data: " + Arrays.asList(p));
        }
    }

    @AfterSuite(enabled = false)
    public void tearDown() {
        appManager.stop();
    }


    @AfterMethod(alwaysRun = true)
    public void stopTest(ITestResult result){

        if(result.isSuccess()){
            logger.info("Test result: PASSED");
        }else{
            logger.error("Test result: FAILED");
            if(!result.getMethod().getMethodName().contains("RestAssured")){
                logger.info("Screenshot: " + appManager.getMainPage().takeScreenShot());
            }

        }

        logger.info("Stop test: " + result.getMethod().getMethodName());
        logger.info("======================================================");
    }


}
