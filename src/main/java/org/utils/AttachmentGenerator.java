package org.utils;

import org.constants.ConfigConstants;
import org.manager.TestDataManager;
import org.models.AttachmentInfo;

import java.io.File;

public class AttachmentGenerator {
    public static AttachmentInfo generate() {
        AttachmentInfo attachmentInfo = new AttachmentInfo();
        attachmentInfo.setId(ConfigConstants.ID_0);
        attachmentInfo.setContent(new File(ConfigConstants.PATH_TO_SCREENSHOT));
        attachmentInfo.setContentType(TestDataManager.getTestData().getContentTypeScreenShot());
        return attachmentInfo;
    }
}