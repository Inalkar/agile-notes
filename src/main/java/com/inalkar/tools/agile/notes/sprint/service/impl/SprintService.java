package com.inalkar.tools.agile.notes.sprint.service.impl;

import com.inalkar.tools.agile.notes.sprint.service.dto.Sprint;
import com.inalkar.tools.agile.notes.sprint.service.ISprintService;
import com.inalkar.tools.agile.notes.storage.DBStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.stream.Stream;

@Service
public class SprintService implements ISprintService {
    
    @Autowired
    private DBStorage dbStorage;

    @Override
    public void addSprint(Sprint sprint) throws Exception {
        sprint.id = UUID.randomUUID();
        dbStorage.insert(sprint).execute();
    }

    @Override
    public Stream<Sprint> getSprintsAsStream() throws Exception {
        return dbStorage.select(Sprint.class).all().stream();
    }

}
