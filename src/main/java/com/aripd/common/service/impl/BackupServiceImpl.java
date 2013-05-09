package com.aripd.common.service.impl;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aripd.common.service.BackupService;

@Service("backupService")
@Transactional(readOnly = true)
public class BackupServiceImpl implements BackupService {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	LocalContainerEntityManagerFactoryBean fb;

	@Value("${app.jdbc.dbname}")
	String jdbcDbname;

	@Value("${app.jdbc.username}")
	String jdbcUsername;

	@Value("${app.jdbc.password}")
	String jdbcPassword;

	@Value("${path.program.mysqldump}")
	String pathProgramMysqldump;

	@Value("${path.program.mysql}")
	String pathProgramMysql;

	@Value("${path.directory.export}")
	String pathDirectoryExport;

	@Override
	@Scheduled(cron = "${cron.backup.database}")
	public void database() {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		Date now = new Date();
		String strDate = sdf.format(now);
		System.out.println(strDate);

		Process p;
		String cmd = pathProgramMysqldump + " -u " + jdbcUsername + " -p"
				+ jdbcPassword + " " + jdbcDbname + " --result-file="
				+ pathDirectoryExport + strDate + ".sql";
		try {
			p = Runtime.getRuntime().exec(cmd);
			int processComplete = p.waitFor();
			if (processComplete == 0) {
				System.out.println("Backup taken successfully");
			} else {
				System.out.println("Could not take backup: " + cmd);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void restore(String file) {
		Process p;
		String cmd = pathProgramMysql + " -u " + jdbcUsername + " -p"
				+ jdbcPassword + " " + jdbcDbname + " < " + pathDirectoryExport
				+ file;
		try {
			p = Runtime.getRuntime().exec(cmd);
			int processComplete = p.waitFor();
			if (processComplete == 0) {
				System.out.println("Restored successfully");
			} else {
				System.out.println("Could not restored: " + cmd);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(String file) {
		try {
			String cem = pathDirectoryExport + file;
			File f = new File(cem);

			if (f.delete()) {
				System.out.println(f.getName() + " is deleted!");
			} else {
				System.out.println("Delete operation is failed." + cem);
			}

		} catch (Exception e) {

			e.printStackTrace();

		}
	}

	@Override
	public File[] findAll(String pathDirectoryExport, String ext) {
		File dir = new File(pathDirectoryExport);
		if (dir.isDirectory() == false) {
			System.out.println("Directory does not exists : "
					+ pathDirectoryExport);
		}
		File[] files = dir.listFiles(new GenericExtFilter(ext));
		if (files.length == 0) {
			System.out.println("no files");
		}
		for (File file : files) {
			System.out.println(file.toString());
		}
		return files;
	}

	// inner class, generic extension filter
	public class GenericExtFilter implements FilenameFilter {

		private String ext;

		public GenericExtFilter(String ext) {
			this.ext = ext;
		}

		public boolean accept(File dir, String name) {
			return (name.endsWith(ext));
		}
	}

}