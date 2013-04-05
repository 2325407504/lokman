package com.aripd.common.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.ejb.Ejb3Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
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
		
		se.setOutputFile("/Users/cem/Desktop/backup/" + strDate + ".sql");
		se.execute(true, false, false, false);
		*/
		
		Process p;
		try {
			p = Runtime.getRuntime().exec("/usr/local/mysql-5.6.10-osx10.7-x86_64/bin/mysqldump -u root -proot lokman --result-file=/Users/cem/Desktop/backup/"+strDate+".sql");
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