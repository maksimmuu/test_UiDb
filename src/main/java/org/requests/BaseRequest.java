package org.requests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public abstract class BaseRequest {
    private static RequestSpecification givenWithAllLog() {
        return RestAssured.given()
                .when().log().all();
    }

    protected static Response doPostRequestWithParameter(String url, String parameterName, String parameterValue) {
        return givenWithAllLog().param(parameterName, parameterValue)
                .post(url);
    }
}