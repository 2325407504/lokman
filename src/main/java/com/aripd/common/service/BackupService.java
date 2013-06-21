package com.aripd.common.service;

import java.io.File;

public interface BackupService {

    void backup();

    void restore(String file);

    void delete(String file);

    File[] findAll();
}
