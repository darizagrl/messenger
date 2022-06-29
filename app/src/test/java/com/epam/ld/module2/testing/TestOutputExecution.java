package com.epam.ld.module2.testing;

import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.io.File;
import java.nio.file.Files;

/**
 * Custom extension to output test execution information to file
 */
public class TestOutputExecution implements AfterTestExecutionCallback {

    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception {
        boolean testResult = context.getExecutionException().isPresent();
        Files.write(new File("testResult.txt").toPath(), Boolean.toString(testResult).getBytes());
    }
}
