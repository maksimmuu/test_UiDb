package org.requests;

import io.restassured.response.Response;
import org.constants.AttributeConstants;
import org.constants.ConfigConstants;
import org.manager.TestDataManager;

public class RequestToTokenGet extends BaseRequest {
    public static Response doPostRequest() {
        return doPostRequestWithParameter(ConfigConstants.API_URL_TOKEN_GET, AttributeConstants.PARAMETER_VARIANT, TestDataManager.getTestData().getVariant());
    }
}