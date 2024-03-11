package org.models;

import jdk.jfr.Name;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class TestInfo {
    private Integer id;
    private String name;
    @Name("status_id")
    private Integer statusId;
    @Name("method_name")
    private String methodName;
    @Name("project_id")
    private Integer projectId;
    @Name("session_id")
    private Integer sessionId;
    @Name("start_time")
    private Timestamp startTime;
    @Name("end_time")
    private Timestamp endTime;
    private String env;
    private String browser;
    @Name("author_id")
    private Integer authorId;
}