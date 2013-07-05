package com.aripd.common.model;

import com.aripd.common.annotation.ContentType;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class FileUploadBean {

    @ContentType(value = {"application/vnd.ms-excel", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"})
    private CommonsMultipartFile file;

    public CommonsMultipartFile getFile() {
        return file;
    }

    public void setFile(CommonsMultipartFile file) {
        this.file = file;
    }
}
