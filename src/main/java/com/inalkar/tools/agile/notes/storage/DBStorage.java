package com.inalkar.tools.agile.notes.storage;

import com.inalkar.daf.storage.api.StorageConfiguration;
import com.inalkar.saa.h2.H2Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("dbStorage")
public class DBStorage extends H2Storage {

    @Autowired(required = true)
    public DBStorage(DBStorageConfig dbStorageConfiguration) throws Exception {
        super(dbStorageConfiguration.buildStorageConfiguration());
    }

    public DBStorage(StorageConfiguration configuration) throws Exception {
        super(configuration);
    }
    
}
