package org.models;

import jdk.jfr.Name;
import lombok.Data;

import java.io.File;

@Data
public class LogInfo {
    private Integer id;
    private File content;
    @Name("is_exception")
    private Integer isException;
    @Name("test_id")
    private Integer testId;
}