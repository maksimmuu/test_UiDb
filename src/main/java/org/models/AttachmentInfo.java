package org.models;

import jdk.jfr.Name;
import lombok.Data;

import java.io.File;

@Data
public class AttachmentInfo {
    private Integer id;
    private File content;
    @Name("content_type")
    private String contentType;
    @Name("test_id")
    private Integer testId;
}