package com.aripd.common.service;

import java.io.File;

public interface BackupService {
	
	void database();

	void restore(String file);

	void delete(String file);

	File[] findAll(String pathDirectoryExport, String ext);
	
}
