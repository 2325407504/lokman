package com.aripd.common.service.impl;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aripd.common.service.BackupService;
import com.google.common.base.Joiner;

@Service("backupService")
@Transactional(readOnly = true)
public class BackupServiceImpl implements BackupService {

    protected static Logger logger = Logger.getLogger(BackupServiceImpl.class);
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
    public void backup() {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        Date now = new Date();
        String strDate = sdf.format(now);

        Process p;

        String[] table = {
            "role",
            "region",
            "electricmeter",
            "machine",
            "expensetype",
            "productgroup",
            "product",
            "wastegroup",
            "waste",
            "client",
            "account",
            "account_role",
            "customer",
            "driver",
            "truck",
            "quota",
            "subcontractor",
            "expense",
            "trip",
            "forwarding",
            "uatf",
            "production",
            "bigbag",
            "compensation",
            "extrication",
            "invoice",
            "waybill",
            "weighbridge",
            "outgoing",
            "machinetime"
        };
        String tables = Joiner.on(" ").join(table);


        // mysqldump --skip-triggers --compact --no-create-info -u username -ppassword database table1 table2 --result-file=filename.sql
        String cmd = pathProgramMysqldump + " --skip-triggers --compact --no-create-info -u " + jdbcUsername + " -p"
                + jdbcPassword + " " + jdbcDbname + " " + tables + " --result-file="
                + pathDirectoryExport + strDate + ".sql";
        try {
            p = Runtime.getRuntime().exec(cmd);
            int processComplete = p.waitFor();
            if (processComplete == 0) {
                logger.debug("Backup taken successfully");
            } else {
                logger.debug("Could not take backup: " + cmd);
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
                logger.debug("Restored successfully");
            } else {
                logger.debug("Could not restored: " + cmd);
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
            File f = new File(pathDirectoryExport + file);

            if (f.delete()) {
                logger.debug(f.getName() + " is deleted.");
            } else {
                logger.debug("Delete operation is failed.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public File[] findAll() {
        File dir = new File(pathDirectoryExport);
        if (dir.isDirectory() == false) {
            logger.debug("Directory does not exists : " + pathDirectoryExport);
        }
        File[] files = dir.listFiles(new GenericExtFilter("sql"));
        if (files.length == 0) {
            logger.debug("No files");
        }
        for (File file : files) {
            logger.debug(file.toString());
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