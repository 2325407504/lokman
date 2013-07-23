package com.aripd.common.service;

import java.io.File;

public interface BackupService {

    public void backup();

    public void restore(String file);

    public void delete(String file);

    public File[] findAll();
}
