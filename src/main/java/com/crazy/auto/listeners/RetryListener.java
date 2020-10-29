package com.crazy.auto.listeners;

import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.ITestAnnotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * 失败重试的监听
 */
public class RetryListener implements IAnnotationTransformer {

    @Override
    public void transform(ITestAnnotation testannotation, Class testClass,
                          Constructor testConstructor, Method testMethod)    {
        IRetryAnalyzer retry = testannotation.getRetryAnalyzer();
        

        if (retry == null)    {
        	System.out.println("setting retry");
            testannotation.setRetryAnalyzer(Retry.class);
        }

    }
}