package com.inalkar.tools.agile.notes.storage;

import com.inalkar.daf.storage.api.StorageConfiguration;
import com.inalkar.daf.storage.jdbc.JDBCStorageConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static com.inalkar.daf.storage.jdbc.JDBCStorageConfiguration.MAX_POOL_SIZE;
import static com.inalkar.daf.storage.jdbc.JDBCStorageConfiguration.PASSWORD;
import static com.inalkar.daf.storage.jdbc.JDBCStorageConfiguration.URL;
import static com.inalkar.daf.storage.jdbc.JDBCStorageConfiguration.USER_NAME;
import static com.inalkar.daf.storage.jdbc.JDBCStorageConfiguration.USE_CONNECTION_POOL;
import static com.inalkar.daf.storage.jdbc.JDBCStorageConfiguration.USE_UNICODE;
import static com.inalkar.saa.h2.H2StorageDriver.H2_STORAGE_DRIVER;

@Component
public class DBStorageConfig {

    @Value("${db.storage.url}")
    private String url;
    
    @Value("${db.storage.user-name}")
    private String userName;

    @Value("${db.storage.password}")
    private String password;
    
    @Value("${db.storage.useUnicode}")
    private Boolean useUnicode = true;
    
    @Value("${db.storage.maxPoolSize}")
    private Integer maxPoolSize = 10;
    
    @Value("${db.storage.useConnectionPool}")
    private Boolean useConnectionPool = true;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isUseUnicode() {
        return useUnicode;
    }

    public void setUseUnicode(boolean useUnicode) {
        this.useUnicode = useUnicode;
    }

    public int getMaxPoolSize() {
        return maxPoolSize;
    }

    public void setMaxPoolSize(int maxPoolSize) {
        this.maxPoolSize = maxPoolSize;
    }

    public boolean isUseConnectionPool() {
        return useConnectionPool;
    }

    public void setUseConnectionPool(boolean useConnectionPool) {
        this.useConnectionPool = useConnectionPool;
    }

    public StorageConfiguration buildStorageConfiguration() {
        StorageConfiguration cfg = new JDBCStorageConfiguration("db.storage", H2_STORAGE_DRIVER);
        cfg.addParameter(USER_NAME, userName);
        cfg.addParameter(PASSWORD, password);
        cfg.addParameter(URL, url);
        cfg.addParameter(USE_CONNECTION_POOL, useConnectionPool);
        cfg.addParameter(USE_UNICODE, useUnicode);
        cfg.addParameter(MAX_POOL_SIZE, maxPoolSize);
        return cfg;
    }
    
}
