package org.utils;

import org.testng.Assert;

public class ApiUtils {
    public static void checkGenerateToken(String token) {
        Assert.assertTrue(token != null && !token.isEmpty(), "Токен не сгенерирован");
    }
}