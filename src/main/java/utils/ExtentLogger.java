package utils;

import com.aventstack.extentreports.Status;
import listeners.ExtentListener;

public class ExtentLogger {

    private ExtentLogger() {
    }

    public static void info(String message) {
        ExtentListener.getTest().log(Status.INFO, message);
    }

    public static void pass(String message) {
        ExtentListener.getTest().log(Status.PASS, message);
    }

    public static void fail(String message) {
        ExtentListener.getTest().log(Status.FAIL, message);
    }

    public static void warning(String message) {
        ExtentListener.getTest().log(Status.WARNING, message);
    }
}