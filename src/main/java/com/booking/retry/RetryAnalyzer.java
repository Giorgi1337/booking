package com.booking.retry;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {
    private int counter = 0;

    @Override
    public boolean retry(ITestResult result) {
        RetryCount annotation = result.getMethod().getConstructorOrMethod().getMethod()
                .getAnnotation(RetryCount.class);

        if (annotation != null && counter < annotation.count()) {
            counter++;
            return true;
        }
        return false;
    }
}
