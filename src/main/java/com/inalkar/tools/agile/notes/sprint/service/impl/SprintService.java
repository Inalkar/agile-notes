package com.inalkar.tools.agile.notes.sprint.service.impl;

import com.inalkar.tools.agile.notes.sprint.service.ISprintService;
import com.inalkar.tools.agile.notes.storage.DBStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SprintService implements ISprintService {
    
    @Autowired
    private DBStorage dbStorage;
    
}
