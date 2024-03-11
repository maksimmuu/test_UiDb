package org.models;

import lombok.Data;

@Data
public class TestData {
    private String variant;
    private int lengthNameProject;
    private String testName;
    private int testSessionId;
    private String testEnv;
    private String testBrowser;
    private int testAuthorId;
    private int testStatus;
    private String testFailed;
    private String testMethod;
    private Long testTimeStamp;
    private String contentTypeScreenShot;
}