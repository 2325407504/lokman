package com.aripd.common.service.impl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@Override
	@Scheduled(cron = "${cron.backup.database}")
	public void database() {
		
		String outputDirectory = "/Users/cem/Documents/backup/";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		Date now = new Date();
		String strDate = sdf.format(now);
		System.out.println(strDate);
		
		/*
		Ejb3Configuration cfg = new Ejb3Configuration();
		Ejb3Configuration configured = cfg.configure(
				fb.getPersistenceUnitInfo(), fb.getJpaPropertyMap());
		
		SchemaExport se = new SchemaExport(
				configured.getHibernateConfiguration());
		
		se.setOutputFile(outputDirectory + strDate + ".sql");
		se.execute(true, false, false, false);
		*/
		
		Process p;
		try {
			p = Runtime.getRuntime().exec("/usr/local/mysql-5.6.11-osx10.7-x86_64/bin/mysqldump -u root -proot lokman --result-file=" + outputDirectory + strDate + ".sql");
			int processComplete = p.waitFor(); 
			if (processComplete == 0) {
				System.out.println("Backup taken successfully");
			}
			else {
				System.out.println("Could not take mysql backup");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}