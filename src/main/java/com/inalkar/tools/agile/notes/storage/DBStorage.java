package com.inalkar.tools.agile.notes.storage;

import com.inalkar.daf.storage.api.StorageConfiguration;
import com.inalkar.saa.h2.H2Storage;
import com.inalkar.tools.agile.notes.sprint.dto.Sprint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository("dbStorage")
public class DBStorage extends H2Storage {

    @Autowired
    public DBStorage(DBStorageConfig dbStorageConfiguration) throws Exception {
        super(dbStorageConfiguration.buildStorageConfiguration());
    }

    public DBStorage(StorageConfiguration configuration) throws Exception {
        super(configuration);
    }
    
}
