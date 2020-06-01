package org.selenoid.example.pages;

import io.qameta.allure.Attachment;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.selenoid.example.settings.SetUp;

import java.lang.reflect.Method;

public class TestWatcher implements AfterTestExecutionCallback {

    @Override
    public void afterTestExecution(ExtensionContext extensionContext)  {
        Method method = extensionContext.getRequiredTestMethod();
        if (extensionContext.getExecutionException().isPresent()) {
            makeScreenshotOnFailure(method.getName());
        }
    }

    @Attachment(value = "{testName} - screenshot", type = "image/png")
    private byte[] makeScreenshotOnFailure(String testName) {

        return ((TakesScreenshot) SetUp.driver).getScreenshotAs(OutputType.BYTES);
    }
}
